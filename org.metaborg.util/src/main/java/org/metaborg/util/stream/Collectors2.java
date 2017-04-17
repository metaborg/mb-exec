package org.metaborg.util.stream;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Collectors2 {

    public static <T> Collector<T, ?, HashSet<T>> toHashSet() {
        return Collectors.toCollection(Sets::newHashSet);
    }

    public static <T> Collector<T, ?, ArrayList<T>> toArrayList() {
        return Collectors.toCollection(Lists::newArrayList);
    }

}