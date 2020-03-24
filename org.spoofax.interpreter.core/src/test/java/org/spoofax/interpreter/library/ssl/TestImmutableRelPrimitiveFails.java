package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.BinaryRelation;
import io.usethesource.capsule.Map;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.metaborg.util.functions.CheckedFunction0;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

import static org.junit.Assert.assertFalse;
import static org.spoofax.terms.AbstractTermFactory.EMPTY_TERM_ARRAY;

@RunWith(Parameterized.class)
public class TestImmutableRelPrimitiveFails extends ImmutableCollectionTestSetup {
    private static final SSL_immutable_relation_intersect SSL_immutable_relation_intersect = new SSL_immutable_relation_intersect();
    private static final SSL_immutable_relation_map SSL_immutable_relation_map = new SSL_immutable_relation_map();

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Iterable<Object[]> data() {
        //@formatter:off
        return Arrays.asList(new Object[][] {
            {"relIntersectMergeFail", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_intersect.call(context, new Strategy[] { InterpreterStrategy.fail },
                    new IStrategoTerm[] { new StrategoImmutableMap(Map.Immutable.of(one, b)) })},
            {"relMapFail", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_relation_map.call(context, new Strategy[] { InterpreterStrategy.test(current -> {
                    return current.getSubterm(0).equals(one); // filter on key == one
                })}, EMPTY_TERM_ARRAY)},
        });
        //@formatter:on
    }

    private final CheckedFunction0<Boolean, InterpreterException> primitiveCall;

    public TestImmutableRelPrimitiveFails(String name, CheckedFunction0<Boolean, InterpreterException> primitiveCall) {
        this.primitiveCall = primitiveCall;
    }

    @Test
    public void test() throws InterpreterException {
        context.setCurrent(new StrategoImmutableRelation(BinaryRelation.Immutable.of(one, a, two, b)));
        boolean result = primitiveCall.apply();
        assertFalse(result);
    }
}
