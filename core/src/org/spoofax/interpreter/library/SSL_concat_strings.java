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
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.stratego.Strategy;

import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermList;

public class SSL_concat_strings extends Primitive {

    protected SSL_concat_strings() {
        super("SSL_concat_strings", 0, 1);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs) throws FatalError {
        debug("SSL_concat_strings");

        if(!(Tools.isCons(targs.get(0))
                || Tools.isNil(targs.get(0))))
            return false;

        ATermList l = Tools.consToList(env.getFactory(), (ATermAppl)targs.get(0));
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<l.getChildCount();i++) {
            sb.append(Tools.stringAt(l, i));
        }
        env.setCurrent(env.makeString(sb.toString()));
        return true;
    }
}
