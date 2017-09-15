package org.metaborg.util.functions;

import java.util.Optional;

@FunctionalInterface
public interface PartialFunction0<R> extends Function0<Optional<R>> {

    public static <R> PartialFunction0<R> never() {
        return () -> Optional.empty();
    }

}