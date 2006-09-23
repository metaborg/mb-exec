/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.io.File;
import java.util.List;

import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoTermList;

import aterm.ATermAppl;

public class SSL_access extends Primitive {

    private static final int R_OK = 4;

    private static final int W_OK = 2;

    private static final int X_OK = 1;

    private static final int F_OK = 0;

    protected SSL_access() {
        super("SSL_access", 0, 2);
    }

    public boolean call(IContext env, List<Strategy> sargs, List<IStrategoTerm> targs) throws InterpreterException {

        if (!Tools.isTermString(targs.get(0)))
            return false;
        if (!(Tools.isTermAppl(targs.get(1))))
            return false;

        String path = Tools.javaString(targs.get(0));
        int permissions = permissions_from_term(Tools.
          consToList(env, (IStrategoAppl) targs.get(1)));
        File f = new File(path);

        if ((permissions & R_OK) != 0) {
            if (!f.canRead())
                return false;
        } else if ((permissions & W_OK) != 0) {
            if (!f.canWrite())
                return false;
        } else if ((permissions & X_OK) != 0) {
            // FIXME: We cannot know this in Java < 1.6
            return false;
        } else if (permissions == F_OK) {
            if (!f.exists())
                return false;
        }

        env.setCurrent(targs.get(0));
        return true;
    }

    private int permissions_from_term(IStrategoTermList perms) {
        int res = 0;
        for (int i = 0; i < perms.size(); i++) {
            ATermAppl t = (ATermAppl) Tools.termAt(perms, i);
            
            if(Tools.termType(t, "W_OK"))
                res |= W_OK;
            else if(Tools.termType(t, "R_OK"))
                res |= R_OK;
            else if(Tools.termType(t, "X_OK"))
                res |= X_OK;
            else if(Tools.termType(t, "F_OK"))
                res |= F_OK;
            else 
                System.err.println("*** ERROR: not an access mode: " + t);
        }
        return res;
    }
}
