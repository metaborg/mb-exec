package org.metaborg.util.task;

import jakarta.annotation.Nullable;
import java.io.Serializable;

/**
 * Cancellation token implementation that never cancels.
 */
public class NullCancel implements ICancel, Serializable {
    @Override public boolean cancelled() {
        return false;
    }

    @Override public void throwIfCancelled() {
    }

    @Override public void cancel() {
    }

    public boolean equals(@Nullable Object other) {
        return this == other || other != null && this.getClass() == other.getClass();
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "NullCancel()";
    }
}
