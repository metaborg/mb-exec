package org.metaborg.util.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.metaborg.util.collection.TestUtils.*;

/** Tests implementations of the mutable {@link Collection} interface. */
@SuppressWarnings("unused")
public interface MutableCollectionTests extends CollectionTests {

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the collection
     * @return the created mutable collection
     */
    Collection<V> createMutableCollection(Iterable<V> elements);

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the collection
     * @return the created mutable collection
     */
    default Collection<V> createMutableCollection(V... elements) {
        return createMutableCollection(Arrays.asList(elements));
    }

    @Override default Collection<V> createCollection(Iterable<V> elements) {
        return createMutableCollection(elements);
    }

    @Override default Collection<V> createCollection(V... elements) {
        return createMutableCollection(elements);
    }

    @Test
    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    default void add_shouldReturnTrue_whenTheCollectionDidNotContainTheElement() {
        // Arrange
        final Collection<V> sut = createMutableCollection();

        // Act
        final V a = new V("a");
        boolean result = sut.add(a);

        // Assert
        assertTrue(result);
        assertTrue(sut.contains(a));
        assertCollectionEquals(Arrays.asList(
                new V("a")
        ), sut);
    }

    @Test
    default void add_shouldAddTheElements_whenAddingDifferentElements() {
        // Arrange
        final Collection<V> sut = createMutableCollection(
                new V("a")
        );

        // Act
        final V b = new V("b");
        final V c = new V("c");
        boolean result0 = sut.add(b);
        boolean result1 = sut.add(c);

        // Assert
        assertTrue(result0);
        assertTrue(result1);
        assertCollectionEquals(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        ), sut);
    }

    @Test
    default void remove_shouldReturnFalse_whenTheCollectionIsEmpty() {
        // Arrange
        final Collection<V> sut = createMutableCollection();

        // Act
        boolean result = sut.remove(new V("X"));

        // Assert
        assertFalse(result);
    }

    @Test
    default void remove_shouldReturnFalse_whenTheCollectionDidNotContainTheElement() {
        // Arrange
        final Collection<V> sut = createMutableCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        boolean result = sut.remove(new V("X"));

        // Assert
        assertFalse(result);
    }

    @Test
    default void remove_shouldReturnTrue_whenTheCollectionContainedTheElement() {
        // Arrange
        final Collection<V> sut = createMutableCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        boolean result = sut.remove(new V("a"));

        // Assert
        assertTrue(result);
        assertFalse(sut.contains(new V("a")));
        assertCollectionEquals(Arrays.asList(
                new V("b"),
                new V("c")
        ), sut);
    }

    @Test
    default void remove_shouldReturnTrue_whenTheCollectionContainedTheSameElement() {
        // Arrange
        final V a = new V("a");
        final Collection<V> sut = createMutableCollection(
                a,
                new V("b"),
                new V("c")
        );

        // Act
        boolean result = sut.remove(a);

        // Assert
        assertTrue(result);
        assertFalse(sut.contains(a));
        assertCollectionEquals(Arrays.asList(
                new V("b"),
                new V("c")
        ), sut);
    }

    @Test
    default void addAll_shouldReturnTrue_whenTheCollectionDidNotContainAnyOfTheElements() {
        // Arrange
        final Collection<V> sut = createMutableCollection();

        // Act
        boolean result = sut.addAll(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        ));

        // Assert
        assertTrue(result);
        assertCollectionEquals(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        ), sut);
    }

    @Test
    default void addAll_shouldReturnTrue_whenTheCollectionContainedSomeOfTheElements() {
        // Arrange
        final Collection<V> sut = createMutableCollection(
                new V("a"),
                new V("b")
        );

        // Act
        boolean result = sut.addAll(Arrays.asList(
                new V("b"),
                new V("c")
        ));

        // Assert
        assertFalse(result);
        assertSetEquals(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        ), sut);
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    default void addAll_shouldThrow_whenTheArgumentIsNull() {
        // Arrange
        final Collection<V> sut = createMutableCollection();

        // Act/Assert
        assertThrows(NullPointerException.class, () -> {
            sut.addAll(null);
        });
    }

    @Test
    default void removeAll_shouldReturnFalse_whenTheCollectionDidNotContainAnyOfTheElements() {
        // Arrange
        final Collection<V> sut = createMutableCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        boolean result = sut.removeAll(Arrays.asList(
                new V("X"),
                new V("Y"),
                new V("Z")
        ));

        // Assert
        assertFalse(result);
        assertCollectionEquals(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        ), sut);
    }

    @Test
    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    default void removeAll_shouldReturnTrue_whenTheCollectionContainedSomeOfTheElements() {
        // Arrange
        final Collection<V> sut = createMutableCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        boolean result = sut.removeAll(Arrays.asList(
                new V("b"),
                new V("c"),
                new V("d")
        ));

        // Assert
        assertTrue(result);
        assertCollectionEquals(Arrays.asList(
                new V("a")
        ), sut);
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    default void removeAll_shouldThrow_whenTheArgumentIsNull() {
        // Arrange
        final Collection<V> sut = createMutableCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act/Assert
        assertThrows(NullPointerException.class, () -> {
            sut.removeAll(null);
        });
    }

    @Test
    default void removeIf_shouldRemoveAllElementsThatMatchThePredicate_whenTheCollectionContainsElements() {
        // Arrange
        final Collection<V> sut = createMutableCollection(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d"),
                new V("e")
        );

        // Act
        boolean result = sut.removeIf(v -> v.value.equals("b") || v.value.equals("d") || v.value.equals("e"));

        // Assert
        assertTrue(result);
        assertCollectionEquals(Arrays.asList(
                new V("a"),
                new V("c")
        ), sut);
    }

    @Test
    default void removeIf_shouldRemoveAllElements_whenTheCollectionContainsElements() {
        // Arrange
        final Collection<V> sut = createMutableCollection(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d"),
                new V("e")
        );

        // Act
        boolean result = sut.removeIf(v -> true);

        // Assert
        assertTrue(result);
        assertCollectionEquals(Collections.emptyList(), sut);
    }

    @Test
    @SuppressWarnings("ConstantValue")
    default void removeIf_shouldReturnFalse_whenTheCollectionContainsNoMatchingElements() {
        // Arrange
        final Collection<V> sut = createMutableCollection(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d"),
                new V("e")
        );

        // Act
        boolean result = sut.removeIf(v -> false);

        // Assert
        assertFalse(result);
        assertCollectionEquals(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d"),
                new V("e")
        ), sut);
    }

    @Test
    default void removeIf_shouldReturnFalse_whenTheCollectionIsEmpty() {
        // Arrange
        final Collection<V> sut = createMutableCollection();

        // Act
        boolean result = sut.removeIf(v -> true);

        // Assert
        assertFalse(result);
        assertCollectionEquals(Collections.emptyList(), sut);
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    default void removeIf_shouldThrowAnException_whenTheArgumentIsNull() {
        // Arrange
        final Collection<V> sut = createMutableCollection();

        // Act/Assert
        assertThrows(NullPointerException.class, () -> {
            sut.removeIf(null);
        });
    }

    @Test
    default void retainAll_shouldReturnFalse_whenTheCollectionDidNotContainAnyOfTheElements() {
        // Arrange
        final Collection<V> sut = createMutableCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        boolean result = sut.retainAll(Arrays.asList(
                new V("X"),
                new V("Y"),
                new V("Z")
        ));

        // Assert
        assertFalse(result);
        assertCollectionEquals(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        ), sut);
    }

    @Test
    default void retainAll_shouldReturnTrue_whenTheCollectionContainedSomeOfTheElements() {
        // Arrange
        final Collection<V> sut = createMutableCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        boolean result = sut.retainAll(Arrays.asList(
                new V("b"),
                new V("c"),
                new V("d")
        ));

        // Assert
        assertTrue(result);
        assertCollectionEquals(Arrays.asList(
                new V("b"),
                new V("c")
        ), sut);
    }

    @Test
    @SuppressWarnings("SuspiciousMethodCalls")
    default void retainAll_shouldClearTheCollection_whenTheArgumentIsAnEmptyCollection() {
        // Arrange
        final Collection<V> sut = createMutableCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        boolean result = sut.retainAll(Collections.emptyList());

        // Assert
        assertTrue(result);
        assertCollectionEquals(Collections.emptyList(), sut);
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    default void retainAll_shouldThrowAnException_whenArgumentIsNull() {
        // Arrange
        final Collection<V> sut = createMutableCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act/Assert
        assertThrows(NullPointerException.class, () -> {
            sut.retainAll(null);
        });
    }

    @Test
    default void clear_shouldRemoveAllElements_whenTheCollectionContainsElements() {
        // Arrange
        final Collection<V> sut = createMutableCollection(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        sut.clear();

        // Assert
        assertCollectionEquals(Collections.emptyList(), sut);
    }

    @Test
    default void clear_shouldDoNothing_whenTheCollectionIsEmpty() {
        // Arrange
        final Collection<V> sut = createMutableCollection();

        // Act
        sut.clear();

        // Assert
        assertCollectionEquals(Collections.emptyList(), sut);
    }

}
