package org.metaborg.util.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Super basic list multimap that cannot shrink.
 * Uses a {@code LinkedHashMap<K, ArrayList<V>>} structure, plus an extra list of all values for insertion order
 * traversal through all values.
 * @param <K>
 * @param <V>
 */
public class ListMultimap<K, V> extends LinkedMultimap<K, V, List<V>> {
    @Override protected List<V> newCollection() {
        return new ArrayList<>();
    }

    @Override protected List<V> emptyCollection() {
        return Collections.emptyList();
    }

    @Override protected List<V> unmodifiableCollection(List<V> collection) {
        return Collections.unmodifiableList(collection);
    }

    @Override public boolean put(K key, V value) {
        values.add(value);
        return backingMap.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }
}
