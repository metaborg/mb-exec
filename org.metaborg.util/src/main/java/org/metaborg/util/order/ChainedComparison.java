package org.metaborg.util.order;

import java.util.Comparator;

public class ChainedComparison {
    private int comparisonResult = 0;

    public <T> ChainedComparison compare(T left, T right, Comparator<T> comparator) {
        if(!done()) {
            comparisonResult = comparator.compare(left, right);
        }
        return this;
    }

    public <T> ChainedComparison compare(Comparable<T> left, T right) {
        return compare((T) left, right, (l, r) -> left.compareTo(r));
    }

    public ChainedComparison compare(int left, int right) {
        return compare(left, right, Integer::compare);
    }

    public ChainedComparison compare(long left, long right) {
        return compare(left, right, Long::compare);
    }

    public ChainedComparison compare(float left, float right) {
        return compare(left, right, Float::compare);
    }

    public ChainedComparison compare(double left, double right) {
        return compare(left, right, Double::compare);
    }

    public ChainedComparison compare(boolean left, boolean right) {
        return compare(left, right, Boolean::compare);
    }

    public boolean done() {
        return comparisonResult != 0;
    }

    public int result() {
        return comparisonResult;
    }
}
