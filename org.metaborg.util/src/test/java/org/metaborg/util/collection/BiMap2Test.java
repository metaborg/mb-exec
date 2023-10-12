package org.metaborg.util.collection;

import java.util.Collections;

import org.junit.Test;
import org.metaborg.util.tuple.Tuple2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class BiMap2Test {
    @SuppressWarnings({ "ConstantValue", "RedundantCollectionOperation" }) @Test
    public void testEmptyBiMap() {
        final BiMap2<Integer, String> theMap = new BiMap2<>();
        testMap(theMap, 1, "1");

        assertEquals(theMap, new BiMap2<>());
        assertEquals(0, theMap.size());
        assertTrue(theMap.isEmpty());
        assertEquals(Collections.emptySet(), theMap.keySet());
        assertEquals(Collections.emptySet(), theMap.valueSet());
        assertEquals(Collections.emptySet(), theMap.entrySet());
        assertEquals(Collections.emptyMap(), theMap.asMap());
        assertEquals(theMap, theMap.inverse());
    }

    @Test public void testNonEmptyMap() {
        final BiMap2<Integer, String> theMap = new BiMap2<>();
        theMap.put(1, "1");

        testNonEmptyMap(theMap, 1, "1", 2, "2");

        assertEquals(1, theMap.size());

        theMap.put(2, "2");

        testNonEmptyMap(theMap, 1, "1", 3, "3");
        assertFalse(theMap.containsEntry(1, "2"));
        assertFalse(theMap.containsEntry(2, "1"));
        testReplacement(theMap, 1, "1", 2, "2", 3, "3");

        assertEquals(2, theMap.size());
    }

    public <K, V> void testMap(BiMap2<K, V> theMap, K unaddedKey, V unaddedValue) {
        assertFalse(theMap.containsKey(unaddedKey));
        assertFalse(theMap.containsValue(unaddedValue));
        assertFalse(theMap.containsEntry(unaddedKey, unaddedValue));
        assertNull(theMap.getValue(unaddedKey));
        assertNull(theMap.getKey(unaddedValue));
        assertEquals("get(K) is an alias", theMap.getValue(unaddedKey), theMap.get(unaddedKey));
        assertThrows("views are unmodifiable", UnsupportedOperationException.class,
            () -> theMap.keySet().add(unaddedKey));
        assertThrows("views are unmodifiable", UnsupportedOperationException.class,
            () -> theMap.valueSet().add(unaddedValue));
        assertEquals("values() is an alias", theMap.valueSet(), theMap.values());
        assertThrows("views are unmodifiable", UnsupportedOperationException.class,
            () -> theMap.entrySet().add(Tuple2.of(unaddedKey, unaddedValue)));
        assertThrows("views are unmodifiable", UnsupportedOperationException.class,
            () -> theMap.asMap().put(unaddedKey, unaddedValue));
        assertNull(theMap.remove(unaddedKey));
        assertFalse(theMap.remove(unaddedKey, unaddedValue));
        assertNull(theMap.removeValue(unaddedValue));
        assertNull(theMap.replace(unaddedKey, unaddedValue));
        assertEquals(theMap, theMap.inverse().inverse());
        final BiMap2<K, V> copy = new BiMap2<>(theMap);
        assertEquals(theMap, copy);
        copy.clear();
        assertEquals(new BiMap2<>(), copy);
    }

    public <K, V> void testNonEmptyMap(BiMap2<K, V> theMap, K addedKey, V addedValue, K unaddedKey,
            V unaddedValue) {
        assertTrue(theMap.containsKey(addedKey));
        assertFalse(theMap.containsKey(unaddedKey));
        assertTrue(theMap.containsValue(addedValue));
        assertFalse(theMap.containsValue(unaddedValue));
        assertTrue(theMap.containsEntry(addedKey, addedValue));
        assertFalse(theMap.containsEntry(addedKey, unaddedValue));
        assertFalse(theMap.containsEntry(unaddedKey, addedValue));
        assertFalse(theMap.containsEntry(unaddedKey, unaddedValue));
        assertFalse(theMap.isEmpty());
        assertEquals(addedValue, theMap.getValue(addedKey));
        assertEquals(addedKey, theMap.getKey(addedValue));
        assertNull(theMap.getValue(unaddedKey));
        assertNull(theMap.getKey(unaddedValue));
        assertEquals("get(K) is an alias", theMap.getValue(addedKey), theMap.get(addedKey));
        assertThrows("views are unmodifiable", UnsupportedOperationException.class,
            () -> theMap.keySet().add(addedKey));
        assertThrows("views are unmodifiable", UnsupportedOperationException.class,
            () -> theMap.valueSet().add(addedValue));
        assertEquals("values() is an alias", theMap.valueSet(), theMap.values());
        assertThrows("views are unmodifiable", UnsupportedOperationException.class,
            () -> theMap.entrySet().add(Tuple2.of(addedKey, addedValue)));
        assertThrows("views are unmodifiable", UnsupportedOperationException.class,
            () -> theMap.asMap().put(addedKey, addedValue));
        testRemoval(theMap, addedKey, addedValue, unaddedKey, unaddedValue);
        testAddition(theMap, addedKey, addedValue, unaddedKey, unaddedValue);

        testMap(theMap, unaddedKey, unaddedValue);
    }

    private <K, V> void testRemoval(BiMap2<K, V> theMap, K addedKey, V addedValue, K unaddedKey,
            V unaddedValue) {
        BiMap2<K, V> copy = new BiMap2<>(theMap);
        assertEquals(theMap, copy);
        assertTrue(copy.containsKey(addedKey));
        assertTrue(copy.containsValue(addedValue));
        assertEquals(addedValue, copy.remove(addedKey));
        assertNotEquals(theMap, copy);
        assertFalse(copy.containsKey(addedKey));
        assertFalse(copy.containsValue(addedValue));

        copy = new BiMap2<>(theMap);
        assertEquals(theMap, copy);
        assertTrue(copy.containsKey(addedKey));
        assertTrue(copy.containsValue(addedValue));
        assertEquals(addedKey, copy.removeValue(addedValue));
        assertNotEquals(theMap, copy);
        assertFalse(copy.containsKey(addedKey));
        assertFalse(copy.containsValue(addedValue));

        copy = new BiMap2<>(theMap);
        assertEquals(theMap, copy);
        assertTrue(copy.containsKey(addedKey));
        assertTrue(copy.containsValue(addedValue));
        assertTrue(copy.remove(addedKey, addedValue));
        assertNotEquals(theMap, copy);
        assertFalse(copy.containsKey(addedKey));
        assertFalse(copy.containsValue(addedValue));

        copy = new BiMap2<>(theMap);
        assertEquals(theMap, copy);
        assertNull(copy.remove(unaddedKey));
        assertEquals(theMap, copy);

        copy = new BiMap2<>(theMap);
        assertEquals(theMap, copy);
        assertFalse(copy.remove(unaddedKey, addedValue));
        assertEquals(theMap, copy);

        copy = new BiMap2<>(theMap);
        assertEquals(theMap, copy);
        assertFalse(copy.remove(addedKey, unaddedValue));
        assertEquals(theMap, copy);
    }

    private <K, V> void testAddition(BiMap2<K, V> theMap, K addedKey, V addedValue, K unaddedKey,
            V unaddedValue) {
        BiMap2<K, V> copy = new BiMap2<>(theMap);
        assertEquals(theMap, copy);
        assertNull(copy.put(unaddedKey, unaddedValue));
        assertNotEquals(theMap, copy);
        assertEquals(theMap.size() + 1, copy.size());

        copy = new BiMap2<>(theMap);
        assertEquals(theMap, copy);
        assertEquals(addedValue, copy.put(addedKey, addedValue));
        assertEquals(theMap, copy);

        copy = new BiMap2<>(theMap);
        assertEquals(theMap, copy);
        assertEquals(addedValue, copy.put(addedKey, unaddedValue));
        assertNotEquals(theMap, copy);
        assertEquals(theMap.size(), copy.size());
        assertEquals(unaddedValue, copy.getValue(addedKey));

        final BiMap2<K, V> finalCopy = new BiMap2<>(theMap);
        assertEquals(theMap, finalCopy);
        assertThrows(IllegalArgumentException.class, () -> finalCopy.put(unaddedKey, addedValue));
        assertEquals(theMap, finalCopy);
    }

    private <K, V> void testReplacement(BiMap2<K, V> theMap, K addedKey1, V addedValue1,
            K addedKey2, V addedValue2, K unaddedKey, V unaddedValue) {
        BiMap2<K, V> copy = new BiMap2<>(theMap);
        assertEquals(theMap, copy);
        assertNull(copy.replace(unaddedKey, unaddedValue));
        assertEquals(theMap, copy);

        copy = new BiMap2<>(theMap);
        assertEquals(theMap, copy);
        assertEquals(addedValue1, copy.replace(addedKey1, addedValue1));
        assertEquals(theMap, copy);

        copy = new BiMap2<>(theMap);
        assertEquals(theMap, copy);
        assertEquals(addedValue1, copy.replace(addedKey1, unaddedValue));
        assertNotEquals(theMap, copy);
        assertEquals(theMap.size(), copy.size());
        assertEquals(unaddedValue, copy.getValue(addedKey1));

        copy = new BiMap2<>(theMap);
        assertEquals(theMap, copy);
        assertNull(copy.replace(unaddedKey, addedValue1));
        assertEquals(theMap, copy);

        final BiMap2<K, V> finalCopy = new BiMap2<>(theMap);
        assertEquals(theMap, finalCopy);
        assertThrows(IllegalArgumentException.class, () -> finalCopy.replace(addedKey1, addedValue2));
        assertEquals(theMap, finalCopy);
    }
}
