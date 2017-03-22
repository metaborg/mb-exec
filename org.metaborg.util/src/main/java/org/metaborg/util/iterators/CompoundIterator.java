package org.metaborg.util.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.google.common.collect.Iterators;

public class CompoundIterator<T> implements Iterator<T> {
    private final Iterator<? extends Iterator<? extends T>> iterators;
    private Iterator<? extends T> iterator = Iterators.emptyIterator();


    public CompoundIterator(Iterable<? extends Iterator<? extends T>> iterators) {
        this.iterators = iterators.iterator();
    }


    @Override public boolean hasNext() {
        if(iterator.hasNext()) {
            return true;
        }
        try {
            iterator = iterators.next();
        } catch(NoSuchElementException ex) {
            return false;
        }
        return hasNext();
    }

    @Override public T next() {
        try {
            return iterator.next();
        } catch(NoSuchElementException ex) {
            iterator = iterators.next();
        }
        return next();
    }

    @Override public void remove() {
        throw new UnsupportedOperationException();
    }
}
