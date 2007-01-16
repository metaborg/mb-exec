package org.spoofax.interpreter.test.compiler;

import java.io.IOException;

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.test.AbstractCompilerTest;

public class CompilerTest extends AbstractCompilerTest {
    @Override
    protected void setUp() throws Exception {
        super.setUp("tests/data/compiler");
    }

    public void test1() throws IOException, InterpreterException
    {
        DebugUtil.tracing = true;
    	exec("test1.str");
    }
}
