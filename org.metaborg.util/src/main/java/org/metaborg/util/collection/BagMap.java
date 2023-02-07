package org.metaborg.util.collection;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * A collection like a map, but which allows multiple values. Unordered, but with an efficient lookup.
 * Size, iterator, and containsValue methods are not the most efficient, because internally duplicates are just counted.
 * @param <K>
 * @param <V>
 */
public class BagMap<K,V> {
    private final Map<K, Bag<V>> backingMap;

    public BagMap() {
        this.backingMap = new HashMap<>();
    }

    public int size() {
        return backingMap.values().stream().mapToInt(Bag::size).sum();
    }

    public boolean isEmpty() {
        return backingMap.isEmpty();
    }

    public boolean containsKey(Object key) {
        return backingMap.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return backingMap.containsValue(value);
    }

    public Bag<V> get(Object key) {
        return backingMap.get(key);
    }

    public void put(K key, V value) {
        backingMap.computeIfAbsent(key, k -> new Bag<>()).add(value);
    }

    public boolean remove(K key, V value) {
        final AtomicBoolean present = new AtomicBoolean(false);
        backingMap.computeIfPresent(key, (k, v) -> {
            present.set(v.remove(value));
            return v.isEmpty() ? null : v;
        });
        return present.get();
    }

    public Bag<V> removeAll(Object key) {
        return backingMap.remove(key);
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        for(Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public void clear() {
        backingMap.clear();
    }

    public Set<K> keySet() {
        return backingMap.keySet();
    }

    public Collection<V> values() {
        return backingMap.values().stream().flatMap(Bag::stream).collect(Collectors.toList());
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return backingMap.entrySet().stream().flatMap(e -> e.getValue().stream().map(v -> new AbstractMap.SimpleImmutableEntry<>(e.getKey(), v))).collect(
            Collectors.toSet());
    }
}
