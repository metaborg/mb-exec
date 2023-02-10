package org.metaborg.util.collection;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * A multimap collection. Like a map, but allowing duplicate pairs. Unordered, but with an efficient lookup.
 * Size, iterator, and containsValue methods are not the most efficient, because internally duplicates are just counted.
 * @param <K>
 * @param <V>
 */
public abstract class BagMap<K, V> {
    // could be a capsule Map.Immutable instead, if you want to bother with that...
    protected abstract Map<K, ? extends Bag<V>> backingMap();

    public abstract Bag<V> get(K key);

    public int size() {
        return backingMap().values().stream().mapToInt(Bag::size).sum();
    }

    public boolean isEmpty() {
        return backingMap().isEmpty();
    }

    public boolean containsKey(K key) {
        return backingMap().containsKey(key);
    }

    public boolean containsValue(V value) {
        return backingMap().values().stream().anyMatch(b -> b.contains(value));
    }

    public Collection<K> keys() {
        return backingMap().entrySet().stream().flatMap(e -> {
            K key = e.getKey();
            return e.getValue().stream().map(v -> key);
        }).collect(Collectors.toList());
    }

    public Collection<V> values() {
        return backingMap().values().stream().flatMap(Bag::stream).collect(Collectors.toList());
    }

    public Collection<Map.Entry<K, V>> entries() {
        return backingMap().entrySet().stream().flatMap(e -> {
            K key = e.getKey();
            return e.getValue().stream().map(v -> new AbstractMap.SimpleImmutableEntry<>(key, v));
        }).collect(Collectors.toList());
    }

    public void forEach(BiConsumer<? super K, ? super V> action) {
        for (Map.Entry<K, V> entry : entries()) {
            action.accept(entry.getKey(), entry.getValue());
        }
    }

    public static class Immutable<K, V> extends BagMap<K, V> {
        private final Map<K, Bag.Transient<V>> backingMap;

        private Immutable(Map<K, Bag.Transient<V>> backingMap) {
            this.backingMap = backingMap;
        }

        public static <K, V> Immutable<K, V> of() {
            return new Immutable(Collections.emptyMap());
        }

        @Override protected Map<K, ? extends Bag<V>> backingMap() {
            return backingMap;
        }

        @Override public Bag.Immutable<V> get(K key) {
            return backingMap.get(key).freeze();
        }

        public Transient<K, V> asTransient() {
            Map<K, Bag.Transient<V>> transientBackingMap = new HashMap<>(Sets.hashCapacity(this.backingMap.size()));
            for(Map.Entry<K, Bag.Transient<V>> e : this.backingMap.entrySet()) {
                transientBackingMap.put(e.getKey(), e.getValue().freeze().asTransient());
            }
            return new Transient<>(transientBackingMap);
        }
    }

    public static class Transient<K, V> extends BagMap<K, V> {
        private final Map<K, Bag.Transient<V>> backingMap;
        private BagMap.Immutable<K, V> frozen = null;

        private Transient(Map<K, Bag.Transient<V>> backingMap) {
            this.backingMap = backingMap;
        }

        public static <K, V> Transient<K, V> of() {
            return new Transient(new HashMap());
        }

        public static <K, V> Transient<K, V> withSizeEstimate(int initialCapacity) {
            return new Transient(new HashMap(Sets.hashCapacity(initialCapacity)));
        }

        @Override protected Map<K, ? extends Bag<V>> backingMap() {
            return backingMap;
        }

        @Override public Bag.Transient<V> get(K key) {
            return backingMap.get(key);
        }

        public void put(K key, V value) {
            if(frozen != null) {
                throw new IllegalStateException();
            }
            backingMap.computeIfAbsent(key, k -> Bag.Transient.of()).add(value);
        }

        public boolean remove(K key, V value) {
            if(frozen != null) {
                throw new IllegalStateException();
            }
            final AtomicBoolean present = new AtomicBoolean(false);
            backingMap.computeIfPresent(key, (k, v) -> {
                present.set(v.remove(value));
                return v.isEmpty() ? null : v;
            });
            return present.get();
        }

        public Bag<V> removeAll(K key) {
            if(frozen != null) {
                throw new IllegalStateException();
            }
            final Bag<V> removed = backingMap.remove(key);
            return removed != null ? removed : Bag.Transient.of();
        }

        public boolean removeAll(Collection<K> keys, V value) {
            if(frozen != null) {
                throw new IllegalStateException();
            }
            boolean changed = false;
            for(K key : keys) {
                changed |= remove(key, value);
            }
            return changed;
        }

        public void putAll(Map<? extends K, ? extends V> m) {
            if(frozen != null) {
                throw new IllegalStateException();
            }
            for(Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }

        public void clear() {
            if(frozen != null) {
                throw new IllegalStateException();
            }
            backingMap.clear();
        }

        public BagMap.Immutable<K, V> freeze() {
            if(frozen == null) {
                frozen = new BagMap.Immutable<>(Collections.unmodifiableMap(backingMap));
            }
            return frozen;
        }
    }
}
