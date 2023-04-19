package org.metaborg.util.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import io.usethesource.capsule.Map;

public abstract class MultiSet<E> implements Iterable<E>, Serializable {

    @SuppressWarnings("rawtypes") private static final MultiSet.Immutable EMPTY =
            new MultiSet.Immutable<>(Map.Immutable.of());

    protected abstract java.util.Map<E, Integer> elements();

    public boolean isEmpty() {
        return elements().isEmpty();
    }

    /**
     * Return number of elements in the set. This operation does not run in constant time.
     */
    public int size() {
        return elements().values().stream().mapToInt(i -> i).sum();
    }

    public int count(E e) {
        return elements().getOrDefault(e, 0);
    }

    public boolean contains(Object e) {
        return elements().containsKey(e);
    }

    public boolean containsAll(Iterable<E> es) {
        for(E e : es) {
            if(!contains(e)) {
                return false;
            }
        }
        return true;
    }

    public Set<Entry<E, Integer>> entrySet() {
        return elements().entrySet();
    }

    public java.util.Set<E> elementSet() {
        return elements().keySet();
    }

    @Override public Iterator<E> iterator() {
        return new MultiSetIterator();
    }

    public Optional<Integer> compareTo(MultiSet<E> other) {
        final java.util.Map<E, Integer> ours = elements();
        final java.util.Map<E, Integer> theirs = other.elements();
        boolean oursMissing = false;
        boolean theirsMissing = false;
        boolean oursSmaller = false;
        boolean theirsSmaller = false;
        for(E e : Sets.union(ours.keySet(), theirs.keySet())) {
            final Integer ourCount = ours.get(e);
            final Integer theirCount = theirs.get(e);
            if(ourCount == null && theirCount == null) {
                // continue
            } else if(ourCount == null) {
                if(theirsMissing) {
                    return Optional.empty();
                } else {
                    oursMissing = true;
                }
            } else if(theirCount == null) {
                if(oursMissing) {
                    return Optional.empty();
                } else {
                    theirsMissing = true;
                }
            } else {
                final int d = ourCount - theirCount;
                if(d < 0) {
                    if(theirsSmaller) {
                        return Optional.empty();
                    } else {
                        oursSmaller = true;
                    }
                } else if(d > 0) {
                    if(oursSmaller) {
                        return Optional.empty();
                    } else {
                        theirsSmaller = true;
                    }
                }
            }
        }
        // at this point, oursMissing && theirsMissing == false, and oursSmaller && theirsMissing == false should hold
        if(oursMissing || oursSmaller) {
            return Optional.of(-1);
        } else if(theirsMissing || theirsSmaller) {
            return Optional.of(1);
        }
        return Optional.of(0);
    }

    public static class Immutable<E> extends MultiSet<E> implements Serializable {

        private static final long serialVersionUID = 1L;

        private final Map.Immutable<E, Integer> elements;

        private Immutable(Map.Immutable<E, Integer> elements) {
            this.elements = elements;
        }

        public static <T> Immutable<T> copyOf(MultiSet<T> toCopy) {
            if(toCopy instanceof Immutable) {
                return (Immutable<T>) toCopy;
            } else {
                final Transient<T> b = Transient.of();
                b.addAll(toCopy);
                return b.freeze();
            }
        }

        @Override protected Map<E, Integer> elements() {
            return elements;
        }

        public Immutable<E> set(E e, int n) {
            if(n < 0) {
                throw new IllegalArgumentException("count must be positive");
            }
            if(n > 0) {
                return new MultiSet.Immutable<>(elements.__put(e, n));
            } else {
                return new MultiSet.Immutable<>(elements.__remove(e));
            }
        }

        public Immutable<E> add(E e, int n) {
            if(n < 0) {
                throw new IllegalArgumentException("count must be positive");
            }
            final Integer oldCount = elements.getOrDefault(e, 0);
            final int newCount = oldCount + n;
            if(newCount > 0) {
                return new MultiSet.Immutable<>(elements.__put(e, newCount));
            } else {
                return new MultiSet.Immutable<>(elements.__remove(e));
            }
        }

        public Immutable<E> addAll(Iterable<E> es) {
            Immutable<E> result = this;
            for(E e : es) {
                result = result.add(e, 1);
            }
            return result;
        }

        public Immutable<E> remove(E e, int n) {
            if(n < 0) {
                throw new IllegalArgumentException("count must be positive");
            }
            final Integer oldCount = elements.getOrDefault(e, 0);
            final int newCount = Math.max(0, oldCount - n);
            if(newCount > 0) {
                return new MultiSet.Immutable<>(elements.__put(e, newCount));
            } else {
                return new MultiSet.Immutable<>(elements.__remove(e));
            }

        }

        public Immutable<E> removeAll(E e) {
            return new MultiSet.Immutable<>(elements.__remove(e));
        }

        public Immutable<E> remove(Iterable<E> es) {
            MultiSet.Immutable<E> result = this;
            for(E e : es) {
                result = result.remove(e, 1);
            }
            return result;
        }

        public <T> Immutable<T> filterMap(Function<E, Optional<T>> filter) {
            boolean changed = false;
            final Map.Transient<T, Integer> map = CapsuleUtil.transientMap();
            for(Entry<E, Integer> e : elements.entrySet()) {
                filter.apply(e.getKey()).ifPresent(t -> map.__put(t,e.getValue()));
            }
            return new Immutable<>(map.freeze());
        }

        public Map.Immutable<E, Integer> asMap() {
            return elements;
        }

        public MultiSet.Transient<E> melt() {
            return new MultiSet.Transient<>(elements.asTransient());
        }

        @SuppressWarnings("unchecked") public static <E> MultiSet.Immutable<E> of() {
            return EMPTY;
        }

        public static <E> MultiSet.Immutable<E> of(E e) {
            return of(e, 1);
        }

        public static <E> MultiSet.Immutable<E> of(Iterable<E> es) {
            final MultiSet.Transient<E> result = MultiSet.Transient.of();
            for(E e : es) {
                result.add(e);
            }
            return result.freeze();
        }

        public static <E> MultiSet.Immutable<E> of(E e, int n) {
            return new MultiSet.Immutable<>(Map.Immutable.of(e, n));
        }

        @SuppressWarnings("unchecked") public static <E> MultiSet.Immutable<E> union(MultiSet.Immutable<E> set1,
                MultiSet.Immutable<E> set2) {
            if(set1.isEmpty() && set2.isEmpty()) {
                return EMPTY;
            } else if(set1.isEmpty()) {
                return set2;
            } else if(set2.isEmpty()) {
                return set1;
            } else {
                return set1.addAll(set2);
            }
        }

        public Collection<E> toCollection() {
            return new ImmutableCollection<E>() {
                @Override public Iterator iterator() {
                    return Immutable.this.iterator();
                }

                @Override public int size() {
                    return Immutable.this.size();
                }
            };
        }
    }

    public static class Transient<E> extends MultiSet<E> {

        private Map.Transient<E, Integer> elements;

        private Transient(Map.Transient<E, Integer> elements) {
            this.elements = elements;
        }

        @Override protected Map<E, Integer> elements() {
            return elements;
        }

        /**
         * Set an element to n.
         *
         * @param e
         *            Element to be set
         * @param n
         *            New count
         * @return Old count for the element
         */
        public int set(E e, int n) {
            if(n < 0) {
                throw new IllegalArgumentException("count must be positive");
            }
            final Integer oldCount;
            if(n > 0) {
                oldCount = elements.__put(e, n);
            } else {
                oldCount = elements.__remove(e);
            }
            return oldCount != null ? oldCount : 0;
        }

        /**
         * Add an element once.
         *
         * @param e
         *            Element to be added
         * @return New count for the element.
         */
        public int add(E e) {
            return add(e, 1);
        }

        /**
         * Add an element n times.
         *
         * @param e
         *            Element to be added
         * @param n
         *            Additions, greater or equal to zero
         * @return Old count for the element
         */
        public int add(E e, int n) {
            if(n < 0) {
                throw new IllegalArgumentException("count must be positive");
            }
            final int oldCount = elements.getOrDefault(e, 0);
            final int newCount = oldCount + n;
            if(newCount > 0) {
                elements.__put(e, newCount);
            } else {
                elements.__remove(e);
            }
            return oldCount;
        }

        public void addAll(Iterable<E> es) {
            for(E e : es) {
                add(e);
            }
        }

        /**
         * Remove an element once.
         *
         * @param e
         *            Element to be removed
         * @return Old count for the element
         */
        public int remove(E e) {
            return remove(e, 1);
        }

        public void removeAll(Iterable<E> es) {
            if(es instanceof MultiSet) {
                for(Entry<E, Integer> entry : ((MultiSet<E>) es).entrySet()) {
                    remove(entry.getKey(), entry.getValue());
                }
            } else {
                for(E e : es) {
                    remove(e);
                }
            }
        }

        /**
         * Remove an element up to n times.
         *
         * @param e
         *            Element to be removed
         * @param n
         *            Removals, greater or equal to zero
         * @return Old count for the element
         */
        public int remove(E e, int n) {
            if(n < 0) {
                throw new IllegalArgumentException("count must be positive");
            }
            final int oldCount = elements.getOrDefault(e, 0);
            final int newCount = Math.max(0, oldCount - n);
            if(newCount > 0) {
                elements.__put(e, newCount);
            } else {
                elements.__remove(e);
            }
            return oldCount;
        }

        /**
         * Remove an element completely.
         *
         * @return Old count for the element
         */
        public int removeAll(E e) {
            final int oldCount = elements.getOrDefault(e, 0);
            elements.__remove(e);
            return oldCount;
        }

        @SuppressWarnings("unchecked") public MultiSet.Immutable<E> freeze() {
            return elements.isEmpty() ? EMPTY : new MultiSet.Immutable<>(elements.freeze());
        }

        public static <E> MultiSet.Transient<E> of() {
            return new MultiSet.Transient<>(Map.Transient.of());
        }


    }

    public static class Mutable<E> extends MultiSet<E> {
        private java.util.Map<E, Integer> elements;

        private Mutable(Map.Transient<E, Integer> elements) {
            this.elements = elements;
        }

        public Mutable() {
            this.elements = new HashMap<>();
        }

        @Override protected java.util.Map<E, Integer> elements() {
            return elements;
        }

        /**
         * Set an element to n.
         *
         * @param e
         *            Element to be set
         * @param n
         *            New count
         * @return Old count for the element
         */
        public int set(E e, int n) {
            if(n < 0) {
                throw new IllegalArgumentException("count must be positive");
            }
            final Integer oldCount;
            if(n > 0) {
                oldCount = elements.put(e, n);
            } else {
                oldCount = elements.remove(e);
            }
            return oldCount != null ? oldCount : 0;
        }

        /**
         * Add an element once.
         *
         * @param e
         *            Element to be added
         * @return New count for the element.
         */
        public int add(E e) {
            return add(e, 1);
        }

        /**
         * Add an element n times.
         *
         * @param e
         *            Element to be added
         * @param n
         *            Additions, greater or equal to zero
         * @return Old count for the element
         */
        public int add(E e, int n) {
            if(n < 0) {
                throw new IllegalArgumentException("count must be positive");
            }
            final int oldCount = elements.getOrDefault(e, 0);
            final int newCount = oldCount + n;
            if(newCount > 0) {
                elements.put(e, newCount);
            } else {
                elements.remove(e);
            }
            return oldCount;
        }

        public void addAll(Iterable<E> es) {
            for(E e : es) {
                add(e);
            }
        }

        /**
         * Remove an element once.
         *
         * @param e
         *            Element to be removed
         * @return Old count for the element
         */
        public int remove(E e) {
            return remove(e, 1);
        }

        public void removeAll(Iterable<E> es) {
            if(es instanceof MultiSet) {
                for(Entry<E, Integer> entry : ((MultiSet<E>) es).entrySet()) {
                    remove(entry.getKey(), entry.getValue());
                }
            } else {
                for(E e : es) {
                    remove(e);
                }
            }
        }

        /**
         * Remove an element up to n times.
         *
         * @param e
         *            Element to be removed
         * @param n
         *            Removals, greater or equal to zero
         * @return Old count for the element
         */
        public int remove(E e, int n) {
            if(n < 0) {
                throw new IllegalArgumentException("count must be positive");
            }
            final int oldCount = elements.getOrDefault(e, 0);
            final int newCount = Math.max(0, oldCount - n);
            if(newCount > 0) {
                elements.put(e, newCount);
            } else {
                elements.remove(e);
            }
            return oldCount;
        }

        /**
         * Remove an element completely.
         *
         * @return Old count for the element
         */
        public int removeAll(E e) {
            final int oldCount = elements.getOrDefault(e, 0);
            elements.remove(e);
            return oldCount;
        }

        public void clear() {
            elements.clear();
        }
    }

    @Override public String toString() {
        return elements().entrySet().stream().map(e -> e.getKey() + ": " + e.getValue())
                .collect(Collectors.joining(", ", "{", "}"));
    }

    private class MultiSetIterator implements Iterator<E> {

        private Iterator<Map.Entry<E, Integer>> it = elements().entrySet().iterator();
        private E next;
        private int count;

        @Override public boolean hasNext() {
            return (next != null && count > 0) || it.hasNext();
        }

        @Override public E next() {
            if(next == null || count <= 0) {
                final Map.Entry<E, Integer> entry = it.next();
                next = entry.getKey();
                count = entry.getValue();
            }
            count -= 1;
            return next;
        }

    }

}
