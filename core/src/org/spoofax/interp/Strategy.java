/*
 * Created on 26.jun.2005
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
import aterm.pure.ATermImpl;

public class Strategy extends NamedDecl {

    private List<String> termParams;
    private List<String> stratParams;
    private ATerm body;
    
    public Strategy(ATerm t) {
        List x = t.match("SDefT(<term>,<term>,<term>,<term>)");
        
        setName(((ATermAppl)x.get(0)).getName());
        
        stratParams = new ArrayList<String>();
        termParams = new ArrayList<String>();
        
        ATermList terms = (ATermList) x.get(1);
        for(int i=0;i<terms.getChildCount();i++)
            termParams.add(((ATermAppl)terms.getChildAt(i)).getName());
        
        ATermList strats = (ATermList) x.get(2);
        for(int i=0;i<strats.getChildCount();i++)
            stratParams.add(((ATermAppl)strats.getChildAt(i)).getName());
        
        body = (ATerm) x.get(3);
    }
    
    List<String> getTermParams() { return termParams; }
    List<String> getStrategyParams() { return stratParams; }
    ATerm getBody() { return body; }
}
