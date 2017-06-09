package org.metaborg.util.functions;

@FunctionalInterface
public interface CheckedFunction1<T, R, E extends Throwable> {

    R apply(T t) throws E;

}