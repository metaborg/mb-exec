package org.metaborg.util.iterators;

import java.util.Iterator;

import com.google.common.collect.ImmutableSet;

public class EmptyIterable<T> implements Iterable<T> {
    public Iterator<T> iterator() {
        return ImmutableSet.<T>of().iterator();
    }
}
