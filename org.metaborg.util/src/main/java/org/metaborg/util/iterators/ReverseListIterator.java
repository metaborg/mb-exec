package org.metaborg.util.iterators;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ReverseListIterator<E> implements Iterator<E> {
    public final ListIterator<E> listIterator;

    public ReverseListIterator(List<E> list) {
        this.listIterator = list.listIterator(list.size());
    }

    @Override public boolean hasNext() {
        return listIterator.hasPrevious();
    }

    @Override public E next() {
        return listIterator.previous();
    }

    public static <E> Iterable<E> reverse(List<E> list) {
        return () -> new ReverseListIterator<>(list);
    }
}
