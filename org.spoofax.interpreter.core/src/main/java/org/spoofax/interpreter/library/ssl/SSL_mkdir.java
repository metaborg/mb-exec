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
public class SSL_mkdir extends AbstractPrimitive {
    
    public SSL_mkdir() {
        super("SSL_mkdir", 0, 2);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {

        if (!TermUtils.isString(tvars[0])) return false;
        // if (!TermUtils.isList(tvars[1])) return false;
        
        SSLLibrary op = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        int result = op.getIOAgent().mkdir(TermUtils.toJavaString(tvars[0])) ? 0 : -1;
        
        /* access parameter is ignored in C version
        AbstractPrimitive access = op.get("SSL_access");
        
        if (result == 0) { // Set access rights
            result = access.call(env, svars, tvars) ? 0 : -1;
        }
        */
        
        env.setCurrent(env.getFactory().makeInt(result));
        
        return true;
    }

}
