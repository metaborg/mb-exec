package org.metaborg.util.iterators;

import java.util.Iterator;
import java.util.function.Supplier;

public class IteratorIterable<T> implements Iterable<T> {
    private final Supplier<Iterator<T>> iteratorGenerator;


    public IteratorIterable(Supplier<Iterator<T>> iteratorGenerator) {
        this.iteratorGenerator = iteratorGenerator;
    }


    @Override public Iterator<T> iterator() {
        return iteratorGenerator.get();
    }
}
