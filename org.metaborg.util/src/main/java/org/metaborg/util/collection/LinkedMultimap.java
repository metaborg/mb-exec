package org.metaborg.util.collection;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import org.metaborg.util.functions.CheckedAction2;

/**
 * Super basic multimap that cannot shrink.
 * Uses a {@code LinkedHashMap<K, HashSet<V>>} structure, plus an extra list of all values for insertion order
 * traversal through all values.
 * @param <K>
 * @param <V>
 */
public abstract class LinkedMultimap<K, V, C extends Collection<V>> extends Multimap<K, V, C> {
    protected final List<V> values;

    public LinkedMultimap() {
        this(new LinkedHashMap<>(), new ArrayList<>());
    }

    protected LinkedMultimap(Map<K, C> backingMap, List<V> values) {
        super(backingMap);
        this.values = values;
    }

    public LinkedMultimap(LinkedMultimap<K, V, C> toCopy) {
        this(toCopy.backingMap, toCopy.values);
    }

    @Override public int size() {
        return this.values.size();
    }

    @Override public boolean isEmpty() {
        return this.values.isEmpty();
    }

    @Override public boolean containsValue(V value) {
        return this.values.contains(value);
    }

    public void clear() {
        backingMap.clear();
        values.clear();
    }

    public Collection<V> values() {
        return Collections.unmodifiableList(values);
    }

    @Override public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;

        LinkedSetMultimap<?, ?> that = (LinkedSetMultimap<?, ?>) o;

        if(!backingMap.equals(that.backingMap))
            return false;
        if(!values.equals(that.values))
            return false;

        return true;
    }

    @Override public int hashCode() {
        return Objects.hash(backingMap, values);
    }
}
