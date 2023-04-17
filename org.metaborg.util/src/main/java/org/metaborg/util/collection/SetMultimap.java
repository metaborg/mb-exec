package org.metaborg.util.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Super basic set multimap.
 * Uses a {@code HashMap<K, HashSet<V>>} structure.
 * @param <K>
 * @param <V>
 */
public class SetMultimap<K, V> extends Multimap<K, V, Set<V>> {
    public SetMultimap() {
        super();
    }

    public SetMultimap(SetMultimap<K, V> toCopy) {
        super(toCopy);
    }

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
        return added;
    }

    public boolean remove(K key, V value) {
        final Set<V> values = backingMap.get(key);
        final boolean removed = values.remove(value);
        if(values.isEmpty()) {
            backingMap.remove(key);
        }
        return removed;
    }

    public Set<V> remove(K key) {
        final Set<V> remove = backingMap.remove(key);
        if(remove != null) {
            remove.clear();
            return remove;
        }
        return Collections.emptySet();
    }

    public Set<V> replaceValues(K key, Set<V> values) {
        return backingMap.replace(key, values);
    }

    public static <K, V> Collector<Map.Entry<K, V>, SetMultimap<K, V>, SetMultimap<K, V>> collector() {
        return new Collector<Map.Entry<K, V>, SetMultimap<K, V>, SetMultimap<K, V>>() {
            @Override public Supplier<SetMultimap<K, V>> supplier() {
                return SetMultimap::new;
            }

            @Override public BiConsumer<SetMultimap<K, V>, Map.Entry<K, V>> accumulator() {
                return (m, e) -> m.put(e.getKey(), e.getValue());
            }

            @Override public BinaryOperator<SetMultimap<K, V>> combiner() {
                return (m1, m2) -> {
                    m1.putAll(m2);
                    return m1;
                };
            }

            @Override public Function<SetMultimap<K, V>, SetMultimap<K, V>> finisher() {
                return Function.identity();
            }

            @Override public Set<Characteristics> characteristics() {
                return Collections.emptySet();
            }
        };
    }
}
