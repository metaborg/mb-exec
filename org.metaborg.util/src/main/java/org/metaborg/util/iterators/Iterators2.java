package org.metaborg.util.iterators;

import java.util.Iterator;

/**
 * Utility class for iterators with missing functionality from Guava's Iterators.
 */
public final class Iterators2 {
    /**
     * Generates an iterator that iterates over single given element.
     */
    public static <T> Iterator<T> singleton(T t) {
        return new SingletonIterator<T>(t);
    }

    /**
     * Generates an iterator that iterates over elements from given vararg elements.
     */
    @SafeVarargs public static <T> Iterator<T> from(T... array) {
        return new ArrayIterator<T>(array);
    }

    /**
     * Generates an iterator that iterates over all elements inside given iterators, pass through an iterable.
     */
    public static <T> Iterator<T> from(Iterable<? extends Iterator<T>> iterators) {
        return new CompoundIterator<T>(iterators);
    }

    /**
     * Generates an iterator that iterates over all elements inside given iterators, passed through varargs.
     */
    @SafeVarargs public static <T> Iterator<T> from(Iterator<T>... iterators) {
        return from(Iterables2.from(iterators));
    }
}
