package org.metaborg.util.collection;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import org.metaborg.util.functions.CheckedAction2;
import org.metaborg.util.log.PrintlineLogger;

/**
 * Super basic multimap that cannot shrink.
 * Uses a {@code LinkedHashMap<K, HashSet<V>>} structure, plus an extra list of all values for insertion order
 * traversal through all values.
 * @param <K>
 * @param <V>
 */
public abstract class LinkedMultimap<K, V, C extends Collection<V>> extends Multimap<K, V, C> {

    private static final PrintlineLogger plLogger = PrintlineLogger.logger(LinkedMultimap.class);

    protected final List<V> values;

    public LinkedMultimap() {
        this(new LinkedHashMap<>(), new ArrayList<>());
    }

    protected LinkedMultimap(Map<K, C> backingMap, List<V> values) {
        super(backingMap);
        this.values = values;
    }

    public LinkedMultimap(LinkedMultimap<K, V, C> toCopy) {
        this(toCopy.copyOfBackingMap(), new ArrayList<>(toCopy.values));
    }

    @Override
    protected Map<K, C> copyOfBackingMap() {
        final HashMap<K, C> kcHashMap = new LinkedHashMap<>(this.backingMap);
        kcHashMap.replaceAll((k, c) -> copyCollection(c));
        return kcHashMap;
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

    @Override public boolean put(K key, V value) {
        if(super.put(key, value)) {
            plLogger.debug("add value {}", value);
            plLogger.debug("old values: {}", values);
            values.add(value);
            plLogger.debug("new values: {}", values);
            return true;
        }
        return false;
    }

    @Override public void clear() {
        backingMap.clear();
        values.clear();
    }

    @Override public Collection<V> values() {
        return Collections.unmodifiableList(values);
    }

    // we do not test equality(/hashcode) of values, multimaps are just mappings, ordering is not taken into account
}
