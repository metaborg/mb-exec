package org.metaborg.util.functions;

@FunctionalInterface
public interface CheckedAction1<T, E extends Throwable> {

    void apply(T t) throws E;

}