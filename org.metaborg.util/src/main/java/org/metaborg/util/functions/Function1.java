package org.metaborg.util.functions;

@FunctionalInterface
public interface Function1<T, R> {

    R apply(T t);

}