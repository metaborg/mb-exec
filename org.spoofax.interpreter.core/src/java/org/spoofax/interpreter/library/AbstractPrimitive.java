/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public abstract class AbstractPrimitive {

    protected String name;
    protected int svars;
    protected int tvars;
    
    public AbstractPrimitive(String name, int svars, int tvars) {
        this.name = name;
        this.svars = svars;
        this.tvars = tvars;
    }
    
    public String getName() { return name; }
    public int getTArity() { return tvars; }
    public int getSArity() { return svars; }
    
    public abstract boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) throws InterpreterException;
    
    protected static void debug(String s) {
        DebugUtil.debug("[ " + s + " ]");
    }
}
