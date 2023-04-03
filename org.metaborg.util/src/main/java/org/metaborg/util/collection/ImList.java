package org.metaborg.util.collection;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Nullable;

/**
 * Mutable/Immutable List interface ala capsule.
 * Does not allow null values.
 * @param <E>
 */
public abstract class ImList<E> implements List<E>, Serializable {
    private static final long serialVersionUID = 1L;

    public static <E> Collector<E, ImList.Transient<E>, ImList.Immutable<E>> toImmutableList() {
        return new Collector<E, ImList.Transient<E>, Immutable<E>>() {
            @Override public Supplier<ImList.Transient<E>> supplier() {
                return Transient::of;
            }

            @Override public BiConsumer<ImList.Transient<E>, E> accumulator() {
                return Transient::add;
            }

            @Override public BinaryOperator<ImList.Transient<E>> combiner() {
                return (l, r) -> {
                    l.addAll(r);
                    return l;
                };
            }

            @Override public Function<ImList.Transient<E>, Immutable<E>> finisher() {
                return Transient::freeze;
            }

            @Override public Set<Characteristics> characteristics() {
                return Collections.emptySet();
            }
        };
    }

    @Override public abstract int size();
    @Override public abstract boolean isEmpty();
    @Override public abstract boolean contains(Object o);
    @Override public abstract Iterator<E> iterator();

    @Override public Object[] toArray() {
        return toArray(new Object[0]);
    }

    @Override public boolean equals(Object that) {
        if (that == this)
            return true;
        if (!(that instanceof List))
            return false;

        final Iterator<E> thisIter = iterator();
        final Iterator<?> thatIter = ((List<?>) that).iterator();
        while (thisIter.hasNext() && thatIter.hasNext()) {
            final E thisElem = thisIter.next();
            final Object thatElem = thatIter.next();
            if (!(thisElem == thatElem || thisElem != null && thisElem.equals(thatElem))) {
                return false;
            }
        }
        return !(thisIter.hasNext() || thatIter.hasNext());
    }

    @Override public int hashCode() {
        int hashCode = 1;
        for (E elem : this) {
            hashCode = 31 * hashCode + (elem == null ? 0 : elem.hashCode());
        }
        return hashCode;
    }

    @Override public abstract <T> T[] toArray(T[] a);

    @Override public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override public abstract boolean containsAll(Collection<?> c);

    @Override public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override public abstract E get(int index);

    @Override public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override public abstract int indexOf(Object o);
    @Override public abstract int lastIndexOf(Object o);
    @Override public abstract ListIterator<E> listIterator();
    @Override public abstract ListIterator<E> listIterator(int index);
    @Override public abstract List<E> subList(int fromIndex, int toIndex);

    public static class Immutable<E> extends ImList<E> {
        private static final long serialVersionUID = 1L;
        private final E[] array;
        private final int size;

        Immutable(E[] array, int size) {
            this.array = array;
            this.size = size;
        }

        @SafeVarargs public static <E> ImList.Immutable<E> of(E... elems) {
            return new Immutable<>(Arrays.copyOf(elems, elems.length), elems.length);
        }

        @SuppressWarnings("unchecked") public static <E> Immutable<E> sortedCopyOf(Collection<E> collection, Comparator<? super E> comparator) {
            Objects.requireNonNull(comparator);
            E[] newArray = (E[]) collection.toArray(new Object[0]);
            Arrays.sort(newArray, comparator);
            return new ImList.Immutable<>(newArray, newArray.length);
        }

        @SuppressWarnings("unchecked") public static <E> Immutable<E> copyOf(Iterable<? extends E> iterable) {
            if(iterable instanceof ImList.Immutable) {
                return (ImList.Immutable<E>) iterable;
            }
            int size = 4;
            if(iterable instanceof Collection) {
                size = ((Collection<? extends E>) iterable).size();
            }
            final ImList.Transient<E> copy = new ImList.Transient<>(size);
            for(E e : iterable) {
                copy.add(e);
            }
            return copy.freeze();
        }

        public static <E> Immutable<E> copyOf(E[] array) {
            final ImList.Transient<E> copy = new ImList.Transient<>(array.length);
            for(E e : array) {
                copy.add(e);
            }
            return copy.freeze();
        }

        @Override public int size() {
            return size;
        }

        @Override public boolean isEmpty() {
            return size == 0;
        }

        @Override public boolean contains(Object o) {
            Objects.requireNonNull(o);
            return indexOf(o) >= 0;
        }

        @Override public Iterator<E> iterator() {
            return new Iter();
        }

        private class Iter implements Iterator<E>  {
            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                final E e = array[cursor];
                cursor += 1;
                return e;
            }
        }

        @SuppressWarnings("unchecked") @Override public <T> T[] toArray(T[] a) {
            int size = size();
            if (a.length < size) {
                return Arrays.copyOf(this.array, size, (Class<? extends T[]>) a.getClass());
            }
            //noinspection SuspiciousSystemArraycopy
            System.arraycopy(this.array, 0, a, 0, size);
            if (a.length > size) {
                a[size] = null;
            }
            return a;
        }

        @Override public boolean containsAll(Collection<?> c) {
            Objects.requireNonNull(c);
            return c.stream().allMatch(this::contains);
        }

        @Override public E get(int index) {
            if(index >= size) {
                throw new IndexOutOfBoundsException();
            }
            return array[index];
        }

        @Override public int indexOf(Object o) {
            return IntStream.range(0, this.array.length).filter(i -> Objects.equals(o, this.array[i])).findFirst()
                .orElse(-1);
        }

        @Override public int lastIndexOf(Object o) {
            return IntStream.iterate(this.array.length - 1, i -> i - 1).limit(this.array.length)
                .filter(i -> Objects.equals(o, this.array[i])).findFirst().orElse(-1);
        }

        @Override public ListIterator<E> listIterator() {
            return listIterator(0);
        }

        @Override public ListIterator<E> listIterator(int index) {
            if(index < 0 || index > size) {
                throw new IndexOutOfBoundsException();
            }
            return new ListIter(index);
        }

        private class ListIter extends Iter implements ListIterator<E> {
            ListIter(int index) {
                cursor = index;
            }

            public boolean hasPrevious() {
                return cursor > 0;
            }

            public E previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                cursor -= 1;
                return array[cursor];
            }

            public int nextIndex() {
                return cursor;
            }

            public int previousIndex() {
                return cursor-1;
            }

            @Override public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override public void set(E e) {
                throw new UnsupportedOperationException();
            }

            @Override public void add(E e) {
                throw new UnsupportedOperationException();
            }

        }

        @Override public List<E> subList(int fromIndex, int toIndex) {
            if(fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
                throw new IndexOutOfBoundsException();
            }
            return new ImList.Immutable<>(Arrays.copyOfRange(this.array, fromIndex, toIndex), toIndex - fromIndex);
        }

        @SuppressWarnings("unchecked") public Transient<E> asTransient() {
            return new Transient<>((E[]) toArray());
        }

        @Override public String toString() {
            return "ImList.Immutable[" + stream().map(Objects::toString).collect(Collectors.joining(", ")) + ']';
        }
    }

    public static class Transient<E> extends ImList<E> {
        private static final long serialVersionUID = 1L;
        private E[] array;
        private int size = 0;
        private @Nullable ImList.Immutable<E> frozen = null;

        @SuppressWarnings("unchecked") public Transient(int initialCapacity) {
            if (initialCapacity > 0) {
                this.array = (E[]) new Object[initialCapacity];
            } else if (initialCapacity == 0) {
                this.array = (E[]) new Object[] {};
            } else {
                throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
            }
        }

        Transient(E[] array) {
            this.array = array;
            size = array.length;
        }

        public static <E> Transient<E> of(E... elements) {
            final Transient<E> result = new Transient<>(elements.length);
            if(elements.length == 0) {
                return result;
            }
            for(E element : elements) {
                result.add(element);
            }
            return result;
        }

        @Override public int size() {
            return size;
        }

        @Override public boolean isEmpty() {
            return size == 0;
        }

        @Override public boolean contains(Object o) {
            return Arrays.stream(array, 0, size).anyMatch(e -> Objects.equals(o, e));
        }

        @Override public Iterator<E> iterator() {
            return new Iter();
        }

        private class Iter implements Iterator<E>  {
            int cursor = 0;
            int lastRet = -1;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                final E e = array[cursor];
                lastRet = cursor;
                cursor += 1;
                return e;
            }

            @Override public void remove() {
                if (lastRet < 0) {
                    throw new IllegalStateException();
                }
                Transient.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            }
        }

        @SuppressWarnings("unchecked") @Override public <T> T[] toArray(T[] a) {
            if(a.length < size) {
                return (T[]) Arrays.copyOf(array, size, a.getClass());
            }
            //noinspection SuspiciousSystemArraycopy
            System.arraycopy(array, 0, a, 0, size);
            if (a.length > size)
                a[size] = null;
            return a;
        }

        @Override public boolean add(E e) {
            if(isFrozen()) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            if(size == array.length) {
                realloc();
            }
            array[size] = e;
            size += 1;
            return true;
        }

        private void realloc() {
            realloc(array.length * 2);
        }

        private void realloc(int newLength) {
            int remainder = newLength % 2;
            if(remainder != 0) {
                newLength = newLength + 2 - remainder;
            }
            array = Arrays.copyOf(array, Math.max(4, newLength));
        }

        @Override public boolean containsAll(Collection<?> c) {
            Objects.requireNonNull(c);
            return c.stream().allMatch(this::contains);
        }

        @Override public boolean addAll(Collection<? extends E> c) {
            if(isFrozen()) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            //noinspection ConstantValue
            return c.stream().map(this::add).reduce(false, (a, b) -> a || b);
        }

        @Override public boolean removeAll(Collection<?> c) {
            if(isFrozen()) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            return c.stream().map(this::remove).reduce(false, (a, b) -> a || b);
        }

        @Override public void clear() {
            if(isFrozen()) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            for(int i = 0; i < size; i++) {
                array[i] = null;
            }
            size = 0;
        }

        @Override public E get(int index) {
            if(index >= size) {
                throw new IndexOutOfBoundsException();
            }
            return array[index];
        }

        @Override public E set(int index, E element) {
            if(isFrozen()) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            if(index >= size) {
                throw new IndexOutOfBoundsException();
            }
            E prev = array[index];
            array[index] = element;
            return prev;
        }

        @Override public int indexOf(Object o) {
            return IntStream.range(0, this.array.length).filter(i -> Objects.equals(o, this.array[i])).findFirst()
                .orElse(-1);
        }

        @Override public int lastIndexOf(Object o) {
            return IntStream.iterate(this.array.length - 1, i -> i - 1).limit(this.array.length)
                .filter(i -> Objects.equals(o, this.array[i])).findFirst().orElse(-1);
        }

        @Override public List<E> subList(int fromIndex, int toIndex) {
            if(fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
                throw new IndexOutOfBoundsException();
            }
            // TODO: if necessary, we can write a proper sublist class, look at ArrayList's for inspiration
            throw new UnsupportedOperationException();
        }

        public Immutable<E> freeze() {
            if(frozen == null) {
                frozen = new Immutable<>(array, size);
            }
            return frozen;
        }

        public boolean isFrozen() {
            return frozen != null;
        }

        @Override public String toString() {
            return "ImList.Transient[" + stream().map(Objects::toString).collect(Collectors.joining(", ")) + ']';
        }

        @Override public boolean remove(Object o) {
            if(isFrozen()) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            int index = indexOf(o);
            if(index == -1) {
                return false;
            }
            remove(index);
            return true;
        }

        @Override public boolean addAll(int index, Collection<? extends E> c) {
            if(isFrozen()) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            if(c.isEmpty()) {
                return false;
            }
            @SuppressWarnings("unchecked") E[] cArray = (E[]) c.toArray();
            final int cSize = cArray.length;
            if(size + cSize >= array.length) {
                realloc(size + cSize);
            }
            if(size != index) {
                System.arraycopy(array, index, array, index + cSize, size - index);
            }
            System.arraycopy(cArray, 0, array, index, cSize);
            size += cSize;
            return true;
        }

        @Override public boolean retainAll(Collection<?> c) {
            if(isFrozen()) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            boolean result = false;
            for(Iterator<E> iterator = this.iterator(); iterator.hasNext(); ) {
                E e = iterator.next();
                if(!c.contains(e)) {
                    iterator.remove();
                    result = true;
                }
            }
            return result;
        }

        @Override public void add(int index, E element) {
            if(isFrozen()) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            if(index < 0 || index > size) {
                throw new IndexOutOfBoundsException();
            }
            if(size != index) {
                System.arraycopy(array, index, array, index + 1, size - index);
            }
            array[index] = element;
            size += 1;
        }

        @Override public E remove(int index) {
            if(isFrozen()) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            if(index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            E elem = array[index];
            size -= 1;
            if(size != index) {
                System.arraycopy(array, index + 1, array, index, size - index);
            }
            array[size] = null;
            return elem;
        }

        @Override public ListIterator<E> listIterator() {
            return new ListIter(0);
        }

        @Override public ListIterator<E> listIterator(int index) {
            if(index < 0 || index > size) {
                throw new IndexOutOfBoundsException();
            }
            return new ListIter(index);
        }

        private class ListIter extends Iter implements ListIterator<E> {
            ListIter(int index) {
                cursor = index;
            }

            public boolean hasPrevious() {
                return cursor > 0;
            }

            public E previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                cursor -= 1;
                lastRet = cursor;
                return array[cursor];
            }

            public int nextIndex() {
                return cursor;
            }

            public int previousIndex() {
                return cursor-1;
            }

            @Override public void set(E e) {
                if (lastRet < 0) {
                    throw new IllegalStateException();
                }
                Transient.this.set(lastRet, e);
            }

            @Override public void add(E e) {
                Transient.this.add(cursor, e);
                cursor += 1;
                lastRet = -1;
            }

        }
    }
}
