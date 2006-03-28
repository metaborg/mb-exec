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
    public static final int INDENT_STEP = 2;

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


    public static void debug(Object... s) {

        // A bit of a hack but saves 17% of time (according to JProfiler)...
        if(Interpreter.isDebugging()) {
            Interpreter.debug(buildIndent(indentation), s);
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
        if (Interpreter.isDebugging()) {
            bump();
            debug("{ " + newVarScope.printVars());
            bump();
        }
    }

    public void popVarScope() {
        VarScope current = varScope;
        varScope = varScope.getParent();
        if (Interpreter.isDebugging()) {
            unbump();
            debug("} " + current.printVars()); //todo: same vars?
            unbump();
        }
    }

    public void restoreVarScope(VarScope anotherVarScope) {
        VarScope current = varScope;
        varScope = anotherVarScope;
        if (Interpreter.isDebugging()) {
            unbump();
            debug("} " + current.printVars()); //todo: same vars?
            unbump();
        }
    }

    void addOpDecl(String name, OpDecl decl) {
        opdecls.put(name, decl);
    }

    public void addSVar(String name, SDefT def) {
        varScope.addSVar(name, def);
    }

    public static void bump() {
        indentation += INDENT_STEP;
    }
    
    public static void unbump() {
        indentation -= INDENT_STEP;
    }

    private final static char[] indent = new char[500];
    static {
        for (int i = 0; i < indent.length; i++) {
            indent[i] = ' ';
        }
    }
    public static StringBuilder buildIndent(final int indentation) {
        StringBuilder b = new StringBuilder(indentation);
        b.append(indent, 0, indentation);
        return b;
    }
}
