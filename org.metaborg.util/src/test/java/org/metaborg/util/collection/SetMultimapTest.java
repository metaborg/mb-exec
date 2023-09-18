package org.metaborg.util.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class SetMultimapTest {
    @SuppressWarnings({ "ConstantValue", "RedundantCollectionOperation" }) @Test public void testEmptyMap() {
        final SetMultimap<String, Integer> emptyMap = new SetMultimap<>();

        // establish emptiness
        assertEquals(0, emptyMap.size());
        assertTrue(emptyMap.isEmpty());

        // establish empty iterator
        final Iterator<Map.Entry<String, Integer>> iterator = emptyMap.entries().iterator();
        assertNotNull(iterator);
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);

        // establish other methods
        assertFalse(emptyMap.containsKey(""));
        assertFalse(emptyMap.containsValue(1));
        assertFalse(emptyMap.containsEntry("", 1));

        final Set<Integer> nonKeyResult = emptyMap.get("");
        assertNotNull(nonKeyResult);
        assertTrue(nonKeyResult.isEmpty());
        assertEquals(0, nonKeyResult.size());
        assertThrows(UnsupportedOperationException.class, () -> nonKeyResult.add(1));

        final Set<String> keySet = emptyMap.keySet();
        assertNotNull(keySet);
        assertTrue(keySet.isEmpty());
        assertEquals(0, keySet.size());

        final Collection<Integer> values = emptyMap.values();
        assertNotNull(values);
        assertTrue(values.isEmpty());
        assertEquals(0, values.size());
    }

    @SuppressWarnings({ "RedundantCollectionOperation" }) @Test public void testMap() {
        final SetMultimap<String, Integer> theMap = new SetMultimap<>();
        assertEquals(0, theMap.size());
        assertTrue(theMap.isEmpty());
        assertFalse(theMap.containsKey(""));
        assertFalse(theMap.containsValue(1));
        assertFalse(theMap.containsEntry("", 1));

        assertTrue(theMap.put("", 1));
        assertEquals(1, theMap.size());
        assertFalse(theMap.isEmpty());
        assertTrue(theMap.containsKey(""));
        assertTrue(theMap.containsValue(1));
        assertTrue(theMap.containsEntry("", 1));
        assertEquals(Collections.singleton(1), theMap.get(""));

        assertTrue(theMap.put("", 2));
        assertEquals(2, theMap.size());
        assertTrue(theMap.containsKey(""));
        assertTrue(theMap.containsValue(1));
        assertTrue(theMap.containsValue(2));
        assertTrue(theMap.containsEntry("", 1));
        assertTrue(theMap.containsEntry("", 2));
        assertEquals(new HashSet<>(Arrays.asList(1,2)), theMap.get(""));

        // adding the same thing a second time
        assertFalse(theMap.put("", 2));
        assertEquals(2, theMap.size());
        assertTrue(theMap.containsKey(""));
        assertTrue(theMap.containsValue(1));
        assertTrue(theMap.containsValue(2));
        assertTrue(theMap.containsEntry("", 1));
        assertTrue(theMap.containsEntry("", 2));
        assertEquals(new HashSet<>(Arrays.asList(1,2)), theMap.get(""));

        assertEquals(Collections.singleton(""), theMap.keySet());
        theMap.forEach((s, ints) -> {
            assertEquals("", s);
            assertEquals(new HashSet<>(Arrays.asList(1, 2)), ints);
        });

        assertTrue(theMap.putAll("", new HashSet<>(Arrays.asList(2,3,4))));
        assertEquals(new HashSet<>(Arrays.asList(1,2,3,4)), theMap.get(""));
    }
}
