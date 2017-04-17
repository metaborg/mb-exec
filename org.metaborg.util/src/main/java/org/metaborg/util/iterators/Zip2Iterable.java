package org.metaborg.util.iterators;

import java.util.Iterator;

import rx.functions.Func2;

class Zip2Iterable<T1, T2, R> implements Iterable<R> {

    private final Iterable<T1> iterable1;
    private final Iterable<T2> iterable2;
    private final Func2<? super T1, ? super T2, R> combine;

    public Zip2Iterable(Iterable<T1> iterable1, Iterable<T2> iterable2, Func2<? super T1, ? super T2, R> combine) {
        this.iterable1 = iterable1;
        this.iterable2 = iterable2;
        this.combine = combine;
    }

    @Override public Iterator<R> iterator() {
        return new Zip2Iterator<>(iterable1.iterator(), iterable2.iterator(), combine);
    }

}
