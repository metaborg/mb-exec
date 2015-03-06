package org.metaborg.util.collection;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Multimap;

/**
 * Interface for bidirectional multimaps.
 */
public interface BiMultimap<K, V> {
    public abstract Map<V, Collection<K>> asInverseMap();

    public abstract Collection<K> getInverse(V value);

    public abstract Collection<K> removeAllInverse(Object value);

    public abstract Multimap<K, V> keyToValue();

    public abstract Multimap<V, K> valueToKey();
}
