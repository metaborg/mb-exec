package org.metaborg.util.task;

import java.io.Serializable;

/**
 * Cancellation token implementation that cancels on thread interrupts.
 */
public class ThreadCancel implements ICancel, Serializable {
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
