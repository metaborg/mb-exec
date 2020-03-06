package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Map;

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
public class TestImmutableMapPrimitiveFails extends ImmutableSetMapTestSetup {
    private static final SSL_immutable_map_filter SSL_immutable_map_filter = new SSL_immutable_map_filter();
    private static final SSL_immutable_map_get SSL_immutable_map_get = new SSL_immutable_map_get();
    private static final SSL_immutable_map_intersect SSL_immutable_map_intersect = new SSL_immutable_map_intersect();
    private static final SSL_immutable_map_map SSL_immutable_map_map = new SSL_immutable_map_map();

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Iterable<Object[]> data() {
        //@formatter:off
        return Arrays.asList(new Object[][] {
            {"mapFilterKeysFailMerge", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_filter.call(context, new Strategy[] { InterpreterStrategy.of(current -> {
                    return f.makeTuple(one, current.getSubterm(1)); // map key in pair to one
                }), InterpreterStrategy.fail }, EMPTY_TERM_ARRAY)},
            {"mapGetNonExisting", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_get.call(context, new Strategy[0], new IStrategoTerm[] { three })},
            {"mapIntersectMergeFail", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_intersect.call(context, new Strategy[] { InterpreterStrategy.fail },
                    new IStrategoTerm[] { new StrategoImmutableMap(Map.Immutable.of(one, b)) })},
            {"mapMapFail", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_map.call(context, new Strategy[] { InterpreterStrategy.test(current -> {
                    return current.getSubterm(0).equals(one); // filter on key == one
                }), null // never used, no overlapping keys
                }, EMPTY_TERM_ARRAY)},
            {"mapMapKeysFailMerge", (CheckedFunction0<Boolean, InterpreterException>) () ->
                SSL_immutable_map_map.call(context, new Strategy[] { InterpreterStrategy.of(current -> {
                    return f.makeTuple(one, current.getSubterm(1)); // map key in pair to one
                }), InterpreterStrategy.fail }, EMPTY_TERM_ARRAY)},
        });
        //@formatter:on
    }

    private final CheckedFunction0<Boolean, InterpreterException> primitiveCall;

    public TestImmutableMapPrimitiveFails(String name, CheckedFunction0<Boolean, InterpreterException> primitiveCall) {
        this.primitiveCall = primitiveCall;
    }

    @Test
    public void test() throws InterpreterException {
        context.setCurrent(new StrategoImmutableMap(Map.Immutable.of(one, a, two, b)));
        boolean result = primitiveCall.apply();
        assertFalse(result);
    }
}
