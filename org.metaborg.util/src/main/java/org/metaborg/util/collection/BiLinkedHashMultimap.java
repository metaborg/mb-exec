package org.metaborg.util.collection;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.metaborg.util.stream.Collectors2;

/**
 * Bidirectional multimap that cannot hold duplicate key value pairs. Originally used guava's
 * LinkedHashMultimap as backing multimaps. But looks like order of entries is not relevant, while
 * order of keys and order of values as insertion order might be, so this is now using a
 * {@code LinkedHashMap<K, LinkedHashSet<V>>} for the forward map; which is used for the order of
 * the results every method.
 */
public class BiLinkedHashMultimap<K, V> implements BiSetMultimap<K, V>, Serializable {
    public static final long serialVersionUID = 1L;
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
        return keyToValue.containsKey(key) && !get((K) key).isEmpty();
    }

    @Override public boolean containsValue(Object value) {
        return keyToValue.containsValue(value) && !getInverse((V) value).isEmpty();
    }

    @Override public boolean containsEntry(Object key, Object value) {
        return keyToValue.getOrDefault(key, Collections.emptySet()).contains(value);
    }

    @Override public Set<V> get(K key) {
        return keyToValue.getOrDefault(key, Collections.emptySet());
    }

    @Override public Set<K> getInverse(V value) {
        return valueToKey.getOrDefault(value, Collections.emptySet());
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
        removeFromKeyToValue(key, value);
        removeFromValueToKey(key, value);
        return true;
    }

    @Override public Set<V> removeAll(K key) {
        final Set<V> removed = keyToValue.remove(key);
        if(removed == null) {
            return Collections.emptySet();
        }
        for(V r : removed) {
            removeFromValueToKey(key, r);
        }

        return removed;
    }

    @Override public Set<K> removeAllInverse(V value) {
        final Set<K> removed1 = valueToKey.remove(value);
        if(removed1 == null) {
            return Collections.emptySet();
        }
        // reconstruct the insertion order of the removed keys
        final Set<K> removed2 = new LinkedHashSet<>();
        for(Iterator<Entry<K, Set<V>>> iterator = keyToValue.entrySet().iterator(); iterator.hasNext();) {
            Entry<K, Set<V>> e = iterator.next();
            final K k = e.getKey();
            if(removed1.contains(k)) {
                final Set<V> values = e.getValue();
                // N.B. cannot use removeFromKeyToValue: leads to ConcurrentModificationException
                values.remove(value);
                if(values.isEmpty()) {
                    iterator.remove();
                }
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

    private void removeFromKeyToValue(K key, V value) {
        keyToValue.computeIfPresent(key, (k, values) -> {
            values.remove(value);
            return values.isEmpty() ? null : values;
        });
    }

    private void removeFromValueToKey(K key, V value) {
        valueToKey.computeIfPresent(value, (v, keys) -> {
            keys.remove(key);
            return keys.isEmpty() ? null : keys;
        });
    }
}