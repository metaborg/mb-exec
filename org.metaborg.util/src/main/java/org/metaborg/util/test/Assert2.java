package org.metaborg.util.test;

import static org.junit.Assert.fail;

import org.metaborg.util.iterators.Iterables2;
import org.metaborg.util.observable.ITestableObserver;
import org.metaborg.util.observable.TimestampedNotification;

import rx.Notification;

import com.google.common.collect.Iterables;

public class Assert2 {
    public static <T> void assertSize(int expected, Iterable<T> actual, String message) {
        final int actualSize = Iterables.size(actual);
        if(actualSize != expected) {
            fail(formatSize(message, expected, actualSize));
        }
    }

    public static <T> void assertSize(int expected, T[] actual, String message) {
        final int actualSize = actual.length;
        if(actualSize != expected) {
            fail(formatSize(message, expected, actualSize));
        }
    }

    public static <T> void assertSize(int expected, Iterable<T> actual) {
        assertSize(expected, actual, null);
    }

    public static <T> void assertSize(int expected, T[] actual) {
        assertSize(expected, actual, null);
    }

    private static String formatSize(String message, int expected, int actual) {
        final String formatted = preformat(message);
        return formatted + "expected size: " + expected + " but was: " + actual;
    }


    public static <T> void assertEmpty(Iterable<T> actual, String message) {
        final int actualSize = Iterables.size(actual);
        if(actualSize != 0) {
            fail(formatEmpty(message, actualSize));
        }
    }

    public static <T> void assertEmpty(T[] actual, String message) {
        final int actualSize = actual.length;
        if(actualSize != 0) {
            fail(formatEmpty(message, actualSize));
        }
    }

    public static <T> void assertEmpty(Iterable<T> actual) {
        assertEmpty(actual, null);
    }

    public static <T> void assertEmpty(T[] actual) {
        assertEmpty(actual, null);
    }

    private static String formatEmpty(String message, int actual) {
        final String formatted = preformat(message);
        return formatted + "expected empty, but size was: " + actual;
    }


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

    private static <T> String formatEquals(String message, T expected, T actual) {
        final String formatted = preformat(message);
        final String expectedString = String.valueOf(expected);
        final String actualString = String.valueOf(actual);
        return formatted + "expected: " + formatClassAndValue(expected, expectedString) + " but was: "
            + formatClassAndValue(actual, actualString);
    }


    public static <T> void assertContains(T expected, Iterable<? extends T> actual, String message) {
        if(!Iterables.contains(actual, expected)) {
            fail(formatContains(message, expected, actual));
        }
    }

    public static <T> void assertContains(T expected, Iterable<? extends T> actual) {
        assertContains(expected, actual, null);
    }

    public static <T> void assertContains(T expected, T[] actual, String message) {
        assertContains(expected, Iterables2.from(actual), message);
    }

    public static <T> void assertContains(T expected, T[] actual) {
        assertContains(expected, actual, null);
    }

    private static <T> String formatContains(String message, T expected, Iterable<? extends T> actual) {
        final String formatted = preformat(message);
        final String expectedString = String.valueOf(expected);
        final String actualString = String.valueOf(actual);
        return formatted + "expected: " + formatClassAndValue(expected, expectedString) + " contained in: "
            + formatClassAndValue(actual, actualString);
    }


    public static <T> void assertNotContains(T expected, Iterable<? extends T> actual, String message) {
        if(Iterables.contains(actual, expected)) {
            fail(formatNotContains(message, expected, actual));
        }
    }

    public static <T> void assertNotContains(T expected, Iterable<? extends T> actual) {
        assertNotContains(expected, actual, null);
    }

    public static <T> void assertNotContains(T expected, T[] actual, String message) {
        assertNotContains(expected, Iterables2.from(actual), message);
    }

    public static <T> void assertNotContains(T expected, T[] actual) {
        assertNotContains(expected, actual, null);
    }

    private static <T> String formatNotContains(String message, T expected, Iterable<? extends T> actual) {
        final String formatted = preformat(message);
        final String expectedString = String.valueOf(expected);
        final String actualString = String.valueOf(actual);
        return formatted + "expected: " + formatClassAndValue(expected, expectedString) + " not contained in: "
            + formatClassAndValue(actual, actualString);
    }


    public static <T> void assertOnNext(T expected, ITestableObserver<T> observer, String message) {
        final TimestampedNotification<T> timestampedNotificaiton = observer.poll();
        final Notification<T> notification = timestampedNotificaiton.notification;

        if(!notification.isOnNext()) {
            fail(formatOnNext(message, notification));
        }
        final T value = notification.getValue();
        if(!value.equals(expected)) {
            fail(formatOnNext(message, expected, value));
        }
    }

    public static <T> void assertOnNext(T expected, ITestableObserver<T> observer) {
        assertOnNext(expected, observer, null);
    }

    private static <T> String formatOnNext(String message, Notification<T> actual) {
        final String formatted = preformat(message);
        return formatted + "expected OnNext, was: " + actual.getKind();
    }

    private static <T> String formatOnNext(String message, T expected, T actual) {
        final String formatted = preformat(message);
        final String expectedString = String.valueOf(expected);
        final String actualString = String.valueOf(actual);
        return formatted + "expected OnNext with value: " + formatClassAndValue(expected, expectedString)
            + " but was: " + formatClassAndValue(actual, actualString);
    }


    private static String preformat(String message) {
        String formatted = "";
        if(message != null && !message.equals("")) {
            formatted = message + " ";
        }
        return formatted;
    }

    /**
     * Copied from {@link org.junit.Assert#formatClassAndValue}
     */
    private static String formatClassAndValue(Object value, String valueString) {
        final String className = value == null ? "null" : value.getClass().getName();
        return className + "<" + valueString + ">";
    }
}
