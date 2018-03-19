package org.metaborg.util.functions;

import java.util.Optional;

@FunctionalInterface
public interface PartialFunction3<T1, T2, T3, R> extends Function3<T1, T2, T3, Optional<R>> {

    public static <T1, T2, T3, R> PartialFunction3<T1, T2, T3, R> never() {
        return (t1, t2, t3) -> Optional.empty();
    }

}