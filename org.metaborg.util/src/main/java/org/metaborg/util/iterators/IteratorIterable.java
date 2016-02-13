package org.metaborg.util.iterators;

import java.util.Iterator;

import rx.functions.Func0;

public class IteratorIterable<T> implements Iterable<T> {
    private final Func0<Iterator<T>> iteratorGenerator;


    public IteratorIterable(Func0<Iterator<T>> iteratorGenerator) {
        this.iteratorGenerator = iteratorGenerator;
    }


    @Override public Iterator<T> iterator() {
        return iteratorGenerator.call();
    }
}
