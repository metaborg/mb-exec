package org.metaborg.util.iterators;

import java.util.Iterator;

public final class Iterators2 {
    public static <T> Iterator<T> singleton(T t) {
        return new SingletonIterator<T>(t);
    }

    @SafeVarargs
    public static <T> Iterator<T> from(T... array) {
        return new ArrayIterator<T>(array);
    }
}
