package org.metaborg.util.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.SetMultimap;

/**
 * Interface for bidirectional multimaps that cannot hold duplicate key-value pairs.
 */
public interface BiSetMultimap<K, V> extends BiMultimap<K, V>, SetMultimap<K, V> {
    @Override public abstract Map<V, Collection<K>> asInverseMap();

    @Override public abstract Set<K> getInverse(V value);

    @Override public abstract Set<K> removeAllInverse(Object value);

    @Override public abstract SetMultimap<K, V> keyToValue();

    @Override public abstract SetMultimap<V, K> valueToKey();
}