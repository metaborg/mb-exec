/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.List;

import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class SSL_explode_string extends Primitive {

    protected SSL_explode_string() {
        super("SSL_explode_string", 0, 1);
    }
    
    public boolean call(IContext env, List<Strategy> svars, IStrategoTerm[] tvars) throws InterpreterException {
        
        IStrategoTerm t = tvars[0];
        
        if(!Tools.isTermString(t))
            return false;
        
        String s = Tools.javaString(t);
        IStrategoTerm[] r = new IStrategoInt[s.length()];
        byte[] bs = s.getBytes();
        
        ITermFactory f = env.getFactory();
        for(int i = 0; i < bs.length; i++)
            r[i] = f.makeInt(bs[i]);
        
        IStrategoTerm sl = env.getFactory().makeList(r);
        env.setCurrent(sl);
        return true;
    }
}
