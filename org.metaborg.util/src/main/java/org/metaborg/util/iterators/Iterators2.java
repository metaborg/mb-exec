package org.metaborg.util.iterators;

import java.util.Iterator;

public final class Iterators2 {
    public static <T> Iterator<T> singleton(T t) {
        return new SingletonIterator<T>(t);
    }

    @SafeVarargs public static <T> Iterator<T> from(T... array) {
        return new ArrayIterator<T>(array);
    }

    public static <T> Iterator<T> from(Iterable<? extends Iterator<T>> iterators) {
        return new CompoundIterator<T>(iterators);
    }

    @SafeVarargs public static <T> Iterator<T> from(Iterator<T>... iterators) {
        return from(Iterables2.from(iterators));
    }
}
