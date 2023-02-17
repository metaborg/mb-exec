package org.metaborg.util.collection;

import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * A multiset collection. Like a set, but allowing duplicates. So unordered, but with an efficient contains check.
 * Iterator method is not the most efficient, because internally duplicates are just counted.
 * @param <E>
 */
public abstract class Bag<E> implements Collection<E> {
    protected abstract Map<E, Integer> backingMap();

    @Override public boolean isEmpty() {
        return backingMap().isEmpty();
    }

    @Override public boolean contains(Object o) {
        //noinspection SuspiciousMethodCalls
        return backingMap().containsKey(o);
    }

    @Override public Object[] toArray() {
        return toArray(new Object[0]);
    }

    @SuppressWarnings("unchecked") @Override public <T> T[] toArray(T[] a) {
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

    @Override public boolean containsAll(Collection<?> c) {
        if(c instanceof Bag) {
            final Bag<E> other = (Bag<E>) c;
            containsAll(other.backingMap().keySet());
        }
        return c.stream().allMatch(this::contains);
    }

    @Override public Iterator<E> iterator() {
        return backingMap().entrySet().stream().flatMap(entry -> {
            final E e = entry.getKey();
            return Stream.generate(() -> e).limit(entry.getValue());
        }).iterator();
    }

    public int count(E i) {
        return backingMap().getOrDefault(i, 0);
    }

    public Set<E> elementSet() {
        return Collections.unmodifiableSet(this.backingMap().keySet());
    }

    public static final class Immutable<E> extends Bag<E> implements ImmutableCollection<E> {
        private Map<E, Integer> backingMap;
        private int size;

        private Immutable(Map<E, Integer> backingMap, int size) {
            this.backingMap = backingMap;
            this.size = size;
        }

        public static <E> Immutable<E> of() {
            return new Immutable<>(Collections.emptyMap(), 0);
        }

        @Override protected Map<E, Integer> backingMap() {
            return backingMap;
        }

        // INVARIANT: is equal to backingMap.values().stream().mapToInt(i -> i).sum();
        @Override public int size() {
            return size;
        }

        public Transient<E> asTransient() {
            return new Transient<>(new HashMap<>(backingMap), size);
        }
    }

    public static final class Transient<E> extends Bag<E> {
        private Map<E, Integer> backingMap;
        private int size = 0;
        private Immutable<E> frozen;

        private Transient(Map<E, Integer> backingMap, int size) {
            this.backingMap = backingMap;
        }

        public static <E> Transient<E> of() {
            return new Transient<>(new HashMap<>(), 0);
        }

        public static <E> Transient<E> withSizeEstimate(int initialCapacity) {
            return new Transient<>(new HashMap<>(Sets.hashCapacity(initialCapacity)), 0);
        }

        public Map<E, Integer> backingMap() {
            return backingMap;
        }

        /**
         * @param e element whose presence in this collection is to be ensured
         * @return whether the element was added (so always true)
         */
        @Override public boolean add(E e) {
            if(frozen != null) {
                throw new IllegalStateException();
            }
            backingMap.compute(e, (k, v) -> (v == null) ? 1 : v + 1);
            size += 1;
            return true;
        }

        /**
         * @param o element to be removed from this collection, if present
         * @return whether (one of the duplicates of) the element was removed
         */
        @SuppressWarnings("unchecked") @Override public boolean remove(Object o) {
            if(frozen != null) {
                throw new IllegalStateException();
            }
            //noinspection SuspiciousMethodCalls
            if(!backingMap.containsKey(o)) {
                return false;
            }
            backingMap.computeIfPresent((E) o, (k, v) -> (v == 1) ? null : v - 1);
            size -= 1;
            return true;
        }

        @Override public boolean addAll(Collection<? extends E> c) {
            if(frozen != null) {
                throw new IllegalStateException();
            }
            if(c instanceof Bag) {
                final Bag<E> other = (Bag<E>) c;
                for(Map.Entry<E, Integer> e : other.backingMap().entrySet()) {
                    final E key = e.getKey();
                    backingMap.put(key, backingMap.getOrDefault(key, 0) + e.getValue());
                }
            }
            return c.stream().map(this::add).reduce(false, (a, b) -> a || b);
        }

        @Override public boolean removeAll(Collection<?> c) {
            if(frozen != null) {
                throw new IllegalStateException();
            }
            if(c instanceof Bag) {
                final Bag<E> other = (Bag<E>) c;
                for(Map.Entry<E, Integer> e : other.backingMap().entrySet()) {
                    final E key = e.getKey();
                    final int newDuplicates = backingMap.getOrDefault(key, 0) - e.getValue();
                    if(newDuplicates <= 0) {
                        backingMap.remove(key);
                    } else {
                        backingMap.put(key, newDuplicates);
                    }
                }
            }
            return c.stream().map(this::remove).reduce(false, (a, b) -> a || b);
        }

        @SuppressWarnings("unchecked") @Override public boolean retainAll(Collection<?> c) {
            if(frozen != null) {
                throw new IllegalStateException();
            }
            if(c instanceof Bag) {
                final Bag<E> other = (Bag<E>) c;
                boolean changed = false;
                for(Map.Entry<E, Integer> e : backingMap.entrySet()) {
                    final E key = e.getKey();
                    final int newDuplicates = other.backingMap().getOrDefault(key, 0);
                    if(newDuplicates <= 0) {
                        backingMap.remove(key);
                        changed = true;
                    } else {
                        changed |= backingMap.put(key, newDuplicates) == newDuplicates;
                    }
                }
            }
            final Map<E, Integer> newBackingMap = new HashMap<>(Sets.hashCapacity(c.size()));
            int newSize = 0;
            for(Object o : c) {
                if(contains(o)) {
                    E e = (E) o;
                    final Integer duplicates = backingMap.get(e);
                    newBackingMap.put(e, duplicates);
                    newSize += duplicates;
                }
            }
            if(newSize < size()) {
                backingMap = newBackingMap;
                size = newSize;
                return true;
            }
            return false;
        }

        @Override public void clear() {
            if(frozen != null) {
                throw new IllegalStateException();
            }
            backingMap.clear();
        }

        // INVARIANT: is equal to backingMap.values().stream().mapToInt(i -> i).sum();
        @Override public int size() {
            return size;
        }

        public Immutable<E> freeze() {
            if(frozen == null) {
                frozen = new Immutable<>(Collections.unmodifiableMap(backingMap), size);
            }
            return frozen;
        }

        public int setCount(E elem, int count) {
            if(count < 0) {
                throw new IllegalArgumentException("Count cannot be negative");
            }
            final int oldCount = backingMap.getOrDefault(elem, 0);
            if(count == 0) {
                backingMap.remove(elem);
            } else {
                backingMap.put(elem, count);
            }

            size += (count - oldCount);
            return oldCount;
        }

        public int add(E elem, int count) {
            if(count < 0) {
                throw new IllegalArgumentException("Count cannot be negative");
            }
            if(count == 0) {
                return count(elem);
            }
            final int oldCount = backingMap.getOrDefault(elem, 0);
            backingMap.put(elem, oldCount + count);
            size += count;
            return oldCount;
        }

        public int remove(E elem, int count) {
            if(count < 0) {
                throw new IllegalArgumentException("Count cannot be negative");
            }
            if(count == 0) {
                return count(elem);
            }
            final int oldCount = backingMap.getOrDefault(elem, 0);
            if(count > oldCount) {
                backingMap.remove(elem);
                size -= oldCount;
            } else {
                backingMap.put(elem, oldCount - count);
                size -= count;
            }
            return oldCount;
        }
    }
}
