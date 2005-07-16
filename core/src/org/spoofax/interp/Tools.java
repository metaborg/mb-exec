/*
 * Created on 24.jun.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermInt;
import aterm.ATermList;
import aterm.pure.PureFactory;

public class Tools {

    private static List<Pair<String,ATerm>> emptyList = new ArrayList<Pair<String,ATerm>>();
    
    public static List<Pair<String,ATerm>> match(ATermAppl t, ATermAppl p) throws FatalError {
        
        System.out.println("?: " + t.getName() + " / " + p.getName());
        
        if(p.getName().equals("Anno"))
            return match(t, (ATermAppl)p.getChildAt(0));
        else if(p.getName().equals("Op")) {

            ATermList l = (ATermList)p.getChildAt(1);
            if(l.getChildCount() != t.getChildCount())
                return null;
            
            ATermAppl c = (ATermAppl)p.getChildAt(0);
            if(!t.getName().equals(c.getName()))
                return null;
            
            List<Pair<String,ATerm>> r = new ArrayList<Pair<String,ATerm>>();
            for(int i=0;i<t.getChildCount();i++) {
                List<Pair<String,ATerm>> m = match((ATerm)t.getChildAt(i), (ATermAppl)l.getChildAt(i));
                if(m != null)
                    r.addAll(m);
            }
            return r;
        }
        
        throw new FatalError("What?" + p);
    }
    
    public static List<Pair<String, ATerm>> match(ATermInt t, ATermAppl p) {
        if(t.match(p) != null)
            return emptyList;
        if(p.getName().equals("Var")) {
            List<Pair<String,ATerm>> r = new ArrayList<Pair<String,ATerm>>();
            r.add(new Pair<String, ATerm>(((ATermAppl)p.getChildAt(0)).getName(), t));
            return r;
        }
        return null;
    }
    
    public static List<Pair<String, ATerm>> match(ATerm t, ATermAppl p) throws FatalError {
        if(t.getType() == ATerm.APPL)
            return match((ATermAppl)t, p);
        else if(t.getType() == ATerm.INT)
            return match((ATermInt)t, p);
            
        throw new FatalError("Current term is not an ATermAppl term [" + t.getClass().toString() + " " + ATerm.APPL + " " + t.getType() + "]");
    }

}
