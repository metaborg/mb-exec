package org.metaborg.util.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import static org.junit.Assert.*;

/** Tests implementations of the immutable {@link List} interface. */
@SuppressWarnings("unused")
public interface ImmutableListTests extends ListTests, ImmutableCollectionTests {

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the list, ordered
     * @return the immutable list
     */
    List<V> createImmutableList(Iterable<V> elements);

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the list, ordered
     * @return the immutable list
     */
    default List<V> createImmutableList(V... elements) {
        return createList(Arrays.asList(elements));
    }

    @Override default List<V> createList(V... elements) {
        return createImmutableList(elements);
    }

    @Override default List<V> createList(Iterable<V> elements) {
        return createImmutableList(elements);
    }

    @Override default Collection<V> createImmutableCollection(V... elements) {
        return createImmutableList(elements);
    }

    @Override default Collection<V> createImmutableCollection(Iterable<V> elements) {
        return createImmutableList(elements);
    }

    @Override default Collection<V> createCollection(V... elements) {
        return createImmutableList(elements);
    }

    @Override default Collection<V> createCollection(Iterable<V> elements) {
        return createImmutableList(elements);
    }

    @Test
    default void add2_shouldThrow_whenTheListIsImmutable() {
        // Arrange
        final List<V> sut = createImmutableList();

        // Act/Assert
        assertThrows(UnsupportedOperationException.class, () -> {
            sut.add(0, new V("a"));
        });
    }

    @Test
    default void remove2_shouldThrow_whenTheListIsImmutable() {
        // Arrange
        final List<V> sut = createImmutableList();

        // Act/Assert
        assertThrows(UnsupportedOperationException.class, () -> {
            sut.remove(0);
        });
    }

    @Test
    default void set_shouldThrow_whenTheListIsImmutable() {
        // Arrange
        final List<V> sut = createImmutableList();

        // Act/Assert
        assertThrows(UnsupportedOperationException.class, () -> {
            sut.set(0, new V("a"));
        });
    }

    @Test
    default void replaceAll_shouldThrow_whenTheListIsImmutable() {
        // Arrange
        final List<V> sut = createImmutableList();

        // Act/Assert
        assertThrows(UnsupportedOperationException.class, () -> {
            sut.replaceAll(element -> new V(element.value.toUpperCase()));
        });
    }

    @Test
    default void sort_shouldThrow_whenTheListIsImmutable() {
        // Arrange
        final List<V> sut = createImmutableList();

        // Act/Assert
        assertThrows(UnsupportedOperationException.class, () -> {
            sut.sort(null);
        });
    }
}
