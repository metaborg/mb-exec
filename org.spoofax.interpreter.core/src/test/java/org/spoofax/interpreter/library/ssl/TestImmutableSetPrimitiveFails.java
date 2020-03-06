package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.metaborg.util.functions.CheckedFunction0;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.spoofax.terms.AbstractTermFactory.EMPTY_TERM_ARRAY;

@RunWith(Parameterized.class)
public class TestImmutableSetPrimitiveFails extends ImmutableSetMapTestSetup {
    private static final SSL_immutable_set_contains SSL_immutable_set_contains = new SSL_immutable_set_contains();
    private static final SSL_immutable_set_map SSL_immutable_set_map = new SSL_immutable_set_map();

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Iterable<Object[]> data() {
        //@formatter:off
        return Arrays.asList(new Object[][] {
            {"setContainsNonExisting", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_contains.call(context, new Strategy[0], new IStrategoTerm[] { three }) },
            {"setMapFail", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_map.call(context, new Strategy[] { InterpreterStrategy.test(current -> {
                    return current.equals(one); // filter on key == one
                }) }, EMPTY_TERM_ARRAY)},
        });
        //@formatter:on
    }

    private final CheckedFunction0<Boolean, InterpreterException> primitiveCall;

    public TestImmutableSetPrimitiveFails(String name, CheckedFunction0<Boolean, InterpreterException> primitiveCall) {
        this.primitiveCall = primitiveCall;
    }

    @Test
    public void test() throws InterpreterException {
        context.setCurrent(new StrategoImmutableSet(Set.Immutable.of(one, two)));
        boolean result = primitiveCall.apply();
        assertFalse(result);
    }
}
