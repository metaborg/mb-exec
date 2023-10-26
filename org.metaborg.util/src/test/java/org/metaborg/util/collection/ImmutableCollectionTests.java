package org.metaborg.util.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.metaborg.util.collection.TestUtils.assertCollectionEquals;
import static org.metaborg.util.collection.TestUtils.assertSetEquals;

/** Tests implementations of the immutable {@link Collection} interface. */
@SuppressWarnings("unused")
public interface ImmutableCollectionTests extends CollectionTests {

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the collection
     * @return the created immutable collection
     */
    Collection<V> createImmutableCollection(Iterable<V> elements);

    /**
     * Creates an instance of the class-under-test with the specified elements.
     *
     * @param elements the elements in the collection
     * @return the created immutable collection
     */
    default Collection<V> createImmutableCollection(V... elements) {
        return createImmutableCollection(Arrays.asList(elements));
    }

    @Override default Collection<V> createCollection(Iterable<V> elements) {
        return createImmutableCollection(elements);
    }

    @Override default Collection<V> createCollection(V... elements) {
        return createImmutableCollection(elements);
    }

    @Test
    default void add_shouldThrow_whenTheCollectionIsImmutable() {
        // Arrange
        final Collection<V> sut = createImmutableCollection();

        // Act/Assert
        assertThrows(UnsupportedOperationException.class, () -> {
            sut.add(new V("a"));
        });
    }

    @Test
    default void remove_shouldThrow_whenTheCollectionIsImmutable() {
        // Arrange
        final Collection<V> sut = createImmutableCollection();

        // Act/Assert
        assertThrows(UnsupportedOperationException.class, () -> {
            sut.remove(new V("a"));
        });
    }

    @Test
    default void addAll_shouldThrow_whenTheCollectionIsImmutable() {
        // Arrange
        final Collection<V> sut = createImmutableCollection();

        // Act/Assert
        assertThrows(UnsupportedOperationException.class, () -> {
            sut.addAll(Arrays.asList(
                    new V("a"),
                    new V("b"),
                    new V("c")
            ));
        });
    }

    @Test
    default void removeAll_shouldThrow_whenTheCollectionIsImmutable() {
        // Arrange
        final Collection<V> sut = createImmutableCollection();

        // Act/Assert
        assertThrows(UnsupportedOperationException.class, () -> {
            sut.removeAll(Arrays.asList(
                    new V("a"),
                    new V("b"),
                    new V("c")
            ));
        });
    }

    @Test
    default void removeIf_shouldThrow_whenTheCollectionIsImmutable() {
        // Arrange
        final Collection<V> sut = createImmutableCollection();

        // Act/Assert
        assertThrows(UnsupportedOperationException.class, () -> {
            sut.removeIf(v -> true);
        });
    }

    @Test
    default void retainAll_shouldThrow_whenTheCollectionIsImmutable() {
        // Arrange
        final Collection<V> sut = createImmutableCollection();

        // Act/Assert
        assertThrows(UnsupportedOperationException.class, () -> {
            sut.retainAll(Arrays.asList(
                    new V("a"),
                    new V("b"),
                    new V("c")
            ));
        });
    }

    @Test
    default void clear_shouldThrow_whenTheCollectionIsImmutable() {
        // Arrange
        final Collection<V> sut = createImmutableCollection();

        // Act/Assert
        assertThrows(UnsupportedOperationException.class, sut::clear);
    }

}
