package org.metaborg.util.test;

import static org.junit.Assert.*;

import org.metaborg.util.iterators.Iterables2;

import com.google.common.collect.Iterables;

public class Assert2 {
    public static <T> void assertIterableEquals(Iterable<T> expected, Iterable<T> actual, String message) {
        if(!Iterables.elementsEqual(expected, actual)) {
            fail(formatEquals(message, expected, actual));
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

    public static <T> void assertContains(T expected, Iterable<T> actual, String message) {
        if(!Iterables.contains(actual, expected)) {
            fail(formatContains(message, expected, actual));
        }
    }

    public static <T> void assertContains(T expected, Iterable<T> actual) {
        assertContains(expected, actual, null);
    }

    public static <T> void assertContains(T expected, T[] actual, String message) {
        assertContains(expected, Iterables2.from(actual));
    }

    public static <T> void assertContains(T expected, T[] actual) {
        assertContains(expected, actual, null);
    }

    public static <T> void assertNotContains(T expected, Iterable<T> actual, String message) {
        if(Iterables.contains(actual, expected)) {
            fail(formatNotContains(message, expected, actual));
        }
    }

    public static <T> void assertNotContains(T expected, Iterable<T> actual) {
        assertNotContains(expected, actual, null);
    }

    public static <T> void assertNotContains(T expected, T[] actual, String message) {
        assertNotContains(expected, Iterables2.from(actual));
    }

    public static <T> void assertNotContains(T expected, T[] actual) {
        assertNotContains(expected, actual, null);
    }


    /**
     * Copied from {@link org.junit.Assert#format}
     */
    private static <T> String formatEquals(String message, T expected, T actual) {
        String formatted = "";
        if(message != null && !message.equals("")) {
            formatted = message + " ";
        }
        String expectedString = String.valueOf(expected);
        String actualString = String.valueOf(actual);
        return formatted + "expected: " + formatClassAndValue(expected, expectedString) + " but was: "
            + formatClassAndValue(actual, actualString);
    }

    private static <T> String formatContains(String message, T expected, Iterable<T> actual) {
        String formatted = "";
        if(message != null && !message.equals("")) {
            formatted = message + " ";
        }
        String expectedString = String.valueOf(expected);
        String actualString = String.valueOf(actual);
        return formatted + "expected: " + formatClassAndValue(expected, expectedString) + " contained in: "
            + formatClassAndValue(actual, actualString);
    }

    private static <T> String formatNotContains(String message, T expected, Iterable<T> actual) {
        String formatted = "";
        if(message != null && !message.equals("")) {
            formatted = message + " ";
        }
        String expectedString = String.valueOf(expected);
        String actualString = String.valueOf(actual);
        return formatted + "expected: " + formatClassAndValue(expected, expectedString) + " not contained in: "
            + formatClassAndValue(actual, actualString);
    }

    /**
     * Copied from {@link org.junit.Assert#formatClassAndValue}
     */
    private static String formatClassAndValue(Object value, String valueString) {
        String className = value == null ? "null" : value.getClass().getName();
        return className + "<" + valueString + ">";
    }
}
