/*
 * Created on 26.jun.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interpreter;

import java.util.ArrayList;
import java.util.List;

import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermList;

public class IntStrategy extends OldStrategy {

    protected ATermAppl body;

    public IntStrategy(ATerm t, DefScope defScope, VarScope varScope) {
        super(defScope, varScope);
        
        List x = t.match("SDefT(<term>,<term>,<term>,<term>)");
        if (x == null)
            System.out.println("" + t);

        setName(((ATermAppl) x.get(0)).getName());

        stratParams = new ArrayList<String>();
        termParams = new ArrayList<String>();

        ATermList strats = (ATermList)x.get(1);
        for (int i = 0; i < strats.getChildCount(); i++) {
            stratParams.add(Tools.stringAt(Tools.applAt(strats, i), 0));
        }

        ATermList terms = (ATermList) x.get(2);
        for (int i = 0; i < terms.getChildCount(); i++)
            termParams.add(Tools.stringAt(Tools.applAt(terms, i), 0));

        body = (ATermAppl) x.get(3);
    }
    
    protected IntStrategy(ATermAppl body, DefScope defScope, VarScope varScope, boolean bull) {
        super(defScope, varScope);
        this.body = body;
    }

    ATermAppl getBody() {
        return body;
    }
    
    @Override
    public String toString() {
        return "[IntStrategy] " + getName();
    }
}
