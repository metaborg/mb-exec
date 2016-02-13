package org.metaborg.util;

/**
 * Reference to boolean
 */
public class RefBool {
    private boolean value;


    public RefBool() {
        this(false);
    }

    public RefBool(boolean value) {
        this.value = value;
    }


    public boolean get() {
        return value;
    }

    public void set(boolean value) {
        this.value = value;
    }

    public void and(boolean value) {
        this.value &= value;
    }

    public void or(boolean value) {
        this.value |= value;
    }
}
