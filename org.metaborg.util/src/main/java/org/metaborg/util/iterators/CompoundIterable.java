package org.metaborg.util.iterators;

import java.util.Collection;
import java.util.Iterator;

import com.google.common.collect.Lists;

public class CompoundIterable<T> implements Iterable<T> {
    private final Iterable<? extends Iterable<? extends T>> iterables;


    public CompoundIterable(Iterable<? extends Iterable<? extends T>> iterables) {
        this.iterables = iterables;
    }


    @Override public Iterator<T> iterator() {
        final Collection<Iterator<? extends T>> iterators = Lists.newLinkedList();
        for(Iterable<? extends T> iterable : iterables) {
            iterators.add(iterable.iterator());
        }
        return new CompoundIterator<T>(iterators);
    }
}
