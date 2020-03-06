package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Set;

import org.junit.Test;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.terms.util.TermUtils;

import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.spoofax.terms.AbstractTermFactory.EMPTY_TERM_ARRAY;

public class TestImmutableSetPrimitiveCreateSet extends ImmutableSetMapTestSetup {
    private final SSL_immutable_set SSL_immutable_set = new SSL_immutable_set();
    private final SSL_immutable_set_elements SSL_immutable_set_elements = new SSL_immutable_set_elements();
    private final SSL_immutable_set_from_list SSL_immutable_set_from_list = new SSL_immutable_set_from_list();

    @Test
    public void setIsEmpty() throws InterpreterException {
        boolean result = SSL_immutable_set.call(context, new Strategy[0], EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertTrue(current.backingSet.isEmpty());
    }

    @Test
    public void setFromListUnique() throws InterpreterException {
        context.setCurrent(f.makeList(one, two));
        boolean result = SSL_immutable_set_from_list.call(context, new Strategy[0],
            EMPTY_TERM_ARRAY);
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
        boolean result = SSL_immutable_set_from_list.call(context, new Strategy[0],
            EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertEquals(current.backingSet.size(), 1);
        assertSame(current.backingSet.iterator().next(), one);
    }

    @Test
    public void setElements() throws InterpreterException {
        context.setCurrent(new StrategoImmutableSet(Set.Immutable.of(one, two)));
        boolean result = SSL_immutable_set_elements.call(context, new Strategy[0], EMPTY_TERM_ARRAY);
        assertTrue(result);
        assertTrue(TermUtils.isList(context.current()));
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
