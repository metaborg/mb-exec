package org.metaborg.util.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/** Tests the {@link MultiSet} implementations. */
@SuppressWarnings({"unused"})
public abstract class MultiSetTests implements CollectionTests {

    public abstract MultiSet<V> createMultiSet(Iterable<V> elements);

    public MultiSet<V> createMultiSet(V... elements) {
        return createMultiSet(Arrays.asList(elements));
    }

    /** Tests the {@link MultiSet.Immutable} implementations. */
    public static class ImmutableTests extends MultiSetTests implements ImmutableCollectionTests {
        @Override public MultiSet.Immutable<V> createMultiSet(Iterable<V> elements) {
            return MultiSet.Immutable.of(elements);
        }

        @Override public Collection<V> createImmutableCollection(Iterable<V> elements) {
            return new MultiSet_Immutable_CollectionWrapper<>((MultiSet.Immutable<V>)createMultiSet(elements));
        }
    }

    /** Tests the {@link MultiSet.Mutable} implementations. */
    public static class MutableTests extends MultiSetTests implements MutableCollectionTests {
        @Override public MultiSet.Mutable<V> createMultiSet(Iterable<V> elements) {
            final MultiSet.Mutable<V> set = new MultiSet.Mutable<>();
            set.addAll(elements);
            return set;
        }

        @Override public Collection<V> createMutableCollection(Iterable<V> elements) {
            return new MultiSet_Mutable_CollectionWrapper<>((MultiSet.Mutable<V>)createMultiSet(elements));
        }
    }

    /** Tests the {@link MultiSet.Transient} implementations. */
    public static class TransientTests extends MultiSetTests implements MutableCollectionTests {
        @Override public MultiSet.Transient<V> createMultiSet(Iterable<V> elements) {
            final MultiSet.Transient<V> set = MultiSet.Transient.of();
            set.addAll(elements);
            return set;
        }

        @Override public Collection<V> createMutableCollection(Iterable<V> elements) {
            return new MultiSet_Transient_CollectionWrapper<>((MultiSet.Transient<V>)createMultiSet(elements));
        }
    }

    @SuppressWarnings("NullableProblems")
    private static abstract class MultiSet_CollectionWrapper<T> implements Collection<T> {

        private final MultiSet<T> innerSet;

        public MultiSet_CollectionWrapper(MultiSet<T> innerSet) {
            this.innerSet = innerSet;
        }

        @Override public int size() {
            return this.innerSet.size();
        }

        @Override public boolean isEmpty() {
            return this.innerSet.isEmpty();
        }

        @Override public boolean contains(Object o) {
            return this.innerSet.contains(o);
        }

        @Override public Iterator<T> iterator() {
            return this.innerSet.iterator();
        }

        @Override public Object[] toArray() {
            return toArray(new Object[0]);
        }

        @Override public <T1> T1[] toArray(T1[] a) {
            final Set<Map.Entry<T, Integer>> entries = this.innerSet.entrySet();
            final ArrayList<T> result = new ArrayList<>(entries.size());
            for (T entry : this.innerSet) {
                result.add(entry);
            }
            return result.toArray(a);
        }

        @Override public abstract boolean add(T t);

        @Override public abstract boolean remove(Object o);

        @Override public abstract boolean containsAll(Collection<?> c);

        @Override public abstract boolean addAll(Collection<? extends T> c);

        @Override public abstract boolean removeAll(Collection<?> c);

        @Override public abstract boolean retainAll(Collection<?> c);

        @Override public abstract void clear();
    }


    @SuppressWarnings("NullableProblems")
    private static class MultiSet_Immutable_CollectionWrapper<T> extends MultiSet_CollectionWrapper<T> implements Collection<T> {

        public MultiSet_Immutable_CollectionWrapper(MultiSet.Immutable<T> innerSet) {
            super(innerSet);
        }

        @Override public boolean add(T t) {
            throw new UnsupportedOperationException();
        }

        @Override public boolean remove(Object o) {
            throw new UnsupportedOperationException();
        }

        @Override public boolean containsAll(Collection<?> c) {
            throw new UnsupportedOperationException();
        }

        @Override public boolean addAll(Collection<? extends T> c) {
            throw new UnsupportedOperationException();
        }

        @Override public boolean removeAll(Collection<?> c) {
            throw new UnsupportedOperationException();
        }

        @Override public boolean retainAll(Collection<?> c) {
            throw new UnsupportedOperationException();
        }

        @Override public void clear() {
            throw new UnsupportedOperationException();
        }
    }

    @SuppressWarnings("NullableProblems")
    private static class MultiSet_Mutable_CollectionWrapper<T> extends MultiSet_CollectionWrapper<T> implements Collection<T> {

        private final MultiSet.Mutable<T> innerSet;

        public MultiSet_Mutable_CollectionWrapper(MultiSet.Mutable<T> innerSet) {
            super(innerSet);
            this.innerSet = innerSet;
        }

        @Override public boolean add(T t) {
            this.innerSet.add(t);
            return true;
        }

        @Override public boolean remove(Object o) {
            //noinspection unchecked
            int oldCount = this.innerSet.remove((T)o);
            return oldCount > 0;
        }

        @Override public boolean containsAll(Collection<?> c) {
            Objects.requireNonNull(c);

            //noinspection unchecked
            return this.innerSet.containsAll((Collection<T>)c);
        }

        @SuppressWarnings("unchecked")
        @Override public boolean addAll(Collection<? extends T> c) {
            Objects.requireNonNull(c);

            int oldSize = this.innerSet.size();
            this.innerSet.addAll((Collection<T>)c);
            return this.innerSet.size() != oldSize;
        }

        @SuppressWarnings("unchecked")
        @Override public boolean removeAll(Collection<?> c) {
            Objects.requireNonNull(c);

            int oldSize = this.innerSet.size();
            this.innerSet.removeAll((Collection<T>)c);
            return this.innerSet.size() != oldSize;
        }

        @Override public boolean retainAll(Collection<?> c) {
            Objects.requireNonNull(c);

            final ArrayList<T> toRemove = new ArrayList<>();
            for (T element : this) {
                if (!c.contains(element)) {
                    toRemove.add(element);
                }
            }
            this.innerSet.removeAll(toRemove);
            return !toRemove.isEmpty();
        }

        @Override public void clear() {
            this.innerSet.clear();
        }
    }

    @SuppressWarnings("NullableProblems")
    private static class MultiSet_Transient_CollectionWrapper<T> extends MultiSet_CollectionWrapper<T> implements Collection<T> {

        private final MultiSet.Transient<T> innerSet;

        public MultiSet_Transient_CollectionWrapper(MultiSet.Transient<T> innerSet) {
            super(innerSet);
            this.innerSet = innerSet;
        }

        @Override public boolean add(T t) {
            this.innerSet.add(t);
            return true;
        }

        @Override public boolean remove(Object o) {
            //noinspection unchecked
            int oldCount = this.innerSet.remove((T)o);
            return oldCount > 0;
        }

        @Override public boolean containsAll(Collection<?> c) {
            Objects.requireNonNull(c);

            //noinspection unchecked
            return this.innerSet.containsAll((Collection<T>)c);
        }

        @SuppressWarnings("unchecked")
        @Override public boolean addAll(Collection<? extends T> c) {
            Objects.requireNonNull(c);

            int oldSize = this.innerSet.size();
            this.innerSet.addAll((Collection<T>)c);
            return this.innerSet.size() != oldSize;
        }

        @SuppressWarnings("unchecked")
        @Override public boolean removeAll(Collection<?> c) {
            Objects.requireNonNull(c);

            int oldSize = this.innerSet.size();
            this.innerSet.removeAll((Collection<T>)c);
            return this.innerSet.size() != oldSize;
        }

        @Override public boolean retainAll(Collection<?> c) {
            Objects.requireNonNull(c);

            final ArrayList<T> toRemove = new ArrayList<>();
            for (T element : this) {
                if (!c.contains(element)) {
                    toRemove.add(element);
                }
            }
            this.innerSet.removeAll(toRemove);
            return !toRemove.isEmpty();
        }

        @Override public void clear() {
            retainAll(Collections.emptyList());
        }
    }
}
