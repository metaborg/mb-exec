package org.metaborg.util;

/**
 * Pointer pointer in Java.
 */
public class Ref<T> {
    private T value;


    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}