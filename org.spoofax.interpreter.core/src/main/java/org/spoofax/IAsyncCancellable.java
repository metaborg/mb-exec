package org.spoofax;

import java.util.concurrent.CancellationException;


/**
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public interface IAsyncCancellable {
    /**
     * Aborts an asynchronously running job, causing it to throw
     * a {@link CancellationException}.
     * 
     * Call {@link #asyncCancel()} after the job completes to reset the
     * cancellation state for the next job.
     *  
     * (Provides no guarantee that the job is actually cancelled.)
     */
    void asyncCancel();
    
    /**
     * Resets the cancellation state of the current job.
     */
    void asyncCancelReset();
}
