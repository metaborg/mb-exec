package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Map;
import io.usethesource.capsule.Set;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.metaborg.util.functions.CheckedFunction0;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.spoofax.terms.AbstractTermFactory.EMPTY_TERM_ARRAY;

@RunWith(Parameterized.class)
public class TestImmutableMapPrimitiveReturnsMap extends ImmutableCollectionTestSetup {
    private static final SSL_immutable_map_filter SSL_immutable_map_filter = new SSL_immutable_map_filter();
    private static final SSL_immutable_map_intersect SSL_immutable_map_intersect = new SSL_immutable_map_intersect();
    private static final SSL_immutable_map_intersect_set SSL_immutable_map_intersect_set =
        new SSL_immutable_map_intersect_set();
    private static final SSL_immutable_map_map SSL_immutable_map_map = new SSL_immutable_map_map();
    private static final SSL_immutable_map_put SSL_immutable_map_put = new SSL_immutable_map_put();
    private static final SSL_immutable_map_remove SSL_immutable_map_remove = new SSL_immutable_map_remove();
    private static final SSL_immutable_map_subtract SSL_immutable_map_subtract = new SSL_immutable_map_subtract();
    private static final SSL_immutable_map_subtract_set SSL_immutable_map_subtract_set =
        new SSL_immutable_map_subtract_set();
    private static final SSL_immutable_map_union SSL_immutable_map_union = new SSL_immutable_map_union();

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Iterable<Object[]> data() {
        //@formatter:off
        return Arrays.asList(new Object[][] {
            {"mapFilterKeysUnique", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_filter.call(context, new Strategy[] { InterpreterStrategy.test(current -> {
                    return current.getSubterm(0).equals(one); // filter on key == one
                }), null // never used, no overlapping keys
                }, EMPTY_TERM_ARRAY),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
                new StrategoImmutableMap(Map.Immutable.of(one, a)),
            },
            {"mapFilterKeysNonUnique", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_filter.call(context, new Strategy[] { InterpreterStrategy.of(current -> {
                    return f.makeTuple(one, current.getSubterm(1)); // map key in pair to one
                }), InterpreterStrategy.of(current -> {
                    return current.getSubterm(1).getSubterm(1); // merge by picking the newer value
                }) }, EMPTY_TERM_ARRAY),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
                new StrategoImmutableMap(Map.Immutable.of(one, b)),
            },
            {"mapIntersectEmpty", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_intersect
                    .call(context, new Strategy[] { null }, new IStrategoTerm[] { new StrategoImmutableMap() }),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
                new StrategoImmutableMap(Map.Immutable.of()),
            },
            {"mapIntersectMerge", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_intersect.call(context, new Strategy[] { InterpreterStrategy.of(current -> {
                    return current.getSubterm(0); // map pair to first
                }) }, new IStrategoTerm[] { new StrategoImmutableMap(Map.Immutable.of(one, b)) }),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
                new StrategoImmutableMap(Map.Immutable.of(one, a)),
            },
            {"mapIntersectSet", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_intersect_set
                    .call(context, new Strategy[0], new IStrategoTerm[] { new StrategoImmutableSet(Set.Immutable.of(one, b)) }),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
                new StrategoImmutableMap(Map.Immutable.of(one, a)),
            },
            {"mapMapKeysNonUnique", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_map.call(context, new Strategy[] { InterpreterStrategy.of(current -> {
                    // map key in pair to one
                    return f.makeTuple(one, current.getSubterm(1));
                }), InterpreterStrategy.of(current -> {
                    // merge by picking the newer value
                    return current.getSubterm(1).getSubterm(1);
                }) }, EMPTY_TERM_ARRAY),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
                new StrategoImmutableMap(Map.Immutable.of(one, b)),
            },
            {"mapPutNonExisting", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_put.call(context, new Strategy[0], new IStrategoTerm[] { three, c }),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b).__put(three, c)),
            },
            {"mapPutExisting", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_put.call(context, new Strategy[0], new IStrategoTerm[] { one, b }),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
                new StrategoImmutableMap(Map.Immutable.of(one, b, two, b)),
            },
            {"mapRemoveExisting", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_remove.call(context, new Strategy[0], new IStrategoTerm[] { two }),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
                new StrategoImmutableMap(Map.Immutable.of(one, a)),
            },
            {"mapRemoveNonExisting", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_remove.call(context, new Strategy[0], new IStrategoTerm[] { three }),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
            },
            {"mapSubtractEmpty", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_subtract
                    .call(context, new Strategy[] { null }, new IStrategoTerm[] { new StrategoImmutableMap() }),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
            },
            {"mapSubtractOverlappingKeys", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_subtract
                    .call(context, new Strategy[0], new IStrategoTerm[] { new StrategoImmutableMap(Map.Immutable.of(one, b)) }),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
                new StrategoImmutableMap(Map.Immutable.of(two, b)),
            },
            {"mapSubtractNonOverlappingKeys", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_subtract.call(context, new Strategy[0],
                    new IStrategoTerm[] { new StrategoImmutableMap(Map.Immutable.of(three, b)) }),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
            },
            {"mapSubtractSet", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_subtract_set
                    .call(context, new Strategy[0], new IStrategoTerm[] { new StrategoImmutableSet(Set.Immutable.of(one, b)) }),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
                new StrategoImmutableMap(Map.Immutable.of(two, b)),
            },
            {"mapUnionEmpty", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_union.call(context, new Strategy[] { null // no merge necessary
                }, new IStrategoTerm[] { new StrategoImmutableMap() }),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
            },
            {"mapUnionOverlappingKeys", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_union.call(context, new Strategy[] { InterpreterStrategy.of(current -> {
                    return current.getSubterm(0); // map pair to first
                }) }, new IStrategoTerm[] { new StrategoImmutableMap(Map.Immutable.of(one, b)) }),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
            },
            {"mapUnionNonOverlappingKeys", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_union.call(context, new Strategy[] { null },
                    new IStrategoTerm[] { new StrategoImmutableMap(Map.Immutable.of(three, b)) }),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)),
                new StrategoImmutableMap(Map.Immutable.of(one, a, two, b).__put(three, b)),
            },
        });
        //@formatter:on
    }

    private final CheckedFunction0<Boolean, InterpreterException> primitiveCall;
    private final StrategoImmutableMap input;
    private final StrategoImmutableMap expected;

    public TestImmutableMapPrimitiveReturnsMap(@SuppressWarnings("unused") String name,
        CheckedFunction0<Boolean, InterpreterException> primitiveCall, StrategoImmutableMap input,
        StrategoImmutableMap expected) {
        this.primitiveCall = primitiveCall;
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void test() throws InterpreterException {
        context.setCurrent(input);
        boolean result = primitiveCall.apply();
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableMap.class));
        assertEquals(context.current(), expected);
    }
}
