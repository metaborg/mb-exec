package org.metaborg.util.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.metaborg.util.iterators.Iterables2;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

/**
 * Multitable that can hold duplicate row-value, column-value and row/column-value pairs. Uses
 * {@link HashBasedTable} as backing table.
 */
public class HashMultiTable<R, C, V> implements MultiTable<R, C, V> {
    private final Table<R, C, Collection<V>> table;


    public static <R, C, V> MultiTable<R, C, V> create() {
        return new HashMultiTable<R, C, V>();
    }


    public HashMultiTable() {
        table = HashBasedTable.create();
    }


    @Override public boolean contains(R rowKey, C columnKey) {
        return table.contains(rowKey, columnKey);
    }

    @Override public boolean containsRow(R rowKey) {
        return table.containsRow(rowKey);
    }

    @Override public boolean containsColumn(C columnKey) {
        return table.containsColumn(columnKey);
    }

    @Override public Collection<V> get(R rowKey, C columnKey) {
        return table.get(rowKey, columnKey);
    }

    @Override public void clear() {
        table.clear();
    }

    @Override public V put(R rowKey, C columnKey, V value) {
        Collection<V> values = table.get(rowKey, columnKey);
        if(values == null) {
            values = createCollection();
            table.put(rowKey, columnKey, values);
        }
        values.add(value);
        return value;
    }

    @Override public Collection<V> removeAll(R rowKey, C columnKey) {
        final Collection<V> values = table.get(rowKey, columnKey);
        if(values == null) {
            return null;
        }
        final Collection<V> removedValues = createCollection(values);
        values.clear();
        return removedValues;
    }

    @Override public Map<C, Collection<V>> row(R rowKey) {
        return table.row(rowKey);
    }

    @Override public Iterable<V> rowValues(R rowKey) {
        return Iterables2.from(row(rowKey).values());
    }

    @Override public Map<R, Collection<V>> column(C columnKey) {
        return table.column(columnKey);
    }

    @Override public Iterable<V> columnValues(C columnKey) {
        return Iterables2.from(column(columnKey).values());
    }

    @Override public Set<Cell<R, C, Collection<V>>> cellSet() {
        return table.cellSet();
    }

    @Override public Set<R> rowKeySet() {
        return table.rowKeySet();
    }

    @Override public Set<C> columnKeySet() {
        return table.columnKeySet();
    }

    @Override public Iterable<V> values() {
        return Iterables2.from(table.values());
    }

    @Override public Map<R, Map<C, Collection<V>>> rowMap() {
        return table.rowMap();
    }

    @Override public Map<C, Map<R, Collection<V>>> columnMap() {
        return table.columnMap();
    }


    private Collection<V> createCollection() {
        return Lists.newLinkedList();
    }

    private Collection<V> createCollection(Collection<V> values) {
        return Lists.newLinkedList(values);
    }
}