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

@RunWith(Parameterized.class)
public class ImmutableSetPrimitiveFails extends ImmutableSetMapTestSetup {
    private static final SSL_immutable_set_contains SSL_immutable_set_contains = new SSL_immutable_set_contains();
    private static final SSL_immutable_set_map SSL_immutable_set_map = new SSL_immutable_set_map();

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Iterable<Object[]> data() {
        //@formatter:off
        return Arrays.asList(new Object[][] {
            {"setContainsNonExisting", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_contains.call(context, new Strategy[0], new IStrategoTerm[] { three }) },
            {"setMapFail", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_set_map.call(context, new Strategy[] { TestStrategy.of((env, hooks) -> {
                    // filter on key == one
                    if(env.current().equals(one)) {
                        return hooks.pop().onSuccess(env);
                    } else {
                        return hooks.pop().onFailure(env);
                    }
                }) }, new IStrategoTerm[0])},
        });
        //@formatter:on
    }

    private final CheckedFunction0<Boolean, InterpreterException> primitiveCall;

    public ImmutableSetPrimitiveFails(String name, CheckedFunction0<Boolean, InterpreterException> primitiveCall) {
        this.primitiveCall = primitiveCall;
    }

    @Test
    public void test() throws InterpreterException {
        context.setCurrent(new StrategoImmutableSet(Set.Immutable.of(one, two)));
        boolean result = primitiveCall.apply();
        assertFalse(result);
    }
}
