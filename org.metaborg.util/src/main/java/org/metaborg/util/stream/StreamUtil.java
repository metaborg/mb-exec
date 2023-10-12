package org.metaborg.util.stream;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class StreamUtil {
    public static <T, U> Stream<U> filterMap(Stream<T> stream, Function<T, Optional<U>> filterMap) {
        return stream.map(filterMap).filter(Optional::isPresent).map(Optional::get);
    }
}
