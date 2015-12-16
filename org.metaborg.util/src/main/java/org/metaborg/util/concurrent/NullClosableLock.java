package org.metaborg.util.concurrent;

public class NullClosableLock implements IClosableLock {
    @Override public void close() {
    }
}
