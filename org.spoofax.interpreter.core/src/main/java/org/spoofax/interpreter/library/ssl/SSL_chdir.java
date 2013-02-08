package org.spoofax.interpreter.library.ssl;

import java.io.IOException;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

/**
 * @author Lennart Kats <lennart add lclnet.nl>
 * @author Sander Vermolen <sandervermolen at gmail.com>
 */
public class SSL_chdir extends AbstractPrimitive {
    
    public SSL_chdir() {
        super("SSL_chdir", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {

        if(!Tools.isTermString(tvars[0]))
            return false;
        
        SSLLibrary op = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        
        try {
            IOAgent io = op.getIOAgent();
            io.setWorkingDir(Tools.asJavaString(tvars[0]));
        
            env.setCurrent(env.getFactory().makeInt(0));
        } catch (IOException e) {
            env.setCurrent(env.getFactory().makeInt(-1));
        }
        
        return true;
    }

}
