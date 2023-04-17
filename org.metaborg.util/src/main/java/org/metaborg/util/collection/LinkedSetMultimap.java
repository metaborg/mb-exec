package org.metaborg.util.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Super basic set multimap that cannot shrink.
 * Uses a {@code LinkedHashMap<K, HashSet<V>>} structure, plus an extra list of all values for insertion order
 * traversal through all values.
 * @param <K>
 * @param <V>
 */
public class LinkedSetMultimap<K, V> extends LinkedMultimap<K, V, Set<V>> {
    @Override protected Set<V> newCollection() {
        return new HashSet<>();
    }

    @Override protected Set<V> emptyCollection() {
        return Collections.emptySet();
    }

    @Override protected Set<V> unmodifiableCollection(Set<V> collection) {
        return Collections.unmodifiableSet(collection);
    }

    public boolean put(K key, V value) {
        final boolean added = backingMap.computeIfAbsent(key, k -> new HashSet<>()).add(value);
        if(added) {
            values.add(value);
        }
        return added;
    }
}
