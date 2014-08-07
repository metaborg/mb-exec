package org.metaborg.util.iterators;

import java.util.Iterator;

public class ArrayIterable<T> implements Iterable<T> {
    private final T[] array;

    public ArrayIterable(T[] array) {
        this.array = array;
    }

    @Override public Iterator<T> iterator() {
        return new ArrayIterator<T>(array);
    }
}
