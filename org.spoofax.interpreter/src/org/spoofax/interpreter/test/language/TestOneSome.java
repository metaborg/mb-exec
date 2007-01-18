package org.spoofax.interpreter.test.language;

import java.io.IOException;

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.test.AbstractLanguageTest;

public class TestOneSome extends AbstractLanguageTest {
    public void testOne() throws IOException, InterpreterException {
        interpTest("test_one", "[[1, 2, 3], [\"a\"]]", "[[2, 2, 3], [\"a\"]]");
    }
    public void testOne2() throws IOException, InterpreterException {
        interpTest("test_one2", "[[\"a\"], [1, 2, 3]]", "[[2], [1, 2, 3]]");    }
    public void testSome() throws IOException, InterpreterException {
        interpTest("test_some", "[[1, 2, 3], [\"a\"]]", "[[2, 3, 4], [\"a\"]]");
    }
    public void testDownUp() throws IOException, InterpreterException {
    	interpTest("test_downup", "[[1, 2, 3], [\"a\"]]", "[[3, 4, 5], [\"a\"]]");
    }
}
