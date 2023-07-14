package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Map;
import io.usethesource.capsule.Set;

import java.util.Arrays;
import java.util.HashSet;
import org.junit.Test;
import org.metaborg.util.collection.CapsuleUtil;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.util.TermUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.spoofax.terms.AbstractTermFactory.EMPTY_TERM_ARRAY;

public class TestImmutableMapPrimitiveMisc extends ImmutableCollectionTestSetup {
    private final SSL_immutable_map SSL_immutable_map = new SSL_immutable_map();
    private final SSL_immutable_map_get SSL_immutable_map_get = new SSL_immutable_map_get();
    private final SSL_immutable_map_get_eq SSL_immutable_map_get_eq = new SSL_immutable_map_get_eq();
    private final SSL_immutable_map_from_list SSL_immutable_map_from_list = new SSL_immutable_map_from_list();
    private final SSL_immutable_map_keys SSL_immutable_map_keys = new SSL_immutable_map_keys();
    private final SSL_immutable_map_keys_to_set SSL_immutable_map_keys_to_set = new SSL_immutable_map_keys_to_set();
    private final SSL_immutable_map_to_list SSL_immutable_map_to_list = new SSL_immutable_map_to_list();
    private final SSL_immutable_map_values SSL_immutable_map_values = new SSL_immutable_map_values();

    @Test
    public void mapIsEmpty() {
        boolean result = SSL_immutable_map.call(context, new Strategy[0], EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertTrue(current.backingMap.isEmpty());
    }

    @Test
    public void mapFromListUnique() throws InterpreterException {
        context.setCurrent(f.makeList(f.makeTuple(one, a), f.makeTuple(two, b)));
        boolean result = SSL_immutable_map_from_list.call(context, new Strategy[] {InterpreterStrategy.of(t -> t.getSubterm(0))},
            EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertEquals(current.backingMap.get(one), a);
        assertEquals(current.backingMap.get(two), b);
    }

    @Test
    public void mapFromListKeepFirst() throws InterpreterException {
        context.setCurrent(f.makeList(f.makeTuple(one, a), f.makeTuple(one, b)));
        boolean result = SSL_immutable_map_from_list.call(context, new Strategy[] {InterpreterStrategy.of(t -> t.getSubterm(0))},
            EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertEquals(current.backingMap.get(one), a);
    }

    @Test
    public void mapFromListKeepSecond() throws InterpreterException {
        context.setCurrent(f.makeList(f.makeTuple(one, a), f.makeTuple(one, b)));
        boolean result = SSL_immutable_map_from_list.call(context, new Strategy[] {InterpreterStrategy.of(t -> t.getSubterm(1))},
            EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertEquals(current.backingMap.get(one), b);
    }

    @Test
    public void mapKeys() {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_keys.call(context, new Strategy[0], EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertTrue(TermUtils.isList(context.current()));
        IStrategoList current = (IStrategoList) context.current();
        assertEquals(current.size(), 2);
        assertEquals(new HashSet<>(Arrays.asList(current.getAllSubterms())), new HashSet<>(Arrays.asList(one, two)));
    }

    @Test
    public void mapKeysToSet() {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_keys_to_set.call(context, new Strategy[0],
            EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertEquals(current.backingSet, CapsuleUtil.immutableSet(one, two));
    }

    @Test
    public void mapToListEmpty() {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of()));
        boolean result = SSL_immutable_map_to_list.call(context, new Strategy[0], EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertTrue(TermUtils.isList(context.current()));
        IStrategoList current = (IStrategoList) context.current();
        assertEquals(current, f.makeList());
    }

    @Test
    public void mapToList() {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_to_list.call(context, new Strategy[0], EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertTrue(TermUtils.isList(context.current()));
        IStrategoList current = (IStrategoList) context.current();
        assertEquals(new HashSet<>(Arrays.asList(current.getAllSubterms())),
            new HashSet<>(Arrays.asList(f.makeTuple(one, a), f.makeTuple(two, b))));
    }

    @Test
    public void mapValues() {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_values.call(context, new Strategy[0], EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertTrue(TermUtils.isList(context.current()));
        IStrategoList current = (IStrategoList) context.current();
        assertEquals(current.size(), 2);
        assertEquals(new HashSet<>(Arrays.asList(current.getAllSubterms())), new HashSet<>(Arrays.asList(a, b)));
    }

    @Test
    public void mapGetExisting() {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_get.call(context, new Strategy[0], new IStrategoTerm[] { one });
        assertTrue(result);
        assertEquals(context.current(), a);
    }

    @Test
    public void mapGetExistingCustomEq() {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        IStrategoTerm oneWithAnno = f.annotateTerm(one, f.makeList(one));
        assertNotEquals(one, oneWithAnno);
        boolean result = SSL_immutable_map_get_eq.call(context, new Strategy[] { InterpreterStrategy.of(current -> {
            IStrategoTerm left = current.getSubterm(0);
            IStrategoTerm right = current.getSubterm(1);
            if(f.annotateTerm(left, TermFactory.EMPTY_LIST).equals(f.annotateTerm(right, TermFactory.EMPTY_LIST))) {
                return current;
            } else {
                return null;
            }
        }) }, new IStrategoTerm[] { oneWithAnno });
        assertTrue(result);
        assertEquals(context.current(), a);
    }
}
