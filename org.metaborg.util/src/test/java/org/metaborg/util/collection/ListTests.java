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
 * Tests implementations of the {@link List} interface:
 * collections whose elements are ordered and allow for duplicates.
 */
@SuppressWarnings("unused")
public interface ListTests extends OrderedCollectionTests {

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the list, ordered
     * @return the mutable list
     */
    List<V> createList(Iterable<V> elements);

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the list, ordered
     * @return the mutable list
     */
    default List<V> createList(V... elements) {
        return createList(Arrays.asList(elements));
    }

    @Override default Collection<V> createOrderedCollection(V... elements) {
        return createList(elements);
    }

    @Override default Collection<V> createOrderedCollection(Iterable<V> elements) {
        return createList(elements);
    }

    @Test
    default void indexOf_shouldReturnTheIndexOfTheFirstOccurrenceOfTheElement_whenTheElementIsInTheList() {
        // Arrange
        final List<V> sut = createList(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        );

        // Act
        int result = sut.indexOf(new V("c"));

        // Assert
        assertEquals(2, result);
    }

    @Test
    default void indexOf_shouldReturnMinusOne_whenTheElementIsNotInTheList() {
        // Arrange
        final List<V> sut = createList(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        );

        // Act
        int result = sut.indexOf(new V("X"));

        // Assert
        assertEquals(-1, result);
    }

    @Test
    default void lastIndexOf_shouldReturnTheIndexOfTheLastOccurrenceOfTheElement_whenTheElementIsInTheList() {
        // Arrange
        final List<V> sut = createList(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        );

        // Act
        int result = sut.lastIndexOf(new V("c"));

        // Assert
        assertEquals(2, result);
    }

    @Test
    default void lastIndexOf_shouldReturnTheIndexOfTheLastOccurrenceOfTheElement_whenTheElementIsInTheListMultipleTimes() {
        // Arrange
        final List<V> sut = createList(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d"),
                new V("c")
        );

        // Assume
        assumeTrue(sut.size() == 5); // Test is only valid if there are two 'c' elements, thus not for a 'Set' List.

        // Act
        int result = sut.lastIndexOf(new V("c"));

        // Assert
        assertEquals(4, result);
    }

    @Test
    default void lastIndexOf_shouldReturnMinusOne_whenTheElementIsNotInTheList() {
        // Arrange
        final List<V> sut = createList(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        );

        // Act
        int result = sut.lastIndexOf(new V("X"));

        // Assert
        assertEquals(-1, result);
    }

    @Test
    default void subList_shouldReturnAViewOfTheList_whenTheIndexesAreWithinBounds() {
        // Arrange
        final List<V> sut = createList(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d"),
                new V("e")
        );

        // Act
        final List<V> result = sut.subList(1, 4);

        // Assert
        assertEquals(Arrays.asList(
                new V("b"),
                new V("c"),
                new V("d")
        ), result);
    }

    @Test
    default void subList_shouldThrowAnException_whenTheIndexesAreNotWithinBounds() {
        // Arrange
        final List<V> sut = createList(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d"),
                new V("e")
        );

        // Act/Assert
        assertThrows(IndexOutOfBoundsException.class, () -> sut.subList(-1, 4));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.subList(1, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.subList(2, 1));
    }

}
