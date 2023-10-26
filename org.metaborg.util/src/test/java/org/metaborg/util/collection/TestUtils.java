package org.metaborg.util.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Map;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

/** Utility methods for the collections tests. */
public final class TestUtils {
    private TestUtils() { /* Prevent instantiation. */ }

    /**
     * Converts the specified iterable to an array.
     *
     * @param iterable the iterable to convert
     * @return the array of elements
     * @param <E> the type of elements in the iterable
     */
    public static <E> E[] iterableToArray(Iterable<E> iterable) {
        Objects.requireNonNull(iterable, "iterable");

        @SuppressWarnings("unchecked")
        final E[] array = (E[])new Object[0];
        return iterableToList(iterable).toArray(array);
    }

    /**
     * Converts the specified iterable to a list.
     *
     * @param iterable the iterable to convert
     * @return the list of elements
     * @param <E> the type of elements in the iterable
     */
    public static <E> List<E> iterableToList(Iterable<E> iterable) {
        Objects.requireNonNull(iterable, "iterable");

        final ArrayList<E> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    /**
     * Iterates (the remaining elements of) the specified iterator into a list.
     *
     * @param iterator the iterator to iterate
     * @return the list of elements
     * @param <E> the type of elements in the iterator
     */
    public static <E> List<E> iteratorToList(Iterator<E> iterator) {
        Objects.requireNonNull(iterator, "iterator");

        final ArrayList<E> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    /**
     * Compares two collections for equality, ignoring order and cardinality of the elements.
     *
     * @param expected the expected set
     * @param actual the actual set
     * @param <E> the type of elements in the collections
     */
    public static <E> void assertSetEquals(Collection<E> expected, Collection<E> actual) {
        Objects.requireNonNull(expected, "expected");
        Objects.requireNonNull(actual, "actual");

        assertEquals(new HashSet<>(expected), new HashSet<>(actual));
    }

    /**
     * Compares two collections for equality, taking into account cardinality but ignoring the order of the elements.
     *
     * @param expected the expected collection
     * @param actual the actual collection to compare to
     * @param <E> the type of elements in the collections
     */
    public static <E> void assertCollectionEquals(Collection<E> expected, Collection<E> actual) {
        Objects.requireNonNull(expected, "expected");
        Objects.requireNonNull(actual, "actual");

        assertEquals(expected.size(), actual.size());
        final Map<E, Integer> expectedCardinality = getCardinalityMap(expected);
        final Map<E, Integer> actualCardinality = getCardinalityMap(actual);
        assertEquals(expectedCardinality, actualCardinality);
    }

    /**
     * Compares two collections for equality, taking into account cardinality but ignoring the order of the elements.
     *
     * @param expected the expected collection
     * @param actual the actual collection to compare to
     * @param <E> the type of elements in the collections
     */
    public static <E> void assertCollectionArrayEquals(E[] expected, E[] actual) {
        Objects.requireNonNull(expected, "expected");
        Objects.requireNonNull(actual, "actual");

        assertEquals(expected.length, actual.length);
        final Map<E, Integer> expectedCardinality = getCardinalityMap(Arrays.asList(expected));
        final Map<E, Integer> actualCardinality = getCardinalityMap(Arrays.asList(actual));
        assertEquals(expectedCardinality, actualCardinality);
    }

    /**
     * Builds a map with the cardinality of each occurrence of a value in the given iterable.
     *
     * @param iterable the iterable
     * @return the map of occurrences
     * @param <E> the type of elements in the iterable
     */
    public static <E> Map<E, Integer> getCardinalityMap(final Iterable<? extends E> iterable) {
        Objects.requireNonNull(iterable, "iterable");

        final Map<E, Integer> count = new HashMap<>();
        for (final E element : iterable) {
            count.compute(element, (k, v) -> (v == null) ? 1 : v + 1);
        }
        return count;
    }
}
