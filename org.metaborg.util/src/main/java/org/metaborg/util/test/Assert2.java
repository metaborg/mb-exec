package org.metaborg.util.test;

import static org.junit.Assert.*;

import org.metaborg.util.iterators.Iterables2;

import com.google.common.collect.Iterables;

public class Assert2 {
    public static <T> void assertIterableEquals(Iterable<T> expected, Iterable<T> actual, String message) {
        if(!Iterables.elementsEqual(expected, actual)) {
            fail(format(message, expected, actual));
        }
    }

    public static <T> void assertIterableEquals(Iterable<T> expected, Iterable<T> actual) {
        assertIterableEquals(expected, actual, null);
    }

    public static <T> void assertIterableEquals(String message, Iterable<T> actual,
        @SuppressWarnings("unchecked") T... expected) {
        assertIterableEquals(Iterables2.from(expected), actual, message);
    }

    public static <T> void assertIterableEquals(Iterable<T> actual, @SuppressWarnings("unchecked") T... expected) {
        assertIterableEquals(null, actual, expected);
    }


    /**
     * Copied from {@link org.junit.Assert#format}
     */
    private static String format(String message, Object expected, Object actual) {
        String formatted = "";
        if(message != null && !message.equals("")) {
            formatted = message + " ";
        }
        String expectedString = String.valueOf(expected);
        String actualString = String.valueOf(actual);
        if(expectedString.equals(actualString)) {
            return formatted + "expected: " + formatClassAndValue(expected, expectedString) + " but was: "
                + formatClassAndValue(actual, actualString);
        } else {
            return formatted + "expected:<" + expectedString + "> but was:<" + actualString + ">";
        }
    }

    /**
     * Copied from {@link org.junit.Assert#formatClassAndValue}
     */
    private static String formatClassAndValue(Object value, String valueString) {
        String className = value == null ? "null" : value.getClass().getName();
        return className + "<" + valueString + ">";
    }
}
