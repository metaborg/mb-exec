package org.metaborg.util.future;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.metaborg.util.functions.Action1;
import org.metaborg.util.functions.CheckedAction1;
import org.metaborg.util.functions.CheckedAction2;
import org.metaborg.util.functions.CheckedFunction1;
import org.metaborg.util.functions.CheckedFunction2;
import org.metaborg.util.functions.Function1;
import org.metaborg.util.log.ILogger;
import org.metaborg.util.log.LoggerUtils;
import org.metaborg.util.tuple.Tuple2;
import org.metaborg.util.tuple.Tuple3;
import org.metaborg.util.tuple.Tuple4;

import com.google.common.collect.Lists;

public class AggregateFuture<T, R> implements IFuture<R> {

    private static final ILogger logger = LoggerUtils.logger(AggregateFuture.class);

    @SuppressWarnings("rawtypes") private static final Function1 ID_REDUCER = v -> v;

    private Object lock = new Object();
    private volatile int remaining;
    private final T[] results;
    private final CompletableFuture<R> result;
    private final Function1<List<T>, R> reducer;

    @SafeVarargs private AggregateFuture(Function1<List<T>, R> reducer,
            IFuture<SC<T, R>>... futures) {
        this(reducer, Arrays.asList(futures));
    }

    private AggregateFuture(Function1<List<T>, R> reducer,
            Iterable<IFuture<SC<T, R>>> futures) {
        this(reducer, Lists.newArrayList(futures));
    }

    @SuppressWarnings("unchecked") private AggregateFuture(Function1<List<T>, R> reducer,
            List<IFuture<SC<T, R>>> futures) {
        final int count = futures.size();
        this.results = (T[]) new Object[count];
        this.result = new CompletableFuture<>();
        this.reducer = reducer;
        logger.trace("{} initialized with {}: {}", this, count, futures);

        this.remaining = count;
        for(int i = 0; i < count; i++) {
            final int j = i;
            futures.get(i).whenComplete((r, ex) -> {
                whenComplete(j, r, ex);
            });
        }
        fireIfComplete();
    }


    private void whenComplete(int i, SC<? extends T, ? extends R> v, Throwable ex) {
        // INVARIANT count > 0
        synchronized(lock) {
            if(remaining > 0) {
                if(ex != null) {
                    logger.trace("{} completed {}: exception", this, i);
                    remaining = -1; // count will never be 0 and trigger completion
                } else if(remaining > 0) {
                    v.visit(t -> {
                        logger.trace("{} completed {}: value", this, i);
                        remaining -= 1;
                        results[i] = t;
                    }, r -> {
                        logger.trace("{} completed {}: short-circuit", this, i);
                        remaining = -1;
                        result.complete(r);
                    });
                }
            }
        }
        if(ex != null) {
            result.completeExceptionally(ex);
        } else {
            fireIfComplete();
        }
    }

    private void fireIfComplete() {
        final List<T> results;
        synchronized(lock) {
            if(remaining == 0) {
                logger.trace("{} done: completed all", this);
                results = Arrays.asList(this.results);
            } else {
                logger.trace("{} open: {} remaining", this, remaining);
                results = null;
            }
        }
        if(results != null) {
            result.complete(reducer.apply(results));
        }
    }

    @Override public <U> IFuture<U>
            handle(CheckedFunction2<? super R, Throwable, ? extends U, ? extends Throwable> handler) {
        return result.handle(handler);
    }

    @Override public IFuture<R> whenComplete(CheckedAction2<? super R, Throwable, ? extends Throwable> handler) {
        return result.whenComplete(handler);
    }

    @Override public <U> IFuture<U> thenApply(CheckedFunction1<? super R, ? extends U, ? extends Throwable> handler) {
        return result.thenApply(handler);
    }

    @Override public IFuture<Void> thenAccept(CheckedAction1<? super R, ? extends Throwable> handler) {
        return result.thenAccept(handler);
    }

    @Override public <U> IFuture<U>
            thenCompose(CheckedFunction1<? super R, ? extends IFuture<? extends U>, ? extends Throwable> handler) {
        return result.thenCompose(handler);
    }

    @Override public <U> IFuture<U> compose(
            CheckedFunction2<? super R, Throwable, ? extends IFuture<? extends U>, ? extends Throwable> handler) {
        return result.compose(handler);
    }

    @Override public boolean isDone() {
        return result.isDone();
    }

    @Override public java.util.concurrent.CompletableFuture<R> asJavaCompletion() {
        return result.asJavaCompletion();
    }

    @SafeVarargs public static <T> IFuture<List<T>> of(IFuture<? extends T>... futures) {
        return of(Arrays.asList(futures));
    }

    @SuppressWarnings("unchecked") public static <T> IFuture<List<T>>
            of(Iterable<? extends IFuture<? extends T>> futures) {
        final ArrayList<IFuture<SC<T, List<T>>>> mappedFutures = new ArrayList<>();
        futures.forEach(future -> mappedFutures.add(future.thenApply(SC::of)));
        return new AggregateFuture<>(ID_REDUCER, mappedFutures);
    }

    @SafeVarargs public static <T, R> IFuture<R> ofShortCircuitable(Function1<List<T>, R> reduce,
            IFuture<SC<T, R>>... futures) {
        return ofShortCircuitable(reduce, Arrays.asList(futures));
    }

    public static <T, R> IFuture<R> ofShortCircuitable(Function1<List<T>, R> reduce,
            Iterable<IFuture<SC<T, R>>> futures) {
        return new AggregateFuture<T, R>(reduce, futures);
    }

    @SuppressWarnings("unchecked") public static <T1, T2> IFuture<Tuple2<T1, T2>> apply(IFuture<T1> f1,
            IFuture<T2> f2) {
        return of(f1, f2).thenApply(rs -> Tuple2.of((T1) rs.get(0), (T2) rs.get(1)));
    }

    @SuppressWarnings("unchecked") public static <T1, T2, T3> IFuture<Tuple3<T1, T2, T3>> apply(IFuture<T1> f1,
            IFuture<T2> f2, IFuture<T3> f3) {
        return of(f1, f2, f3).thenApply(rs -> Tuple3.of((T1) rs.get(0), (T2) rs.get(1), (T3) rs.get(2)));
    }

    @SuppressWarnings("unchecked") public static <T1, T2, T3, T4> IFuture<Tuple4<T1, T2, T3, T4>> apply(IFuture<T1> f1,
            IFuture<T2> f2, IFuture<T3> f3, IFuture<T4> f4) {
        return of(f1, f2, f3, f4)
                .thenApply(rs -> Tuple4.of((T1) rs.get(0), (T2) rs.get(1), (T3) rs.get(2), (T4) rs.get(3)));
    }

    public static <T, U> IFuture<List<U>> forAll(Iterable<T> items, Function1<T, IFuture<U>> toFuture) {
        final ArrayList<IFuture<U>> futures = new ArrayList<>();
        items.forEach(item -> futures.add(toFuture.apply(item)));
        return of(futures);
    }


    public interface SC<T, R> {

        void visit(Action1<T> onNoSC, Action1<R> onSC);

        public static <T, R> SC<T, R> shortCircuit(R result) {
            return new DoSC<>(result);
        }

        public static <T, R> SC<T, R> of(T result) {
            return new NoSC<>(result);
        }

    }

    private static final class DoSC<T, R> implements SC<T, R> {

        private final R result;

        public DoSC(R result) {
            this.result = result;
        }

        @Override public void visit(Action1<T> onNoSC, Action1<R> onSC) {
            onSC.apply(result);
        }
    }

    private static final class NoSC<T, R> implements SC<T, R> {

        private final T result;

        public NoSC(T result) {
            this.result = result;
        }

        @Override public void visit(Action1<T> onNoSC, Action1<R> onSC) {
            onNoSC.apply(result);
        }
    }

}