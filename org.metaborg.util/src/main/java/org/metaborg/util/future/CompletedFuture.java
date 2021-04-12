package org.metaborg.util.future;

import org.metaborg.util.functions.CheckedAction1;
import org.metaborg.util.functions.CheckedAction2;
import org.metaborg.util.functions.CheckedFunction1;
import org.metaborg.util.functions.CheckedFunction2;

class CompletedFuture<T> implements ICompletableFuture<T> {

    private final T result;

    public CompletedFuture(T result) {
        this.result = result;
    }

    /////////////////////////////////////////////////////////////////////
    // ICompletable
    /////////////////////////////////////////////////////////////////////

    @SuppressWarnings("unused") @Override public void complete(T value, Throwable ex) {
        // ignore
    }


    /////////////////////////////////////////////////////////////////////
    // IFuture
    /////////////////////////////////////////////////////////////////////

    @Override public <U> IFuture<U>
            handle(CheckedFunction2<? super T, Throwable, ? extends U, ? extends Throwable> handler) {
        try {
            return CompletableFuture.completedFuture(handler.apply(result, null));
        } catch(Throwable ex) {
            return CompletableFuture.completedExceptionally(ex);
        }
    }

    @Override public IFuture<T> whenComplete(CheckedAction2<? super T, Throwable, ? extends Throwable> handler) {
        try {
            handler.apply(result, null);
            return CompletableFuture.completedFuture(result);
        } catch(Throwable ex) {
            return CompletableFuture.completedExceptionally(ex);
        }
    }

    @Override public <U> IFuture<U> thenApply(CheckedFunction1<? super T, ? extends U, ? extends Throwable> handler) {
        try {
            return CompletableFuture.completedFuture(handler.apply(result));
        } catch(Throwable ex) {
            return CompletableFuture.completedExceptionally(ex);
        }
    }

    @Override public IFuture<Void> thenAccept(CheckedAction1<? super T, ? extends Throwable> handler) {
        try {
            handler.apply(result);
            return CompletableFuture.completedFuture(null);
        } catch(Throwable ex) {
            return CompletableFuture.completedExceptionally(ex);
        }
    }

    @SuppressWarnings("unchecked") @Override public <U> IFuture<U>
            thenCompose(CheckedFunction1<? super T, ? extends IFuture<? extends U>, ? extends Throwable> handler) {
        try {
            return (IFuture<U>) handler.apply(result);
        } catch(Throwable ex) {
            return CompletableFuture.completedExceptionally(ex);
        }
    }

    @SuppressWarnings("unchecked") @Override public <U> IFuture<U> compose(
            CheckedFunction2<? super T, Throwable, ? extends IFuture<? extends U>, ? extends Throwable> handler) {
        try {
            return (IFuture<U>) handler.apply(result, null);
        } catch(Throwable ex) {
            return CompletableFuture.completedExceptionally(ex);
        }
    }

    @Override public boolean isDone() {
        return true;
    }

    @Override public java.util.concurrent.CompletableFuture<T> asJavaCompletion() {
        final java.util.concurrent.CompletableFuture<T> future = new java.util.concurrent.CompletableFuture<>();
        future.complete(result);
        return future;
    }

    @Override public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    }

}