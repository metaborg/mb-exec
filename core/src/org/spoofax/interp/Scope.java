/*
 * Created on 26.jun.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interp;

import java.util.HashMap;
import java.util.Map;

import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermList;

public class Scope {

    private Scope parent;

    private Map<String, ATerm> vars;

    private Map<String, Strategy> svars;

    public Scope(Scope parent) {
        this.parent = parent;
        vars = new HashMap<String, ATerm>();
        svars = new HashMap<String, Strategy>();
        
    }

    public ATerm lookup(String name) {
        ATerm t = vars.get(name);
        if(t == null && parent != null)
            return parent.lookup(name);
        return t;
    }

    public Strategy lookupSVar(String name) {
        Strategy t = svars.get(name);
        if(t == null && parent != null)
            return parent.lookupSVar(name);
        return t;
    }

    public void addSVar(String svar, Strategy strat) {
        svars.put(svar, strat);
    }    

    public void add(String var, ATerm t) {
        vars.put(var, t);
    }

    public boolean hasVarInLocalScope(String first) {
        return vars.containsKey(first) && vars.get(first) != null;
    }

    public void addUndeclaredVars(ATermList newVars) {
        for(int i=0;i<newVars.getLength();i++) {
            add(((ATermAppl)newVars.getChildAt(i)).getName(), null);
        }
        
        
    }
}
