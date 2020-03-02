package org.spoofax.interpreter.library.ssl;

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
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.util.TermUtils;

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
            if(TermUtils.isString(tvars[0])) {
                in = agent.openInputStream(TermUtils.toJavaString(tvars[0]));
                closeIn = true;
            } else if(TermUtils.isAppl(tvars[0], "stdin")) {
                in = agent.internalGetInputStream(IOAgent.CONST_STDIN);
                closeIn = false;
            } else {
                return false;
            }

            final OutputStream out;
            final boolean closeOut;
            if(TermUtils.isString(tvars[1])) {
                out = agent.openFileOutputStream(TermUtils.toJavaString(tvars[1]));
                closeOut = true;
            } else if(TermUtils.isAppl(tvars[1], "stdout")) {
                out = agent.internalGetOutputStream(IOAgent.CONST_STDOUT);
                closeOut = false;
            } else if(TermUtils.isAppl(tvars[1], "stderr")) {
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
        if(TermUtils.isString(tvars[0]) && TermUtils.isString(tvars[1])) {
            File file1 = agent.openFile(TermUtils.toJavaString(tvars[0]));
            File file2 = agent.openFile(TermUtils.toJavaString(tvars[1]));
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
