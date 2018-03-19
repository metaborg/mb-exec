package org.metaborg.util.functions;

@FunctionalInterface
public interface Predicate1<T> {

    boolean test(T t);

    public static <T> Predicate1<T> never() {
        return t -> false;
    }

    public static <T> Predicate1<T> always() {
        return t -> true;
    }

}