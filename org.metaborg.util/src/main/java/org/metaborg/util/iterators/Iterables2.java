package org.metaborg.util.iterators;

import java.util.Iterator;

import com.google.common.collect.ImmutableList;

import rx.functions.Func0;

/**
 * Utility class for iterables with missing functionality from Guava's Iterables.
 */
public final class Iterables2 {
    /**
     * Generates an iterable that is empty; the iterator it returns will always return false for hasNext.
     */
    public static <T> Iterable<T> empty() {
        return ImmutableList.<T>of();
    }

    /**
     * Generates an iterable that contains given single element.
     */
    public static <T> Iterable<T> singleton(T t) {
        return ImmutableList.<T>of(t);
    }

    /**
     * Generates an iterable that contains elements from given vararg elements.
     */
    @SafeVarargs public static <T> Iterable<T> from(T... array) {
        return new ArrayIterable<T>(array);
    }

    /**
     * Generates an iterable that contains elements from given iterator generator.
     */
    public static <T> Iterable<T> from(Func0<Iterator<T>> iteratorGenerator) {
        return new IteratorIterable<T>(iteratorGenerator);
    }

    /**
     * Generates an iterable that contains elements from given iterator once. After a full iteration, the iterable will
     * be empty.
     */
    public static <T> Iterable<T> fromOnce(Iterator<T> iterator) {
        return new IteratorIterableOnce<T>(iterator);
    }

    /**
     * Generates an iterable that contains all elements inside given iterables, passed through an iterable.
     */
    public static <T> Iterable<T> fromConcat(Iterable<? extends Iterable<? extends T>> iterables) {
        return new CompoundIterable<T>(iterables);
    }

    /**
     * Generates an iterable that contains all elements inside given iterables, passed through varargs.
     */
    @SafeVarargs public static <T> Iterable<T> fromConcat(Iterable<? extends T>... iterablesArray) {
        return fromConcat(Iterables2.from(iterablesArray));
    }

}
