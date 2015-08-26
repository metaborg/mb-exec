package org.metaborg.util.concurrent;

import java.util.concurrent.locks.Lock;

public class ClosableLock implements IClosableLock {
    private final Lock lock;


    public ClosableLock(Lock lock) {
        this.lock = lock;
        lock.lock();
    }

    @Override public void close() {
        lock.unlock();
    }
}
