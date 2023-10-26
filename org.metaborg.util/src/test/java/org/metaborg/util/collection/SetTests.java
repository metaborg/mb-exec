package org.metaborg.util.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import static org.junit.Assert.assertFalse;

/**
 * Tests implementations of the {@link Set} interface:
 * collections that do not contain duplicates.
 */
public interface SetTests extends CollectionTests {

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the collection
     * @return the created mutable collection
     */
    Set<V> createSet(Iterable<V> elements);

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the collection
     * @return the created mutable collection
     */
    default Set<V> createSet(V... elements) {
        return createSet(Arrays.asList(elements));
    }

    @Override default Collection<V> createCollection(Iterable<V> elements) {
        return createSet(elements);
    }

    @Override default Collection<V> createCollection(V... elements) {
        return createSet(elements);
    }

}
