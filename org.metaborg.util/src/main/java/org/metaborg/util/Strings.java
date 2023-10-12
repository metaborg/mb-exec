package org.metaborg.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Strings {
    public static String repeat(String s, int n) {
        return String.join("", Collections.nCopies(n, s));
    }

    public static String nullToEmpty(String s) {
        return (s == null) ? "" : s;
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static String join(String[] collection, String delimiter) {
        return join(Arrays.asList(collection), delimiter);
    }

    public static String join(Collection<String> collection, String delimiter) {
        return collection.stream().collect(Collectors.joining(delimiter));
    }

    public static <T> String join(Collection<T> collection, Function<T, String> toString, String delimiter) {
        return collection.stream().map(toString).collect(Collectors.joining(delimiter));
    }

    public static <T> String tsJoin(T[] collection, String delimiter) {
        return join(Arrays.asList(collection), Objects::toString, delimiter);
    }

    public static <T> String tsJoin(Collection<T> collection, String delimiter) {
        return join(collection, Objects::toString, delimiter);
    }

    public static <T> Collector<T,?,String> joining(String delimiter) {
        return Collectors.mapping(Object::toString, Collectors.joining(delimiter));
    }
}
