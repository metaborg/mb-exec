package org.metaborg.util.tuple;

import java.io.Serializable;
import java.util.Objects;

import org.metaborg.util.functions.Function4;

public final class Tuple4<T1, T2, T3, T4> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final T1 _1;
    private final T2 _2;
    private final T3 _3;
    private final T4 _4;

    private Tuple4(T1 _1, T2 _2, T3 _3, T4 _4) {
        Objects.requireNonNull(_1);
        Objects.requireNonNull(_2);
        Objects.requireNonNull(_3);
        Objects.requireNonNull(_4);
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
        this._4 = _4;
    }


    public T1 _1() {
        return _1;
    }

    public T2 _2() {
        return _2;
    }

    public T3 _3() {
        return _3;
    }

    public T4 _4() {
        return _4;
    }


    public <R> R apply(Function4<T1, T2, T3, T4, R> f) {
        return f.apply(_1, _2, _3, _4);
    }


    @Override public int hashCode() {
        return Objects.hash(_1, _2, _3, _4);
    }

    @Override public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Tuple4<?, ?, ?, ?> other = (Tuple4<?, ?, ?, ?>) obj;
        return Objects.equals(_1, other._1) && Objects.equals(_2, other._2) && Objects.equals(_3, other._3)
                && Objects.equals(_4, other._4);
    }


    @Override public String toString() {
        return new StringBuilder().append("<").append(_1).append(", ").append(_2).append(", ").append(", ").append(_4)
                .append(">").toString();
    }


    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> of(T1 _1, T2 _2, T3 _3, T4 _4) {
        return new Tuple4<>(_1, _2, _3, _4);
    }

}