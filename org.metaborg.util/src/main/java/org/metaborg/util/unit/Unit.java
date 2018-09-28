package org.metaborg.util.unit;

import java.io.Serializable;

import org.metaborg.util.functions.CheckedFunction0;
import org.metaborg.util.functions.Function0;

public final class Unit implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final Unit unit = new Unit();

    private Unit() {
    }

    public <T> T andThen(Function0<T> next) {
        return next.apply();
    }

    public <T, E extends Throwable> T andThenOrThrow(CheckedFunction0<T, E> next) throws E {
        return next.apply();
    }

    @Override public String toString() {
        return "unit";
    }

}