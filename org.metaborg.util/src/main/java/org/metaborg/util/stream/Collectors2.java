package org.metaborg.util.stream;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Collectors2 {

    public static <T> Collector<T, ?, HashSet<T>> toHashSet() {
        return Collectors.toCollection(() -> new HashSet<>());
    }

    public static <T> Collector<T, ?, HashSet<T>> toLinkedHashSet() {
        return Collectors.toCollection(() -> new LinkedHashSet<>());
    }

    public static <T> Collector<T, ?, ArrayList<T>> toArrayList() {
        return Collectors.toCollection(() -> new ArrayList<>());
    }

}