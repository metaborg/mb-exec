package org.metaborg.util.functions;

import java.util.Optional;

@FunctionalInterface
public interface PartialFunction2<T1, T2, R> extends Function2<T1, T2, Optional<R>> {

    public static <T1, T2, R> PartialFunction2<T1, T2, R> never() {
        return (t1, t2) -> Optional.empty();
    }

}