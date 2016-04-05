package org.metaborg.util.exception;

public final class SneakyThrow {
    private SneakyThrow() {
    }

    public static void sneakyThrow(Throwable t) {
        SneakyThrow.<Error>doThrow(t);
    }

    @SuppressWarnings("unchecked") private static <T extends Throwable> void doThrow(Throwable t) throws T {
        throw (T) t;
    }
}