/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.util.List;

import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_is_int extends AbstractPrimitive {

    protected SSL_is_int() {
        super("SSL_is_int", 0, 1);
    }
    
    public boolean call(IContext env, List<IConstruct> svars, IStrategoTerm[] tvars) throws InterpreterException {
        return Tools.isTermInt(tvars[0]);
    }
}
