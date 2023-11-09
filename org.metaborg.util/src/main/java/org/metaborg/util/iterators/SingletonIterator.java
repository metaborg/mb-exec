package org.metaborg.util.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

final public class SingletonIterator<T> implements Iterator<T> {
    private final T value;
    private boolean done;


    public SingletonIterator(T value) {
        this.value = value;
    }


    @Override public boolean hasNext() {
        return !done;
    }

    @Override public T next() {
        if(done) {
            throw new NoSuchElementException();
        }
        done = true;
        return value;
    }
}