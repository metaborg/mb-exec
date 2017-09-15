package org.metaborg.util.functions;

import java.util.Optional;

@FunctionalInterface
public interface PartialFunction1<T, R> extends Function1<T, Optional<R>> {

    public static <T, R> PartialFunction1<T, R> never() {
        return (t) -> Optional.empty();
    }

}