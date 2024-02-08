package org.metaborg.util.task;

import jakarta.annotation.Nullable;
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

    public boolean equals(@Nullable Object other) {
        return this == other || other != null && this.getClass() == other.getClass();
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "ThreadCancel()";
    }
}
