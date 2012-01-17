package org.spoofax.interpreter.library.ssl;

import java.io.File;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;

/**
 * Primitive to determine the size of a file.
 *
 * Fails if and only if:
 * - the input is not a string, or
 * - the file does not exist, or
 * - the file is not a normal file.
 *
 * The file size is returned as a real as this is the smallest type that can
 * represent file sizes accurately.  (for integer values reals are exact)
 *
 * @author Tobi Vollebregt
 */
public class SSL_filesize extends AbstractPrimitive {

    protected SSL_filesize() {
        super("SSL_filesize", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) {

       if (tvars[0].getTermType() != IStrategoTerm.STRING)
           return false;

       IStrategoString filename = (IStrategoString) tvars[0];
       SSLLibrary library = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
       File file = library.getIOAgent().openFile(filename.stringValue());

       if (!file.isFile()) {
           return false;
       }

       env.setCurrent(env.getFactory().makeReal(file.length()));
       return true;
    }

}
