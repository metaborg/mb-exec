package org.metaborg.util.iterators;

import java.util.Iterator;

public class PeekingIterator<E> implements Iterator<E> {
    private final Iterator<E> iterator;
    private E peekedElement = null;

    public PeekingIterator(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    @Override public boolean hasNext() {
        return peekedElement != null || iterator.hasNext();
    }

    @Override public E next() {
        if(peekedElement == null) {
            return iterator.next();
        }
        final E result = peekedElement;
        peekedElement = null;
        return result;
    }

    @Override public void remove() {
        if(peekedElement != null) {
            throw new IllegalStateException("Cannot remove after peeking.");
        }
        iterator.remove();
    }

    public E peek() {
        if(peekedElement == null) {
            peekedElement = iterator.next();
        }
        return peekedElement;
    }
}
