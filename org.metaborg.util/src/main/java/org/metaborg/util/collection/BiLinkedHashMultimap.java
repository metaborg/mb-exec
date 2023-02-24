package org.metaborg.util.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.metaborg.util.stream.Collectors2;

/**
 * Bidirectional multimap that cannot hold duplicate key value pairs. Originally used guava's
 * LinkedHashMultimap as backing multimaps. But looks like order of entries is not relevant, while
 * order of keys and order of values as insertion order might be, so this is now using a
 * {@code LinkedHashMap<K, LinkedHashSet<V>>} for the forward map; which is used for the order of
 * the results every method.
 */
public class BiLinkedHashMultimap<K, V> implements BiSetMultimap<K, V> {
    private final Map<K, Set<V>> keyToValue;
    private final Map<V, Set<K>> valueToKey;

    
    public static <K, V> BiSetMultimap<K, V> create() {
        return new BiLinkedHashMultimap<K, V>();
    }

    public static <K, V> BiSetMultimap<K, V> create(BiSetMultimap<K, V> map) {
        return new BiLinkedHashMultimap<K, V>(map);
    }


    public BiLinkedHashMultimap() {
        keyToValue = new LinkedHashMap<>();
        valueToKey = new HashMap<>();
    }

    public BiLinkedHashMultimap(BiSetMultimap<K, V> map) {
        keyToValue = new LinkedHashMap<>(map.keyToValue());
        valueToKey = new HashMap<>(map.valueToKey());
    }


    @Override public void clear() {
        keyToValue.clear();
        valueToKey.clear();
    }

    @Override public boolean containsKey(Object key) {
        return keyToValue.containsKey(key);
    }

    @Override public boolean containsValue(Object value) {
        return keyToValue.containsValue(value);
    }

    @Override public boolean containsEntry(Object key, Object value) {
        return keyToValue.getOrDefault(key, Collections.emptySet()).contains(value);
    }

    @Override public Set<V> get(K key) {
        return keyToValue.get(key);
    }

    @Override public Set<K> getInverse(V value) {
        return valueToKey.get(value);
    }

    @Override public boolean isEmpty() {
        return keyToValue.isEmpty();
    }

    @Override public Set<K> keySet() {
        return keyToValue.keySet();
    }

    @Override public boolean put(K key, V value) {
        return keyToValue.computeIfAbsent(key, k -> new LinkedHashSet<>()).add(value)
            & valueToKey.computeIfAbsent(value, v -> new HashSet<>()).add(key);
    }

    @Override public boolean putAll(K key, Iterable<? extends V> values) {
        boolean result = true;

        for(V value : values) {
            result &= put(key, value);
        }

        return result;
    }

    @Override public boolean remove(K key, V value) {
        if(!containsEntry(key, value)) {
            return false;
        }
        keyToValue.computeIfPresent(key, (k, values) -> {
            values.remove(value);
            return values.isEmpty() ? null : values;
        });
        valueToKey.computeIfPresent(value, (v, keys) -> {
            keys.remove(key);
            return keys.isEmpty() ? null : keys;
        });
        return true;
    }

    @Override public Set<V> removeAll(K key) {
        final Set<V> removed = keyToValue.remove(key);
        for(V r : removed) {
            valueToKey.remove(r, key);
        }

        return removed;
    }

    @Override public Set<K> removeAllInverse(V value) {
        final Set<K> removed1 = valueToKey.remove(value);
        final Set<K> removed2 = new LinkedHashSet<>();
        for(K k : keyToValue.keySet()) {
            if(removed1.contains(k)) {
                keyToValue.remove(k, value);
                removed2.add(k);
            }
        }

        return removed2;
    }

    @Override public int size() {
        return keyToValue.size();
    }

    @Override public Set<V> values() {
        return keyToValue.values().stream().flatMap(Set::stream)
            .collect(Collectors2.toLinkedHashSet());
    }

    @Override public Map<K, Set<V>> keyToValue() {
        return Collections.unmodifiableMap(keyToValue);
    }

    @Override public Map<V, Set<K>> valueToKey() {
        return Collections.unmodifiableMap(valueToKey);
    }
}