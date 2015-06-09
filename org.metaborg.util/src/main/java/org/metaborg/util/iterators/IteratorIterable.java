package org.metaborg.util.iterators;

import java.util.Iterator;

public class IteratorIterable<T> implements Iterable<T> {
    private final Iterator<T> iterator;


    public IteratorIterable(Iterator<T> iterator) {
        this.iterator = iterator;
    }


    @Override public Iterator<T> iterator() {
        return iterator;
    }
}
