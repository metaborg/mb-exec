package org.metaborg.util.unit;

import java.io.Serializable;

public final class Unit implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final Unit unit = new Unit();

    private Unit() {
    }

    @Override public String toString() {
        return "unit";
    }

}