package org.metaborg.util.collection;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

/*
 * This file contains Derivative Work based on the com.google.common.collect.Sets class from the
 * Guava library. Therefore, the following copyright and license information from that original
 * source code is provided below. Modifications done in this file are copyright of the Spoofax
 * Authors.
 *
 * Copyright (C) 2007 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class Sets {
    /**
     * Returns an unmodifiable <b>view</b> of the union of two sets. The returned set contains all
     * elements that are contained in either backing set. Iterating over the returned set iterates
     * first over all the elements of {@code set1}, then over each element of {@code set2}, in order,
     * that is not contained in {@code set1}.
     *
     * <p>Results are undefined if {@code set1} and {@code set2} are sets based on different
     * equivalence relations (as {@link HashSet}, {@link TreeSet}, and the {@link Map#keySet} of an
     * {@code IdentityHashMap} all are).
     */
    public static <E> Set<E> union(final Set<? extends E> set1, final Set<? extends E> set2) {
        Objects.requireNonNull(set1, "set1");
        Objects.requireNonNull(set2, "set2");

        return new AbstractSet<E>() {
            @Override
            public int size() {
                int size = set1.size();
                for (E e : set2) {
                    if (!set1.contains(e)) {
                        size++;
                    }
                }
                return size;
            }

            @Override
            public boolean isEmpty() {
                return set1.isEmpty() && set2.isEmpty();
            }

            @Override
            public Iterator<E> iterator() {
                return new Iterator<E>() {
                    final Iterator<? extends E> itr1 = set1.iterator();
                    final Iterator<? extends E> itr2 = set2.iterator();
                    E next = null;
                    boolean hasNext = false;

                    private E computeNext() {
                        if (itr1.hasNext()) {
                            hasNext = true;
                            return itr1.next();
                        }
                        while (itr2.hasNext()) {
                            E e = itr2.next();
                            if (!set1.contains(e)) {
                                hasNext = true;
                                return next;
                            }
                        }
                        hasNext = false;
                        return null;
                    }

                    @Override public boolean hasNext() {
                        if(!hasNext) {
                            next = computeNext();
                        }
                        return hasNext;
                    }

                    @Override public E next() {
                        if(hasNext()) {
                            hasNext = false;
                            return next;
                        } else {
                            throw new NoSuchElementException();
                        }
                    }
                };
            }

            @Override
            public Stream<E> stream() {
                return Stream.concat(set1.stream(), set2.stream().filter(e -> !set1.contains(e)));
            }

            @Override
            public Stream<E> parallelStream() {
                return stream().parallel();
            }

            @Override
            public boolean contains(Object object) {
                return set1.contains(object) || set2.contains(object);
            }
        };
    }

    /**
     * Returns an unmodifiable <b>view</b> of the intersection of two sets. The returned set contains
     * all elements that are contained by both backing sets. The iteration order of the returned set
     * matches that of {@code set1}.
     *
     * <p>Results are undefined if {@code set1} and {@code set2} are sets based on different
     * equivalence relations (as {@code HashSet}, {@code TreeSet}, and the keySet of an {@code
     * IdentityHashMap} all are).
     *
     * <p><b>Note:</b> The returned view performs slightly better when {@code set1} is the smaller of
     * the two sets. If you have reason to believe one of your sets will generally be smaller than the
     * other, pass it first. Unfortunately, since this method sets the generic type of the returned
     * set based on the type of the first set passed, this could in rare cases force you to make a
     * cast, for example:
     *
     * <pre>{@code
     * Set<Object> aFewBadObjects = ...
     * Set<String> manyBadStrings = ...
     *
     * // impossible for a non-String to be in the intersection
     * SuppressWarnings("unchecked")
     * Set<String> badStrings = (Set) Sets.intersection(
     *     aFewBadObjects, manyBadStrings);
     * }</pre>
     *
     * <p>This is unfortunate, but should come up only very rarely.
     */
    public static <E> Set<E> intersection(final Set<E> set1, final Set<?> set2) {
        Objects.requireNonNull(set1, "set1");
        Objects.requireNonNull(set2, "set2");

        return new AbstractSet<E>() {
            @Override
            public Iterator<E> iterator() {
                return new Iterator<E>() {
                    final Iterator<E> itr = set1.iterator();
                    E next = null;
                    boolean hasNext = false;

                    protected E computeNext() {
                        while (itr.hasNext()) {
                            E e = itr.next();
                            if (set2.contains(e)) {
                                hasNext = true;
                                return e;
                            }
                        }
                        hasNext = false;
                        return null;
                    }

                    @Override public boolean hasNext() {
                        if(!hasNext) {
                            next = computeNext();
                        }
                        return hasNext;
                    }

                    @Override public E next() {
                        if(hasNext()) {
                            hasNext = false;
                            return next;
                        } else {
                            throw new NoSuchElementException();
                        }
                    }
                };
            }

            @Override
            public Stream<E> stream() {
                return set1.stream().filter(set2::contains);
            }

            @Override
            public Stream<E> parallelStream() {
                return set1.parallelStream().filter(set2::contains);
            }

            @Override
            public int size() {
                int size = 0;
                for (E e : set1) {
                    if (set2.contains(e)) {
                        size++;
                    }
                }
                return size;
            }

            @Override
            public boolean isEmpty() {
                return Collections.disjoint(set1, set2);
            }

            @Override
            public boolean contains(Object object) {
                return set1.contains(object) && set2.contains(object);
            }

            @Override
            public boolean containsAll(Collection<?> collection) {
                return set1.containsAll(collection) && set2.containsAll(collection);
            }
        };
    }

    /**
     * Returns an unmodifiable <b>view</b> of the difference of two sets. The returned set contains
     * all elements that are contained by {@code set1} and not contained by {@code set2}. {@code set2}
     * may also contain elements not present in {@code set1}; these are simply ignored. The iteration
     * order of the returned set matches that of {@code set1}.
     *
     * <p>Results are undefined if {@code set1} and {@code set2} are sets based on different
     * equivalence relations (as {@code HashSet}, {@code TreeSet}, and the keySet of an {@code
     * IdentityHashMap} all are).
     */
    public static <E> Set<E> difference(final Set<E> set1, final Set<?> set2) {
        Objects.requireNonNull(set1, "set1");
        Objects.requireNonNull(set2, "set2");

        return new AbstractSet<E>() {
            @Override
            public Iterator<E> iterator() {
                return new Iterator<E>() {
                    final Iterator<E> itr = set1.iterator();
                    E next = null;
                    boolean hasNext = false;

                    protected E computeNext() {
                        while (itr.hasNext()) {
                            E e = itr.next();
                            if (!set2.contains(e)) {
                                hasNext = true;
                                return e;
                            }
                        }
                        hasNext = false;
                        return null;
                    }

                    @Override public boolean hasNext() {
                        if(!hasNext) {
                            next = computeNext();
                        }
                        return hasNext;
                    }

                    @Override public E next() {
                        if(hasNext()) {
                            hasNext = false;
                            return next;
                        } else {
                            throw new NoSuchElementException();
                        }
                    }
                };
            }

            @Override
            public Stream<E> stream() {
                return set1.stream().filter(e -> !set2.contains(e));
            }

            @Override
            public Stream<E> parallelStream() {
                return set1.parallelStream().filter(e -> !set2.contains(e));
            }

            @Override
            public int size() {
                int size = 0;
                for (E e : set1) {
                    if (!set2.contains(e)) {
                        size++;
                    }
                }
                return size;
            }

            @Override
            public boolean isEmpty() {
                return set2.containsAll(set1);
            }

            @Override
            public boolean contains(Object element) {
                return set1.contains(element) && !set2.contains(element);
            }
        };
    }

    /**
     * Returns a capacity that is sufficient to keep the map from being resized as long as it grows no
     * larger than expectedSize and the load factor is ≥ its default (0.75).
     */
    public static int hashCapacity(int expectedSize) {
        if (expectedSize < 3) {
            return Integer.max(expectedSize, 2) + 1;
        }
        if (expectedSize < 1 << (Integer.SIZE - 2)) {
            // This is the calculation used in JDK8 to resize when a putAll
            // happens; it seems to be the most conservative calculation we
            // can make.  0.75 is the default load factor.
            return (int) ((float) expectedSize / 0.75F + 1.0F);
        }
        return Integer.MAX_VALUE; // any large value
    }
}
