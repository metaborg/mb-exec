package org.metaborg.util.future;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.metaborg.util.future.CompletableFuture.completedExceptionally;
import static org.metaborg.util.future.CompletableFuture.completedFuture;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;
import org.metaborg.util.functions.Function1;
import org.metaborg.util.future.AggregateFuture.SC;

public class AggregateFutureTest {

    @Test public void testAggregateOfNone() {
        final AtomicBoolean marker = new AtomicBoolean(false);
        final IFuture<List<Integer>> af = AggregateFuture.of();
        af.whenComplete((r, ex) -> {
            marker.set(ex == null && r.isEmpty());
        });
        assertTrue(marker.get());
    }

    @Test public void testAggregateOfOneCompleted() {
        final AtomicBoolean marker = new AtomicBoolean(false);
        final IFuture<List<Integer>> af = AggregateFuture.of(completedFuture(1));
        af.whenComplete((r, ex) -> {
            marker.set(ex == null && r.size() == 1);
        });
        assertTrue(marker.get());
    }

    @Test public void testAggregateOfOneFailed() {
        final AtomicBoolean marker = new AtomicBoolean(false);
        final IFuture<List<Integer>> af = AggregateFuture.of(completedExceptionally(new Exception()));
        af.whenComplete((r, ex) -> {
            marker.set(ex != null);
        });
        assertTrue(marker.get());
    }

    @Test public void testAggregateOfOneFailed_Delayed() {
        final AtomicBoolean marker = new AtomicBoolean(false);
        final CompletableFuture<Integer> f = new CompletableFuture<>();
        final IFuture<List<Integer>> af = AggregateFuture.of(f);
        af.whenComplete((r, ex) -> {
            marker.set(ex != null);
        });
        f.completeExceptionally(new Exception());
        assertTrue(marker.get());
    }

    @Test public void testAggregateOfMultipleCompleted() {
        final AtomicBoolean marker = new AtomicBoolean(false);
        final IFuture<List<Integer>> af = AggregateFuture.of(completedFuture(1), completedFuture(2));
        af.whenComplete((r, ex) -> {
            marker.set(ex == null && r.size() == 2);
        });
        assertTrue(marker.get());
    }

    @Test public void testAggregateOfMultipleCompleted_Delayed() {
        final AtomicBoolean marker = new AtomicBoolean(false);
        final CompletableFuture<Integer> f1 = new CompletableFuture<>();
        final CompletableFuture<Integer> f2 = new CompletableFuture<>();
        final IFuture<List<Integer>> af = AggregateFuture.of(f1, f2);
        af.whenComplete((r, ex) -> {
            marker.set(ex == null && r.size() == 2);
        });
        f1.complete(1);
        assertFalse(marker.get());
        f2.complete(2);
        assertTrue(marker.get());
    }

    @Test public void testAggregateOfMultipleFailed() {
        final AtomicBoolean marker = new AtomicBoolean(false);
        final IFuture<List<Integer>> af =
                AggregateFuture.of(completedExceptionally(new Exception()), completedExceptionally(new Exception()));
        af.whenComplete((r, ex) -> {
            marker.set(ex != null);
        });
        assertTrue(marker.get());
    }

    @Test public void testAggregateOfMultipleFailed_Delayed() {
        final AtomicBoolean marker = new AtomicBoolean(false);
        final CompletableFuture<Integer> f1 = new CompletableFuture<>();
        final CompletableFuture<Integer> f2 = new CompletableFuture<>();
        final IFuture<List<Integer>> af = AggregateFuture.of(f1, f2);
        af.whenComplete((r, ex) -> {
            marker.set(ex != null);
        });
        assertFalse(marker.get());
        f2.completeExceptionally(new Exception());
        assertTrue(marker.get());
        f1.completeExceptionally(new Exception());
        assertTrue(marker.get());
    }

    @Test public void testAggregateOfMultipleCompletedAndFailed() {
        final AtomicBoolean marker = new AtomicBoolean(false);
        final IFuture<List<Integer>> af =
                AggregateFuture.of(completedFuture(42), completedExceptionally(new Exception()));
        af.whenComplete((r, ex) -> {
            marker.set(ex != null);
        });
        assertTrue(marker.get());
    }

    @Test public void testAggregateOfMultipleCompletedAndFailed_Delayed() {
        final AtomicBoolean valMarker = new AtomicBoolean(false);
        final AtomicBoolean marker = new AtomicBoolean(false);
        final CompletableFuture<Integer> f1 = new CompletableFuture<>();
        final CompletableFuture<Integer> f2 = new CompletableFuture<>();
        final IFuture<List<Integer>> af = AggregateFuture.of(f1, f2);
        af.whenComplete((r, ex) -> {
            valMarker.set(r != null);
            marker.set(ex != null);
        });
        f2.complete(42);
        assertFalse(valMarker.get());
        assertFalse(marker.get());
        f1.completeExceptionally(new Exception());
        assertFalse(valMarker.get());
        assertTrue(marker.get());
    }

    @Test public void testResultOnShortCircuit() {
        final AtomicBoolean marker = new AtomicBoolean(false);
        final AtomicBoolean redMarker = new AtomicBoolean(false);
        final CompletableFuture<SC<Integer, Integer>> f1 = new CompletableFuture<>();
        final CompletableFuture<SC<Integer, Integer>> f2 = new CompletableFuture<>();
        final Function1<List<Integer>, Integer> sum = intList -> {
            redMarker.set(true);
            return intList.stream().mapToInt(Integer::intValue).sum();
        };
        final IFuture<Integer> af = AggregateFuture.<Integer, Integer>ofShortCircuitable(sum, f1, f2);
        af.whenComplete((r, ex) -> {
            marker.set(ex == null && r == 42);
        });
        f2.complete(SC.shortCircuit(42));
        assertTrue(marker.get());
        assertFalse(redMarker.get());
    }

    @Test public void testResultNoShortCircuit() {
        final AtomicBoolean redMarker = new AtomicBoolean(false);
        final AtomicBoolean marker = new AtomicBoolean(false);
        final CompletableFuture<SC<Integer, Integer>> f1 = new CompletableFuture<>();
        final CompletableFuture<SC<Integer, Integer>> f2 = new CompletableFuture<>();
        final Function1<List<Integer>, Integer> sum = intList -> {
            redMarker.set(true);
            return intList.stream().mapToInt(Integer::intValue).sum();
        };
        final IFuture<Integer> af = AggregateFuture.<Integer, Integer>ofShortCircuitable(sum, f1, f2);
        af.whenComplete((r, ex) -> {
            marker.set(ex == null && r == 42);
        });
        f1.complete(SC.of(20));
        assertFalse(marker.get());
        assertFalse(redMarker.get());
        f2.complete(SC.of(22));
        assertTrue(marker.get());
        assertTrue(redMarker.get());
    }

    @Test public void testNoReduceOnException_First() {
        final AtomicBoolean redMarker = new AtomicBoolean(false);
        final AtomicBoolean exMarker = new AtomicBoolean(false);
        final AtomicBoolean marker = new AtomicBoolean(false);
        final CompletableFuture<SC<Integer, Integer>> f1 = new CompletableFuture<>();
        final CompletableFuture<SC<Integer, Integer>> f2 = new CompletableFuture<>();
        final Function1<List<Integer>, Integer> sum = intList -> {
            redMarker.set(true);
            return intList.stream().mapToInt(Integer::intValue).sum();
        };
        final IFuture<Integer> af = AggregateFuture.<Integer, Integer>ofShortCircuitable(sum, f1, f2);
        af.whenComplete((r, ex) -> {
            marker.set(ex == null);
            exMarker.set(ex != null);
        });
        f1.completeExceptionally(new Exception());
        assertFalse(marker.get());
        assertTrue(exMarker.get());
        assertFalse(redMarker.get());
        f1.complete(SC.shortCircuit(22));
        assertFalse(marker.get());
        assertTrue(exMarker.get());
        assertFalse(redMarker.get());
    }

    @Test public void testNoReduceOnException_Last() {
        final AtomicBoolean redMarker = new AtomicBoolean(false);
        final AtomicBoolean marker = new AtomicBoolean(false);
        final CompletableFuture<SC<Integer, Integer>> f1 = new CompletableFuture<>();
        final CompletableFuture<SC<Integer, Integer>> f2 = new CompletableFuture<>();
        final Function1<List<Integer>, Integer> sum = intList -> {
            redMarker.set(true);
            return intList.stream().mapToInt(Integer::intValue).sum();
        };
        final IFuture<Integer> af = AggregateFuture.<Integer, Integer>ofShortCircuitable(sum, f1, f2);
        af.whenComplete((r, ex) -> {
            marker.set(ex != null);
        });
        f1.complete(SC.of(20));
        assertFalse(marker.get());
        assertFalse(redMarker.get());
        f2.completeExceptionally(new Exception());
        assertTrue(marker.get());
        assertFalse(redMarker.get());
    }

}