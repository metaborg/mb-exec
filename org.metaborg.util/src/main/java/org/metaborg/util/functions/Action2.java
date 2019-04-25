package org.metaborg.util.functions;

@FunctionalInterface
public interface Action2<T1, T2> {

    void apply(T1 t1, T2 t2);

}