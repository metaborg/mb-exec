/*
 * Created on 11. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import static org.spoofax.interpreter.core.Tools.isTermString;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

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
        
        if (isSameFile(tvars, agent))
            return true;
        
        InputStream in = null;
        OutputStream out = null;
        
        boolean closeIn = true;
        boolean closeOut = true;

        try {
            if (Tools.isTermString(tvars[0])) {
                in = agent.openInputStream(Tools.javaString(tvars[0]));
            } else if (Tools.isTermAppl(tvars[0]) && Tools.hasConstructor((IStrategoAppl) tvars[0], "stdin")) {
                in = agent.internalGetInputStream(IOAgent.CONST_STDIN);
                closeIn = false;
            } else {
                return false;
            }
            
            if (Tools.isTermString(tvars[1])) {
                out = agent.openFileOutputStream(Tools.javaString(tvars[1]));
            } else if (Tools.isTermAppl(tvars[1]) && Tools.hasConstructor((IStrategoAppl) tvars[1], "stdout")) {
                out = agent.internalGetOutputStream(IOAgent.CONST_STDOUT);
                closeOut = false;
            } else if (Tools.isTermAppl(tvars[1]) && Tools.hasConstructor((IStrategoAppl) tvars[1], "stderr")) {
                out = agent.internalGetOutputStream(IOAgent.CONST_STDERR);
                closeOut = false;
            } else {
                return false;
            }
            
            if (in instanceof FileInputStream && out instanceof FileOutputStream) {
                FileChannel inChannel = ((FileInputStream) in).getChannel();
                inChannel.transferTo(0, inChannel.size(), ((FileOutputStream) out).getChannel());
            } else {
                // TODO: Optimize - use the fancy new Java API for copying files
                byte[] bs = new byte[1024];
                int read;
            
                read = in.read(bs, 0, 1024);
                while(read != -1) {
                    out.write(bs,0, read);
                    read = in.read(bs, 0, 1024);
                }
    
                if (closeOut) out.close();
                if (closeIn) in.close();
            }
        } catch (IOException e) {
            agent.printError("SSL_copy: Could not copy file (" + e.getMessage() + "-" + "attempted to copy to " + tvars[1]);
            return false;
        }
        
        
        return true;
    }

    private boolean isSameFile(IStrategoTerm[] tvars, IOAgent agent) {
        if (isTermString(tvars[0]) && isTermString(tvars[1])) {
            File file1 = agent.openFile(Tools.javaString(tvars[0]));
            File file2 = agent.openFile(Tools.javaString(tvars[1]));
            try {
                if (file1.exists() && file1.getCanonicalPath().equals(file2.getCanonicalPath()))
                    return true;
            } catch (IOException e) {
                // Ignore: files may not exist yet
            }
        }
        return false;
    }

}
