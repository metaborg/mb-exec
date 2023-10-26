package org.metaborg.util.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/** Tests the {@link ConsList} implementations. */
@SuppressWarnings({"unused", "deprecation"})
public abstract class ConsListTests implements CollectionTests {

    public abstract ConsList<V> createConsList(Iterable<V> elements);

    public ConsList<V> createConsList(V... elements) {
        return createConsList(Arrays.asList(elements));
    }

    @Override public Collection<V> createCollection(Iterable<V> elements) {
        return createConsList(elements);
    }

    @Test
    public void prepend_shouldAddTheElementToTheFrontOfTheList() {
        // Arrange
        final ConsList<V> sut = createConsList(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        final ConsList<V> result = sut.prepend(new V("d"));

        // Assert
        assertEquals(
                createConsList(
                        new V("d"),
                        new V("a"),
                        new V("b"),
                        new V("c")
                ),
                result
        );
    }

    @Test
    public void prepend2_shouldAddTheConsListToTheFrontOfThisList() {
        // Arrange
        final ConsList<V> sut = createConsList(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        final ConsList<V> result = sut.prepend(createConsList(
                new V("d"),
                new V("e"),
                new V("f")
        ));

        // Assert
        assertEquals(
                createConsList(
                        new V("d"),
                        new V("e"),
                        new V("f"),
                        new V("a"),
                        new V("b"),
                        new V("c")
                ),
                result
        );
    }

    @Test
    public void prepend2_shouldAddTheListToTheFrontOfThisList() {
        // Arrange
        final ConsList<V> sut = createConsList(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        final ConsList<V> result = sut.prepend(Arrays.asList(
                new V("d"),
                new V("e"),
                new V("f")
        ));

        // Assert
        assertEquals(
                createConsList(
                        new V("d"),
                        new V("e"),
                        new V("f"),
                        new V("a"),
                        new V("b"),
                        new V("c")
                ),
                result
        );
    }

    @Test
    public void append_shouldAddTheConsListToTheBackOfThisList() {
        // Arrange
        final ConsList<V> sut = createConsList(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        final ConsList<V> result = sut.append(createConsList(
                new V("d"),
                new V("e"),
                new V("f")
        ));

        // Assert
        assertEquals(
                createConsList(
                        new V("a"),
                        new V("b"),
                        new V("c"),
                        new V("d"),
                        new V("e"),
                        new V("f")
                ),
                result
        );
    }

    @Test
    public void tail_shouldReturnTheTailOfThisList_whenThisListIsNotEmpty() {
        // Arrange
        final ConsList<V> sut = createConsList(
                new V("a"),
                new V("b"),
                new V("c")
        );

        // Act
        final ConsList<V> result = sut.tail();

        // Assert
        assertEquals(
                createConsList(
                        new V("b"),
                        new V("c")
                ),
                result
        );
    }

    @Test
    public void tail_shouldReturnThisList_whenThisListIsEmpty() {
        // Arrange
        final ConsList<V> sut = createConsList();

        // Act
        final ConsList<V> result = sut.tail();

        // Assert
        assertEquals(sut, result);
    }
}
