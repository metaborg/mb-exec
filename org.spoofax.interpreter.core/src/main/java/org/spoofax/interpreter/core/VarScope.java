/*
 * Created on 26.jun.2005
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser Lesser General Public License, v2.1.1
 */
package org.spoofax.interpreter.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.spoofax.interpreter.stratego.SDefT;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.util.DebugUtil;

import jakarta.annotation.Nullable;

public class VarScope {

    private VarScope parent;

    private Map<String, IStrategoTerm> vars;

    private Map<String, SDefT> svars;

    // Term variables whose bindings were backtracked
    private Set<String> semiBound;

    public VarScope(VarScope parent) {
        this.parent = parent;

        vars = new HashMap<String, IStrategoTerm>(0); //todo: create these on demand
        svars = new HashMap<String, SDefT>(0);
        semiBound = new HashSet<>();
    }

    /**
     * Looks up the value of a variable with the given name.
     *
     * @param name the name of the variable
     * @return the term value of the variable; or {@code null} if not found
     */
    public @Nullable IStrategoTerm lookup(String name) {
        IStrategoTerm t = vars.get(name);

        if (t == null && parent != null)
            t = parent.lookup(name);

        if(DebugUtil.isDebugging()) {
            Context.debug(DebugUtil.buildIndent(DebugUtil.INDENT_STEP), "lookup : ", name, " = ", t);
            VarScope vs = this;
            while(vs != null) {
                Context.debug(DebugUtil.buildIndent(DebugUtil.INDENT_STEP), vs);
                vs = vs.parent;
            }
        }

        return t;
    }

    public @Nullable SDefT lookupSVar(String name) {

        SDefT t = svars.get(name);

        if (t == null && parent != null)
            return parent.lookupSVar(name);

        return t;
    }

    public void addSVar(String svar, SDefT strat) {
        svars.put(svar, strat);
    }

    public void addSVars(SDefT[] sdefs) {
        for(int i = 0, sz = sdefs.length; i < sz; i++)
            svars.put(sdefs[i].getName(), sdefs[i]);
    }

    public void add(String var, IStrategoTerm t) {
        vars.put(var, t);
        semiBound.remove(var);
    }

    public void addVars(List<String> vars) {
        for (String var : vars) {
            this.vars.put(var, null);
            semiBound.remove(var);
        }
    }

    public Collection<String> getVarNames() {
        return Collections.unmodifiableCollection(vars.keySet());
    }

    public Collection<SDefT> getSVars() {
        return Collections.unmodifiableCollection(svars.values());
    }

    public String printVars() {
        StringBuilder sb = new StringBuilder("");
        Iterator<String> it = vars.keySet().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(":");
        return sb.toString();
    }

    public boolean hasVarInLocalScope(String name) {
        return vars.containsKey(name) && vars.get(name) != null && !semiBound.contains(name);
    }

    public VarScope getParent() {
        return parent;
    }

    public VarScope scopeOf(String name) {

        if (vars.containsKey(name))
            return this;

        if (parent != null)
            return parent.scopeOf(name);

        return null;
    }

    public String dump(String prefix, boolean localOnly) {
        String pre = prefix;

        if (!localOnly && parent != null) {
            pre = parent.dump(prefix);
        }

        if (DebugUtil.isDebugging()) {
            debug(pre, "=== ", this);

            for (String t : vars.keySet()) {
                debug(pre, "[v] ", t, "   ", vars.get(t));
            }
            for (String t : svars.keySet()) {
                debug(pre, "[s] ", t, "   ", svars.get(t));
            }
            if (svars.size() == 0 && vars.size() == 0) {
                debug(pre, "<empty>");
            }
        }
        return pre + "  ";
    }
    public String dump(String prefix) {
        return dump(prefix, false);
    }

    private static void debug(Object... s) {
        DebugUtil.debug(s);
    }

    public void clear() {
        vars.clear();
        semiBound.clear();
        svars.clear();
    }

    public List<BindingInfo> saveUnboundVars() {
        return saveUnboundVars(new ArrayList<BindingInfo>());
    }

    private List<BindingInfo> saveUnboundVars(ArrayList<BindingInfo> results) {
        for (String k : vars.keySet()) {
            if (vars.get(k) == null)
                results.add(new BindingInfo(this, k));
        }

        if (parent != null)
            return parent.saveUnboundVars(results);

        return results;
    }

    public static void backtrackUnboundVars(List<BindingInfo> bindings) {
        for (BindingInfo binding : bindings) {
            debug("backtracking variable: ", binding.name);
            binding.scope.backtrackVar(binding.name);
        }
    }

    public static void restoreUnboundVars(List<BindingInfo> bindings) {
        for (BindingInfo binding : bindings) {
            debug("restoring backtracked variable: ", binding.name);
            binding.scope.restoreVar(binding.name);
        }
    }

    private void backtrackVar(String name) {
        semiBound.add(name);
    }
    private void restoreVar(String name) {
        semiBound.remove(name);
    }

    public Collection<SDefT> getStrategyDefinitions() {
        return svars.values();
    }

    public boolean removeVar(String varName) {
        semiBound.remove(varName);
        return vars.remove(varName) != null;
    }

    public boolean removeSVar(String svarName) {
        return svars.remove(svarName) != null;
    }

    public void removeAllVars() {
        vars.clear();
        semiBound.clear();
    }
}
