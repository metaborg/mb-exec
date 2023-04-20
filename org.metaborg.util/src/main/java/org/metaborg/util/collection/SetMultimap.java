package org.metaborg.util.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
public class SetMultimap<K, V> extends Multimap<K, V, Set<V>> implements Serializable {
    public static final long serialVersionUID = 1L;

    public SetMultimap() {
        super();
    }

    public SetMultimap(SetMultimap<K, V> toCopy) {
        super(toCopy);
    }

    // N.B. LinkedHashSet for proper serializability that roundtrips into the same set
    @Override protected Set<V> newCollection() {
        return new LinkedHashSet<>();
    }

    @Override protected Set<V> emptyCollection() {
        return Collections.emptySet();
    }

    @Override protected Set<V> unmodifiableCollection(Set<V> collection) {
        return Collections.unmodifiableSet(collection);
    }

    @Override protected Set<V> copyCollection(Set<V> collection) {
        return new LinkedHashSet<V>(collection);
    }

    public boolean remove(K key, V value) {
        final Set<V> values = backingMap.get(key);
        final boolean removed = values.remove(value);
        if(values.isEmpty()) {
            backingMap.remove(key);
        }
        return removed;
    }

    /**
     * DANGER: this method *clears* the original set this key pointed to. So if you use the pattern
     * {@code values = smm.get(key); smm.remove(); } you will have an empty set in the values
     * variable!
     * Use the result of this method instead to get the set of values that the key pointed to, it's
     * a copy of the one that was in this multimap.
     * (This behaviour is required by org.spoofax.interpreter.library.index.tests.IndexSymbolTableTest)
     */
    public Set<V> remove(K key) {
        final Set<V> remove = backingMap.remove(key);
        if(remove != null) {
            final Set<V> copyToReturn = copyCollection(remove);
            remove.clear();
            return copyToReturn;
        }
        return Collections.emptySet();
    }

    public Set<V> replaceValues(K key, Set<V> values) {
        if(values.isEmpty()) {
            return remove(key);
        }
        return backingMap.replace(key, copyCollection(values));
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
                return EnumSet.of(Characteristics.IDENTITY_FINISH);
            }
        };
    }
}
