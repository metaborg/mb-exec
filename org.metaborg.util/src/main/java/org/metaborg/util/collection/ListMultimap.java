package org.metaborg.util.collection;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import org.metaborg.util.functions.CheckedAction2;

/**
 * Super basic list multimap that cannot shrink.
 * Uses a {@code Map<K, List<V>>} structure, plus an extra list of all values for insertion order
 * traversal through all values.
 * @param <K>
 * @param <V>
 */
public class ListMultimap<K, V> {
    private final Map<K, List<V>> backingMap = new HashMap<>();
    private final List<V> values = new ArrayList<>();

    public int size() {
        return values.size();
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public boolean containsKey(K key) {
        return backingMap.containsKey(key);
    }

    public boolean containsValue(V value) {
        return values.contains(value);
    }

    public List<V> get(K key) {
        return Collections.unmodifiableList(backingMap.getOrDefault(key, Collections.emptyList()));
    }

    public boolean put(K key, V value) {
        values.add(value);
        return backingMap.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }

    public boolean put(K key, List<V> values) {
        values.addAll(values);
        return backingMap.computeIfAbsent(key, k -> new ArrayList<>()).addAll(values);
    }

    public boolean putAll(Map<? extends K, ? extends V> m) {
        boolean changed = false;
        for(Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            changed |= put(entry.getKey(), entry.getValue());
        }
        return changed;
    }

    public boolean putAll(ListMultimap<? extends K, V> mm) {
        final AtomicBoolean changed = new AtomicBoolean(false);
        mm.forEach((key, values) -> {
            changed.set(changed.get() || put(key, values));
        });
        return changed.get();
    }

    public void clear() {
        values.clear();
        backingMap.clear();
    }

    public Set<K> keySet() {
        return backingMap.keySet();
    }

    public Collection<V> values() {
        return Collections.unmodifiableList(values);
    }

    public Stream<Map.Entry<K, V>> entries() {
        return backingMap.entrySet().stream().flatMap(e -> {
            final K k = e.getKey();
            return e.getValue().stream().map(v -> new AbstractMap.SimpleImmutableEntry<>(k, v));
        });
    }

    public <E extends Throwable> void forEach(CheckedAction2<? super K, ? super List<V>, E> action) throws E {
        for (Map.Entry<K, List<V>> entry : backingMap.entrySet()) {
            action.apply(entry.getKey(), Collections.unmodifiableList(entry.getValue()));
        }
    }

    @Override public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;

        ListMultimap<?, ?> that = (ListMultimap<?, ?>) o;

        if(!backingMap.equals(that.backingMap))
            return false;
        if(!values.equals(that.values))
            return false;

        return true;
    }

    @Override public int hashCode() {
        return Objects.hash(backingMap, values);
    }
}
