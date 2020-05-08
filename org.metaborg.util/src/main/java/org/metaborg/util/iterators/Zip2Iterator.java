package org.metaborg.util.iterators;

import java.util.Iterator;
import java.util.function.BiFunction;

class Zip2Iterator<T1, T2, R> implements Iterator<R> {

    private final Iterator<T1> iterator1;
    private final Iterator<T2> iterator2;
    private final BiFunction<? super T1, ? super T2, R> combine;

    public Zip2Iterator(Iterator<T1> iterator1, Iterator<T2> iterator2, BiFunction<? super T1, ? super T2, R> combine) {
        this.iterator1 = iterator1;
        this.iterator2 = iterator2;
        this.combine = combine;
    }

    @Override public boolean hasNext() {
        return iterator1.hasNext() && iterator2.hasNext();
    }

    @Override public R next() {
        return combine.apply(iterator1.next(), iterator2.next());
    }

    @Override public void remove() {
        iterator1.remove();
        iterator2.remove();
    }

}
