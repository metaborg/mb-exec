package org.spoofax.interpreter.library.ssl;

import java.io.File;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;

/**
 * Non-POSIX compliant filemode implementation.
 * 
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class SSL_filemode extends AbstractPrimitive {

    protected SSL_filemode() {
        super("SSL_filemode", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
       if (tvars[0].getTermType() != IStrategoTerm.STRING)
           return false;

       SSLLibrary library = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
       IStrategoString filename = (IStrategoString) tvars[0];
       File file = library.getIOAgent().openFile(filename.stringValue());

       if (!file.exists())
           return false;
       
       // TODO: Return filemode as an int?
       env.setCurrent(env.getFactory().makeTuple(filename, env.getFactory().makeInt(0)));
       
       return true;
    }

}
