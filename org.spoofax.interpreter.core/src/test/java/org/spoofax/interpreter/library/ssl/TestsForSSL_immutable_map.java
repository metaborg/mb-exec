package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Map;
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
import org.spoofax.terms.TermFactory;

import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class TestsForSSL_immutable_map extends ImmutableSetMapTestSetup {
    private final SSL_immutable_map SSL_immutable_map = new SSL_immutable_map();
    private final SSL_immutable_map_filter SSL_immutable_map_filter = new SSL_immutable_map_filter();
    private final SSL_immutable_map_from_list SSL_immutable_map_from_list = new SSL_immutable_map_from_list();
    private final SSL_immutable_map_get SSL_immutable_map_get = new SSL_immutable_map_get();
    private final SSL_immutable_map_get_eq SSL_immutable_map_get_eq = new SSL_immutable_map_get_eq();
    private final SSL_immutable_map_intersect SSL_immutable_map_intersect = new SSL_immutable_map_intersect();
    private final SSL_immutable_map_intersect_set SSL_immutable_map_intersect_set =
        new SSL_immutable_map_intersect_set();
    private final SSL_immutable_map_keys SSL_immutable_map_keys = new SSL_immutable_map_keys();
    private final SSL_immutable_map_keys_to_set SSL_immutable_map_keys_to_set = new SSL_immutable_map_keys_to_set();
    private final SSL_immutable_map_map SSL_immutable_map_map = new SSL_immutable_map_map();
    private final SSL_immutable_map_put SSL_immutable_map_put = new SSL_immutable_map_put();
    private final SSL_immutable_map_remove SSL_immutable_map_remove = new SSL_immutable_map_remove();
    private final SSL_immutable_map_subtract SSL_immutable_map_subtract = new SSL_immutable_map_subtract();
    private final SSL_immutable_map_subtract_set SSL_immutable_map_subtract_set = new SSL_immutable_map_subtract_set();
    private final SSL_immutable_map_to_list SSL_immutable_map_to_list = new SSL_immutable_map_to_list();
    private final SSL_immutable_map_union SSL_immutable_map_union = new SSL_immutable_map_union();
    private final SSL_immutable_map_values SSL_immutable_map_values = new SSL_immutable_map_values();

    @Test public void mapIsEmpty() throws InterpreterException {
        boolean result = SSL_immutable_map.call(context, new Strategy[0], new IStrategoTerm[0]);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertTrue(current.backingMap.isEmpty());
    }

    @Test public void mapFromListUnique() throws InterpreterException {
        context.setCurrent(f.makeList(f.makeTuple(one, a), f.makeTuple(two, b)));
        boolean result = SSL_immutable_map_from_list.call(context, new Strategy[0], new IStrategoTerm[0]);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertEquals(current.backingMap.get(one), a);
        assertEquals(current.backingMap.get(two), b);
    }

    @Test public void mapFromListKeepFirst() throws InterpreterException {
        context.setCurrent(f.makeList(f.makeTuple(one, a), f.makeTuple(one, b)));
        boolean result = SSL_immutable_map_from_list.call(context, new Strategy[0], new IStrategoTerm[0]);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertEquals(current.backingMap.get(one), a);
    }

    @Test public void mapFilterKeysUnique() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_filter.call(context, new Strategy[] { new Strategy() {
            @Override public IConstruct eval(IContext env) throws InterpreterException {
                // filter on key == one
                if(env.current().getSubterm(0).equals(one)) {
                    return getHook().pop().onSuccess(env);
                } else {
                    return getHook().pop().onFailure(env);
                }
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
            }
        }, null // never used, no overlapping keys
        }, new IStrategoTerm[0]);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertEquals(current.backingMap.get(one), a);
        assertNull(current.backingMap.get(two));
    }

    @Test public void mapFilterKeysNonUnique() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_filter.call(context, new Strategy[] { new Strategy() {
            @Override public IConstruct eval(IContext env) throws InterpreterException {
                // map key in pair to one
                env.setCurrent(f.makeTuple(one, env.current().getSubterm(1)));
                return getHook().pop().onSuccess(env);
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
            }
        }, new Strategy() {
            @Override public IConstruct eval(IContext env) throws InterpreterException {
                // merge by picking the newer value
                env.setCurrent(env.current().getSubterm(1).getSubterm(1));
                return getHook().pop().onSuccess(env);
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
            }
        } }, new IStrategoTerm[0]);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertEquals(current.backingMap.get(one), b);
        assertNull(current.backingMap.get(two));
    }

    @Test public void mapFilterKeysFailMerge() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_filter.call(context, new Strategy[] { new Strategy() {
            @Override public IConstruct eval(IContext env) throws InterpreterException {
                // map key in pair to one
                env.setCurrent(f.makeTuple(one, env.current().getSubterm(1)));
                return getHook().pop().onSuccess(env);
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
            }
        }, new Strategy() {
            @Override public IConstruct eval(IContext env) throws InterpreterException {
                // fail to merge
                return getHook().pop().onFailure(env);
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
            }
        } }, new IStrategoTerm[0]);
        assertFalse(result);
    }

    @Test public void mapGetExisting() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_get.call(context, new Strategy[0], new IStrategoTerm[] { one });
        assertTrue(result);
        assertEquals(context.current(), a);
    }

    @Test public void mapGetNonExisting() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_get.call(context, new Strategy[0], new IStrategoTerm[] { three });
        assertFalse(result);
    }

    @Test public void mapGetExistingCustomEq() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        IStrategoTerm oneWithAnno = f.annotateTerm(one, f.makeList(one));
        assertNotEquals(one, oneWithAnno);
        boolean result = SSL_immutable_map_get_eq.call(context, new Strategy[] { new Strategy() {
            @Override public IConstruct eval(IContext env) throws InterpreterException {
                IStrategoTerm left = env.current().getSubterm(0);
                IStrategoTerm right = env.current().getSubterm(1);
                if(f.annotateTerm(left, TermFactory.EMPTY_LIST).equals(f.annotateTerm(right, TermFactory.EMPTY_LIST))) {
                    return getHook().pop().onSuccess(env);
                } else {
                    return getHook().pop().onFailure(env);
                }
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
            }
        } }, new IStrategoTerm[] { oneWithAnno });
        assertTrue(result);
        assertEquals(context.current(), a);
    }

    @Test public void mapIntersectEmpty() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_intersect
            .call(context, new Strategy[] { null }, new IStrategoTerm[] { new StrategoImmutableMap() });
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertTrue(current.backingMap.isEmpty());
    }

    @Test public void mapIntersectMerge() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_intersect.call(context, new Strategy[] { new Strategy() {
            @Override public IConstruct eval(IContext env) throws InterpreterException {
                // map pair to first
                env.setCurrent(env.current().getSubterm(0));
                return getHook().pop().onSuccess(env);
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
            }
        } }, new IStrategoTerm[] { new StrategoImmutableMap(Map.Immutable.of(one, b)) });
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertEquals(current.backingMap.get(one), a);
        assertNull(current.backingMap.get(two));
    }

    @Test public void mapIntersectMergeFail() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_intersect.call(context, new Strategy[] { new Strategy() {
            @Override public IConstruct eval(IContext env) throws InterpreterException {
                return getHook().pop().onFailure(env);
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
            }
        } }, new IStrategoTerm[] { new StrategoImmutableMap(Map.Immutable.of(one, b)) });
        assertFalse(result);
    }

    @Test public void mapIntersectSet() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_intersect_set
            .call(context, new Strategy[0], new IStrategoTerm[] { new StrategoImmutableSet(Set.Immutable.of(one, b)) });
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertEquals(current.backingMap.get(one), a);
        assertNull(current.backingMap.get(two));
    }

    @Test public void mapKeys() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_keys.call(context, new Strategy[0], new IStrategoTerm[0]);
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

    @Test public void mapKeysToSet() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_keys_to_set.call(context, new Strategy[0], new IStrategoTerm[0]);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        StrategoImmutableSet current = (StrategoImmutableSet) context.current();
        assertEquals(current.backingSet, Set.Immutable.of(one, two));
    }

    @Test public void mapMapFail() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_map.call(context, new Strategy[] { new Strategy() {
            @Override public IConstruct eval(IContext env) throws InterpreterException {
                // filter on key == one
                if(env.current().getSubterm(0).equals(one)) {
                    return getHook().pop().onSuccess(env);
                } else {
                    return getHook().pop().onFailure(env);
                }
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
            }
        }, null // never used, no overlapping keys
        }, new IStrategoTerm[0]);
        assertFalse(result);
    }

    @Test public void mapMapKeysNonUnique() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_map.call(context, new Strategy[] { new Strategy() {
            @Override public IConstruct eval(IContext env) throws InterpreterException {
                // map key in pair to one
                env.setCurrent(f.makeTuple(one, env.current().getSubterm(1)));
                return getHook().pop().onSuccess(env);
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
            }
        }, new Strategy() {
            @Override public IConstruct eval(IContext env) throws InterpreterException {
                // merge by picking the newer value
                env.setCurrent(env.current().getSubterm(1).getSubterm(1));
                return getHook().pop().onSuccess(env);
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
            }
        } }, new IStrategoTerm[0]);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertEquals(current.backingMap.get(one), b);
        assertNull(current.backingMap.get(two));
    }

    @Test public void mapMapKeysFailMerge() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_map.call(context, new Strategy[] { new Strategy() {
            @Override public IConstruct eval(IContext env) throws InterpreterException {
                // map key in pair to one
                env.setCurrent(f.makeTuple(one, env.current().getSubterm(1)));
                return getHook().pop().onSuccess(env);
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
            }
        }, new Strategy() {
            @Override public IConstruct eval(IContext env) throws InterpreterException {
                // fail to merge
                return getHook().pop().onFailure(env);
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
            }
        } }, new IStrategoTerm[0]);
        assertFalse(result);
    }

    @Test public void mapPutNonExisting() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_put.call(context, new Strategy[0], new IStrategoTerm[] { three, c });
        assertTrue(result);
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertEquals(current.backingMap.get(one), a);
        assertEquals(current.backingMap.get(two), b);
        assertEquals(current.backingMap.get(three), c);
    }

    @Test public void mapPutExisting() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_put.call(context, new Strategy[0], new IStrategoTerm[] { one, b });
        assertTrue(result);
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertEquals(current.backingMap.get(one), b);
        assertEquals(current.backingMap.get(two), b);
    }

    @Test public void mapRemoveExisting() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_remove.call(context, new Strategy[0], new IStrategoTerm[] { two });
        assertTrue(result);
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertEquals(current.backingMap.get(one), a);
        assertNull(current.backingMap.get(two));
    }

    @Test public void mapRemoveNonExisting() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_remove.call(context, new Strategy[0], new IStrategoTerm[] { three });
        assertTrue(result);
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertEquals(current.backingMap.get(one), a);
        assertEquals(current.backingMap.get(two), b);
    }

    @Test public void mapSubtractEmpty() throws InterpreterException {
        StrategoImmutableMap before = new StrategoImmutableMap(Map.Immutable.of(one, a, two, b));
        context.setCurrent(before);
        boolean result = SSL_immutable_map_subtract
            .call(context, new Strategy[] { null }, new IStrategoTerm[] { new StrategoImmutableMap() });
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertEquals(current, before);
    }

    @Test public void mapSubtractOverlappingKeys() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_subtract
            .call(context, new Strategy[0], new IStrategoTerm[] { new StrategoImmutableMap(Map.Immutable.of(one, b)) });
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertNull(current.backingMap.get(one));
        assertEquals(current.backingMap.get(two), b);
    }

    @Test public void mapSubtractNonOverlappingKeys() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_subtract.call(context, new Strategy[0],
            new IStrategoTerm[] { new StrategoImmutableMap(Map.Immutable.of(three, b)) });
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertEquals(current.backingMap.get(one), a);
        assertEquals(current.backingMap.get(two), b);
    }

    @Test public void mapSubtractSet() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_subtract_set
            .call(context, new Strategy[0], new IStrategoTerm[] { new StrategoImmutableSet(Set.Immutable.of(one, b)) });
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertNull(current.backingMap.get(one));
        assertEquals(current.backingMap.get(two), b);
    }

    @Test public void mapToListEmpty() throws InterpreterException {
        context.setCurrent(f.makeList());
        boolean result = SSL_immutable_map_from_list.call(context, new Strategy[0], new IStrategoTerm[0]);
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        result = SSL_immutable_map_to_list.call(context, new Strategy[0], new IStrategoTerm[0]);
        assertTrue(result);
        assertTrue(Tools.isTermList(context.current()));
        IStrategoList current = (IStrategoList) context.current();
        assertEquals(current, f.makeList());
    }

    @Test public void mapToList() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_to_list.call(context, new Strategy[0], new IStrategoTerm[0]);
        assertTrue(result);
        assertTrue(Tools.isTermList(context.current()));
        IStrategoList current = (IStrategoList) context.current();
        assertEquals(current, f.makeList(f.makeTuple(one, a), f.makeTuple(two, b)));
    }

    @Test public void mapUnionEmpty() throws InterpreterException {
        StrategoImmutableMap before = new StrategoImmutableMap(Map.Immutable.of(one, a, two, b));
        context.setCurrent(before);
        boolean result = SSL_immutable_map_union.call(context, new Strategy[] { null // no merge necessary
        }, new IStrategoTerm[] { new StrategoImmutableMap() });
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertEquals(current, before);
    }

    @Test public void mapUnionOverlappingKeys() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_union.call(context, new Strategy[] { new Strategy() {
            @Override public IConstruct eval(IContext env) throws InterpreterException {
                // map pair to first
                env.setCurrent(env.current().getSubterm(0));
                return getHook().pop().onSuccess(env);
            }

            @Override public void prettyPrint(StupidFormatter fmt) {
            }
        } }, new IStrategoTerm[] { new StrategoImmutableMap(Map.Immutable.of(one, b)) });
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertEquals(current.backingMap.get(one), a);
        assertEquals(current.backingMap.get(two), b);
    }

    @Test public void mapUnionNonOverlappingKeys() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_union.call(context, new Strategy[] { null },
            new IStrategoTerm[] { new StrategoImmutableMap(Map.Immutable.of(three, b)) });
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        StrategoImmutableMap current = (StrategoImmutableMap) context.current();
        assertEquals(current.backingMap.get(one), a);
        assertEquals(current.backingMap.get(two), b);
        assertEquals(current.backingMap.get(three), b);
    }

    @Test public void mapValues() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = SSL_immutable_map_values.call(context, new Strategy[0], new IStrategoTerm[0]);
        assertTrue(result);
        assertTrue(Tools.isTermList(context.current()));
        IStrategoList current = (IStrategoList) context.current();
        assertEquals(current.size(), 2);
        assertThat(current.head(), anyOf(equalTo(a), equalTo(b)));
        if(current.head().equals(a)) {
            assertEquals(current.tail().head(), b);
        } else {
            assertEquals(current.tail().head(), a);
        }
    }

}
