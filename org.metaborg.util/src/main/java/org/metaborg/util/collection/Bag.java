package org.metaborg.util.collection;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * A collection like a set, but which allows multiples. So unordered, but with an efficient contains check.
 * Size and iterator methods are not the most efficient, because internally duplicates are just counted.
 * @param <E>
 */
public class Bag<E> extends AbstractCollection<E> {
    private Map<E, Integer> backingMap;

    public Bag() {
        this.backingMap = new HashMap<>();
    }
    public Bag(int initialCapacity) {
        this.backingMap = new HashMap<>(initialCapacity);
    }

    @Override public boolean isEmpty() {
        return backingMap.isEmpty();
    }

    @Override public boolean contains(Object o) {
        //noinspection SuspiciousMethodCalls
        return backingMap.containsKey(o);
    }

    @Override public Object[] toArray() {
        return toArray(new Object[0]);
    }

    @SuppressWarnings("unchecked") @Override public <T> T[] toArray(T[] a) {
        if(a.length <= size()) {
            E[] b = (E[]) a;
            int i = 0;
            for(E e : this) {
                b[i] = e;
            }
            return (T[]) b;
        }
        return (T[]) StreamSupport.stream(spliterator(), false).toArray();
    }

    /**
     * @param e element whose presence in this collection is to be ensured
     * @return whether the element was added (so always true)
     */
    @Override public boolean add(E e) {
        backingMap.compute(e, (k, v) -> (v == null) ? 1 : v + 1);
        return true;
    }

    /**
     * @param o element to be removed from this collection, if present
     * @return whether (one of the duplicates of) the element was removed
     */
    @SuppressWarnings("unchecked") @Override public boolean remove(Object o) {
        //noinspection SuspiciousMethodCalls
        if(!backingMap.containsKey(o)) {
            return false;
        }
        backingMap.computeIfPresent((E) o, (k, v) -> (v == 1) ? null : v - 1);
        return true;
    }

    @Override public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    @Override public boolean addAll(Collection<? extends E> c) {
        return c.stream().map(this::add).reduce(false, (a, b) -> a || b);
    }

    @Override public boolean removeAll(Collection<?> c) {
        return c.stream().map(this::remove).reduce(false, (a, b) -> a || b);
    }

    @SuppressWarnings("unchecked") @Override public boolean retainAll(Collection<?> c) {
        final Map<E, Integer> newBackingMap = new HashMap<>(c.size());
        for(Object o : c) {
            if(contains(o)) {
                E e = (E) o;
                newBackingMap.put(e, backingMap.get(e));
            }
        }
        if(newBackingMap.size() < backingMap.size()) {
            backingMap = newBackingMap;
            return true;
        }
        return false;
    }

    @Override public void clear() {
        backingMap.clear();
    }

    @Override public int size() {
        return backingMap.values().stream().mapToInt(i -> i).sum();
    }

    @Override public Iterator<E> iterator() {
        return backingMap.entrySet().stream().flatMap(entry -> {
            final E e = entry.getKey();
            return Stream.generate(() -> e).limit(entry.getValue());
        }).iterator();
    }

}
