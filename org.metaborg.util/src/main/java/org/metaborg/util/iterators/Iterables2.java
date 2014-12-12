package org.metaborg.util.iterators;


public final class Iterables2 {
    public static <T> Iterable<T> empty() {
        return new EmptyIterable<T>();
    }

    public static <T> Iterable<T> singleton(T t) {
        return new SingletonIterable<T>(t);
    }

    @SafeVarargs public static <T> Iterable<T> from(T... array) {
        return new ArrayIterable<T>(array);
    }

    public static <T> Iterable<T> from(Iterable<? extends Iterable<T>> iterables) {
        return new CompoundIterable<T>(iterables);
    }

    @SafeVarargs public static <T> Iterable<T> from(Iterable<T>... iterablesArray) {
        return from(Iterables2.<Iterable<T>>from(iterablesArray));
    }
}
