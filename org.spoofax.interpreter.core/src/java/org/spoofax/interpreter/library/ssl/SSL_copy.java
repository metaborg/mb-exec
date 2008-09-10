/*
 * Created on 11. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_copy extends AbstractPrimitive {

    SSL_copy() {
        super("SSL_copy", 0, 2);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {

        if(!Tools.isTermString(tvars[0]))
            return false;
        
        if(!Tools.isTermString(tvars[1]))
            return false;
        
        SSLLibrary op = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);

        byte[] bs = new byte[1024];
        int read;

        try {
            InputStream fis = op.getIOAgent().openInputStream(Tools.javaString(tvars[0]));
            OutputStream fos =  op.getIOAgent().openFileOutputStream(Tools.javaString(tvars[1]));
        
            read = fis.read(bs, 0, 1024);
            while(read != -1) {
                fos.write(bs,0, read);
                read = fis.read(bs, 0, 1024);
            }
            
            fis.close();
            fos.close();
        } catch(IOException e) {
            throw new InterpreterException(e);
        }
        
        
        return true;
    }

}
