package org.metaborg.util.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** Tests implementations of the immutable {@link Set} interface. */
@SuppressWarnings("unused")
public interface ImmutableSetTests extends ImmutableCollectionTests, SetTests {

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the collection
     * @return the created immutable set
     */
    Set<V> createImmutableSet(Iterable<V> elements);

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the collection
     * @return the created immutable set
     */
    default Set<V> createImmutableSet(V... elements) {
        return createImmutableSet(Arrays.asList(elements));
    }

    @Override default Collection<V> createImmutableCollection(Iterable<V> elements) {
        return createImmutableSet(elements);
    }

    @Override default Collection<V> createImmutableCollection(V... elements) {
        return createImmutableSet(elements);
    }

    @Override default Collection<V> createCollection(Iterable<V> elements) {
        return createImmutableSet(elements);
    }

    @Override default Collection<V> createCollection(V... elements) {
        return createImmutableSet(elements);
    }

    @Override default Set<V> createSet(V... elements) {
        return createImmutableSet(elements);
    }

    @Override default Set<V> createSet(Iterable<V> elements) {
        return createImmutableSet(elements);
    }

}
