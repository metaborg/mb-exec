/*
 * Created on 17.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interp;

import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermList;

public class Library {

    public static boolean match_cons( ATermAppl t, String s) {
        // FIXME:
        return false;
        
    }
    
    public static boolean Cons_2_0(Interpreter itp, ATermList svars, ATermList tvars) {
        return false;
    }

    public static boolean Nil_2_0(Interpreter itp, ATermList svars, ATermList tvars) {
        return false;
    }

    public static boolean Nil_0_0(Interpreter itp, ATermList svars, ATermList tvars) {
        return false;
    }

    public static boolean _2_0(Interpreter itp, ATermList svars, ATermList tvars) throws FatalError {
        
        System.out.println(svars);
        
        String a = Tools.stringAt(Tools.applAt(svars, 0), 0); 
        String b = Tools.stringAt(Tools.applAt(svars, 1), 0);
        
        ATermAppl t = (ATermAppl) itp.getCurrent();
        
        if(!match_cons(t, ""))
            return false;
        
        ATerm t0 = (ATerm) t.getChildAt(0);
        ATerm t1 = (ATerm) t.getChildAt(1);
        
        itp.setCurrent(t0);
        
        if(!itp.invoke(new SVar(a), null, null))
            return false;
        
        ATerm t3 = itp.getCurrent();
        
        itp.setCurrent(t1);
        if(!itp.invoke(new SVar(b), null, null))
            return false;
        ATerm t4 = itp.getCurrent();
        
        itp.setCurrent(itp.makeTerm("", t3, t4));
        return true;
            
    }
}
