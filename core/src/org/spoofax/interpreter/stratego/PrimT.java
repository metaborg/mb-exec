/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import java.util.ArrayList;
import java.util.List;

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.SSL;
import org.spoofax.interpreter.library.Primitive;

import aterm.ATerm;
import aterm.ATermList;

public class PrimT extends Strategy {

    protected String name;
    protected List<Strategy> svars;
    protected List<ATerm> tvars;
    
    public PrimT(String name, List<Strategy> svars, List<ATerm> tvars) {
        this.name = name;
        this.svars = svars;
        this.tvars = tvars;
    }

    public boolean eval(IContext env) throws FatalError {
        debug("PrimT.eval() - " + env.current());
        Primitive prim = SSL.lookup(name);
        
        if(prim == null)
            throw new FatalError("No such function : '" + name + "'");
        
        debug("" + svars);
        List<ATerm> vals = new ArrayList<ATerm>(svars.size());
        for(ATerm t : tvars) {
            vals.add(env.lookupVar(Tools.stringAt(t, 0)));
        }
        debug("" + vals);
        
        return prim.call(env, svars, vals);
    }

}
