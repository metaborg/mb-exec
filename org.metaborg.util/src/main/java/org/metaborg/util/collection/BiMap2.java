package org.metaborg.util.collection;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Super basic bidirectional map with two maps. Both keys and values are unique.
 * @param <K>
 * @param <V>
 */
public class BiMap2<K, V> implements Serializable {
    public static final long serialVersionUID = 1L;

    private final Map<K, V> fwd;
    private final Map<V, K> bwd;

    // N.B. LinkedHashMap for proper serializability that roundtrips into the same map
    public BiMap2() {
        this(new LinkedHashMap<>(), new LinkedHashMap<>());
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
        return Collections.unmodifiableSet(fwd.keySet());
    }

    public Set<V> valueSet() {
        return Collections.unmodifiableSet(bwd.keySet());
    }

    public Set<V> values() {
        return valueSet();
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return Collections.unmodifiableSet(fwd.entrySet());
    }

    public Map<K, V> asMap() {
        return Collections.unmodifiableMap(fwd);
    }

    public V remove(K key) {
        if(!containsKey(key)) {
            return null;
        }
        final V removed = fwd.remove(key);
        final K removedKey = bwd.remove(removed);
        assert key.equals(removedKey): "BiMap2 maps got desynchronised.";
        return removed;
    }

    public boolean remove(K key, V value) {
//        if(!containsEntry(key, value)) {
//            return false;
//        }
        // using short-circuit logical AND to not even try removing from bwd if it's not in fwd.
        final boolean fwdRemoval = fwd.remove(key, value);
        final boolean bwdRemoval = bwd.remove(value, key);
        assert fwdRemoval == bwdRemoval : "BiMap2 maps got desynchronised.";
        return fwdRemoval;
    }

    public K removeValue(V value) {
        if(!containsValue(value)) {
            return null;
        }
        final K removed = bwd.remove(value);
        final V removedValue = fwd.remove(removed);
        assert value.equals(removedValue): "BiMap2 maps got desynchronised.";
        return removed;
    }

    /**
     * @throws IllegalArgumentException when value is already set to a different key
     */
    public V put(K key, V value) {
        if(containsEntry(key, value)) {
            return value;
        }
        if(bwd.containsKey(value)) {
            throw new IllegalArgumentException("Value already present.");
        }
        final V oldValue = fwd.put(key, value);
        if(oldValue != null){
            final boolean removed = bwd.remove(oldValue, key);
            assert removed: "BiMap2 maps got desynchronised.";
        }
        bwd.put(value, key);
        return oldValue;
    }

    public boolean putAll(BiMap2<K, V> other) {
        return putAll(other.entrySet());
    }

    public boolean putAll(Iterable<Map.Entry<K, V>> entries) {
        boolean changed = false;
        for(Map.Entry<K, V> e : entries) {
            changed |= put(e.getKey(), e.getValue()) != null;
        }
        return changed;
    }

    /**
     * Replaces the entry for the specified key-value pair if key is currently mapped to something.
     * @throws IllegalArgumentException when value is already set to a different key
     */
    public V replace(K key, V value) {
        if(!fwd.containsKey(key)) {
            return null;
        }
        return put(key, value);
    }

    public BiMap2<V, K> inverse() {
        return new BiMap2<>(bwd, fwd);
    }

    public void clear() {
        fwd.clear();
        bwd.clear();
    }
}
