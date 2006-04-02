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
import org.spoofax.interpreter.stratego.DebugUtil;

import aterm.ATerm;

public class Context extends ATermBuilder implements IContext {

    public static int indentation = 0; //todo: should this be non static?

    protected ATerm current;

    private Map<String, OpDecl> opdecls;

    protected VarScope varScope;

    public Context() {
        super();

        assert factory != null;
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
        if(DebugUtil.isDebugging()) {
            DebugUtil.debug(DebugUtil.buildIndent(indentation), s);
        }
    }

    public boolean invoke(String name, Object object, Object object2)
            throws InterpreterException {

        SDefT s = lookupSVar(name);

        return s.getBody().eval(this);
    }

    public ATerm lookupVar(String n) throws InterpreterException {
        return varScope.lookup(n);
    }

    public SDefT lookupSVar(String n) throws InterpreterException {
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
                    if (DebugUtil.isDebugging()) {
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

    public VarScope getVarScope() {
        return varScope;
    }

    public void setVarScope(VarScope newVarScope) {
        varScope = newVarScope;
        if (DebugUtil.isDebugging()) {
            DebugUtil.bump(this);
            debug("{ " + newVarScope.printVars());
            DebugUtil.bump(this);
        }
    }

    public void popVarScope() {
        VarScope current = varScope;
        varScope = varScope.getParent();
        if (DebugUtil.isDebugging()) {
            DebugUtil.unbump(this);
            debug("} " + current.printVars()); //todo: same vars?
            DebugUtil.unbump(this);
        }
    }

    public void restoreVarScope(VarScope anotherVarScope) {
        VarScope current = varScope;
        varScope = anotherVarScope;
        if (DebugUtil.isDebugging()) {
            DebugUtil.unbump(this);
            debug("} " + current.printVars()); //todo: same vars?
            DebugUtil.unbump(this);
        }
    }

    void addOpDecl(String name, OpDecl decl) {
        opdecls.put(name, decl);
    }

    public void addSVar(String name, SDefT def) {
        varScope.addSVar(name, def);
    }
}
