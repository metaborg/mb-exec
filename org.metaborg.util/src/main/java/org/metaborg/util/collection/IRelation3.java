package org.metaborg.util.collection;

import java.util.Map;

import org.metaborg.util.functions.Function3;
import org.metaborg.util.tuple.Tuple3;

import io.usethesource.capsule.Set;
import io.usethesource.capsule.SetMultimap;

public interface IRelation3<K, L, V> {

    IRelation3<V, L, K> inverse();

    java.util.Set<K> keySet();

    java.util.Set<V> valueSet();

    boolean contains(K key);

    boolean contains(K key, L label);

    boolean contains(K key, L label, V value);

    boolean isEmpty();

    java.util.Set<? extends Map.Entry<L, V>> get(K key);

    java.util.Set<V> get(K key, L label);

    default java.util.stream.Stream<Tuple3<K, L, V>> stream() {
        return this.stream(Tuple3::of);
    }

    default <R> java.util.stream.Stream<R> stream(final Function3<K, L, V, R> converter) {
        return this.keySet().stream().flatMap(
                key -> this.get(key).stream().map(entry -> converter.apply(key, entry.getKey(), entry.getValue())));
    }

    interface Immutable<K, L, V> extends IRelation3<K, L, V> {

        @Override IRelation3.Immutable<V, L, K> inverse();

        IRelation3.Immutable<K, L, V> put(K key, L label, V value);

        IRelation3.Immutable<K, L, V> putAll(IRelation3<K, L, V> other);

        IRelation3.Transient<K, L, V> melt();

    }

    interface Transient<K, L, V> extends IRelation3<K, L, V> {

        @Override IRelation3.Transient<V, L, K> inverse();

        boolean put(K key, L label, V value);

        boolean putAll(IRelation3<K, L, V> other);

        SetMultimap.Immutable<L, V> remove(K key);

        Set.Immutable<V> remove(K key, L label);

        boolean remove(K key, L label, V value);

        IRelation3.Immutable<K, L, V> freeze();

    }

}