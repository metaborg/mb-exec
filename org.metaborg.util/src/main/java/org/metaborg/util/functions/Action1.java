package org.metaborg.util.functions;

@FunctionalInterface
public interface Action1<T> {

    void apply(T t);

}