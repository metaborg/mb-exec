/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.List;

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Interpreter;
import org.spoofax.interpreter.stratego.Strategy;

import aterm.ATerm;

public abstract class Primitive {

    protected String name;
    protected int svars;
    protected int tvars;
    
    protected Primitive(String name, int svars, int tvars) {
        this.name = name;
        this.svars = svars;
        this.tvars = tvars;
    }
    
    public String getName() { return name; }
    public int getArity() { return tvars; }
    public int getSArity() { return svars; }
    
    public abstract boolean call(IContext env, List<Strategy> svars, List<ATerm> tvars) throws FatalError;
    
    protected static void debug(String s) {
        Interpreter.debug(s);
    }
}
