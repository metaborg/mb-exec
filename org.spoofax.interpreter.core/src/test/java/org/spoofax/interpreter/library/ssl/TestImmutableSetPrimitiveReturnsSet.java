package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.metaborg.util.functions.CheckedFunction0;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.TermFactory;
import java.util.Arrays;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestImmutableSetPrimitiveReturnsSet extends ImmutableSetMapTestSetup {
    private static final SSL_immutable_set_contains SSL_immutable_set_contains = new SSL_immutable_set_contains();
    private static final SSL_immutable_set_contains_eq SSL_immutable_set_contains_eq =
        new SSL_immutable_set_contains_eq();
    private static final SSL_immutable_set_filter SSL_immutable_set_filter = new SSL_immutable_set_filter();
    private static final SSL_immutable_set_insert SSL_immutable_set_insert = new SSL_immutable_set_insert();
    private static final SSL_immutable_set_intersect SSL_immutable_set_intersect = new SSL_immutable_set_intersect();
    private static final SSL_immutable_set_map SSL_immutable_set_map = new SSL_immutable_set_map();
    private static final SSL_immutable_set_remove SSL_immutable_set_remove = new SSL_immutable_set_remove();
    private static final SSL_immutable_set_subtract SSL_immutable_set_subtract = new SSL_immutable_set_subtract();
    private static final SSL_immutable_set_union SSL_immutable_set_union = new SSL_immutable_set_union();

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Iterable<Object[]> data() {
        //@formatter:off
        return Arrays.asList(new Object[][] {
            {"containsExisting", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_contains.call(context, new Strategy[0], new IStrategoTerm[] { one }),
                new StrategoImmutableSet(one, two),
                new StrategoImmutableSet(one, two),
            },
            {"containsExistingCustomEq", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_contains_eq.call(context, new Strategy[] { InterpreterStrategy.test(current -> {
                    final IStrategoTerm leftNoAnnos = f.annotateTerm(current.getSubterm(0), TermFactory.EMPTY_LIST);
                    final IStrategoTerm rightNoAnnos = f.annotateTerm(current.getSubterm(1), TermFactory.EMPTY_LIST);
                    return leftNoAnnos.equals(rightNoAnnos);
                })
                }, new IStrategoTerm[] { oneWithAnno }),
                new StrategoImmutableSet(one, two),
                new StrategoImmutableSet(one, two),
            },
            {"filterUnique", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_filter.call(context, new Strategy[] { InterpreterStrategy.test(current -> {
                    return current.equals(one); // filter on key == one
                })
                }, new IStrategoTerm[0]),
                new StrategoImmutableSet(one, two),
                new StrategoImmutableSet(one),
            },
            {"filterNonUnique", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_filter.call(context, new Strategy[] { InterpreterStrategy.constant(one) }, new IStrategoTerm[0]),
                new StrategoImmutableSet(one, two),
                new StrategoImmutableSet(one),
            },
            {"insertNonExisting", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_insert.call(context, new Strategy[0], new IStrategoTerm[] { three }),
                new StrategoImmutableSet(one, two),
                new StrategoImmutableSet(one, two, three),
            },
            {"insertExisting", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_insert.call(context, new Strategy[0], new IStrategoTerm[] { one }),
                new StrategoImmutableSet(one, two),
                new StrategoImmutableSet(one, two),
            },
            {"intersectEmpty", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_intersect
                    .call(context, new Strategy[] { null }, new IStrategoTerm[] { new StrategoImmutableSet() }),
                new StrategoImmutableSet(one, two),
                new StrategoImmutableSet(),
            },
            {"intersect", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_intersect.call(context, new Strategy[0],
                    new IStrategoTerm[] { new StrategoImmutableSet(Set.Immutable.of(one, three)) }),
                new StrategoImmutableSet(one, two),
                new StrategoImmutableSet(one),
            },
            {"mapNonUnique", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_map.call(context, new Strategy[] { InterpreterStrategy.constant(one) }, new IStrategoTerm[0]),
                new StrategoImmutableSet(one, two),
                new StrategoImmutableSet(one),
            },
            {"mapUnique", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_map.call(context, new Strategy[] { InterpreterStrategy.id }, new IStrategoTerm[0]),
                new StrategoImmutableSet(one, two),
                new StrategoImmutableSet(one, two),
            },
            {"removeExisting", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_remove.call(context, new Strategy[0], new IStrategoTerm[] { two }),
                new StrategoImmutableSet(one, two),
                new StrategoImmutableSet(one),
            },
            {"removeNonExisting", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_remove.call(context, new Strategy[0], new IStrategoTerm[] { three }),
                new StrategoImmutableSet(one, two),
                new StrategoImmutableSet(one, two),
            },
            {"subtractEmpty", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_subtract
                    .call(context, new Strategy[] { null }, new IStrategoTerm[] { new StrategoImmutableSet() }),
                new StrategoImmutableSet(one, two),
                new StrategoImmutableSet(one, two),
            },
            {"subtractOverlapping", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_subtract.call(context, new Strategy[0],
                    new IStrategoTerm[] { new StrategoImmutableSet(Set.Immutable.of(one, three)) }),
                new StrategoImmutableSet(one, two),
                new StrategoImmutableSet(two),
            },
            {"subtractNonOverlapping", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_subtract.call(context, new Strategy[0],
                    new IStrategoTerm[] { new StrategoImmutableSet(Set.Immutable.of(three, four)) }),
                new StrategoImmutableSet(one, two),
                new StrategoImmutableSet(one, two),
            },
            {"unionEmpty", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_union.call(context, new Strategy[] { null /* no merge necessary */ },
                    new IStrategoTerm[] { new StrategoImmutableSet() }),
                new StrategoImmutableSet(one, two),
                new StrategoImmutableSet(one, two),
            },
            {"unionOverlapping", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_union.call(context, new Strategy[] { InterpreterStrategy.of(current -> {
                    return current.getSubterm(0); // set pair to first
                }) }, new IStrategoTerm[] { new StrategoImmutableSet(Set.Immutable.of(one, three)) }),
                new StrategoImmutableSet(one, two),
                new StrategoImmutableSet(one, two, three),
            },
            {"unionNonOverlapping", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_union.call(context, new Strategy[] { null },
                    new IStrategoTerm[] { new StrategoImmutableSet(Set.Immutable.of(three, four)) }),
                new StrategoImmutableSet(one, two),
                new StrategoImmutableSet(one, two, three, four),
            },
        });
        //@formatter:on
    }

    private final CheckedFunction0<Boolean, InterpreterException> primitiveCall;
    private final StrategoImmutableSet input;
    private final StrategoImmutableSet expected;

    public TestImmutableSetPrimitiveReturnsSet(@SuppressWarnings("unused") String name,
        CheckedFunction0<Boolean, InterpreterException> primitiveCall, StrategoImmutableSet input,
        StrategoImmutableSet expected) {
        this.primitiveCall = primitiveCall;
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void test() throws InterpreterException {
        context.setCurrent(input);
        boolean result = primitiveCall.apply();
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableSet.class));
        assertEquals(context.current(), expected);
    }
}
