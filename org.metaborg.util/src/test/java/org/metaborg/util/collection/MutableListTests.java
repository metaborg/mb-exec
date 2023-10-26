package org.metaborg.util.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import static org.junit.Assert.*;

/** Tests implementations of the mutable {@link List} interface. */
@SuppressWarnings("unused")
public interface MutableListTests extends ListTests, MutableCollectionTests {

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the list, ordered
     * @return the created list
     */
    List<V> createMutableList(Iterable<V> elements);

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the list, ordered
     * @return the created list
     */
    default List<V> createMutableList(V... elements) {
        return createList(Arrays.asList(elements));
    }

    @Override default List<V> createList(V... elements) {
        return createMutableList(elements);
    }

    @Override default List<V> createList(Iterable<V> elements) {
        return createMutableList(elements);
    }

    @Override default Collection<V> createMutableCollection(V... elements) {
        return createMutableList(elements);
    }

    @Override default Collection<V> createMutableCollection(Iterable<V> elements) {
        return createMutableList(elements);
    }

    @Override default Collection<V> createCollection(V... elements) {
        return createMutableList(elements);
    }

    @Override default Collection<V> createCollection(Iterable<V> elements) {
        return createMutableList(elements);
    }

    @Test
    @SuppressWarnings("ConstantValue")
    default void add_shouldAppendTheElementToTheEndOfTheList_whenTheListIsNotEmpty() {
        // Arrange
        final List<V> sut = createMutableList(
                new V("a"),
                new V("b")
        );
        final V element = new V("c");

        // Act
        final boolean result = sut.add(element);

        // Assert
        assertTrue(result);
        assertEquals(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        ), sut);
    }

    @Test
    @SuppressWarnings("ConstantValue")
    default void add_shouldAppendTheElementToTheEndOfTheList_whenTheListIsEmpty() {
        // Arrange
        final List<V> sut = createMutableList();
        final V element = new V("a");

        // Act
        final boolean result = sut.add(element);

        // Assert
        assertTrue(result);
        assertEquals(Collections.singletonList(
                new V("a")
        ), sut);
    }

    @Test
    default void add_shouldInsertAtTheSpecifiedIndex_whenTheListIsNotEmptyAndTheIndexIsValid() {
        // Arrange
        final List<V> sut = createMutableList(
                new V("a"),
                new V("b")
        );
        final V element = new V("c");

        // Act
        sut.add(1, element);

        // Assert
        assertEquals(Arrays.asList(
                new V("a"),
                new V("c"),
                new V("b")
        ), sut);
    }

    @Test
    default void add_shouldInsertAtTheStartOfTheList_whenTheIndexIsZero() {
        // Arrange
        final List<V> sut = createMutableList(
                new V("a"),
                new V("b")
        );
        final V element = new V("c");

        // Act
        sut.add(0, element);

        // Assert
        assertEquals(Arrays.asList(
                new V("c"),
                new V("a"),
                new V("b")
        ), sut);
    }

    @Test
    default void add_shouldInsertAtTheEndOfTheList_whenTheIndexIsValid() {
        // Arrange
        final List<V> sut = createMutableList(
                new V("a"),
                new V("b")
        );
        final V element = new V("c");

        // Act
        sut.add(2, element);

        // Assert
        assertEquals(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c")
        ), sut);
    }

    @Test
    default void add_shouldThrow_whenTheIndexIsNegative() {
        // Arrange
        final List<V> sut = createMutableList();
        final V element = new V("a");

        // Act/Assert
        assertThrows(IndexOutOfBoundsException.class, () -> sut.add(-1, element));
    }

    @Test
    default void add_shouldThrow_whenTheIndexIsTooLarge() {
        // Arrange
        final List<V> sut = createMutableList();
        final V element = new V("a");

        // Act/Assert
        assertThrows(IndexOutOfBoundsException.class, () -> sut.add(2, element));
    }

    @Test
    default void remove_shouldRemoveTheElementFromTheList_whenTheListContainsTheElement() {
        // Arrange
        final List<V> sut = createMutableList(
                new V("a"),
                new V("b"),
                new V("c")
        );
        final V element = new V("b");

        // Act
        final boolean result = sut.remove(element);

        // Assert
        assertTrue(result);
        assertEquals(Arrays.asList(
                new V("a"),
                new V("c")
        ), sut);
    }

    @Test
    default void remove_shouldRemoveAndReturnTheElementAtTheSpecifiedIndex_whenTheIndexIsValid() {
        // Arrange
        final List<V> sut = createMutableList(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        final V result = sut.remove(1);

        // Assert
        assertEquals(new V("b"), result);
        assertEquals(Arrays.asList(
                new V("a"),
                new V("c")
        ), sut);
    }

    @Test
    default void remove_shouldRemoveAndReturnTheElement_whenTheIndexIsZero() {
        // Arrange
        final List<V> sut = createMutableList(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        final V result = sut.remove(0);

        // Assert
        assertEquals(new V("a"), result);
        assertEquals(Arrays.asList(
                new V("b"),
                new V("c")
        ), sut);
    }

    @Test
    default void remove_shouldRemoveAndReturnTheElement_whenTheIndexIsAtTheEndOfTheList() {
        // Arrange
        final List<V> sut = createMutableList(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        final V result = sut.remove(2);

        // Assert
        assertEquals(new V("c"), result);
        assertEquals(Arrays.asList(
                new V("a"),
                new V("b")
        ), sut);
    }

    @Test
    @SuppressWarnings("DataFlowIssue")
    default void remove_shouldThrow_whenTheIndexIsNegative() {
        // Arrange
        final List<V> sut = createMutableList();

        // Act/Assert
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(-1));
    }

    @Test
    default void remove_shouldThrow_whenTheIndexIsTooLarge() {
        // Arrange
        final List<V> sut = createMutableList();

        // Act/Assert
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(0));
    }

    @Test
    default void set_shouldReplaceTheElementAtTheSpecifiedIndex_whenTheIndexIsValid() {
        // Arrange
        final List<V> sut = createMutableList(
                new V("a"),
                new V("b"),
                new V("c")
        );
        final V element = new V("X");

        // Act
        final V result = sut.set(1, element);

        // Assert
        assertEquals(new V("b"), result);
        assertEquals(Arrays.asList(
                new V("a"),
                new V("X"),
                new V("c")
        ), sut);
    }

    @Test
    default void set_shouldReplaceTheElement_whenTheIndexIsZero() {
        // Arrange
        final List<V> sut = createMutableList(
                new V("a"),
                new V("b"),
                new V("c")
        );
        final V element = new V("X");

        // Act
        final V result = sut.set(0, element);

        // Assert
        assertEquals(new V("a"), result);
        assertEquals(Arrays.asList(
                new V("X"),
                new V("b"),
                new V("c")
        ), sut);
    }

    @Test
    default void set_shouldReplaceTheElement_whenTheIndexIsAtTheEndOfTheList() {
        // Arrange
        final List<V> sut = createMutableList(
                new V("a"),
                new V("b"),
                new V("c")
        );
        final V element = new V("X");

        // Act
        final V result = sut.set(2, element);

        // Assert
        assertEquals(new V("c"), result);
        assertEquals(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("X")
        ), sut);
    }

    @Test
    default void set_shouldThrow_whenTheIndexIsNegative() {
        // Arrange
        final List<V> sut = createMutableList();
        final V element = new V("X");

        // Act/Assert
        assertThrows(IndexOutOfBoundsException.class, () -> sut.set(-1, element));
    }

    @Test
    default void set_shouldThrow_whenTheIndexIsTooLarge() {
        // Arrange
        final List<V> sut = createMutableList();
        final V element = new V("X");

        // Act/Assert
        assertThrows(IndexOutOfBoundsException.class, () -> sut.set(0, element));
    }

    @Test
    default void addAll_shouldAppendTheElementsToTheEndOfTheList_whenTheListIsNotEmpty() {
        // Arrange
        final List<V> sut = createMutableList(
                new V("a"),
                new V("b")
        );
        final Collection<V> elements = Arrays.asList(
                new V("c"),
                new V("d")
        );

        // Act
        final boolean result = sut.addAll(elements);

        // Assert
        assertTrue(result);
        assertEquals(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        ), sut);
    }

    @Test
    default void addAll_shouldAppendTheElementsToTheEndOfTheList_whenTheListIsEmpty() {
        // Arrange
        final List<V> sut = createMutableList();
        final Collection<V> elements = Arrays.asList(
                new V("a"),
                new V("b")
        );

        // Act
        final boolean result = sut.addAll(elements);

        // Assert
        assertTrue(result);
        assertEquals(Arrays.asList(
                new V("a"),
                new V("b")
        ), sut);
    }

    @Test
    default void removeAll_shouldRemoveTheElementsFromTheList_whenTheListContainsTheElements() {
        // Arrange
        final List<V> sut = createMutableList(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        );
        final Collection<V> elements = Arrays.asList(
                new V("b"),
                new V("d")
        );

        // Act
        final boolean result = sut.removeAll(elements);

        // Assert
        assertTrue(result);
        assertEquals(Arrays.asList(
                new V("a"),
                new V("c")
        ), sut);
    }

    @Test
    default void removeIf_shouldRemoveTheElementsFromTheList_whenThePredicateReturnsTrue() {
        // Arrange
        final List<V> sut = createMutableList(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        );
        final Predicate<V> predicate = element -> element.value.equals("b") || element.value.equals("d");

        // Act
        final boolean result = sut.removeIf(predicate);

        // Assert
        assertTrue(result);
        assertEquals(Arrays.asList(
                new V("a"),
                new V("c")
        ), sut);
    }

    @Test
    default void retainAll_shouldRetainTheElementsFromTheList_whenTheListContainsTheElements() {
        // Arrange
        final List<V> sut = createMutableList(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        );
        final Collection<V> elements = Arrays.asList(
                new V("d"),
                new V("b")
        );

        // Act
        final boolean result = sut.retainAll(elements);

        // Assert
        assertTrue(result);
        assertEquals(Arrays.asList(
                new V("b"),
                new V("d")
        ), sut);
    }

    @Test
    default void replaceAll_shouldReplaceTheElementsInTheList_whenTheListContainsTheElements() {
        // Arrange
        final List<V> sut = createMutableList(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        );
        final UnaryOperator<V> operator = element -> new V(element.value.toUpperCase());

        // Act
        sut.replaceAll(operator);

        // Assert
        assertEquals(Arrays.asList(
                new V("A"),
                new V("B"),
                new V("C"),
                new V("D")
        ), sut);
    }

    @Test
    default void replaceAll_shouldThrow_whenTheArgumentIsNull() {
        // Arrange
        final List<V> sut = createMutableList();

        // Act/Assert
        assertThrows(NullPointerException.class, () -> sut.replaceAll(null));

    }

    @Test
    default void sort_shouldSortTheElementsInTheList_whenTheListContainsElements() {
        // Arrange
        final List<V> sut = createMutableList(
                new V("d"),
                new V("b"),
                new V("a"),
                new V("c")
        );

        // Act
        sut.sort(null);

        // Assert
        assertEquals(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        ), sut);
    }

    @Test
    default void sort_shouldUseTheProvidedComparator() {
        // Arrange
        final List<V> sut = createMutableList(
                new V("d"),
                new V("b"),
                new V("a"),
                new V("c")
        );

        // Act
        sut.sort((o1, o2) -> o2.value.compareTo(o1.value));

        // Assert
        assertEquals(Arrays.asList(
                new V("d"),
                new V("c"),
                new V("b"),
                new V("a")
        ), sut);
    }
}
