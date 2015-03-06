package org.metaborg.util.collection;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ListMultimap;

/**
 * Interface for bidirectional multimaps that holds duplicate key-value pairs.
 */
public interface BiListMultimap<K, V> extends BiMultimap<K, V>, ListMultimap<K, V> {
    @Override public abstract Map<V, Collection<K>> asInverseMap();

    @Override public abstract List<K> getInverse(V value);

    @Override public abstract List<K> removeAllInverse(Object value);

    @Override public abstract ListMultimap<K, V> keyToValue();

    @Override public abstract ListMultimap<V, K> valueToKey();
}