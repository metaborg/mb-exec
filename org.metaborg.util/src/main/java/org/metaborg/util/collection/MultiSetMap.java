package org.metaborg.util.collection;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.metaborg.util.tuple.Tuple2;

import io.usethesource.capsule.Map;

public abstract class MultiSetMap<K, V> implements Serializable {

    // INVARIANT toMap()/entries never contains empty MultiSet values
    //           Thus, if there is an entry for a key, there is at least one value as well.

    @SuppressWarnings("rawtypes") private static final Immutable EMPTY = new Immutable<>(Map.Immutable.of());

    protected abstract java.util.Map<K, MultiSet.Immutable<V>> asMap();

    public Set<Entry<K, MultiSet.Immutable<V>>> entrySet() {
        return asMap().entrySet();
    }

    public Collection<Entry<K, V>> entries() {
        return entryStream().collect(ImList.toImmutableList());
    }

    public void forEach(BiConsumer<? super K, ? super V> action) {
        entryStream().forEach(e -> action.accept(e.getKey(), e.getValue()));
    }

    public Stream<Map.Entry<K, V>> entryStream() {
        return asMap().entrySet().stream().flatMap(e -> {
            final K key = e.getKey();
            return e.getValue().toCollection().stream().map(v -> Tuple2.of(key, v));
        });
    }

    public boolean isEmpty() {
        return asMap().isEmpty();
    }

    public int size() {
        return asMap().entrySet().stream().mapToInt(e -> e.getValue().size()).sum();
    }

    public boolean containsKey(K key) {
        return asMap().containsKey(key);
    }

    public boolean contains(K key, V value) {
        return get(key).contains(value);
    }

    public boolean containsValue(V value) {
        return asMap().values().stream().anyMatch(vs -> vs.contains(value));
    }

    public int count(K key, V value) {
        return get(key).count(value);
    }

    public MultiSet.Immutable<V> get(K key) {
        return asMap().getOrDefault(key, MultiSet.Immutable.of());
    }

    public Set<K> keySet() {
        return asMap().keySet();
    }

    public static class Immutable<K, V> extends MultiSetMap<K, V> implements Serializable {

        private static final long serialVersionUID = 1L;

        private final Map.Immutable<K, MultiSet.Immutable<V>> entries;

        private Immutable(Map.Immutable<K, MultiSet.Immutable<V>> entries) {
            this.entries = entries;
        }

        public static <K, V> Immutable<K, V> of(K key, V value) {
            final Immutable<K, V> result = of();
            return result.put(key, value);
        }

        @Override public Map.Immutable<K, MultiSet.Immutable<V>> asMap() {
            return entries;
        }

        public Immutable<K, V> put(K key, V value) {
            final MultiSet.Immutable<V> values = entries.getOrDefault(key, MultiSet.Immutable.of());
            return new Immutable<>(entries.__put(key, values.add(value, 1)));
        }

        public Immutable<K, V> put(K key, V value, int n) {
            if(n < 0) {
                throw new IllegalArgumentException("Negative count");
            }
            if(n == 0) {
                return this;
            }
            final MultiSet.Immutable<V> values = entries.getOrDefault(key, MultiSet.Immutable.of());
            final MultiSet.Immutable<V> newValues = values.add(value, n);
            return new Immutable<>(entries.__put(key, newValues));
        }

        public Immutable<K, V> putAll(K key, MultiSet.Immutable<V> values) {
            final MultiSet.Immutable<V> oldValues = entries.get(key);
            final MultiSet.Immutable<V> newValues;
            if(oldValues != null) {
                newValues = MultiSet.Immutable.union(oldValues, values);
            } else {
                newValues = values;
            }
            return new Immutable<>(entries.__put(key, newValues));
        }

        public Immutable<K, V> removeKey(K key) {
            return new Immutable<>(entries.__remove(key));
        }

        public Immutable<K, V> remove(K key, V value) {
            final MultiSet.Immutable<V> values = entries.get(key);
            if(values == null) {
                return this;
            }
            final MultiSet.Immutable<V> newValues = values.remove(value, 1);
            final Map.Immutable<K, MultiSet.Immutable<V>> newEntries;
            if(newValues.isEmpty()) {
                newEntries = entries.__remove(key);
            } else {
                newEntries = entries.__put(key, newValues);
            }
            return new Immutable<>(newEntries);
        }

        public Immutable<K, V> removeAll(java.util.Set<K> keys) {
            final Map.Transient<K, MultiSet.Immutable<V>> newEntries = this.entries.asTransient();
            CapsuleUtil.filter(newEntries, k -> !keys.contains(k));
            return new Immutable<>(newEntries.freeze());
        }

        public Immutable<K, V> retainAll(java.util.Set<K> keys) {
            final Map.Transient<K, MultiSet.Immutable<V>> newEntries = this.entries.asTransient();
            CapsuleUtil.filter(newEntries, k -> keys.contains(k));
            return new Immutable<>(newEntries.freeze());
        }

        public MultiSetMap.Transient<K, V> melt() {
            return new Transient<>(entries.asTransient());
        }

        @SuppressWarnings("unchecked") public static <K, V> MultiSetMap.Immutable<K, V> of() {
            return EMPTY;
        }

    }

    public static class Transient<K, V> extends MultiSetMap.Mutable<K, V> {

        private final Map.Transient<K, MultiSet.Immutable<V>> entries;

        private Transient(Map.Transient<K, MultiSet.Immutable<V>> entries) {
            this.entries = entries;
        }

        @Override public Map.Transient<K, MultiSet.Immutable<V>> asMap() {
            return entries;
        }

        @Override protected MultiSet.Immutable<V> remove(K key) {
            return entries.__remove(key);
        }

        @Override protected MultiSet.Immutable<V> put(K key, MultiSet.Immutable<V> value) {
            return entries.__put(key, value);
        }

        @SuppressWarnings("unchecked") public Immutable<K, V> freeze() {
            return entries.isEmpty() ? EMPTY : new Immutable<>(entries.freeze());
        }

        public static <K, V> MultiSetMap.Transient<K, V> of() {
            return new Transient<>(Map.Transient.of());
        }
    }

    public static class Ordered<K, V> extends MultiSetMap.Mutable<K, V> {
        private final LinkedHashMap<K, MultiSet.Immutable<V>> entries;

        private Ordered(LinkedHashMap<K, MultiSet.Immutable<V>> entries) {
            this.entries = entries;
        }

        @Override public java.util.Map<K, MultiSet.Immutable<V>> asMap() {
            return entries;
        }

        @Override protected MultiSet.Immutable<V> remove(K key) {
            return entries.remove(key);
        }

        @Override protected MultiSet.Immutable<V> put(K key, MultiSet.Immutable<V> value) {
            return entries.put(key, value);
        }
    }

    public static abstract class Mutable<K, V> extends MultiSetMap<K, V> {
        @Override public abstract java.util.Map<K, MultiSet.Immutable<V>> asMap();

        protected abstract MultiSet.Immutable<V> remove(K key);

        protected abstract MultiSet.Immutable<V> put(K key, MultiSet.Immutable<V> value);


        /**
         * Add an entry to the map, return the old count.
         */
        public int put(K key, V value) {
            return put(key, value, 1);
        }

        public int put(K key, V value, int n) {
            if(n < 0) {
                throw new IllegalArgumentException("Negative count");
            }
            final MultiSet.Immutable<V> oldValues = remove(key);
            final MultiSet.Immutable<V> newValues;
            final int oldCount;
            if(oldValues != null) {
                final MultiSet.Transient<V> values = oldValues.melt();
                oldCount = values.add(value, n);
                newValues = values.freeze();
            } else {
                oldCount = 0;
                newValues = MultiSet.Immutable.of(value, n);
            }
            if(!newValues.isEmpty()) {
                put(key, newValues);
            }
            return oldCount;
        }

        public void putAll(K key, Iterable<V> values) {
            for(V value : values) {
                put(key, value);
            }
        }

        public void putAll(K key, MultiSet.Immutable<V> values) {
            final MultiSet.Immutable<V> oldValues = remove(key);
            final MultiSet.Immutable<V> newValues;
            if(oldValues != null) {
                newValues = MultiSet.Immutable.union(oldValues, values);
            } else {
                newValues = values;
            }
            if(!newValues.isEmpty()) {
                put(key, newValues);
            }
        }

        public MultiSet.Immutable<V> removeKey(K key) {
            if(asMap().containsKey(key)) {
                return remove(key);
            } else {
                return MultiSet.Immutable.of();
            }
        }

        /**
         * Remove an entry from the map, return the old count.
         */
        public int remove(K key, V value) {
            return remove(key, value, 1);
        }

        public int remove(K key, V value, int n) {
            if(n < 0) {
                throw new IllegalArgumentException("Negative count");
            }
            final MultiSet.Immutable<V> oldValues = remove(key);
            final MultiSet.Immutable<V> newValues;
            final int oldCount;
            if(oldValues != null) {
                final MultiSet.Transient<V> values = oldValues.melt();
                oldCount = values.remove(value, n);
                newValues = values.freeze();
            } else {
                oldCount = 0;
                newValues = MultiSet.Immutable.of();
            }
            if(!newValues.isEmpty()) {
                put(key, newValues);
            }
            return oldCount;
        }

        public Immutable<K, V> clear() {
            final Immutable<K, V> cleared =
                new Immutable<>(Map.Immutable.<K, MultiSet.Immutable<V>>of().__putAll(asMap()));
            for(K k : asMap().keySet()) {
                remove(k);
            }
            return cleared;
        }

        public Collection<V> removeAll(K key) {
            final MultiSet.Immutable<V> removed = remove(key);
            return removed != null ? removed.toCollection() : Collections.emptySet();
        }

        public boolean removeAll(Collection<K> keys, V value) {
            boolean changed = false;
            for(K key : keys) {
                changed |= remove(key, value) != 0;
            }
            return changed;
        }
    }

    @Override public boolean equals(Object obj) {
        if(!(obj instanceof MultiSetMap))
            return false;
        final MultiSetMap<?, ?> that = (MultiSetMap<?, ?>) obj;
        return this.asMap().equals(that.asMap());
    }

    @Override public int hashCode() {
        return this.asMap().hashCode();
    }

    @Override public String toString() {
        return asMap().entrySet().stream().map(e -> e.getKey() + ": " + e.getValue())
                .collect(Collectors.joining(", ", "{", "}"));

    }



}
