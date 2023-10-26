package org.metaborg.util.collection;

import java.util.Arrays;
import java.util.List;

/** Tests the {@link ImList} implementations. */
@SuppressWarnings({"unused"})
public abstract class ImListTests implements ListTests {

    public abstract ImList<V> createImList(Iterable<V> elements);

    public ImList<V> createImList(V... elements) {
        return createImList(Arrays.asList(elements));
    }

    @Override public List<V> createList(Iterable<V> elements) {
        return createImList(elements);
    }

    /** Tests the {@link ImList.Immutable} implementations. */
    public static class ImmutableTests extends ImListTests implements ImmutableListTests {
        @Override public ImList<V> createImList(Iterable<V> elements) {
            return ImList.Immutable.of(TestUtils.iterableToArray(elements));
        }

        @Override public List<V> createImmutableList(Iterable<V> elements) {
            return createImList(elements);
        }
    }

    /** Tests the {@link ImList.Mutable} implementations. */
    public static class MutableTests extends ImListTests implements MutableListTests {
        @Override public ImList<V> createImList(Iterable<V> elements) {
            return ImList.Mutable.of(TestUtils.iterableToArray(elements));
        }

        @Override public List<V> createMutableList(Iterable<V> elements) {
            return createImList(elements);
        }
    }
}
