package org.metaborg.util.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

import com.google.common.collect.Queues;

public class CompoundIterator<T> implements Iterator<T> {
    private final Queue<? extends Iterator<T>> iterators;


    public CompoundIterator(Iterable<? extends Iterator<T>> iterators) {
        this.iterators = Queues.newArrayDeque(iterators);
    }


    @Override public boolean hasNext() {
        final Iterator<T> iterator = iterators.peek();
        if(iterator == null) {
            return false;
        }
        return iterator.hasNext();
    }

    @Override public T next() {
        final Iterator<T> iterator = iterators.peek();
        if(iterator == null) {
            throw new NoSuchElementException();
        }

        if(iterator.hasNext()) {
            return iterator.next();
        } else {
            iterators.poll();
            return next();
        }
    }

    @Override public void remove() {
        throw new UnsupportedOperationException();
    }
}
