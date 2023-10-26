package org.metaborg.util.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

/** Tests the {@link Bag} implementations. */
@SuppressWarnings({"unused", "deprecation"})
public abstract class BagTests implements CollectionTests {

    public abstract Bag<V> createBag(Iterable<V> elements);

    public Bag<V> createBag(V... elements) {
        return createBag(Arrays.asList(elements));
    }

    @Override public Collection<V> createCollection(Iterable<V> elements) {
        return createBag(elements);
    }

    @Test
    public void count_shouldReturnTheNumberOfOccurrencesOfAnEqualElement() {
        // Arrange
        final Bag<V> sut = createBag(
                new V("a"),
                new V("a"),
                new V("a"),
                new V("b"),
                new V("b"),
                new V("c"),
                new V("d"),
                new V("d"),
                new V("d"),
                new V("d")
        );

        // Act
        final int as = sut.count(new V("a"));
        final int bs = sut.count(new V("b"));
        final int cs = sut.count(new V("c"));
        final int ds = sut.count(new V("d"));

        // Assert
        assertEquals(3, as);
        assertEquals(2, bs);
        assertEquals(1, cs);
        assertEquals(4, ds);
    }

    @Test
    public void count_shouldReturnZero_whenTheElementIsNotInTheBag() {
        // Arrange
        final Bag<V> sut = createBag(
                new V("a"),
                new V("a"),
                new V("a"),
                new V("b"),
                new V("b"),
                new V("c"),
                new V("d"),
                new V("d"),
                new V("d"),
                new V("d")
        );

        // Act
        final int xs = sut.count(new V("X"));

        // Assert
        assertEquals(0, xs);
    }


    @Test
    public void elementSet_shouldReturnAllUniqueElements() {
        // Arrange
        final Bag<V> sut = createBag(
                new V("a"),
                new V("a"),
                new V("a"),
                new V("b"),
                new V("b"),
                new V("c"),
                new V("d"),
                new V("d"),
                new V("d"),
                new V("d")
        );

        // Act
        final Collection<V> elements = sut.elementSet();

        // Assert
        assertEquals(new HashSet<>(Arrays.asList(
                new V("a"),
                new V("b"),
                new V("c"),
                new V("d")
        )), elements);
    }


    /** Tests the {@link Bag.Immutable} implementations. */
    public static class ImmutableTests extends BagTests implements ImmutableCollectionTests {
        @Override public Bag<V> createBag(Iterable<V> elements) {
            final Bag.Transient<V> sut = Bag.Transient.of();
            sut.addAll(TestUtils.iterableToList(elements));
            return sut.freeze();
        }

        @Override public Collection<V> createImmutableCollection(Iterable<V> elements) {
            return createBag(elements);
        }
    }

    /** Tests the {@link Bag.Transient} implementations. */
    public static class TransientTests extends BagTests implements MutableCollectionTests {
        @Override public Bag<V> createBag(Iterable<V> elements) {
            final Bag.Transient<V> sut = Bag.Transient.of();
            sut.addAll(TestUtils.iterableToList(elements));
            return sut;
        }

        @Override public Collection<V> createMutableCollection(Iterable<V> elements) {
            return createBag(elements);
        }
    }
}
