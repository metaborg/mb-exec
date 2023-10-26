package org.metaborg.util.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

/**
 * Tests implementations of the ordered {@link Collection} interface:
 * collections whose elements are ordered.
 */
@SuppressWarnings("unused")
public interface OrderedCollectionTests extends CollectionTests {

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the ordered collection
     * @return the created ordered collection
     */
    Collection<V> createOrderedCollection(Iterable<V> elements);

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the ordered collection
     * @return the created ordered collection
     */
    default Collection<V> createOrderedCollection(V... elements) {
        return createOrderedCollection(Arrays.asList(elements));
    }

    @Override default Collection<V> createCollection(V... elements) {
        return createOrderedCollection(elements);
    }

    @Override default Collection<V> createCollection(Iterable<V> elements) {
        return createOrderedCollection(elements);
    }

    @Test
    default void iterator_shouldReturnAnIteratorThatReturnsAllElementsInOrder_whenTheCollectionContainsElements() {
        // Arrange
        final List<V> expected = Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        );
        final Collection<V> sut = createOrderedCollection(expected);

        // Act
        final Iterator<V> iterator = sut.iterator();

        // Assert
        final List<V> actual = TestUtils.iteratorToList(iterator);
        assertEquals(expected, actual);
    }

    @Test
    default void iterator_shouldReturnAnIteratorThatReturnsNothing_whenTheCollectionIsEmpty() {
        // Arrange
        final Collection<V> sut = createOrderedCollection();

        // Act
        final Iterator<V> iterator = sut.iterator();

        // Assert
        final List<V> actual = TestUtils.iteratorToList(iterator);
        assertEquals(Collections.emptyList(), actual);
    }


    @Test
    default void toArray_shouldReturnAnArrayWithTheElementsInOrder_whenTheCollectionHasSomeElements() {
        // Arrange
        final Collection<V> sut = createCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        final Object[] result = sut.toArray();

        // Assert
        assertArrayEquals(new Object[] {
                new V("a"),
                new V("b"),
                new V("c")
        }, result);
    }

    @Test
    default void equals_shouldReturnTrue_whenTheElementsInTheCollectionAreInTheSameOrder() {
        // Arrange
        final Collection<V> sut0 = createCollection(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        );
        final Collection<V> sut1 = createCollection(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        );

        // Act
        boolean result = sut0.equals(sut1);

        // Assert
        assertTrue(result);
    }

    @Test
    default void equals_shouldReturnFalse_whenTheElementsInTheCollectionsAreInADifferentOrder() {
        // Arrange
        final Collection<V> sut0 = createCollection(
                new V("a"),
                new V("b")
        );
        final Collection<V> sut1 = createCollection(
                new V("c"),
                new V("d"),
                new V("a"),
                new V("b")
        );

        // Act
        boolean result = sut0.equals(sut1);

        // Assert
        assertFalse(result);
    }

}
