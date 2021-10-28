package org.metaborg.util.task;

import javax.annotation.Nullable;
import java.io.Serializable;

/**
 * Progress reporter implementation that ignores all progress reporting.
 */
public class NullProgress implements IProgress, Serializable {
    @Override public void work(int ticks) {
    }

    @Override public void setDescription(String description) {
    }

    @Override public void setWorkRemaining(int ticks) {
    }

    @Override public IProgress subProgress(int ticks) {
        return new NullProgress();
    }

    public boolean equals(@Nullable Object other) {
        return this == other || other != null && this.getClass() == other.getClass();
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "NullProgress()";
    }
}
