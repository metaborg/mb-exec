/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.util.TermUtils;

public class SSL_access extends AbstractPrimitive {
    private static final int R_OK = 4;
    private static final int W_OK = 2;
    private static final int X_OK = 1;
    private static final int F_OK = 0;


    protected SSL_access() {
        super("SSL_access", 0, 2);
    }


    @Override public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        if(!TermUtils.isString(targs[0])) {
            return false;
        }
        if(!(TermUtils.isList(targs[1]))) {
            return false;
        }

        final SSLLibrary op = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        final IOAgent agent = op.getIOAgent();

        final String path = TermUtils.toJavaString(targs[0]);
        final int permissions = permissions_from_term((IStrategoList) targs[1]);

        if((permissions & R_OK) != 0) {
            if(!agent.readable(path)) {
                return false;
            }
        } else if((permissions & W_OK) != 0) {
            if(!agent.writable(path)) {
                return false;
            }
        } else if((permissions & X_OK) != 0) {
            // FIXME: We cannot know this in Java < 1.6
            return false;
        } else if(permissions == F_OK) {
            if(!agent.exists(path)) {
                return false;
            }
        }

        env.setCurrent(targs[0]);
        return true;
    }

    private int permissions_from_term(IStrategoList perms) {
        int res = 0;
        for(int i = 0; i < perms.size(); i++) {
            IStrategoAppl t = TermUtils.toApplAt(perms, i);

            // FIXME -- put in StrategoSignature (LibrarySignature)

            if(TermUtils.isAppl(t, "W_OK"))
                res |= W_OK;
            else if(TermUtils.isAppl(t, "R_OK"))
                res |= R_OK;
            else if(TermUtils.isAppl(t, "X_OK"))
                res |= X_OK;
            else if(TermUtils.isAppl(t, "F_OK"))
                res |= F_OK;
            else
                System.err.println("*** ERROR: not an access mode: " + t);
        }
        return res;
    }
}
