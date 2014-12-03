package org.metaborg.util.iterators;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayIterable<T> implements Iterable<T> {
    private final T[] array;


    public ArrayIterable(T[] array) {
        this.array = array;
    }


    @Override public Iterator<T> iterator() {
        return new ArrayIterator<T>(array);
    }


    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(array);
        return result;
    }

    @Override public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        @SuppressWarnings("rawtypes") ArrayIterable other = (ArrayIterable) obj;
        if(!Arrays.equals(array, other.array))
            return false;
        return true;
    }

    @Override public String toString() {
        return Arrays.toString(array);
    }
}
