/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter;

import java.util.HashMap;
import java.util.Map;

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.library.IOperatorRegistry;
import org.spoofax.interpreter.library.ssl.SSLLibrary;
import org.spoofax.interpreter.stratego.OpDecl;
import org.spoofax.interpreter.stratego.SDefT;
import org.spoofax.interpreter.stratego.Match.Binding;
import org.spoofax.interpreter.stratego.Match.Results;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.interpreter.terms.StrategoSignature;


public class Context implements IContext {

    public static int indentation = 0; // TODO should this be non static?

    private IStrategoTerm current;

    // FIXME deprecated?
    private Map<String, OpDecl> opdecls;

    private VarScope varScope;

    private StrategoSignature strategoSignature;
    
    private ITermFactory factory;
    private ITermFactory programFactory;

    private Map<String, IOperatorRegistry> operatorRegistries;

    private ChoicePointStack choicePointStack;

    public Context(ITermFactory factory, ITermFactory programFactory) {
        this.programFactory =  programFactory;
        this.factory = factory;
        choicePointStack = new ChoicePointStack();
        opdecls = new HashMap<String, OpDecl>();
        varScope = new VarScope(null);
        strategoSignature = new StrategoSignature(programFactory);
        operatorRegistries = new HashMap<String, IOperatorRegistry>();
        
        operatorRegistries.put(SSLLibrary.REGISTRY_NAME, new SSLLibrary());
    }

    public IStrategoTerm current() {
        return current;
    }

   public void setCurrent(IStrategoTerm term) {
        current = term;
    }


    public static void debug(Object... s) {

        // A bit of a hack but saves 17% of time (according to JProfiler)...
        if(DebugUtil.isDebugging()) {
            DebugUtil.debug(DebugUtil.buildIndent(indentation), s);
        }
    }

    public IStrategoTerm lookupVar(String n) throws InterpreterException {
        return varScope.lookup(n);
    }

    public SDefT lookupSVar(String n) throws InterpreterException {
        return varScope.lookupSVar(n);
    }

    public ITermFactory getFactory() {
        return factory;
    }

    public boolean bindVars(Results r) {

        for (Binding x : r) {

            VarScope s = varScope.scopeOf(x.first);

            if (s == null) {
                varScope.add(x.first, x.second);
            } else if (s.hasVarInLocalScope(x.first)) {
                IStrategoTerm t = s.lookup(x.first);
                if (!t.equals(x.second)) {
                    if (DebugUtil.isDebugging()) {
                        debug(" no bind : ", x.first, " already bound to ", t, ", new: ", x.second);
                    }
                    return false;
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
//        if (DebugUtil.isDebugging()) {
//            DebugUtil.bump();
//            debug("{ " + newVarScope.printVars());
//            DebugUtil.bump();
//        }
    }

    public void popVarScope() {
        //VarScope current = varScope;
        varScope = varScope.getParent();
//        if (DebugUtil.isDebugging()) {
//            DebugUtil.unbump();
//            debug("} " + current.printVars()); //todo: same vars?
//            DebugUtil.unbump();
//        }
    }

    public void restoreVarScope(VarScope anotherVarScope) {
        VarScope current = varScope;
        varScope = anotherVarScope;
//        if (DebugUtil.isDebugging()) {
//            DebugUtil.unbump();
//            debug("} " + current.printVars()); //todo: same vars?
//            DebugUtil.unbump();
//        }
    }

    void addOpDecl(String name, OpDecl decl) {
        opdecls.put(name, decl);
    }

    public void addSVar(String name, SDefT def) {
        varScope.addSVar(name, def);
    }

    public StrategoSignature getStrategoSignature() {
        return strategoSignature;
    }

    public ITermFactory getProgramFactory() {
        return programFactory;
    }

    public IOperatorRegistry getOperatorRegistry(String domain) {
        return operatorRegistries.get(domain);
    }

    public AbstractPrimitive lookupOperator(String name) {
        for(IOperatorRegistry or : operatorRegistries.values()) {
            AbstractPrimitive t = or.get(name);
            if(t != null)
                return t;
        }
        return null;
    }

    public void addOperatorRegistry(String domain, IOperatorRegistry or) {
        operatorRegistries.put(domain, or);
    }

    public ChoicePointStack getChoicePointStack() {
        return choicePointStack;
    }
}
