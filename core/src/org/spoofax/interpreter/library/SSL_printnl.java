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

public class SSL_printnl extends Primitive {

    protected SSL_printnl() {
        super("SSL_printnl", 0, 1);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs) throws FatalError {
        debug("SSL_printnl - " + targs);
        
        ATermList l = Tools.consToListDeep(env.getFactory(), (ATermAppl) targs.get(1));
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<l.getChildCount();i++) {
            ATerm t = Tools.termAt(l, i);
            if(t.getType() == ATerm.APPL) {
                ATermAppl a = (ATermAppl) t;
                if(Tools.isCons(a)) 
                    sb.append(Tools.consToListDeep(env.getFactory(), a));
                else if(Tools.isATermString(t))
                    sb.append(Tools.getATermString(t));
                continue;
            }
            sb.append(t.toString());
        }
            
        System.out.println(sb);
        return true;
    }
}
