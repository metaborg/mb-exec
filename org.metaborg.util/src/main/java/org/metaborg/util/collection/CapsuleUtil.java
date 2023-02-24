package org.metaborg.util.collection;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import org.metaborg.util.functions.Function1;
import org.metaborg.util.functions.Function2;
import org.metaborg.util.functions.Predicate1;

import io.usethesource.capsule.Map;
import io.usethesource.capsule.Set;
import io.usethesource.capsule.SetMultimap;

public final class CapsuleUtil {

    private CapsuleUtil() {
    }

    /**
     * Transforms a map using a function that returns a map entry,
     * and returns the resulting immutable map.
     *
     * @param map the map to transform
     * @param f the function to apply to each entry
     * @return the resulting entries
     * @param <K1> the type of the keys of the input map
     * @param <V1> the type of the values of the input map
     * @param <K2> the type of the keys of the output map
     * @param <V2> the type of the values of the output map
     */
    public static <K1, V1, K2, V2> Map.Immutable<K2, V2> mapEntries(Map<K1, V1> map, BiFunction<K1, V1, Entry<K2, V2>> f) {
        final Map.Transient<K2, V2> newMap = Map.Transient.of();
        map.forEach((k, v) ->
        {
            final Map.Entry<K2, V2> newEntry = f.apply(k, v);
            newMap.__put(newEntry.getKey(), newEntry.getValue());
        });
        return newMap.freeze();
    }


    /**
     * Replace the entry's value, or keep original value if the function returns `null`.
     */
    public static <K, V> void updateValues(Map.Transient<K, V> map, Function2<K, V, V> mapper) {
        for(Entry<K, V> entry : map.entrySet()) {
            final K key = entry.getKey();
            final V val = mapper.apply(key, entry.getValue());
            if(val != null) {
                map.__put(key, val);
            }
        }
    }

    /**
     * Replace the entry's value, or remove entry if the function returns `null`.
     */
    public static <K, V> void updateValuesOrRemove(Map.Transient<K, V> map, Function2<K, V, V> mapper) {
        for(Entry<K, V> entry : map.entrySet()) {
            final K key = entry.getKey();
            final V val = mapper.apply(key, entry.getValue());
            if(val != null) {
                map.__put(key, val);
            } else {
                map.__remove(key);
            }
        }
    }

    /**
     * Replace the entry's key, or keep original value if the function returns `null`.
     */
    public static <K, V> void updateKeys(Map.Transient<K, V> map, Function2<K, V, K> mapper) {
        for(Entry<K, V> entry : map.entrySet()) {
            final K key = entry.getKey();
            final K newKey = mapper.apply(key, entry.getValue());
            if(newKey != null) {
                map.__remove(key);
                map.__put(newKey, entry.getValue());
            }
        }
    }

    /**
     * Filter the map by key.
     */
    public static <K, V> void filter(Map.Transient<K, V> map, Predicate1<K> filter) {
        for(Entry<K, V> entry : map.entrySet()) {
            final K key = entry.getKey();
            if(!filter.test(key)) {
                map.__remove(key);
            }
        }
    }

    /**
     * Replace the entry's key, or remove entry if the function returns `null`.
     */
    public static <K, V> void updateKeysOrRemove(Map.Transient<K, V> map, Function2<K, V, K> mapper) {
        for(Entry<K, V> entry : map.entrySet()) {
            final K key = entry.getKey();
            final K newKey = mapper.apply(key, entry.getValue());
            map.__remove(key);
            if(newKey != null) {
                map.__put(newKey, entry.getValue());
            }
        }
    }

    /**
     * Replace the set values, or keep if the function returns `null`.
     */
    public static <K, V> void update(Set.Transient<V> set, Function1<V, V> mapper) {
        for(V val : set) {
            final V newVal = mapper.apply(val);
            if(newVal != null) {
                set.__remove(val);
                set.__insert(newVal);
            }
        }
    }

    /**
     * Replace the set values, or remove if the function returns `null`.
     */
    public static <K, V> void updateOrRemove(Set.Transient<V> set, Function1<V, V> mapper) {
        for(V val : set) {
            final V newVal = mapper.apply(val);
            set.__remove(val);
            if(newVal != null) {
                set.__insert(newVal);
            }
        }
    }

    @SuppressWarnings("unchecked") public static <V> Set.Immutable<V> toSet(Iterable<? extends V> values) {
        if(values instanceof Set.Immutable) {
            return (Set.Immutable<V>) values;
        }
        final Set.Transient<V> set = transientSet();
        for(V v : values) {
            set.__insert(v);
        }
        return set.freeze();
    }

    @SuppressWarnings("unchecked") public static <V> Set.Immutable<V> toSet(V... values) {
        final Set.Transient<V> set = transientSet();
        for(V v : values) {
            set.__insert(v);
        }
        return set.freeze();
    }

    @SuppressWarnings("unchecked") public static <K, V> Map.Immutable<K, V>
            toMap(java.util.Map<? extends K, ? extends V> map) {
        if(map instanceof Map.Immutable) {
            return (Map.Immutable<K, V>) map;
        }
        return (Map.Immutable<K, V>) Map.Immutable.of().__putAll(map);
    }

    public static <K, V> Map.Immutable<K, V> toMap(Iterable<? extends Entry<? extends K, ? extends V>> entries) {
        final Map.Transient<K, V> map = Map.Transient.of();
        for(Entry<? extends K, ? extends V> e : entries) {
            map.__put(e.getKey(), e.getValue());
        }
        return map.freeze();
    }

    @SuppressWarnings("unchecked") public static <K, V> SetMultimap.Immutable<K, V>
            toSetMultimap(SetMultimap<? extends K, ? extends V> map) {
        if(map instanceof SetMultimap.Immutable) {
            return (SetMultimap.Immutable<K, V>) map;
        }
        final SetMultimap.Transient<K, V> multimap = SetMultimap.Transient.of();
        for(Entry<? extends K, ? extends V> e : map.entrySet()) {
            multimap.__insert(e.getKey(), e.getValue());
        }
        return multimap.freeze();
    }

    @SuppressWarnings("rawtypes") private static final Set.Immutable EMPTY_SET = Set.Immutable.of();

    /**
     * Constructor for Set.Immutable that reuses an instantiated object. Used not to hit the reflection used in the
     * default construction methods.
     */
    @SuppressWarnings("unchecked") public static <K> Set.Immutable<K> immutableSet() {
        return EMPTY_SET;
    }

    /**
     * Constructor for Set.Immutable that reuses an instantiated object. Used not to hit the reflection used in the
     * default construction methods.
     */
    @SuppressWarnings("unchecked") public static <K> Set.Immutable<K> immutableSet(K value) {
        return EMPTY_SET.__insert(value);
    }

    /**
     * Constructor for Set.Immutable that reuses an instantiated object. Used not to hit the reflection used in the
     * default construction methods.
     */
    @SuppressWarnings("unchecked") public static <K> Set.Immutable<K> immutableSet(K value1, K value2) {
        return EMPTY_SET.__insert(value1).__insert(value2);
    }

    /**
     * Constructor for Set.Transient that reuses an instantiated object. Used not to hit the reflection used in the
     * default construction methods.
     */
    @SuppressWarnings("unchecked") public static <K> Set.Transient<K> transientSet() {
        return EMPTY_SET.asTransient();
    }

    /**
     * Constructor for Set.Transient that reuses an instantiated object. Used not to hit the reflection used in the
     * default construction methods.
     */
    @SuppressWarnings("unchecked") public static <K> Set.Transient<K> transientSet(K value1) {
        return EMPTY_SET.__insert(value1).asTransient();
    }

    @SuppressWarnings("rawtypes") private static final Map.Immutable EMPTY_MAP = Map.Immutable.of();

    /**
     * Constructor for Map.Transient that reuses an instantiated object. Used not to hit the reflection used in the
     * default construction methods.
     */
    @SuppressWarnings("unchecked") public static <K, V> Map.Immutable<K, V> immutableMap() {
        return EMPTY_MAP;
    }

    /**
     * Constructor for Map.Transient that reuses an instantiated object. Used not to hit the reflection used in the
     * default construction methods.
     */
    @SuppressWarnings("unchecked") public static <K, V> Map.Transient<K, V> transientMap() {
        return EMPTY_MAP.asTransient();
    }

    public static <K, V> Set.Immutable<V> removeAll(SetMultimap.Transient<K, V> map, K key) {
        final Set.Immutable<V> vs = map.get(key);
        map.__remove(key);
        return vs;
    }

    public static <K, V> void putAll(Map.Transient<K, V> map, K key, Collection<V> values) {
        values.forEach(value -> map.__put(key, value));
    }

    public static <K, V> void addAll(Set.Transient<K> map, Collection<K> keys) {
        keys.forEach(key -> map.__insert(key));
    }

    public static <K, V> void putAll(SetMultimap.Transient<K, V> map, K key, Collection<V> values) {
        values.forEach(value -> map.__insert(key, value));
    }

    public static <K, V> void putAll(SetMultimap.Transient<K, V> map, SetMultimap.Transient<K, V> toAdd) {
        toAdd.entrySet().forEach(e -> map.__insert(e.getKey(), e.getValue()));
    }

    public static <T, K, V> Map.Immutable<K, V> collectToMap(Stream<T> stream, Function<? super T, ? extends K> keyFunction,
        Function<? super T, ? extends V> valueFunction,
        BinaryOperator<V> mergeFunction) {
        final Map.Transient<K, V> result = transientMap();
        stream.forEach(t -> {
            final K key = keyFunction.apply(t);
            final V value = valueFunction.apply(t);
            final @Nullable V oldValue = result.get(key);
            final @Nullable V newValue =
                (oldValue == null) ? value : mergeFunction.apply(oldValue, value);
            if(newValue == null) {
                result.__remove(key);
            } else {
                result.__put(key, newValue);
            }
        });
        return result.freeze();
    }

    public static <T, K, V> Map.Immutable<K, V> collectToMap(Stream<T> stream, Function<? super T, ? extends K> keyFunction,
        Function<? super T, ? extends V> valueFunction) {
        return collectToMap(stream, keyFunction, valueFunction, (u,v) -> { throw new IllegalStateException("Duplicate key" + u); });
    }

    public static final class MapBuilder<K, V> {
        public final Map.Transient<K, V> map = CapsuleUtil.transientMap();

        public MapBuilder<K, V> put(K key, V value) {
            map.__put(key, value);
            return this;
        }

        public Map.Immutable<K, V> build() {
            return map.freeze();
        }
    }

    public static final class SetBuilder<K> {
        public final Set.Transient<K> set = CapsuleUtil.transientSet();

        public SetBuilder<K> add(K key) {
            set.__insert(key);
            return this;
        }

        public Set.Immutable<K> build() {
            return set.freeze();
        }
    }

}
