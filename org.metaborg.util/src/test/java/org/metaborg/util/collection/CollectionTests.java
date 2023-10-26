package org.metaborg.util.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;
import static org.metaborg.util.collection.TestUtils.assertCollectionArrayEquals;
import static org.metaborg.util.collection.TestUtils.assertCollectionEquals;

/**
 * Tests implementations of the {@link Collection} interface:
 * collections whose elements provide no guarantees related to their order or whether they allow duplicates.
 */
@SuppressWarnings("unused")
public interface CollectionTests extends IterableTests {

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the collection
     * @return the created collection
     */
    Collection<V> createCollection(Iterable<V> elements);

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the collection
     * @return the created collection
     */
    default Collection<V> createCollection(V... elements) {
        return createCollection(Arrays.asList(elements));
    }

    @Override default Iterable<V> createIterable(V... elements) {
        return createCollection(elements);
    }

    @Override default Iterable<V> createIterable(Iterable<V> elements) {
        return createCollection(elements);
    }

    @Test
    default void size_shouldReturn0_whenTheCollectionHasNoElements() {
        // Arrange
        final Collection<V> sut = createCollection();

        // Act
        int result = sut.size();

        // Assert
        assertEquals(0, result);
    }

    @Test
    default void size_shouldReturn1_whenTheCollectionHasOneElement() {
        // Arrange
        final Collection<V> sut = createCollection(
                new V("a")
        );

        // Act
        int result = sut.size();

        // Assert
        assertEquals(1, result);
    }

    @Test
    default void size_shouldReturnTheNumberOfElementsInTheCollection_whenTheCollectionHasSomeElements() {
        // Arrange
        final Collection<V> sut = createCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        int result = sut.size();

        // Assert
        assertEquals(3, result);
    }

    @Test
    default void isEmpty_shouldReturnTrue_whenTheCollectionHasNoElements() {
        // Arrange
        final Collection<V> sut = createCollection();

        // Act
        boolean result = sut.isEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    default void isEmpty_shouldReturnFalse_whenTheCollectionHasOneElement() {
        // Arrange
        final Collection<V> sut = createCollection(
                new V("a")
        );

        // Act
        boolean result = sut.isEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    default void isEmpty_shouldReturnFalse_whenTheCollectionHasSomeElements() {
        // Arrange
        final Collection<V> sut = createCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        boolean result = sut.isEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    default void contains_shouldReturnFalse_whenTheCollectionHasNoElements() {
        // Arrange
        final Collection<V> sut = createCollection();

        // Act
        boolean result = sut.contains(new V("a"));

        // Assert
        assertFalse(result);
    }

    @Test
    default void contains_shouldReturnFalse_whenTheCollectionDoesNotContainTheElement() {
        // Arrange
        final Collection<V> sut = createCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        boolean result = sut.contains(new V("d"));

        // Assert
        assertFalse(result);
    }

    @Test
    default void contains_shouldReturnTrue_whenTheCollectionContainsTheElement() {
        // Arrange
        final Collection<V> sut = createCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        boolean result = sut.contains(new V("b"));

        // Assert
        assertTrue(result);
    }

    @Test
    default void contains_shouldReturnTrue_whenTheCollectionContainsTheSameElement() {
        // Arrange
        final V element = new V("a");
        final Collection<V> sut = createCollection(
                element,
                new V("b"),
                new V("c")
        );

        // Act
        boolean result = sut.contains(element);

        // Assert
        assertTrue(result);
    }

    @Test
    default void containsAll_shouldReturnTrue_whenTheCollectionContainsAllElements() {
        // Arrange
        final Collection<V> sut = createCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        boolean result = sut.containsAll(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        ));

        // Assert
        assertTrue(result);
    }

    @Test
    default void containsAll_shouldReturnFalse_whenTheCollectionDoesNotContainAllElements() {
        // Arrange
        final Collection<V> sut = createCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        boolean result = sut.containsAll(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("d")
        ));

        // Assert
        assertFalse(result);
    }

    @Test
    default void containsAll_shouldReturnFalse_whenTheCollectionDoesNotContainAnyElements() {
        // Arrange
        final Collection<V> sut = createCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        boolean result = sut.containsAll(Arrays.asList(
                new V("d"),
                new V("e"),
                new V("f")
        ));

        // Assert
        assertFalse(result);
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    default void containsAll_shouldThrow_whenTheArgumentIsNull() {
        // Arrange
        final Collection<V> sut = createCollection();

        // Act
        boolean result = sut.containsAll(null);

        // Assert
        assertFalse(result);
    }

    @Test
    default void toArray_shouldReturnAnArrayWithTheElements_whenTheCollectionHasSomeElements() {
        // Arrange
        final Collection<V> sut = createCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        final Object[] result = sut.toArray();

        // Assert
        assertCollectionArrayEquals(new Object[] {
                new V("a"),
                new V("b"),
                new V("c")
        }, result);
    }

    @Test
    @SuppressWarnings("MismatchedReadAndWriteOfArray")
    default void toArray_shouldNotModifyTheCollection_whenModifyingTheReturnedArray() {
        // Arrange
        final Collection<V> sut = createCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        final Object[] result = sut.toArray();
        result[1] = new V("X");

        // Assert
        assertCollectionEquals(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        ), sut);
    }

    @Test
    default void toArray_shouldCopyElementsIntoAnArray_whenTheCollectionHasSomeElementsAndTheArrayIsLargeEnough() {
        // Arrange
        final Collection<V> sut = createCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );
        final Object[] array = new Object[3];

        // Act
        final Object[] result = sut.toArray(array);

        // Assert
        assertSame(array, result);
        assertCollectionArrayEquals(new Object[] {
                new V("a"),
                new V("b"),
                new V("c")
        }, result);
    }

    @Test
    default void toArray_shouldCopyElementsIntoANewArray_whenTheCollectionHasSomeElementsAndTheArrayIsTooSmall() {
        // Arrange
        final Collection<V> sut = createCollection(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        );
        final Object[] array = new Object[2];

        // Act
        final Object[] result = sut.toArray(array);

        // Assert
        assertNotSame(array, result);
        assertCollectionArrayEquals(new Object[] {
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        }, result);
    }

    @Test
    default void toArray_shouldCopyElementsIntoANewArray_whenTheCollectionHasSomeElementsAndTheArrayIsEmpty() {
        // Arrange
        final Collection<V> sut = createCollection(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        );

        // Act
        final V[] result = sut.toArray(new V[0]);

        // Assert
        assertCollectionArrayEquals(new Object[] {
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        }, result);
    }

    @Test
    @SuppressWarnings({"ConstantValue", "EqualsWithItself"})
    default void equals_shouldReturnTrue_whenTheCollectionsAreTheSameInstance() {
        // Arrange
        final Collection<V> sut = createCollection(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        );

        // Act
        boolean result = sut.equals(sut);

        // Assert
        assertTrue(result);
    }

    @Test
    default void equals_shouldReturnTrue_whenTheElementsInTheCollectionAreEqual() {
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
    default void equals_shouldReturnFalse_whenTheElementsInTheCollectionsAreNotEqual() {
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
                new V("X")
        );

        // Act
        boolean result = sut0.equals(sut1);

        // Assert
        assertFalse(result);
    }

    @Test
    default void equals_shouldReturnFalse_whenTheCollectionsAreNotTheSameSize() {
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
                new V("c")
        );

        // Act
        boolean result = sut0.equals(sut1);

        // Assert
        assertFalse(result);
    }

    @Test
    default void hashCode_shouldReturnTheSameValue_whenTheCollectionsAreEqual() {
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

        // Assume
        assumeTrue(sut0.equals(sut1));

        // Act
        int result0 = sut0.hashCode();
        int result1 = sut1.hashCode();

        // Assert
        assertEquals(result0, result1);
    }

    @Test
    default void hashCode_shouldReturnTheSameValue_whenCalledMultipleTimes() {
        // Arrange
        final Collection<V> sut = createCollection(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        );

        // Act
        int result0 = sut.hashCode();
        int result1 = sut.hashCode();

        // Assert
        assertEquals(result0, result1);
    }

}
