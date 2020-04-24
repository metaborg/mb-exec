package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.util.TermUtils;

/**
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class SSL_S_ISDIR extends AbstractPrimitive {
    
    public SSL_S_ISDIR() {
        super("SSL_S_ISDIR", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {

        if(!TermUtils.isString(tvars[0]))
            return false;
        
        SSLLibrary op = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        
        return op.getIOAgent().isDirectory(TermUtils.toJavaString(tvars[0]));
    }

}
