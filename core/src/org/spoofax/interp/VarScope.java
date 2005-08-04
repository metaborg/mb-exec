/*
 * Created on 26.jun.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermList;

public class VarScope {

    private VarScope parent;

    private Map<String, ATerm> vars;

    private Map<String, Strategy> svars;

    public VarScope(VarScope parent) {
        this.parent = parent;
        vars = new HashMap<String, ATerm>();
        svars = new HashMap<String, Strategy>();
    }

    public ATerm lookup(String name) {
        ATerm t = vars.get(name);
        if (t == null && parent != null)
            return parent.lookup(name);
        return t;
    }

    public Strategy lookupSVar(String name) {
        Strategy t = svars.get(name);
        if (t == null && parent != null)
            return parent.lookupSVar(name);
        return t;
    }

    public void addSVar(String svar, Strategy strat) {
        svars.put(svar, strat);
    }

    public void add(String var, ATerm t) {
        vars.put(var, t);
    }

    public boolean hasVarInLocalScope(String name) {
        return vars.containsKey(name) && vars.get(name) != null;
    }

    public void addUndeclaredVars(ATermList newVars) {
        for (int i = 0; i < newVars.getLength(); i++) {
            add(((ATermAppl) newVars.getChildAt(i)).getName(), null);
        }
    }

    public VarScope getParent() {
        return parent;
    }

    public VarScope scopeOf(String name) {
        if(vars.containsKey(name))
            return this;
        if(parent != null)
            return parent.scopeOf(name);
        return null;
    }
    
    public String dumpScope(String prefix) {
        String pre = prefix;
        if(parent != null)
            pre = parent.dumpScope(prefix);

        for(String t : vars.keySet()) {
            System.out.println(pre + "[v] " + t + "   " + vars.get(t));
        }
        for(String t : svars.keySet()) {
            System.out.println(pre + "[s] " + t + "   " + svars.get(t));
        }
        if(svars.size() == 0 && vars.size() == 0)
            System.out.println(pre + "<empty>");
        
        return pre + "  ";
    }

    public BindingInfo saveUnboundVars() {
        return saveUnboundVars(new BindingInfo());
    }
    
    private BindingInfo saveUnboundVars(BindingInfo bi) {
        for(String k : vars.keySet()) {
            if(vars.get(k) == null)
                bi.add(this, k);
        }
        if(parent != null)
            return parent.saveUnboundVars(bi);
        return bi;
    }

    public void restoreUnboundVars(BindingInfo bi) {
        List<Pair<VarScope, String>> bindings = bi.getBindings();
        for(Pair<VarScope, String> p : bindings) {
            p.first.resetVar(p.second);
        }
    }

    private void resetVar(String name) {
        vars.put(name, null);
    }
}
