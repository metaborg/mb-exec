package org.metaborg.util.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.metaborg.util.stream.Collectors2;

/**
 * Mutable/Immutable List interface ala capsule.
 * Does not allow null values.
 * @param <E>
 */
public abstract class ImList<E> implements List<E> {
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
        private final List<E> array;
        private final int size;

        Immutable(E[] array, int size) {
            // TODO: replace with raw array, which can be larger than size and therefore we can't dispatch to List<E>.toArray
            this.array = Arrays.asList(array);
            this.size = size;
        }

        @SuppressWarnings("unchecked") public static <E> ImList.Immutable<E> of() {
            return new Immutable<>((E[]) new Object[] {}, 0);
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

        @Override public int size() {
            return size;
        }

        @Override public boolean isEmpty() {
            return size == 0;
        }

        @Override public boolean contains(Object o) {
            Objects.requireNonNull(o);
            return array.contains(o);
        }

        @Override public Iterator<E> iterator() {
            return array.iterator();
        }

        @Override public <T> T[] toArray(T[] a) {
            return array.toArray(a);
        }

        @Override public boolean containsAll(Collection<?> c) {
            Objects.requireNonNull(c);
            //noinspection SlowListContainsAll
            return array.containsAll(c);
        }

        @Override public E get(int index) {
            if(index >= size) {
                throw new IndexOutOfBoundsException();
            }
            return array.get(index);
        }

        @Override public int indexOf(Object o) {
            Objects.requireNonNull(o);
            return array.indexOf(o);
        }

        @Override public int lastIndexOf(Object o) {
            Objects.requireNonNull(o);
            return array.lastIndexOf(o);
        }

        @Override public ListIterator<E> listIterator() {
            return array.listIterator();
        }

        @Override public ListIterator<E> listIterator(int index) {
            return array.listIterator(index);
        }

        @Override public List<E> subList(int fromIndex, int toIndex) {
            return Collections.unmodifiableList(array.subList(fromIndex, toIndex));
        }

        @SuppressWarnings("unchecked") public Transient<E> asTransient() {
            return new Transient<>((E[]) array.toArray());
        }
    }

    public static class Transient<E> extends ImList<E> {
        private E[] array;
        private int size = 0;
        private boolean frozen = false;

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

        public static <E> Transient<E> of() {
            return new Transient<>(0);
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
            return Arrays.stream(array, 0, size).iterator();
        }

        @SuppressWarnings("unchecked") @Override public <T> T[] toArray(T[] a) {
            if(a.length < size) {
                return (T[]) Arrays.copyOf(array, size, a.getClass());
            }
            System.arraycopy(array, 0, a, 0, size);
            if (a.length > size)
                a[size] = null;
            return a;
        }

        @Override public boolean add(E e) {
            if(frozen) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            if(size == array.length) {
                array = Arrays.copyOf(array, array.length * 2);
            }
            array[size] = e;
            size += 1;
            return true;
        }

        @Override public boolean remove(Object o) {
            if(frozen) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            // TODO if necessary
            throw new UnsupportedOperationException();
        }

        @Override public boolean containsAll(Collection<?> c) {
            Objects.requireNonNull(c);
            return c.stream().allMatch(this::contains);
        }

        @Override public boolean addAll(Collection<? extends E> c) {
            if(frozen) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            return c.stream().map(this::add).reduce(false, (a, b) -> a || b);
        }

        @Override public boolean addAll(int index, Collection<? extends E> c) {
            if(frozen) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            // TODO if necessary
            throw new UnsupportedOperationException();
        }

        @Override public boolean removeAll(Collection<?> c) {
            if(frozen) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            return c.stream().map(this::remove).reduce(false, (a, b) -> a || b);
        }

        @Override public boolean retainAll(Collection<?> c) {
            if(frozen) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            // TODO if necessary
            throw new UnsupportedOperationException();
        }

        @Override public void clear() {
            if(frozen) {
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
            if(frozen) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            if(index >= size) {
                throw new IndexOutOfBoundsException();
            }
            E prev = array[index];
            array[index] = element;
            return prev;
        }

        @Override public void add(int index, E element) {
            if(frozen) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            // TODO if necessary
            throw new UnsupportedOperationException();
        }

        @Override public E remove(int index) {
            if(frozen) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            // TODO if necessary
            throw new UnsupportedOperationException();
        }

        @Override public int indexOf(Object o) {
            if(frozen) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            // TODO if necessary
            throw new UnsupportedOperationException();
        }

        @Override public int lastIndexOf(Object o) {
            if(frozen) {
                throw new IllegalStateException("Transient frozen into Immutable already");
            }
            // TODO if necessary
            throw new UnsupportedOperationException();
        }

        @Override public ListIterator<E> listIterator() {
            // TODO if necessary
            throw new UnsupportedOperationException();
        }

        @Override public ListIterator<E> listIterator(int index) {
            // TODO if necessary
            throw new UnsupportedOperationException();
        }

        @Override public List<E> subList(int fromIndex, int toIndex) {
            // TODO if necessary
            throw new UnsupportedOperationException();
        }

        public Immutable<E> freeze() {
            frozen = true;
            return new Immutable<>(array, size);
        }
    }
}
