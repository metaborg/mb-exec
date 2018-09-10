package org.metaborg.util.iterators;

import java.util.Iterator;

final public class ConsIterable<T> implements Iterable<T> {

    private final T head;
    private final Iterable<T> tail;


    public ConsIterable(T head, Iterable<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    public Iterator<T> iterator() {
        return new ConsIterator<>(head, tail.iterator());
    }

}