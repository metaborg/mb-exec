package org.metaborg.util.concurrent;

public interface IClosableLock extends AutoCloseable {
    public abstract void close();
}
