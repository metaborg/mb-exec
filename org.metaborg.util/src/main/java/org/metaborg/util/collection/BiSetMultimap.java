package org.metaborg.util.collection;

import java.util.Map;
import java.util.Set;

/**
 * Interface for bidirectional multimaps that cannot hold duplicate key-value pairs.
 */
public interface BiSetMultimap<K, V> extends BiMultimap<K, V> {
    @Override Set<K> getInverse(V value);

    @Override Set<V> values();

    Map<K, Set<V>> keyToValue();

    Map<V, Set<K>> valueToKey();

    Set<K> removeAllInverse(V value);

    Set<V> removeAll(K key);
}