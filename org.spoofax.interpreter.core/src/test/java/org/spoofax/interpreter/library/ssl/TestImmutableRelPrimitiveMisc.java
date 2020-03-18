package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.BinaryRelation;
import io.usethesource.capsule.Map;
import io.usethesource.capsule.Set;

import java.util.Arrays;
import java.util.HashSet;
import org.junit.Test;
import org.spoofax.interpreter.core.Interpreter;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.util.TermUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.spoofax.terms.AbstractTermFactory.EMPTY_TERM_ARRAY;

public class TestImmutableRelPrimitiveMisc extends ImmutableCollectionTestSetup {
    private final SSL_immutable_relation SSL_immutable_relation = new SSL_immutable_relation();
    private final SSL_immutable_relation_to_list SSL_immutable_relation_to_list = new SSL_immutable_relation_to_list();
    private static final SSL_immutable_relation_to_map SSL_immutable_relation_to_map =
        new SSL_immutable_relation_to_map();
    private static final SSL_immutable_relation_to_set SSL_immutable_relation_to_set =
        new SSL_immutable_relation_to_set();
    private final SSL_immutable_relation_from_list SSL_immutable_relation_from_list =
        new SSL_immutable_relation_from_list();
    private final SSL_immutable_relation_keys SSL_immutable_relation_keys = new SSL_immutable_relation_keys();
    private final SSL_immutable_relation_keys_set SSL_immutable_relation_keys_set =
        new SSL_immutable_relation_keys_set();
    private final SSL_immutable_relation_values SSL_immutable_relation_values = new SSL_immutable_relation_values();
    private final SSL_immutable_relation_values_set SSL_immutable_relation_values_set = new SSL_immutable_relation_values_set();
    private final SSL_immutable_relation_get SSL_immutable_relation_get = new SSL_immutable_relation_get();

    @Test
    public void relIsEmpty() {
        boolean result = SSL_immutable_relation.call(context, new Strategy[0], EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableRelation.class));
        StrategoImmutableRelation current = (StrategoImmutableRelation) context.current();
        assertTrue(current.backingRelation.isEmpty());
    }

    @Test
    public void relFromListUnique() {
        context.setCurrent(f.makeList(f.makeTuple(one, two), f.makeTuple(one, three)));
        boolean result = SSL_immutable_relation_from_list.call(context, new Strategy[0], EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableRelation.class));
        StrategoImmutableRelation current = (StrategoImmutableRelation) context.current();
        assertEquals(BinaryRelation.Immutable.of(one, two, one, three), current.backingRelation);
    }

    @Test
    public void relFromListKeepFirst() {
        assertEquals(oneIsh, one);
        assertNotSame(one, oneIsh);
        context.setCurrent(f.makeList(f.makeTuple(one, two), f.makeTuple(oneIsh, two)));
        boolean result = SSL_immutable_relation_from_list.call(context, new Strategy[0], EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableRelation.class));
        StrategoImmutableRelation current = (StrategoImmutableRelation) context.current();
        assertEquals(current.backingRelation.size(), 1);
        assertSame(current.backingRelation.keyIterator().next(), one);
    }

    @Test
    public void relKeys() {
        context.setCurrent(new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_relation_keys.call(context, new Strategy[0], EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertTrue(TermUtils.isList(context.current()));
        IStrategoList current = (IStrategoList) context.current();
        assertEquals(2, current.size());
        assertEquals(new HashSet<>(Arrays.asList(one, two)), new HashSet<>(Arrays.asList(current.getAllSubterms())));
    }

    @Test
    public void relKeysToSet() {
        context.setCurrent(new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_relation_keys_set.call(context, new Strategy[0], EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertEquals(Set.Immutable.of(one, two), current.backingSet);
    }

    @Test
    public void relValues() {
        context.setCurrent(new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_relation_values.call(context, new Strategy[0], EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertTrue(TermUtils.isList(context.current()));
        IStrategoList current = (IStrategoList) context.current();
        assertEquals(2, current.size());
        assertEquals(new HashSet<>(Arrays.asList(a, b)), new HashSet<>(Arrays.asList(current.getAllSubterms())));
    }

    @Test
    public void relValuesToSet() {
        context.setCurrent(new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_relation_values_set.call(context, new Strategy[0], EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertEquals(Set.Immutable.of(a, b), current.backingSet);
    }

    @Test
    public void relToList() {
        context.setCurrent(new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, two)));
        boolean result = SSL_immutable_relation_to_list.call(context, new Strategy[0], EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertTrue(TermUtils.isList(context.current()));
        IStrategoList current = (IStrategoList) context.current();
        assertEquals(1, current.size());
        assertEquals(f.makeTuple(one, two), current.head());
    }

    @Test
    public void relToMap() throws InterpreterException {
        context.setCurrent(new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, two, one, three)));
        boolean result = SSL_immutable_relation_to_map.call(context, new Strategy[] {
            InterpreterStrategy.of(t -> new StrategoImmutableSet(t.getSubterm(0), t.getSubterm(1))) // keep both as a set
        }, EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertEquals(Map.Immutable.of(one, new StrategoImmutableSet(two, three)), current.backingMap);
    }

    @Test
    public void relToSet() {
        context.setCurrent(new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, two, one, three)));
        boolean result = SSL_immutable_relation_to_set.call(context, new Strategy[0], EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertEquals(Set.Immutable.of(f.makeTuple(one, two), f.makeTuple(one, three)), current.backingSet);
    }

    @Test
    public void relGetExisting() {
        context.setCurrent(new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_relation_get.call(context, new Strategy[0], new IStrategoTerm[] { one });
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertEquals(Set.Immutable.of(a), current.backingSet);
    }
}
