/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spoofax.interpreter.stratego.OpDecl;
import org.spoofax.interpreter.stratego.SDefT;

import aterm.ATerm;

public class Context extends ATermBuilder implements IContext {

    private static int indentation = 0;

    protected ATerm current;

    private Map<String, OpDecl> opdecls;
    
    protected VarScope varScope;

    public Context() {
        super();
        
        opdecls = new HashMap<String, OpDecl>();
        varScope = new VarScope(null);
    }

    public ATerm current() {
        return current;
    }

    public void setCurrent(ATerm term) {
        current = term;
    }


    private final static char[] indent = new char[100];
    static {
        for (int i = 0; i < indent.length; i++) {
            indent[i] = 'c';
        }
    }
    public static void debug(Object... s) {

        // A bit of a hack but saves 17% of time (according to JProfiler)...
        if(Interpreter.isDebugging()) {
            StringBuffer b = new StringBuffer(indentation);
            b.append(indent, 0, indentation);
            Interpreter.debug(b, s);
        }
    }

    public boolean invoke(String name, Object object, Object object2)
            throws FatalError {
        
        SDefT s = lookupSVar(name);
        
        return s.getBody().eval(this);
    }

    public ATerm lookupVar(String n) throws FatalError {
        return varScope.lookup(n);
    }

    public SDefT lookupSVar(String n) throws FatalError {
        return varScope.lookupSVar(n);
    }

    public TermFactory getFactory() {
        return factory;
    }

    public boolean bindVars(List<Pair<String, ATerm>> r) {
        
        for (Pair<String, ATerm> x : r) {
        
            VarScope s = varScope.scopeOf(x.first);
            
            if (s == null) {
                varScope.add(x.first, x.second);
            } else if (s.hasVarInLocalScope(x.first)) {
                ATerm t = s.lookup(x.first);
                boolean eq = t.match(x.second) != null;
                if (!eq) {
                    if (Interpreter.isDebugging()) {
                        debug(" no bind : ", x.first, " already bound to ", t, ", new: ", x.second);
                    }
                    return eq;
                }
            } else {
                s.add(x.first, x.second);
            }
        }
        
        return true;
    }

    public void dump(String prefix) {
        varScope.dump(prefix);
    }

    public VarScope getVarScope() {
        return varScope;
    }

    public void setVarScope(VarScope newVarScope) {
        varScope = newVarScope;
    }

    void addOpDecl(String name, OpDecl decl) {
        opdecls.put(name, decl);
    }

    public void addSVar(String name, SDefT def) {
        varScope.addSVar(name, def);
    }

    public static void bump() {
        indentation++;
    }
    
    public static void unbump() {
        indentation--;
    }
}
