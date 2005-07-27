/*
 * Created on 17.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interp;

import java.util.ArrayList;
import java.util.List;

import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermList;

public class ExtStrategy extends Strategy {
    
    public ExtStrategy(ATerm t) {
        List x = t.match("ExtSDef(<term>,<term>,<term>)");
        if(x == null)
            System.out.println("" + t);
        
        setName(((ATermAppl)x.get(0)).getName());
        
        stratParams = new ArrayList<String>();
        termParams = new ArrayList<String>();

        ATermList strats = (ATermList) x.get(1);
        for(int i=0;i<strats.getChildCount();i++)
            stratParams.add(((ATermAppl)strats.getChildAt(i)).getName());

        ATermList terms = (ATermList) x.get(2);
        for(int i=0;i<terms.getChildCount();i++)
            termParams.add(((ATermAppl)terms.getChildAt(i)).getName());
        
        System.out.println("Added strat " + getName());
    }

}
