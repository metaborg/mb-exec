package org.metaborg.util.iterators;

import java.util.Iterator;

public class IteratorIterableOnce<T> implements Iterable<T> {
    private final Iterator<T> iterator;


    public IteratorIterableOnce(Iterator<T> iterator) {
        this.iterator = iterator;
    }


    @Override public Iterator<T> iterator() {
        return iterator;
    }
}
