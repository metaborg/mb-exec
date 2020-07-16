package org.metaborg.util.functions;

@FunctionalInterface
public interface CheckedAction2<T1, T2, E extends Throwable> {

    void apply(T1 t1, T2 t2) throws E;

}