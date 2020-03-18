package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.BinaryRelation;

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
public class TestImmutableRelPrimitiveReturnsRel extends ImmutableCollectionTestSetup {
    private static final SSL_immutable_relation_filter SSL_immutable_relation_filter =
        new SSL_immutable_relation_filter();
    private static final SSL_immutable_relation_intersect SSL_immutable_relation_intersect =
        new SSL_immutable_relation_intersect();
    private static final SSL_immutable_relation_map SSL_immutable_relation_map = new SSL_immutable_relation_map();
    private static final SSL_immutable_relation_insert SSL_immutable_relation_insert =
        new SSL_immutable_relation_insert();
    private static final SSL_immutable_relation_remove SSL_immutable_relation_remove =
        new SSL_immutable_relation_remove();
    private static final SSL_immutable_relation_subtract SSL_immutable_relation_subtract =
        new SSL_immutable_relation_subtract();
    private static final SSL_immutable_relation_union SSL_immutable_relation_union = new SSL_immutable_relation_union();
    private static final SSL_immutable_relation_compose SSL_immutable_relation_compose =
        new SSL_immutable_relation_compose();
    private static final SSL_immutable_relation_contains SSL_immutable_relation_contains =
        new SSL_immutable_relation_contains();
    private static final SSL_immutable_relation_inverse SSL_immutable_relation_inverse =
        new SSL_immutable_relation_inverse();
    private static final SSL_immutable_relation_transitive_closure SSL_immutable_relation_transitive_closure =
        new SSL_immutable_relation_transitive_closure();
    private static final SSL_immutable_relation_reflexive_transitive_closure
        SSL_immutable_relation_reflexive_transitive_closure = new SSL_immutable_relation_reflexive_transitive_closure();

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Iterable<Object[]> data() {
        //@formatter:off
        return Arrays.asList(new Object[][] {
            {"relFilterKeysUnique", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_filter.call(context, new Strategy[] { InterpreterStrategy.test(current -> {
                    return current.getSubterm(0).equals(one); // filter on key == one
                })}, EMPTY_TERM_ARRAY),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a)),
            },
            {"relFilterKeysNonUnique", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_filter.call(context, new Strategy[] { InterpreterStrategy.of(current -> {
                    return f.makeTuple(one, current.getSubterm(1)); // map key in pair to one
                })}, EMPTY_TERM_ARRAY),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, one, b)),
            },
            {"relIntersectEmpty", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_intersect
                    .call(context, new Strategy[0], new IStrategoTerm[] { new StrategoImmutableRelation() }),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of()),
            },
            {"relIntersectNoOverlap", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_intersect.call(context, new Strategy[0], new IStrategoTerm[] {
                    new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, b)) }),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of()),
            },
            {"relIntersectOverlap", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_intersect.call(context, new Strategy[0], new IStrategoTerm[] {
                    new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a)) }),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a)),
            },
            {"relMapKeysNonUnique", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_map.call(context, new Strategy[] { InterpreterStrategy.of(current -> {
                    // map key in pair to one
                    return f.makeTuple(one, current.getSubterm(1));
                })}, EMPTY_TERM_ARRAY),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, one, b)),
            },
            {"relPutNonExisting", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_insert.call(context, new Strategy[0], new IStrategoTerm[] { three, c }),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b, three, c)),
            },
            {"relPutExistingKey", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_insert.call(context, new Strategy[0], new IStrategoTerm[] { one, b }),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, one, b, two, b)),
            },
            {"relPutExistingPair", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_insert.call(context, new Strategy[0], new IStrategoTerm[] { one, a }),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
            },
            {"relRemoveExisting", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_remove.call(context, new Strategy[0], new IStrategoTerm[] { two }),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a)),
            },
            {"relRemoveNonExisting", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_remove.call(context, new Strategy[0], new IStrategoTerm[] { three }),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
            },
            {"relSubtractEmpty", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_subtract
                    .call(context, new Strategy[] { null }, new IStrategoTerm[] { new StrategoImmutableRelation() }),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
            },
            {"relSubtractOverlappingKey", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_subtract
                    .call(context, new Strategy[0], new IStrategoTerm[] { new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, b)) }),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
            },
            {"relSubtractOverlappingPair", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_subtract
                    .call(context, new Strategy[0], new IStrategoTerm[] { new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a)) }),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(two, b)),
            },
            {"relSubtractNonOverlapping", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_subtract.call(context, new Strategy[0],
                    new IStrategoTerm[] { new StrategoImmutableRelation(BinaryRelation.Immutable.of(three, b)) }),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
            },
            {"relUnionEmpty", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_union.call(context, new Strategy[] { null // no merge necessary
                }, new IStrategoTerm[] { new StrategoImmutableRelation() }),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
            },
            {"relUnionOverlappingKey", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_union.call(context, new Strategy[] { InterpreterStrategy.of(current -> {
                    return current.getSubterm(0); // map pair to first
                }) }, new IStrategoTerm[] { new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, b)) }),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, one, b, two, b)),
            },
            {"relUnionOverlappingPair", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_union.call(context, new Strategy[] { InterpreterStrategy.of(current -> {
                    return current.getSubterm(0); // map pair to first
                }) }, new IStrategoTerm[] { new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a)) }),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
            },
            {"relUnionNonOverlapping", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_union.call(context, new Strategy[] { null },
                    new IStrategoTerm[] { new StrategoImmutableRelation(BinaryRelation.Immutable.of(three, b)) }),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b, three, b)),
            },
            {"relCompose", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_compose.call(context, new Strategy[0], new IStrategoTerm[] {
                    new StrategoImmutableRelation(BinaryRelation.Immutable.of(a, three, a, four)) }),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b, three, a)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, three, one, four, three, three, three, four)),
            },
            {"relContainsExisting", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_contains.call(context, new Strategy[0], new IStrategoTerm[] { one, two }),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, two)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, two)),
            },
            {"relInverse", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_inverse.call(context, new Strategy[0],  new IStrategoTerm[0]),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b, three, a)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(a, one, b, two, a, three)),
            },
            {"relTransitiveClosure", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_transitive_closure.call(context, new Strategy[0],  new IStrategoTerm[0]),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, a, b, b, c)),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, one, b, one, c, a, b, a, c, b, c)),
            },
            {"relReflexiveTransitiveClosure", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_reflexive_transitive_closure.call(context, new Strategy[0],  new IStrategoTerm[0]),
                new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, a, b, b, c)),
                new StrategoImmutableRelation((BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm>)
                    BinaryRelation.Immutable.of(one, a, one, b, one, c, a, b, a, c, b, c)
                    .__insert(one, one)
                    .__insert(a, a)
                    .__insert(b, b)
                    .__insert(c, c)
                ),
            },
        });
        //@formatter:on
    }

    private final CheckedFunction0<Boolean, InterpreterException> primitiveCall;
    private final StrategoImmutableRelation input;
    private final StrategoImmutableRelation expected;

    public TestImmutableRelPrimitiveReturnsRel(@SuppressWarnings("unused") String name,
        CheckedFunction0<Boolean, InterpreterException> primitiveCall, StrategoImmutableRelation input,
        StrategoImmutableRelation expected) {
        this.primitiveCall = primitiveCall;
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void test() throws InterpreterException {
        context.setCurrent(input);
        boolean result = primitiveCall.apply();
        assertTrue(result);
        assertThat(context.current(), instanceOf(StrategoImmutableRelation.class));
        assertEquals(expected, context.current());
    }
}
