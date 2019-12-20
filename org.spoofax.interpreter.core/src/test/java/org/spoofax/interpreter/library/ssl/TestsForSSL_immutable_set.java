package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Set;

import org.junit.Test;
import org.spoofax.interpreter.core.IConstruct;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.stratego.StupidFormatter;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TestsForSSL_immutable_set extends TestsForSSL_immutable {
    private final SSL_immutable_set SSL_immutable_set = new SSL_immutable_set();
    private final SSL_immutable_set_contains SSL_immutable_set_contains = new SSL_immutable_set_contains();
    private final SSL_immutable_set_elements SSL_immutable_set_elements = new SSL_immutable_set_elements();
    private final SSL_immutable_set_filter SSL_immutable_set_filter = new SSL_immutable_set_filter();
    private final SSL_immutable_set_from_list SSL_immutable_set_from_list = new SSL_immutable_set_from_list();
    private final SSL_immutable_set_insert SSL_immutable_set_insert = new SSL_immutable_set_insert();
    private final SSL_immutable_set_intersect SSL_immutable_set_intersect = new SSL_immutable_set_intersect();
    private final SSL_immutable_set_map SSL_immutable_set_map = new SSL_immutable_set_map();
    private final SSL_immutable_set_remove SSL_immutable_set_remove = new SSL_immutable_set_remove();
    private final SSL_immutable_set_subtract SSL_immutable_set_subtract = new SSL_immutable_set_subtract();
    private final SSL_immutable_set_union SSL_immutable_set_union = new SSL_immutable_set_union();

    @Test public void setIsEmpty() throws InterpreterException {
        boolean result = SSL_immutable_set.call(context, new Strategy[0], new IStrategoTerm[0]);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertTrue(current.backingSet.isEmpty());
    }

    @Test public void setFromListUnique() throws InterpreterException {
        context.setCurrent(f.makeList(one, two));
        boolean result = SSL_immutable_set_from_list.call(context, new Strategy[0], new IStrategoTerm[0]);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertEquals(current.backingSet, Set.Immutable.of(one, two));
    }

    @Test public void setFromListKeepFirst() throws InterpreterException {
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

    @Test public void setContainsExisting() throws InterpreterException {
        IStrategoTerm before = new StrategoImmutableSet(Set.Immutable.of(one, two));
        context.setCurrent(before);
        boolean result = SSL_immutable_set_contains.call(context, new Strategy[0], new IStrategoTerm[] { one });
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        assertEquals(context.current(), before);
    }

    @Test public void setContainsNonExisting() throws InterpreterException {
        context.setCurrent(new StrategoImmutableSet(Set.Immutable.of(one, two)));
        boolean result = SSL_immutable_set_contains.call(context, new Strategy[0], new IStrategoTerm[] { three });
        assertFalse(result);
    }

    @Test public void setElements() throws InterpreterException {
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

    @Test public void setFilterUnique() throws InterpreterException {
        context.setCurrent(new StrategoImmutableSet(Set.Immutable.of(one, two)));
        boolean result = SSL_immutable_set_filter.call(context, new Strategy[] { new Strategy() {
            @Override public IConstruct eval(IContext env) throws InterpreterException {
                // filter on key == one
                if(env.current().equals(one)) {
                    return getHook().pop().onSuccess(env);
                } else {
                    return getHook().pop().onFailure(env);
                }
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
            }
        }, }, new IStrategoTerm[0]);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertTrue(current.backingSet.contains(one));
        assertFalse(current.backingSet.contains(two));
    }

    @Test public void setFilterNonUnique() throws InterpreterException {
        context.setCurrent(new StrategoImmutableSet(Set.Immutable.of(one, two)));
        boolean result = SSL_immutable_set_filter.call(context, new Strategy[] { new Strategy() {
            @Override public IConstruct eval(IContext env) throws InterpreterException {
                // key to one
                env.setCurrent(one);
                return getHook().pop().onSuccess(env);
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
            }
        }, }, new IStrategoTerm[0]);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertTrue(current.backingSet.contains(one));
        assertFalse(current.backingSet.contains(two));
    }

    @Test public void setInsertNonExisting() throws InterpreterException {
        context.setCurrent(new StrategoImmutableSet(Set.Immutable.of(one, two)));
        boolean result = SSL_immutable_set_insert.call(context, new Strategy[0], new IStrategoTerm[] { three });
        assertTrue(result);
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertTrue(current.backingSet.contains(one));
        assertTrue(current.backingSet.contains(two));
        assertTrue(current.backingSet.contains(three));
    }

    @Test public void setInsertExisting() throws InterpreterException {
        context.setCurrent(new StrategoImmutableSet(Set.Immutable.of(one, two)));
        boolean result = SSL_immutable_set_insert.call(context, new Strategy[0], new IStrategoTerm[] { one });
        assertTrue(result);
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertTrue(current.backingSet.contains(one));
        assertTrue(current.backingSet.contains(two));
    }

    @Test public void setIntersectEmpty() throws InterpreterException {
        context.setCurrent(new StrategoImmutableSet(Set.Immutable.of(one, two)));
        boolean result = SSL_immutable_set_intersect
            .call(context, new Strategy[] { null }, new IStrategoTerm[] { new StrategoImmutableSet() });
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertTrue(current.backingSet.isEmpty());
    }

    @Test public void setIntersect() throws InterpreterException {
        context.setCurrent(new StrategoImmutableSet(Set.Immutable.of(one, two)));
        boolean result = SSL_immutable_set_intersect.call(context, new Strategy[0],
            new IStrategoTerm[] { new StrategoImmutableSet(Set.Immutable.of(one, three)) });
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertTrue(current.backingSet.contains(one));
        assertFalse(current.backingSet.contains(two));
    }

    @Test public void setMapFail() throws InterpreterException {
        context.setCurrent(new StrategoImmutableSet(Set.Immutable.of(one, two)));
        boolean result = SSL_immutable_set_map.call(context, new Strategy[] { new Strategy() {
            @Override public IConstruct eval(IContext env) throws InterpreterException {
                // filter on key == one
                if(env.current().equals(one)) {
                    return getHook().pop().onSuccess(env);
                } else {
                    return getHook().pop().onFailure(env);
                }
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
            }
        }, }, new IStrategoTerm[0]);
        assertFalse(result);
    }

    @Test public void setMapNonUnique() throws InterpreterException {
        context.setCurrent(new StrategoImmutableSet(Set.Immutable.of(one, two)));
        boolean result = SSL_immutable_set_map.call(context, new Strategy[] { new Strategy() {
            @Override public IConstruct eval(IContext env) throws InterpreterException {
                // key to one
                env.setCurrent(one);
                return getHook().pop().onSuccess(env);
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
            }
        }, }, new IStrategoTerm[0]);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertTrue(current.backingSet.contains(one));
        assertFalse(current.backingSet.contains(two));
    }

    @Test public void setMapUnique() throws InterpreterException {
        context.setCurrent(new StrategoImmutableSet(Set.Immutable.of(one, two)));
        boolean result = SSL_immutable_set_map.call(context, new Strategy[] { new Strategy() {
            @Override public IConstruct eval(IContext env) throws InterpreterException {
                // identity function
                return getHook().pop().onSuccess(env);
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
            }
        }, }, new IStrategoTerm[0]);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertTrue(current.backingSet.contains(one));
        assertTrue(current.backingSet.contains(two));
    }

    @Test public void setRemoveExisting() throws InterpreterException {
        context.setCurrent(new StrategoImmutableSet(Set.Immutable.of(one, two)));
        boolean result = SSL_immutable_set_remove.call(context, new Strategy[0], new IStrategoTerm[] { two });
        assertTrue(result);
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertTrue(current.backingSet.contains(one));
        assertFalse(current.backingSet.contains(two));
    }

    @Test public void setRemoveNonExisting() throws InterpreterException {
        context.setCurrent(new StrategoImmutableSet(Set.Immutable.of(one, two)));
        boolean result = SSL_immutable_set_remove.call(context, new Strategy[0], new IStrategoTerm[] { three });
        assertTrue(result);
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertTrue(current.backingSet.contains(one));
        assertTrue(current.backingSet.contains(two));
    }

    @Test public void setSubtractEmpty() throws InterpreterException {
        StrategoImmutableSet before = new StrategoImmutableSet(Set.Immutable.of(one, two));
        context.setCurrent(before);
        boolean result = SSL_immutable_set_subtract
            .call(context, new Strategy[] { null }, new IStrategoTerm[] { new StrategoImmutableSet() });
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertEquals(current, before);
    }

    @Test public void setSubtractOverlapping() throws InterpreterException {
        context.setCurrent(new StrategoImmutableSet(Set.Immutable.of(one, two)));
        boolean result = SSL_immutable_set_subtract.call(context, new Strategy[0],
            new IStrategoTerm[] { new StrategoImmutableSet(Set.Immutable.of(one, three)) });
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertFalse(current.backingSet.contains(one));
        assertTrue(current.backingSet.contains(two));
    }

    @Test public void setSubtractNonOverlapping() throws InterpreterException {
        context.setCurrent(new StrategoImmutableSet(Set.Immutable.of(one, two)));
        boolean result = SSL_immutable_set_subtract.call(context, new Strategy[0],
            new IStrategoTerm[] { new StrategoImmutableSet(Set.Immutable.of(three, four)) });
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertTrue(current.backingSet.contains(one));
        assertTrue(current.backingSet.contains(two));
    }

    @Test public void setUnionEmpty() throws InterpreterException {
        StrategoImmutableSet before = new StrategoImmutableSet(Set.Immutable.of(one, two));
        context.setCurrent(before);
        boolean result = SSL_immutable_set_union.call(context, new Strategy[] { null // no merge necessary
        }, new IStrategoTerm[] { new StrategoImmutableSet() });
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertEquals(current, before);
    }

    @Test public void setUnionOverlapping() throws InterpreterException {
        context.setCurrent(new StrategoImmutableSet(Set.Immutable.of(one, two)));
        boolean result = SSL_immutable_set_union.call(context, new Strategy[] { new Strategy() {
            @Override public IConstruct eval(IContext env) throws InterpreterException {
                // set pair to first
                env.setCurrent(env.current().getSubterm(0));
                return getHook().pop().onSuccess(env);
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
            }
        } }, new IStrategoTerm[] { new StrategoImmutableSet(Set.Immutable.of(one, b)) });
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertTrue(current.backingSet.contains(one));
        assertTrue(current.backingSet.contains(two));
    }

    @Test public void setUnionNonOverlapping() throws InterpreterException {
        context.setCurrent(new StrategoImmutableSet(Set.Immutable.of(one, two)));
        boolean result = SSL_immutable_set_union.call(context, new Strategy[] { null },
            new IStrategoTerm[] { new StrategoImmutableSet(Set.Immutable.of(three, b)) });
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertTrue(current.backingSet.contains(one));
        assertTrue(current.backingSet.contains(two));
        assertTrue(current.backingSet.contains(three));
    }
}
