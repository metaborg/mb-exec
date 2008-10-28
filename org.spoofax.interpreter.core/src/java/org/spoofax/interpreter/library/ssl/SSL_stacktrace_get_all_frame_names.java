package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

/**
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class SSL_stacktrace_get_all_frame_names extends AbstractPrimitive {

    protected SSL_stacktrace_get_all_frame_names() {
        super("SSL_stacktrace_get_all_frame_names", 0, 0);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        // FIXME: stacktracing API
        
        ITermFactory factory = env.getFactory();
        env.setCurrent(factory.makeList(factory.makeString("stacktraces_not_supported_yet_0_0")));
        return true;
    }

}
