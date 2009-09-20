/*
 * Created on 11. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import static org.spoofax.interpreter.core.Tools.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_copy extends AbstractPrimitive {

    SSL_copy() {
        super("SSL_copy", 0, 2);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {

        SSLLibrary op = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        IOAgent agent = op.getIOAgent();
        
        InputStream fis = null;
        OutputStream fos = null;
        
        boolean closeIn = true;
        boolean closeOut = true;
        
        if (isTermString(tvars[0]) && isTermString(tvars[1])) {
            // Avoid a file to itself
            File file1 = agent.openFile(Tools.javaString(tvars[0]));
            File file2 = agent.openFile(Tools.javaString(tvars[1]));
            try {
                if (file1.exists() && file1.getCanonicalPath().equals(file2.getCanonicalPath()))
                    return true;
            } catch (IOException e) {
                // Ignore: files may not exist yet
            }
        }

        try {
            if (Tools.isTermString(tvars[0])) {
                fis = agent.openInputStream(Tools.javaString(tvars[0]));
            } else if (Tools.isTermAppl(tvars[0]) && Tools.hasConstructor((IStrategoAppl) tvars[0], "stdin")) {
                fis = agent.getInputStream(IOAgent.CONST_STDIN);
                closeIn = false;
            } else {
                return false;
            }
            
            if (Tools.isTermString(tvars[1])) {
                fos =  agent.openFileOutputStream(Tools.javaString(tvars[1]));
            } else if (Tools.isTermAppl(tvars[1]) && Tools.hasConstructor((IStrategoAppl) tvars[1], "stdout")) {
                fos =  agent.getOutputStream(IOAgent.CONST_STDOUT);
                closeOut = false;
            } else if (Tools.isTermAppl(tvars[1]) && Tools.hasConstructor((IStrategoAppl) tvars[1], "stderr")) {
                fos =  agent.getOutputStream(IOAgent.CONST_STDERR);
                closeOut = false;
            } else {
                return false;
            }
    
            // TODO: Optimize - use the fancy new Java API for copying files
            byte[] bs = new byte[1024];
            int read;
        
            read = fis.read(bs, 0, 1024);
            while(read != -1) {
                fos.write(bs,0, read);
                read = fis.read(bs, 0, 1024);
            }

            if (closeOut) fos.close();
            if (closeIn) fis.close();
        } catch(IOException e) {
            agent.getOutputStream(IOAgent.CONST_STDERR).println("SSL_copy: Could not copy file " + e.getMessage());
            return false;
        }
        
        
        return true;
    }

}
