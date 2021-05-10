package org.metaborg.util.tuple;

import java.io.Serializable;
import java.util.Objects;

import org.metaborg.util.functions.Function3;

import com.google.common.base.Preconditions;

public final class Tuple3<T1, T2, T3> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final T1 _1;
    private final T2 _2;
    private final T3 _3;

    private Tuple3(T1 _1, T2 _2, T3 _3) {
        Preconditions.checkNotNull(_1);
        Preconditions.checkNotNull(_2);
        Preconditions.checkNotNull(_3);
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
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


    public <R> R apply(Function3<T1, T2, T3, R> f) {
        return f.apply(_1, _2, _3);
    }


    @Override public int hashCode() {
        return Objects.hash(_1, _2, _3);
    }

    @Override public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Tuple3<?, ?, ?> other = (Tuple3<?, ?, ?>) obj;
        return Objects.equals(_1, other._1) && Objects.equals(_2, other._2) && Objects.equals(_3, other._3);
    }


    @Override public String toString() {
        return new StringBuilder().append("<").append(_1).append(", ").append(_2).append(", ").append(_3).append(">")
                .toString();
    }


    public static <T1, T2, T3> Tuple3<T1, T2, T3> of(T1 _1, T2 _2, T3 _3) {
        return new Tuple3<>(_1, _2, _3);
    }

}