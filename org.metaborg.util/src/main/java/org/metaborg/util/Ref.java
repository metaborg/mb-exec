package org.metaborg.util;

import java.io.Serializable;

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
}
