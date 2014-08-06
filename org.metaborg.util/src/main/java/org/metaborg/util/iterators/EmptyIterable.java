package org.metaborg.util.iterators;

import java.util.Iterator;

import com.google.common.collect.Iterators;

public class EmptyIterable<T> implements Iterable<T> {
    public Iterator<T> iterator() {
        return Iterators.emptyIterator();
    }
}
