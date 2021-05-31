package org.metaborg.util.tuple;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

import org.metaborg.util.functions.Function2;

import com.google.common.base.Preconditions;

public final class Tuple2<T1, T2> implements Map.Entry<T1, T2>, Serializable {

    private static final long serialVersionUID = 1L;

    private final T1 _1;
    private final T2 _2;

    private Tuple2(T1 _1, T2 _2) {
        Preconditions.checkNotNull(_1);
        Preconditions.checkNotNull(_2);
        this._1 = _1;
        this._2 = _2;
    }


    public T1 _1() {
        return _1;
    }

    public T2 _2() {
        return _2;
    }


    @Override public T1 getKey() {
        return _1;
    }

    @Override public T2 getValue() {
        return _2;
    }

    @Override public T2 setValue(@SuppressWarnings("unused") T2 value) {
        throw new UnsupportedOperationException("Tuple2 does not support setValue.");
    }


    public <R> R apply(Function2<T1, T2, R> f) {
        return f.apply(_1, _2);
    }


    @Override public int hashCode() {
        return Objects.hash(_1, _2);
    }

    @Override public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Tuple2<?, ?> other = (Tuple2<?, ?>) obj;
        return Objects.equals(_1, other._1) && Objects.equals(_2, other._2);
    }


    @Override public String toString() {
        return new StringBuilder().append("<").append(_1).append(", ").append(_2).append(">").toString();
    }


    public static <T1, T2> Tuple2<T1, T2> of(T1 _1, T2 _2) {
        return new Tuple2<>(_1, _2);
    }

    public static <T1, T2> Tuple2<T1, T2> of(Map.Entry<? extends T1, ? extends T2> entry) {
        return new Tuple2<>(entry.getKey(), entry.getValue());
    }

}