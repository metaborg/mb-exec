package org.metaborg.util.iterators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Utility class for iterables with missing functionality from Guava's Iterables.
 */
public final class Iterables2 {
    /**
     * Generates an iterable that is empty; the iterator it returns will always return false for hasNext.
     */
    public static <T> Iterable<T> empty() {
        return Collections.emptyList();
    }

    /**
     * Generates an iterable that contains given single element.
     */
    public static <T> Iterable<T> singleton(T t) {
        return Collections.singletonList(t);
    }

    /**
     * Generates an iterable that contains elements from given vararg elements.
     */
    @SafeVarargs public static <T> Iterable<T> from(T... array) {
        return new ArrayIterable<T>(array);
    }

    /**
     * Generates an iterable with the given element first, followed by the elements of the tail iterable.
     */
    public static <T> Iterable<T> cons(T head, Iterable<T> tail) {
        return new ConsIterable<>(head, tail);
    }

    /**
     * Generates an iterable that contains elements from given iterator generator.
     */
    public static <T> Iterable<T> from(Supplier<Iterator<T>> iteratorGenerator) {
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

    /**
     * Create a stream from an iterable.
     *
     * @param iterable
     * @return stream
     */
    public static <T> Stream<T> stream(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public static <T1, T2, R> Iterable<R> zip(Iterable<T1> iterable1, Iterable<T2> iterable2,
        BiFunction<? super T1, ? super T2, R> combine) {
        return new Zip2Iterable<>(iterable1, iterable2, combine);
    }

    public static <E> ArrayList<E> toArrayList(Iterable<E> iterable) {
        if(iterable instanceof Collection) {
            return new ArrayList<>((Collection<? extends E>) iterable);
        }
        final ArrayList<E> result = new ArrayList<>();
        for(E e : iterable) {
            result.add(e);
        }
        return result;
    }

    public static <E> E[] toArray(Iterable<E> iterable) {
        return (E[]) toArrayList(iterable).toArray();
    }

    public static <E> boolean isEmpty(Iterable<E> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection<?>) iterable).isEmpty();
        }
        return !iterable.iterator().hasNext();
    }
}
