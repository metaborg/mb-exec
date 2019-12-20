package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Set;

import org.junit.Test;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class ImmutableSetPrimitiveCreateSet extends ImmutableSetMapTestSetup {
    private final SSL_immutable_set SSL_immutable_set = new SSL_immutable_set();
    private final SSL_immutable_set_contains SSL_immutable_set_contains = new SSL_immutable_set_contains();
    private final SSL_immutable_set_contains_eq SSL_immutable_set_contains_eq = new SSL_immutable_set_contains_eq();
    private final SSL_immutable_set_elements SSL_immutable_set_elements = new SSL_immutable_set_elements();
    private final SSL_immutable_set_filter SSL_immutable_set_filter = new SSL_immutable_set_filter();
    private final SSL_immutable_set_from_list SSL_immutable_set_from_list = new SSL_immutable_set_from_list();
    private final SSL_immutable_set_insert SSL_immutable_set_insert = new SSL_immutable_set_insert();
    private final SSL_immutable_set_intersect SSL_immutable_set_intersect = new SSL_immutable_set_intersect();
    private final SSL_immutable_set_map SSL_immutable_set_map = new SSL_immutable_set_map();
    private final SSL_immutable_set_remove SSL_immutable_set_remove = new SSL_immutable_set_remove();
    private final SSL_immutable_set_subtract SSL_immutable_set_subtract = new SSL_immutable_set_subtract();
    private final SSL_immutable_set_union SSL_immutable_set_union = new SSL_immutable_set_union();

    @Test
    public void setIsEmpty() throws InterpreterException {
        boolean result = SSL_immutable_set.call(context, new Strategy[0], new IStrategoTerm[0]);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertTrue(current.backingSet.isEmpty());
    }

    @Test
    public void setFromListUnique() throws InterpreterException {
        context.setCurrent(f.makeList(one, two));
        boolean result = SSL_immutable_set_from_list.call(context, new Strategy[0], new IStrategoTerm[0]);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertEquals(current.backingSet, Set.Immutable.of(one, two));
    }

    @Test
    public void setFromListKeepFirst() throws InterpreterException {
        assertEquals(one, oneIsh);
        assertNotSame(one, oneIsh);
        context.setCurrent(f.makeList(one, oneIsh));
        boolean result = SSL_immutable_set_from_list.call(context, new Strategy[0], new IStrategoTerm[0]);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertEquals(current.backingSet.size(), 1);
        assertSame(current.backingSet.iterator().next(), one);
    }

    @Test
    public void setElements() throws InterpreterException {
        context.setCurrent(new StrategoImmutableSet(Set.Immutable.of(one, two)));
        boolean result = SSL_immutable_set_elements.call(context, new Strategy[0], new IStrategoTerm[0]);
        assertTrue(result);
        assertTrue(Tools.isTermList(context.current()));
        IStrategoList current = (IStrategoList) context.current();
        assertEquals(current.size(), 2);
        assertThat(current.head(), anyOf(equalTo(one), equalTo(two)));
        if(current.head().equals(one)) {
            assertEquals(current.tail().head(), two);
        } else {
            assertEquals(current.tail().head(), one);
        }
    }
}
