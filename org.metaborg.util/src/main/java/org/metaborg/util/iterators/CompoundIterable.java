package org.metaborg.util.iterators;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class CompoundIterable<T> implements Iterable<T> {
    private final Iterable<? extends Iterable<? extends T>> iterables;


    public CompoundIterable(Iterable<? extends Iterable<? extends T>> iterables) {
        this.iterables = iterables;
    }


    @Override public Iterator<T> iterator() {
        final Collection<Iterator<? extends T>> iterators = new LinkedList<>();
        for(Iterable<? extends T> iterable : iterables) {
            iterators.add(iterable.iterator());
        }
        return new CompoundIterator<T>(iterators);
    }
}
