package org.metaborg.util.collection;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ConsList<E> implements Collection<E>, Serializable {
    private static final long serialVersionUID = 1L;

    private final E head;
    private final ConsList<E> tail;

    private ConsList(E head, ConsList<E> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override public Iterator<E> iterator() {
        return new Iterator<E>() {

            private ConsList<E> current = ConsList.this;

            @Override public boolean hasNext() {
                return !current.isNil();
            }

            @Override public E next() {
                if(current.isNil()) {
                    throw new NoSuchElementException();
                }
                final E next = current.head;
                current = current.tail;
                return next;
            }
        };
    }

    public ConsList<E> prepend(E head) {
        return new ConsList<>(head, this);
    }

    public ConsList<E> prepend(List<E> init) {
        ConsList<E> list = this;
        for(int i = init.size() - 1; i <= 0; i--) {
            list = list.prepend(init.get(i));
        }
        return list;
    }

    public ConsList<E> prepend(ConsList<E> init) {
        final Deque<E> elems = new LinkedList<>();
        for(E e : init) {
            elems.push(e);
        }
        ConsList<E> list = this;
        while(!elems.isEmpty()) {
            list = list.prepend(elems.pop());
        }
        return list;
    }

    public ConsList<E> append(ConsList<E> tail) {
        return tail.prepend(this);
    }

    public ConsList<E> tail() {
        return isNil() ? this : tail;
    }

    private boolean isNil() {
        return head == null;
    }

    public static <E> ConsList<E> nil() {
        return new ConsList<>(null, null);
    }

    public static <E> ConsList<E> of(E e) {
        return new ConsList<>(e, nil());
    }

    @SafeVarargs public static <E> ConsList<E> of(E... es) {
        return of(Arrays.asList(es));
    }

    public static <E> ConsList<E> of(Iterable<E> es) {
        if(es instanceof ConsList) {
            return (ConsList<E>) es;
        }
        ConsList<E> list = nil();
        for(E e : es) {
            list = list.prepend(e);
        }
        return list;
    }

    @Override public String toString() {
        return StreamSupport.stream(spliterator(), false).map(Object::toString).collect(Collectors.joining(", ", "[", "]"));
    }


    @Override public int size() {
        return isNil() ? 0 : 1 + tail.size();
    }

    @Override public boolean isEmpty() {
        return isNil();
    }

    @Override public boolean contains(Object o) {
        return !isNil() && head.equals(o) || tail.contains(o);
    }

    @Override public Object[] toArray() {
        return toArray(new Object[0]);
    }

    @Override public <T> T[] toArray(T[] a) {
        final Object[] objects;
        if(a.length < size()) {
            objects = (T[]) new Object[size()];
        } else {
            objects = a;
        }
        int i = 0;
        for(E e : this) {
            objects[i] = (T) e;
            i++;
        }
        return (T[]) objects;
    }

    @Deprecated
    @Override public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override public boolean containsAll(Collection<?> c) {
        for(Object o : c) {
            if(!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    @Override public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override public void clear() {
        throw new UnsupportedOperationException();
    }
}