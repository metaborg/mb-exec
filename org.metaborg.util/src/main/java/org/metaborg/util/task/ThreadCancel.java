package org.metaborg.util.task;

/**
 * Cancellation token implementation that cancels on thread interrupts.
 */
public class ThreadCancel implements ICancel {
    @Override public boolean cancelled() {
        return Thread.currentThread().isInterrupted();
    }

    @Override public void throwIfCancelled() throws InterruptedException {
        if(Thread.interrupted()) {
            throw new InterruptedException();
        }
    }

    @Override public void cancel() {
        Thread.currentThread().interrupt();
    }
}
