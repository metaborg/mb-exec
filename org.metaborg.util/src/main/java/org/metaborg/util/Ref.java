package org.metaborg.util;

import java.io.Serializable;
import java.util.Objects;

/**
 * Pointer pointer in Java.
 */
public class Ref<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private T value;


    public Ref() {
        this(null);
    }

    public Ref(T value) {
        this.value = value;
    }


    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }

    @Override public String toString() {
        return "&" + Objects.toString(value);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Ref)) {
            return false;
        }
        @SuppressWarnings("rawtypes")
        Ref other = (Ref) obj;
        if(value == null) {
            if(other.value != null) {
                return false;
            }
        } else if(!value.equals(other.value)) {
            return false;
        }
        return true;
    }

}
