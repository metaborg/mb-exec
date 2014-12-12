package org.metaborg.util.iterators;

import java.util.Collection;
import java.util.Iterator;

import com.google.common.collect.Lists;

public class CompoundIterable<T> implements Iterable<T> {
    private final Iterable<? extends Iterable<T>> iterables;


    public CompoundIterable(Iterable<? extends Iterable<T>> iterables) {
        this.iterables = iterables;
    }


    @Override public Iterator<T> iterator() {
        final Collection<Iterator<T>> iterators = Lists.newLinkedList();
        for(Iterable<T> iterable : iterables) {
            iterators.add(iterable.iterator());
        }
        return new CompoundIterator<T>(iterators);
    }
}
