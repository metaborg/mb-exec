package org.metaborg.util.iterators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {
    private final T[] array;
    private int pos = 0;


    public ArrayIterator(T array[]) {
        this.array = array;
    }

    
    @Override public boolean hasNext() {
        return pos < array.length;
    }

    @Override public T next() throws NoSuchElementException {
        if(hasNext())
            return array[pos++];
        else
            throw new NoSuchElementException();
    }

    @Override public void remove() {
        throw new UnsupportedOperationException();
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
        @SuppressWarnings("rawtypes") ArrayIterator other = (ArrayIterator) obj;
        if(!Arrays.equals(array, other.array))
            return false;
        return true;
    }

    @Override public String toString() {
        return Arrays.toString(array) + "@" + pos;
    }
}
