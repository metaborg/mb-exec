package org.spoofax.interpreter.library.ssl;

import static org.spoofax.interpreter.core.Tools.isTermString;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import org.apache.commons.io.IOUtils;
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


    @Override public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) throws InterpreterException {
        final SSLLibrary op = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        final IOAgent agent = op.getIOAgent();

        if(isSameFile(tvars, agent)) {
            return true;
        }

        try {
            final InputStream in;
            final boolean closeIn;
            if(Tools.isTermString(tvars[0])) {
                in = agent.openInputStream(Tools.javaString(tvars[0]));
                closeIn = true;
            } else if(Tools.isTermAppl(tvars[0]) && Tools.hasConstructor((IStrategoAppl) tvars[0], "stdin")) {
                in = agent.internalGetInputStream(IOAgent.CONST_STDIN);
                closeIn = false;
            } else {
                return false;
            }

            final OutputStream out;
            final boolean closeOut;
            if(Tools.isTermString(tvars[1])) {
                out = agent.openFileOutputStream(Tools.javaString(tvars[1]));
                closeOut = true;
            } else if(Tools.isTermAppl(tvars[1]) && Tools.hasConstructor((IStrategoAppl) tvars[1], "stdout")) {
                out = agent.internalGetOutputStream(IOAgent.CONST_STDOUT);
                closeOut = false;
            } else if(Tools.isTermAppl(tvars[1]) && Tools.hasConstructor((IStrategoAppl) tvars[1], "stderr")) {
                out = agent.internalGetOutputStream(IOAgent.CONST_STDERR);
                closeOut = false;
            } else {
                return false;
            }

            try {
                if(in instanceof FileInputStream && out instanceof FileOutputStream) {
                    final FileChannel inChannel = ((FileInputStream) in).getChannel();
                    final FileChannel outChannel = ((FileOutputStream) out).getChannel();
                    inChannel.transferTo(0, inChannel.size(), outChannel);
                } else {
                    IOUtils.copy(in, out);
                }
            } finally {
                if(closeOut) {
                    out.close();
                }
                if(closeIn) {
                    in.close();
                }
            }
        } catch(IOException e) {
            agent.printError(
                "SSL_copy: Could not copy file (" + e.getMessage() + "-" + "attempted to copy to " + tvars[1]);
            return false;
        }

        return true;
    }

    private boolean isSameFile(IStrategoTerm[] tvars, IOAgent agent) {
        if(isTermString(tvars[0]) && isTermString(tvars[1])) {
            File file1 = agent.openFile(Tools.javaString(tvars[0]));
            File file2 = agent.openFile(Tools.javaString(tvars[1]));
            try {
                if(file1.exists() && file1.getCanonicalPath().equals(file2.getCanonicalPath()))
                    return true;
            } catch(IOException e) {
                // Ignore: files may not exist yet
            }
        }
        return false;
    }
}
