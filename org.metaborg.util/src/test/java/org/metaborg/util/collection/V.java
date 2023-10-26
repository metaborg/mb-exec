package org.metaborg.util.collection;

import java.util.Objects;

/**
 * This class is used in collection tests, to be able to construct instances
 * of objects that are equal but not the same (not referentially equal).
 */
public final class V {
    public final String value;

    /**
     * Creates a new instance of the {@link V} class.
     *
     * @param value the value
     */
    public V(String value) {
        Objects.requireNonNull(value, "value");

        this.value = value;
    }

    @Override public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof V)) return false;
        final V that  = (V) obj;
        return this.value.equals(that.value);
    }

    @Override public int hashCode() {
        return value.hashCode();
    }

    @Override public String toString() {
        return value;
    }
}
