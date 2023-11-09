package org.metaborg.util.iterators;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;

public class MappedIterator<E1, E2> implements Iterator<E2> {
    public final Iterator<E1> wrappedIterator;
    private final Function<E1, E2> map;

    public MappedIterator(Iterator<E1> wrappedIterator, Function<E1, E2> map) {
        this.wrappedIterator = wrappedIterator;
        this.map = map;
    }

    @Override public boolean hasNext() {
        return wrappedIterator.hasNext();
    }

    @Override public E2 next() {
        return map.apply(wrappedIterator.next());
    }

    @Override public void remove() {
        wrappedIterator.remove();
    }
}
