package org.metaborg.util.collection;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Collection that overrides mutating methods from Collection with UnsupportedOperationException,
 * and fills in some defaults for others. This way you only have to implement iterator() and size().
 *
 * Do _not_ override mutating methods to actually mutate again! Then it wouldn't be an _immutable_
 * collection any more.
 */
public interface ImmutableCollection<E> extends Collection<E> {
    @Override Iterator<E> iterator();

    @Override int size();

    @Override default boolean isEmpty() {
        return size() == 0;
    }

    @Override default boolean contains(Object o) {
        Iterator<E> it = iterator();
        while(it.hasNext()) {
            if(Objects.equals(o, it.next())) {
                return true;
            }
        }
        return false;
    }

    @Override default Object[] toArray() {
        return toArray(new Object[0]);
    }

    @Override default <T> T[] toArray(T[] a) {
        int size = size();
        final T[] result;
        if(a.length < size) {
            result = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        } else {
            result = a;
        }

        int i = 0;
        for(E e : this) {
            result[i] = (T) e;
            i++;
        }
        return result;
    }

    @Override default boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return c.stream().allMatch(this::contains);
    }

    @Override default boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override default boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override default boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override default boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override default boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override default boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException();
    }

    @Override default void clear() {
        throw new UnsupportedOperationException();
    }

}