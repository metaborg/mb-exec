package org.metaborg.util.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

import static org.junit.Assert.assertNotNull;
import static org.metaborg.util.collection.TestUtils.assertCollectionEquals;

/** Tests implementations of the {@link Iterable} interface. */
@SuppressWarnings("unused")
public interface IterableTests {

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the iterable
     * @return the created iterable
     */
    Iterable<V> createIterable(Iterable<V> elements);

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the iterable
     * @return the created iterable
     */
    default Iterable<V> createIterable(V... elements) {
        return createIterable(Arrays.asList(elements));
    }

    @Test
    default void iterator_shouldReturnAnIterator_whenTheIterableContainsElements() {
        // Arrange
        final List<V> expected = Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        );
        final Iterable<V> sut = createIterable(expected);

        // Act
        final Iterator<V> iterator = sut.iterator();

        // Assert
        assertNotNull(iterator);
        assertCollectionEquals(expected, TestUtils.iteratorToList(iterator));
    }

    @Test
    default void iterator_shouldReturnAnIterator_whenTheIterableIsEmpty() {
        // Arrange
        final Iterable<V> sut = createIterable();

        // Act
        final Iterator<V> iterator = sut.iterator();

        // Assert
        assertNotNull(iterator);
        assertCollectionEquals(Collections.emptyList(), TestUtils.iteratorToList(iterator));
    }

    @Test
    default void forEach_shouldApplyAnActionToEachElement_whenTheIterableContainsElements() {
        // Arrange
        final List<V> expected = Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        );
        final Iterable<V> sut = createIterable(expected);
        final List<V> actual = new ArrayList<V>();

        // Act
        sut.forEach(actual::add);

        // Assert
        assertCollectionEquals(expected, actual);
    }

    @Test
    default void forEach_shouldApplyAnActionToEachElement_whenTheIterableIsEmpty() {
        // Arrange
        final Iterable<V> sut = createIterable();
        final List<V> actual = new ArrayList<V>();

        // Act
        sut.forEach(actual::add);

        // Assert
        assertCollectionEquals(Collections.emptyList(), actual);
    }

    @Test
    default void spliterator_shouldReturnNonNullSpliterator_whenTheIterableContainsElements() {
        // Arrange
        final List<V> expected = Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        );
        final Iterable<V> sut = createIterable(expected);

        // Act
        final Spliterator<V> spliterator = sut.spliterator();

        // Assert
        assertNotNull(spliterator);
        // TODO: Actually test the `spliterator`
    }

    @Test
    default void spliterator_shouldReturnNonNullSpliterator_whenTheIterableIsEmpty() {
        // Arrange
        final Iterable<V> sut = createIterable();

        // Act
        final Spliterator<V> spliterator = sut.spliterator();

        // Assert
        assertNotNull(spliterator);
        // TODO: Actually test the `spliterator`
    }

}
