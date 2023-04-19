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
        final V removed = fwd.remove(key);
        bwd.remove(removed);
        return removed;
    }

    public boolean remove(K key, V value) {
        // using short-circuit logical AND to not even try removing from bwd if it's not in fwd.
        return fwd.remove(key, value) && bwd.remove(value, key);
    }

    public K removeValue(V value) {
        if(!containsValue(value)) {
            return null;
        }
        final K removed = bwd.remove(value);
        fwd.remove(removed);
        return removed;
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

    public boolean putOrReplace(K key, V value) {
        if(containsEntry(key, value)) {
            return false;
        }
        remove(key);
        removeValue(value);
        put(key, value);
        return true;
    }

    public boolean putOrReplaceAll(BiMap2<K, V> other) {
        return putOrReplaceAll(other.entrySet());
    }

    public boolean putOrReplaceAll(Iterable<Map.Entry<K, V>> entries) {
        boolean changed = false;
        for(Map.Entry<K, V> e : entries) {
            changed |= putOrReplace(e.getKey(), e.getValue());
        }
        return changed;
    }

    /**
     * Replaces the entry for the specified key-value pair either is currently mapped to something.
     */
    public boolean replace(K key, V value) {
        if(containsEntry(key, value)) {
            return false;
        }
        boolean removed = remove(key) != null;
        removed |= removeValue(value) != null;
        if(removed) {
            put(key, value);
            return true;
        }
        return false;
    }

    public BiMap2<V, K> inverse() {
        return new BiMap2<>(bwd, fwd);
    }

    public void clear() {
        fwd.clear();
        bwd.clear();
    }
}
