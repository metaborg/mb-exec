package org.metaborg.util.observable;

import rx.Observer;

public interface ITestableObserver<T> extends Observer<T>, Iterable<TimestampedNotification<T>> {
    public TimestampedNotification<T> peek();

    public TimestampedNotification<T> poll();

    public int size();

    public void clear();
}
