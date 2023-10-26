package org.metaborg.util.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.metaborg.util.collection.TestUtils.assertCollectionEquals;

/** Tests implementations of the {@link Iterator} interface. */
@SuppressWarnings("unused")
public interface IteratorTests {

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the iterator
     * @return the created iterator
     */
    Iterator<V> createIterator(Iterable<V> elements);

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the iterator
     * @return the created iterator
     */
    default Iterator<V> createIterator(V... elements) {
        return createIterator(Arrays.asList(elements));
    }

    @Test
    default void hasNext_shouldReturnFalse_whenTheIteratorHasNoMoreElements() {
        // Arrange
        final Iterator<V> sut = createIterator();

        // Act
        final boolean result = sut.hasNext();

        // Assert
        assertFalse(result);
    }

    @Test
    default void hasNext_shouldReturnTrue_whileTheIteratorHasOneElement() {
        // Arrange
        final Iterator<V> sut = createIterator(
                new V("a")
        );

        // Act
        final boolean result0 = sut.hasNext();
        sut.next();
        final boolean result1 = sut.hasNext();

        // Assert
        assertTrue(result0);
        assertFalse(result1);
    }

    @Test
    default void hasNext_shouldReturnTrue_whileTheIteratorHasMoreElements() {
        // Arrange
        final Iterator<V> sut = createIterator(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        final boolean result0 = sut.hasNext();
        sut.next();
        final boolean result1 = sut.hasNext();
        sut.next();
        final boolean result2 = sut.hasNext();
        sut.next();
        final boolean result3 = sut.hasNext();

        // Assert
        assertTrue(result0);
        assertTrue(result1);
        assertTrue(result2);
        assertFalse(result3);
    }

    @Test
    @SuppressWarnings("Convert2MethodRef")
    default void next_shouldThrowNoSuchElementException_whenTheIteratorHasNoMoreElements() {
        // Arrange
        final Iterator<V> sut = createIterator();

        // Act
        assertThrows(
                NoSuchElementException.class,
                () -> sut.next()
        );
    }

    @Test
    default void next_shouldReturnTheNextElement_whileTheIteratorHasOneElement() {
        // Arrange
        final Iterator<V> sut = createIterator(
                new V("a")
        );

        // Act
        final V result = sut.next();

        // Assert
        assertEquals(new V("a"), result);
    }

    @Test
    default void next_shouldReturnTheNextElement_whileTheIteratorHasMoreElements() {
        // Arrange
        final Iterator<V> sut = createIterator(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        final V result0 = sut.next();
        final V result1 = sut.next();
        final V result2 = sut.next();

        // Assert
        assertEquals(new V("a"), result0);
        assertEquals(new V("b"), result1);
        assertEquals(new V("c"), result2);
    }

    @Test
    default void forEachRemaining_shouldApplyAnActionToEachRemainingElement_whileTheIteratorHasElements() {
        // Arrange
        final List<V> expected = Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        );
        final Iterator<V> sut = createIterator(expected);
        final List<V> actual = new ArrayList<V>();

        // Act
        sut.forEachRemaining(actual::add);

        // Assert
        assertCollectionEquals(expected, actual);
    }

    @Test
    default void forEachRemaining_shouldApplyAnActionToEachRemainingElement_whileTheIteratorHasMoreElements() {
        // Arrange
        final Iterator<V> sut = createIterator(
                new V("a"),
                new V("b"),
                new V("c")
        );
        final List<V> actual = new ArrayList<V>();

        // Act
        sut.next(); // skip the first
        sut.forEachRemaining(actual::add);

        // Assert
        assertCollectionEquals(Arrays.asList(
                new V("b"),
                new V("c")
        ), actual);
    }

    @Test
    default void forEachRemaining_shouldDoNothing_whenTheIteratorHasNoMoreElements() {
        // Arrange
        final Iterator<V> sut = createIterator(
                new V("a"),
                new V("b"),
                new V("c")
        );
        final List<V> actual = new ArrayList<V>();

        // Act
        sut.next(); // skip the first
        sut.next(); // skip the second
        sut.next(); // skip the third
        sut.forEachRemaining(actual::add);

        // Assert
        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    default void forEachRemaining_shouldDoNothing_whenTheIteratorIsEmpty() {
        // Arrange
        final Iterator<V> sut = createIterator();
        final List<V> actual = new ArrayList<V>();

        // Act
        sut.forEachRemaining(actual::add);

        // Assert
        assertEquals(Collections.emptyList(), actual);
    }
}
