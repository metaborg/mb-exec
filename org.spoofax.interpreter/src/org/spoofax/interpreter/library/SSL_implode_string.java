/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.List;

import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.stratego.Strategy;

import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermInt;
import aterm.ATermList;

public class SSL_implode_string extends Primitive {

    protected SSL_implode_string() {
        super("SSL_implode_string", 0, 1);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs) throws InterpreterException {
        debug("SSL_implode_string");
        
        ATerm t = targs.get(0);
        if(t.getType() != ATerm.APPL)
            return false;
        
        ATermAppl a = (ATermAppl) t;
        
        if(!(Tools.isCons(a) || Tools.isNil(a)))
            return false;
        
        ATermList l = Tools.consToList(env.getFactory(), (ATermAppl)t);
        
        StringBuffer sb = new StringBuffer();
        
        for(int i=0;i<l.getChildCount();i++) {
            ATermInt v = Tools.intAt(l, i);
            sb.append(new Character((char)v.getInt()));
        }
        env.setCurrent(env.makeString(sb.toString()));
        return true;
    }
}
