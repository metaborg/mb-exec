package org.metaborg.util.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ImListTest {
    @SuppressWarnings({ "ConstantValue", "RedundantCollectionOperation" }) @Test public void testEmptyImmutableList() {
        final ImList.Immutable<Integer> emptyList = ImList.Immutable.of();
        // establish emptiness
        assertEquals(0, emptyList.size());
        assertTrue(emptyList.isEmpty());
        assertFalse(emptyList.contains(1));

        // establish empty iterator, no mutation allowed
        @SuppressWarnings("RedundantOperationOnEmptyContainer")
        final Iterator<Integer> iterator = emptyList.iterator();
        assertNotNull(iterator);
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
        assertThrows(UnsupportedOperationException.class, iterator::remove);

        // establish some other methods, lots unsupported due to mutation
        assertArrayEquals(emptyList.toArray(), new Object[] {});
        assertThrows(UnsupportedOperationException.class, () -> emptyList.add(1));
        assertThrows(UnsupportedOperationException.class, () -> emptyList.remove(Integer.valueOf(1)));
        assertTrue(emptyList.containsAll(Collections.<Integer>emptyList()));
        assertFalse(emptyList.containsAll(Collections.singleton(1)));
        assertThrows(UnsupportedOperationException.class, () -> emptyList.addAll(Collections.singleton(1)));
        assertThrows(UnsupportedOperationException.class, () -> emptyList.addAll(0, Collections.singleton(1)));
        assertThrows(UnsupportedOperationException.class, () -> emptyList.removeAll(Collections.singleton(1)));
        assertThrows(UnsupportedOperationException.class, () -> emptyList.retainAll(Collections.singleton(1)));
        assertThrows(UnsupportedOperationException.class, emptyList::clear);
        //noinspection ResultOfMethodCallIgnored
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(0));
        assertThrows(UnsupportedOperationException.class, () -> emptyList.set(0, 1));
        assertThrows(UnsupportedOperationException.class, () -> emptyList.add(0, 1));
        assertThrows(UnsupportedOperationException.class, () -> emptyList.remove(0));
        assertEquals(-1, emptyList.indexOf(1));
        assertEquals(-1, emptyList.lastIndexOf(1));

        // establish empty list iterator, no mutation allowed
        final ListIterator<Integer> listiter = emptyList.listIterator();
        assertNotNull(listiter);
        assertFalse(listiter.hasNext());
        assertFalse(listiter.hasPrevious());
        assertEquals(0, listiter.nextIndex());
        assertEquals(-1, listiter.previousIndex());
        assertThrows(NoSuchElementException.class, listiter::next);
        assertThrows(NoSuchElementException.class, listiter::previous);
        assertThrows(UnsupportedOperationException.class, listiter::remove);
        assertThrows(UnsupportedOperationException.class, () -> listiter.add(1));
        assertThrows(UnsupportedOperationException.class, () -> listiter.set(1));

        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.listIterator(1));
        assertEquals("Sublists of ImList.Immutable are themselves ImList.Immutable",
            ImList.Immutable.of(), emptyList.subList(0, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.subList(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.subList(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.subList(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.subList(1, 0));
        assertEquals(ImList.Immutable.of(), emptyList);
        assertEquals(ImList.Immutable.of().hashCode(), emptyList.hashCode());
        assertEquals("ImList.Immutable[]", emptyList.toString());
    }

    @SuppressWarnings({ "RedundantCollectionOperation" }) @Test public void testImmutableList() {
        final ImList.Immutable<Integer> theList = ImList.Immutable.of(1, 1);
        assertEquals(2, theList.size());
        assertFalse(theList.isEmpty());
        assertTrue(theList.contains(1));

        final Iterator<Integer> iterator = theList.iterator();
        assertNotNull(iterator);
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(1), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(1), iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
        assertThrows(UnsupportedOperationException.class, iterator::remove);

        assertArrayEquals(theList.toArray(), new Object[] { 1, 1 });
        assertThrows(UnsupportedOperationException.class, () -> theList.add(1));
        assertThrows(UnsupportedOperationException.class, () -> theList.remove(Integer.valueOf(1)));
        assertTrue(theList.containsAll(Collections.<Integer>emptyList()));
        assertTrue(theList.containsAll(Collections.singleton(1)));
        assertTrue(theList.containsAll(Arrays.asList(1, 1, 1, 1)));
        assertFalse(theList.containsAll(Arrays.asList(1, 2)));
        assertThrows(UnsupportedOperationException.class, () -> theList.addAll(Collections.singleton(1)));
        assertThrows(UnsupportedOperationException.class, () -> theList.addAll(0, Collections.singleton(1)));
        assertThrows(UnsupportedOperationException.class, () -> theList.removeAll(Collections.singleton(1)));
        assertThrows(UnsupportedOperationException.class, () -> theList.retainAll(Collections.singleton(1)));
        assertThrows(UnsupportedOperationException.class, theList::clear);
        assertEquals(Integer.valueOf(1), theList.get(0));
        assertEquals(Integer.valueOf(1), theList.get(1));
        //noinspection ResultOfMethodCallIgnored
        assertThrows(IndexOutOfBoundsException.class, () -> theList.get(2));
        assertThrows(UnsupportedOperationException.class, () -> theList.set(0, 1));
        assertThrows(UnsupportedOperationException.class, () -> theList.add(0, 1));
        assertThrows(UnsupportedOperationException.class, () -> theList.remove(0));
        assertEquals(0, theList.indexOf(1));
        assertEquals(-1, theList.indexOf(2));
        assertEquals(1, theList.lastIndexOf(1));
        assertEquals(-1, theList.lastIndexOf(2));

        final ListIterator<Integer> listIter = theList.listIterator();
        assertNotNull(listIter);
        assertTrue(listIter.hasNext());
        assertFalse(listIter.hasPrevious());
        assertEquals(0, listIter.nextIndex());
        assertEquals(-1, listIter.previousIndex());
        assertTrue(listIter.hasNext());
        assertEquals(Integer.valueOf(1), listIter.next());
        assertTrue(listIter.hasNext());
        assertTrue(listIter.hasPrevious());
        assertThrows(UnsupportedOperationException.class, listIter::remove);
        assertThrows(UnsupportedOperationException.class, () -> listIter.add(1));
        assertThrows(UnsupportedOperationException.class, () -> listIter.set(1));
        assertEquals(Integer.valueOf(1), listIter.next());
        assertFalse(listIter.hasNext());
        assertThrows(NoSuchElementException.class, listIter::next);
        assertEquals(2, listIter.nextIndex());
        assertEquals(1, listIter.previousIndex());
        assertTrue(listIter.hasPrevious());
        assertEquals(Integer.valueOf(1), listIter.previous());
        assertTrue(listIter.hasPrevious());
        assertEquals(Integer.valueOf(1), listIter.previous());
        assertFalse(listIter.hasPrevious());
        assertThrows(NoSuchElementException.class, listIter::previous);

        // Another list iterator, now starting at the end
        final ListIterator<Integer> listIter2 = theList.listIterator(theList.size());
        assertNotNull(listIter2);
        assertFalse(listIter2.hasNext());
        assertThrows(NoSuchElementException.class, listIter2::next);
        assertEquals(2, listIter2.nextIndex());
        assertEquals(1, listIter2.previousIndex());
        assertTrue(listIter2.hasPrevious());
        assertEquals(Integer.valueOf(1), listIter2.previous());
        assertTrue(listIter2.hasPrevious());
        assertEquals(Integer.valueOf(1), listIter2.previous());
        assertFalse(listIter2.hasPrevious());
        assertThrows(NoSuchElementException.class, listIter2::previous);
        assertTrue(listIter2.hasNext());
        assertFalse(listIter2.hasPrevious());
        assertEquals(0, listIter2.nextIndex());
        assertEquals(-1, listIter2.previousIndex());
        assertTrue(listIter2.hasNext());
        assertEquals(Integer.valueOf(1), listIter2.next());
        assertTrue(listIter2.hasNext());
        assertEquals(Integer.valueOf(1), listIter2.next());

        assertThrows(IndexOutOfBoundsException.class, () -> theList.listIterator(3));
        assertEquals(ImList.Immutable.of(), theList.subList(0, 0));
        assertEquals(ImList.Immutable.of(1), theList.subList(0, 1));
        assertEquals(ImList.Immutable.of(1), theList.subList(1, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> theList.subList(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> theList.subList(0, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> theList.subList(-1, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> theList.subList(1, 0));
        assertEquals(ImList.Immutable.of(1, 1), theList);
        assertEquals(ImList.Immutable.of(1, 1).hashCode(), theList.hashCode());
        assertEquals("ImList.Immutable[1, 1]", theList.toString());
    }

    @SuppressWarnings({ "ConstantValue", "RedundantCollectionOperation" }) @Test public void testEmptyTransientList() {
        final ImList.Transient<Integer> emptyList = ImList.Transient.of();
        assertEquals(0, emptyList.size());
        assertTrue(emptyList.isEmpty());
        assertFalse(emptyList.contains(1));

        @SuppressWarnings("RedundantOperationOnEmptyContainer")
        final Iterator<Integer> iterator = emptyList.iterator();
        assertNotNull(iterator);
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);

        assertArrayEquals(emptyList.toArray(), new Object[] {});
        assertTrue(emptyList.containsAll(Collections.<Integer>emptyList()));
        assertFalse(emptyList.containsAll(Collections.singleton(1)));
        //noinspection ResultOfMethodCallIgnored
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(0));
        assertEquals(-1, emptyList.indexOf(1));
        assertEquals(-1, emptyList.lastIndexOf(1));

        final ListIterator<Integer> listiter = emptyList.listIterator();
        assertNotNull(listiter);
        assertFalse(listiter.hasNext());
        assertFalse(listiter.hasPrevious());
        assertEquals(0, listiter.nextIndex());
        assertEquals(-1, listiter.previousIndex());
        assertThrows(NoSuchElementException.class, listiter::next);
        assertThrows(NoSuchElementException.class, listiter::previous);

        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.listIterator(1));
//        assertEquals(ImList.Transient.of(), emptyList.subList(0, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.subList(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.subList(0, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.subList(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> emptyList.subList(1, 0));
        assertNotEquals(ImList.Immutable.of(), emptyList);
        assertNotEquals(ImList.Immutable.of().hashCode(), emptyList.hashCode());
        assertEquals(ImList.Transient.of(), emptyList);
        assertEquals(ImList.Transient.of().hashCode(), emptyList.hashCode());
        // ImList.Transient.of() === new ImList.Transient<>(0)
        assertEquals(new ImList.Transient<>(0), emptyList);
        assertEquals(new ImList.Transient<>(0).hashCode(), emptyList.hashCode());
        // extra capacity does not influence equals/hashcode of lists
        assertEquals(new ImList.Transient<>(2), emptyList);
        assertEquals(new ImList.Transient<>(2).hashCode(), emptyList.hashCode());
        assertEquals("ImList.Transient[]", emptyList.toString());
    }

    @Test public void testTransientListMutation() {
        final ImList.Transient<Integer> emptyList = ImList.Transient.of();
        assertEquals(ImList.Transient.of(), emptyList);
        final ImList.Immutable<Integer> frozen = emptyList.freeze();
        assertEquals(ImList.Immutable.of(), frozen);
        assertSame(frozen, emptyList.freeze());
        assertNotEquals("Transient lists are not equal to their frozen counterparts.",
            ImList.Transient.of(), emptyList);
        // Frozen transient lists do not allow mutation any more.
        assertThrows(IllegalStateException.class, () -> emptyList.add(1));
        assertThrows(IllegalStateException.class, () -> emptyList.remove(Integer.valueOf(1)));
        assertThrows(IllegalStateException.class, () -> emptyList.addAll(Collections.singleton(1)));
        assertThrows(IllegalStateException.class, () -> emptyList.addAll(0, Collections.singleton(1)));
        assertThrows(IllegalStateException.class, () -> emptyList.removeAll(Collections.singleton(1)));
        assertThrows(IllegalStateException.class, () -> emptyList.retainAll(Collections.singleton(1)));
        assertThrows(IllegalStateException.class, emptyList::clear);
        assertThrows(IllegalStateException.class, () -> emptyList.set(0, 1));
        assertThrows(IllegalStateException.class, () -> emptyList.add(0, 1));
        assertThrows(IllegalStateException.class, () -> emptyList.remove(0));

        final ImList.Transient<Integer> empty2CapacityList = new ImList.Transient<>(2);
        final ImList.Immutable<Integer> frozen2Capacity = empty2CapacityList.freeze();
        assertEquals(
            "Unused capacity (due to the transient's unused capacity) of the immutable list does not affect its behaviour.",
            ImList.Immutable.of(), frozen2Capacity);

        final ImList.Transient<Integer> theList = new ImList.Transient<>(0);
        assertArrayEquals(theList.toArray(), new Object[] {});
        assertTrue(theList.add(1));
        assertArrayEquals(theList.toArray(), new Object[] {1});
        assertTrue(theList.remove(Integer.valueOf(1)));
        assertArrayEquals(theList.toArray(), new Object[] {});
        assertTrue(theList.addAll(Arrays.asList(1, 1, 2, 3, 5, 7, 11, 13)));
        assertArrayEquals(theList.toArray(), new Object[] {1, 1, 2, 3, 5, 7, 11, 13});
        assertTrue(theList.addAll(1, Arrays.asList(42, 420)));
        assertArrayEquals(theList.toArray(), new Object[] {1, 42, 420, 1, 2, 3, 5, 7, 11, 13});
        assertTrue(theList.removeAll(Arrays.asList(1, 2, 3)));
        assertArrayEquals(theList.toArray(), new Object[] {42, 420, 1, 5, 7, 11, 13});
        assertTrue(theList.retainAll(Arrays.asList(7, 13)));
        assertArrayEquals(theList.toArray(), new Object[] {7, 13});
        assertFalse(theList.retainAll(Arrays.asList(7, 13)));
        assertEquals(Integer.valueOf(7), theList.set(0, 1));
        assertArrayEquals(theList.toArray(), new Object[] {1, 13});
        theList.add(0, 5);
        assertArrayEquals(theList.toArray(), new Object[] {5, 1, 13});
        theList.add(2, 6);
        assertArrayEquals(theList.toArray(), new Object[] {5, 1, 6, 13});
        theList.add(4, 7);
        assertArrayEquals(theList.toArray(), new Object[] {5, 1, 6, 13, 7});
        theList.remove(1);
        assertArrayEquals(theList.toArray(), new Object[] {5, 6, 13, 7});
        theList.remove(2);
        assertArrayEquals(theList.toArray(), new Object[] {5, 6, 7});
        theList.clear();
        assertTrue(theList.isEmpty());

        theList.addAll(Arrays.asList(1, 1, 2, 3, 5, 7, 11, 13));
        assertArrayEquals(theList.toArray(), new Object[] {1, 1, 2, 3, 5, 7, 11, 13});
        final ListIterator<Integer> listIter = theList.listIterator();
        assertEquals(Integer.valueOf(1), listIter.next());
        assertEquals(Integer.valueOf(1), listIter.next());
        assertEquals(Integer.valueOf(2), listIter.next());
        assertEquals(Integer.valueOf(2), listIter.previous());
        assertEquals(2, listIter.nextIndex());
        listIter.remove(); // removes the thing last returned by previous() (or next() if that was the latest call)
        assertArrayEquals(theList.toArray(), new Object[] {1, 1, 3, 5, 7, 11, 13});
        assertEquals(2, listIter.nextIndex());
        assertEquals(Integer.valueOf(3), listIter.next());
        assertEquals(3, listIter.nextIndex());
        listIter.remove(); // removes the Integer.valueOf(3) last returned by next()
        assertArrayEquals(theList.toArray(), new Object[] {1, 1, 5, 7, 11, 13});
        assertEquals("Notably, the index is different because we were beyond the thing we removed",
            2, listIter.nextIndex());
        assertThrows("Can't call remove unless next/previous was called and not edited by mutation "
            + "calls like remove/add", IllegalStateException.class, () -> listIter.remove());
        assertThrows("Can't call set unless next/previous was called and not edited by mutation "
            + "calls like remove/add", IllegalStateException.class, () -> listIter.set(1));
        assertEquals(Integer.valueOf(5), listIter.next());
        listIter.add(1); // now it's fine to call
        assertArrayEquals(theList.toArray(), new Object[] {1, 1, 5, 1, 7, 11, 13});
        assertEquals("The cursor is _after_ the added element", 4, listIter.nextIndex());
        assertEquals(Integer.valueOf(1), listIter.previous());
        listIter.set(6); // set applies to the element return by the last next/previous
        assertArrayEquals(theList.toArray(), new Object[] {1, 1, 5, 6, 7, 11, 13});
        assertEquals(Integer.valueOf(6), listIter.next());
    }
}
