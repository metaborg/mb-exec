package org.metaborg.util.functions;

@FunctionalInterface
public interface CheckedFunction0<R, E extends Throwable> {

    R apply() throws E;

}