package org.metaborg.util.collection;

import java.io.Serializable;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import io.usethesource.capsule.Map;

public abstract class MultiSetMap<K, V> {

    // INVARIANT toMap()/entries never contains empty MultiSet values
    //           Thus, if there is an entry for a key, there is at least one value as well.

    @SuppressWarnings("rawtypes") private static final Immutable EMPTY = new Immutable<>(Map.Immutable.of());

    protected abstract Map<K, MultiSet.Immutable<V>> asMap();

    public Set<Entry<K, MultiSet.Immutable<V>>> entrySet() {
        return asMap().entrySet();
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

        @Override public Map.Immutable<K, MultiSet.Immutable<V>> asMap() {
            return entries;
        }

        public Immutable<K, V> put(K key, V value) {
            final MultiSet.Immutable<V> values = entries.getOrDefault(key, MultiSet.Immutable.of());
            return new Immutable<>(entries.__put(key, values.add(value)));
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
            final MultiSet.Immutable<V> newValues = values.remove(value);
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

    public static class Transient<K, V> extends MultiSetMap<K, V> {

        private final Map.Transient<K, MultiSet.Immutable<V>> entries;

        private Transient(Map.Transient<K, MultiSet.Immutable<V>> entries) {
            this.entries = entries;
        }

        @Override public Map.Transient<K, MultiSet.Immutable<V>> asMap() {
            return entries;
        }

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
            final MultiSet.Immutable<V> oldValues = entries.__remove(key);
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
                entries.__put(key, newValues);
            }
            return oldCount;
        }

        public void putAll(K key, Iterable<V> values) {
            for(V value : values) {
                put(key, value);
            }
        }

        public void putAll(K key, MultiSet.Immutable<V> values) {
            final MultiSet.Immutable<V> oldValues = entries.__remove(key);
            final MultiSet.Immutable<V> newValues;
            if(oldValues != null) {
                newValues = MultiSet.Immutable.union(oldValues, values);
            } else {
                newValues = values;
            }
            if(!newValues.isEmpty()) {
                entries.__put(key, newValues);
            }
        }

        public MultiSet.Immutable<V> removeKey(K key) {
            if(entries.containsKey(key)) {
                return entries.__remove(key);
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
            final MultiSet.Immutable<V> oldValues = entries.__remove(key);
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
                entries.__put(key, newValues);
            }
            return oldCount;
        }

        public Immutable<K, V> clear() {
            final Immutable<K, V> cleared =
                    new Immutable<>(Map.Immutable.<K, MultiSet.Immutable<V>>of().__putAll(entries));
            for(K k : entries.keySet()) {
                entries.__remove(k);
            }
            return cleared;
        }

        @SuppressWarnings("unchecked") public Immutable<K, V> freeze() {
            return entries.isEmpty() ? EMPTY : new Immutable<>(entries.freeze());
        }

        public static <K, V> MultiSetMap.Transient<K, V> of() {
            return new Transient<>(Map.Transient.of());
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
