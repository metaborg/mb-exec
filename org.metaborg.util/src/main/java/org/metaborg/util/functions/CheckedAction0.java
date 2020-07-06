package org.metaborg.util.functions;

@FunctionalInterface
public interface CheckedAction0<E extends Throwable> {

    void apply() throws E;

}