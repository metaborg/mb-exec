package org.metaborg.util.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.BiMap;

/**
 * Super basic bidirectional map with two maps. Both keys and values are unique.
 * @param <K>
 * @param <V>
 */
public class BiMap2<K, V> {
    private final Map<K, V> fwd;
    private final Map<V, K> bwd;

    public BiMap2() {
        this(new HashMap<>(), new HashMap<>());
    }

    protected BiMap2(Map<K, V> fwd, Map<V, K> bwd) {
        this.fwd = fwd;
        this.bwd = bwd;
    }

    public BiMap2(BiMap2<K, V> toCopy) {
        this(new HashMap<>(toCopy.fwd), new HashMap<>(toCopy.bwd));
    }

    public boolean containsKey(K key) {
        return fwd.containsKey(key);
    }

    public boolean containsValue(V value) {
        return bwd.containsKey(value);
    }

    public boolean containsEntry(K key, V value) {
        return fwd.containsKey(key) && bwd.containsKey(value);
    }

    public int size() {
        return fwd.size();
    }

    public boolean isEmpty() {
        return fwd.isEmpty();
    }

    public V get(K key) {
        return getValue(key);
    }

    public K getKey(V value) {
        return bwd.get(value);
    }

    public V getValue(K key) {
        return fwd.get(key);
    }

    public Set<K> keySet() {
        return fwd.keySet();
    }

    public Set<V> valueSet() {
        return bwd.keySet();
    }

    public Set<V> values() {
        return valueSet();
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return fwd.entrySet();
    }

    public Map<K, V> asMap() {
        return Collections.unmodifiableMap(fwd);
    }

    public V remove(K key) {
        if(!containsKey(key)) {
            return null;
        }
        final V remove = fwd.remove(key);
        bwd.remove(remove);
        return remove;
    }

    public boolean remove(K key, V value) {
        // using short-circuit logical AND to not even try removing from bwd if it's not in fwd.
        return fwd.remove(key, value) && bwd.remove(value, key);
    }

    public boolean removeValue(V value) {
        if(!containsValue(value)) {
            return false;
        }
        fwd.remove(bwd.remove(value));
        return true;
    }

    public boolean canPut(K key, V value) {
        if(fwd.containsKey(key) && !fwd.get(key).equals(value)) {
            return false;
        }
        if(bwd.containsKey(value) && !bwd.get(value).equals(key)) {
            return false;
        }
        return true;
    }

    /**
     * @throws IllegalArgumentException when key or value is already set
     */
    public boolean put(K key, V value) {
        if(containsEntry(key, value)) {
            return false;
        }
        if(!canPut(key, value)) {
            throw new IllegalArgumentException("Key or value already set.");
        }
        fwd.put(key, value);
        bwd.put(value, key);
        return true;
    }

    public boolean putAll(BiMap2<K, V> other) {
        return putAll(other.entrySet());
    }

    public boolean putAll(Iterable<Map.Entry<K, V>> entries) {
        boolean changed = false;
        for(Map.Entry<K, V> e : entries) {
            changed |= put(e.getKey(), e.getValue());
        }
        return changed;
    }

    /**
     * Replaces the entry for the specified key only if it is currently mapped to some value.
     */
    public V replace(K key, V value) {
        if(!containsKey(key)) {
            return null;
        }
        final V remove = remove(key);
        put(key, value);
        return remove;
    }

    public BiMap2<V, K> inverse() {
        return new BiMap2<>(bwd, fwd);
    }
}
