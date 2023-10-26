package org.metaborg.util.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** Tests implementations of the mutable {@link Set} interface. */
@SuppressWarnings("unused")
public interface MutableSetTests extends MutableCollectionTests, SetTests {

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the collection
     * @return the created mutable collection
     */
    Set<V> createMutableSet(Iterable<V> elements);

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the collection
     * @return the created mutable collection
     */
    default Set<V> createMutableSet(V... elements) {
        return createMutableSet(Arrays.asList(elements));
    }

    @Override default Collection<V> createMutableCollection(Iterable<V> elements) {
        return createMutableSet(elements);
    }

    @Override default Collection<V> createMutableCollection(V... elements) {
        return createMutableSet(elements);
    }

    @Override default Collection<V> createCollection(Iterable<V> elements) {
        return createMutableSet(elements);
    }

    @Override default Collection<V> createCollection(V... elements) {
        return createMutableSet(elements);
    }

    @Override default Set<V> createSet(V... elements) {
        return createMutableSet(elements);
    }

    @Override default Set<V> createSet(Iterable<V> elements) {
        return createMutableSet(elements);
    }

    @Test
    default void add_shouldReturnFalse_whenTheSetAlreadyContainsTheElement() {
        // Arrange
        final Set<V> sut = createMutableSet(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        boolean result = sut.add(new V("b"));

        // Assert
        assertFalse(result);
        assertEquals(new HashSet<>(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        )), sut);
    }

    @Test
    default void add_shouldReturnTrue_whenTheSetDoesNotContainTheElement() {
        // Arrange
        final Set<V> sut = createMutableSet(
                new V("a")
        );

        // Act
        boolean result = sut.add(new V("b"));

        // Assert
        assertTrue(result);
        assertEquals(new HashSet<>(Arrays.asList(
                new V("a"),
                new V("b")
        )), sut);
    }

    @Test
    default void addAll_shouldReturnFalse_whenTheSetAlreadyContainsAllElements() {
        // Arrange
        final Set<V> sut = createMutableSet(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        boolean result = sut.addAll(Arrays.asList(
                new V("b"),
                new V("c")
        ));

        // Assert
        assertFalse(result);
        assertEquals(new HashSet<>(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        )), sut);
    }

    @Test
    default void addAll_shouldReturnTrue_whenTheSetDoesNotContainAllElements() {
        // Arrange
        final Set<V> sut = createMutableSet(
                new V("a")
        );

        // Act
        boolean result = sut.addAll(Arrays.asList(
                new V("b"),
                new V("c")
        ));

        // Assert
        assertTrue(result);
        assertEquals(new HashSet<>(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        )), sut);
    }

    @Test
    default void addAll_shouldNotAddDuplicateElements() {
        // Arrange
        final Set<V> sut = createMutableSet(
                new V("a")
        );

        // Act
        boolean result = sut.addAll(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("a"),
                new V("b"),
                new V("c")
        ));

        // Assert
        assertTrue(result);
        assertEquals(new HashSet<>(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        )), sut);
    }

    @Test
    default void retainAll_shouldReturnFalse_whenTheSetDoesNotChange() {
        // Arrange
        final Set<V> sut = createMutableSet(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        boolean result = sut.retainAll(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        ));

        // Assert
        assertFalse(result);
        assertEquals(new HashSet<>(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        )), sut);
    }

    @Test
    default void retainAll_shouldReturnTrue_whenTheSetChanges() {
        // Arrange
        final Set<V> sut = createMutableSet(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        boolean result = sut.retainAll(Arrays.asList(
                new V("a"),
                new V("b")
        ));

        // Assert
        assertTrue(result);
        assertEquals(new HashSet<>(Arrays.asList(
                new V("a"),
                new V("b")
        )), sut);
    }

    @Test
    default void retainAll_shouldNotRetainDuplicateElements() {
        // Arrange
        final Set<V> sut = createMutableSet(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        );

        // Act
        boolean result = sut.retainAll(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("a"),
                new V("b"),
                new V("c")
        ));

        // Assert
        assertFalse(result);
        assertEquals(new HashSet<>(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        )), sut);
    }

    @Test
    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    default void removeAll_shouldDealWithDuplicateElements() {
        // Arrange
        final Set<V> sut = createMutableSet(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        );

        // Act
        boolean result = sut.removeAll(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("a"),
                new V("b"),
                new V("c")
        ));

        // Assert
        assertTrue(result);
        assertEquals(new HashSet<>(Arrays.asList(
                new V("d")
        )), sut);
    }

}
