package org.metaborg.util.iterators;

import java.util.Iterator;

import com.google.common.collect.UnmodifiableIterator;

final public class ConsIterator<T> extends UnmodifiableIterator<T> {

    private final T head;
    private final Iterator<T> tail;
    private boolean inTail;


    public ConsIterator(T head, Iterator<T> tail) {
        this.head = head;
        this.tail = tail;
    }


    @Override public boolean hasNext() {
        return !inTail || tail.hasNext();
    }

    @Override public T next() {
        if (!inTail) {
            inTail = true;
            return head;
        } else {
            return tail.next();
        }
    }

}