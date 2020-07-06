package org.metaborg.util.task;

public class RateLimitedCancel implements ICancel {

    private final ICancel cancel;
    private final int rate;

    private int n = 0;

    public RateLimitedCancel(ICancel cancel, int rate) {
        this.cancel = cancel;
        this.rate = rate;
    }

    @Override public boolean cancelled() {
        return (n++ % rate) == 0 ? cancel.cancelled() : false;
    }

    @Override public void throwIfCancelled() throws InterruptedException {
        if((n++ % rate) == 0) {
            cancel.throwIfCancelled();
        }
    }

    @Override public void cancel() {
        cancel.cancel();
    }

}
