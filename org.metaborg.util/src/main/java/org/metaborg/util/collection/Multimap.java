package org.metaborg.util.collection;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.metaborg.util.functions.CheckedAction2;

/**
 * Super basic multimap that cannot shrink.
 * Uses a {@code HashMap<K, HashSet<V>>} structure.
 * @param <K>
 * @param <V>
 */
public abstract class Multimap<K, V, C extends Collection<V>> {
    protected final Map<K, C> backingMap;

    protected Multimap(Map<K, C> backingMap) {
        this.backingMap = backingMap;
    }

    public Multimap() {
        this(new HashMap<>());
    }

    protected Multimap(Multimap<K, V, C> toCopy) {
        this.backingMap = toCopy.copyOfBackingMap();
    }

    protected Map<K, C> copyOfBackingMap() {
        final HashMap<K, C> kcHashMap = new HashMap<>(this.backingMap);
        kcHashMap.replaceAll((k, c) -> copyCollection(c));
        return kcHashMap;
    }

    protected abstract C newCollection();
    protected abstract C emptyCollection();
    protected abstract C unmodifiableCollection(C collection);
    protected abstract C copyCollection(C collection);

    public int size() {
        return (int) this.backingMap.values().stream().flatMap(Collection::stream).count();
    }

    public boolean isEmpty() {
        return this.backingMap.isEmpty();
    }

    public boolean containsKey(K key) {
        return backingMap.containsKey(key);
    }

    public boolean containsValue(V value) {
        return this.backingMap.values().stream().flatMap(Collection::stream).anyMatch(v -> v.equals(value));
    }

    public boolean containsEntry(K key, V value) {
        return get(key).contains(value);
    }

    public C get(K key) {
        return unmodifiableCollection(backingMap.getOrDefault(key, emptyCollection()));
    }

    public boolean put(K key, V value) {
        return backingMap.computeIfAbsent(key, k -> newCollection()).add(value);
    }

    public boolean putAll(K key, Collection<? extends V> values) {
        boolean allAdded = false;
        for(V value : values) {
            allAdded |= put(key, value);
        }
        return allAdded;
    }

    public boolean putAll(Map<? extends K, ? extends V> m) {
        boolean changed = false;
        for(Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            changed |= put(entry.getKey(), entry.getValue());
        }
        return changed;
    }

    public boolean putAll(Multimap<? extends K, V, C> mm) {
        final AtomicBoolean changed = new AtomicBoolean(false);
        mm.forEach((key, values) -> {
            final boolean localChanged = putAll(key, values);
            changed.compareAndSet(false, localChanged);
        });
        return changed.get();
    }

    public void clear() {
        backingMap.clear();
    }

    public Set<K> keySet() {
        return backingMap.keySet();
    }

    /**
     * @return NON-VIEW collection of values as they are at the time of the call
     */
    public Collection<V> values() {
        // TODO: make this a view instead of a new collection?
        return backingMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * @return stream of entries
     * NOTE: will throw concurrent modification exceptions if you edit the multimap while consuming the stream!
     */
    public Stream<Map.Entry<K, V>> entries() {
        return backingMap.entrySet().stream().flatMap(e -> {
            final K k = e.getKey();
            return e.getValue().stream().map(v -> new AbstractMap.SimpleImmutableEntry<>(k, v));
        });
    }

    public <E extends Throwable> void forEach(CheckedAction2<? super K, ? super C, E> action) throws E {
        for (Map.Entry<K, C> entry : backingMap.entrySet()) {
            action.apply(entry.getKey(), unmodifiableCollection(entry.getValue()));
        }
    }

    @Override public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;

        Multimap<?, ?, ?> that = (Multimap<?, ?, ?>) o;

        if(!backingMap.equals(that.backingMap))
            return false;

        return true;
    }

    @Override public int hashCode() {
        return Objects.hash(backingMap);
    }

    @Override public String toString() {
        return backingMap.toString();
    }
}
