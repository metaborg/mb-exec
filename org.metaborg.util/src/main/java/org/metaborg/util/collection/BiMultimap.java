package org.metaborg.util.collection;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface for bidirectional multimaps.
 */
public interface BiMultimap<K, V> {
    Set<K> getInverse(V value);

    int size();

    Collection<V> values();

    boolean containsKey(Object key);

    boolean containsValue(Object value);

    boolean containsEntry(Object key, Object value);

    Set<V> get(K key);

    boolean isEmpty();

    Set<K> keySet();

    boolean put(K key, V value);

    boolean remove(K key, V value);

    boolean putAll(K key, Iterable<? extends V> values);

    void clear();
}
