/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser Lesser General Public License, v2.1.1
 */
package org.spoofax.interpreter.core;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;

import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.library.IOperatorRegistry;
import org.spoofax.interpreter.library.PrimitiveCache;
import org.spoofax.interpreter.library.ssl.SSLLibrary;
import org.spoofax.interpreter.stratego.Match.Binding;
import org.spoofax.interpreter.stratego.Match.Results;
import org.spoofax.interpreter.stratego.OpDecl;
import org.spoofax.interpreter.stratego.SDefT;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.interpreter.util.DebugUtil;

import javax.annotation.Nullable;


public class Context implements IContext {

    public static int indentation = 0; // TODO should this be non static?

    private final StackTracer stackTracer;

    private final ITermFactory programFactory;

    private final Map<String, IOperatorRegistry> operatorRegistries;

    private final PrimitiveCache operatorCache;
    
    private final Map<String, OpDecl> opdecls;

    private final StrategoSignature strategoSignature;

    private ITermFactory factory;

    private IStrategoTerm current;

    private VarScope varScope;

    private transient volatile boolean asyncCancelled;
    
    private Object contextObject;

    public Context(ITermFactory factory, ITermFactory programFactory) {
        this(factory, programFactory, false);
    }

    public Context(ITermFactory factory, ITermFactory programFactory, boolean skipStandardLibraries) {
        this.programFactory =  programFactory;
        this.factory = factory;
        stackTracer = new StackTracer();
        opdecls = new HashMap<String, OpDecl>();
        varScope = new VarScope(null);
        strategoSignature = new StrategoSignature(programFactory);
        operatorRegistries = new LinkedHashMap<String, IOperatorRegistry>();
        operatorCache = new PrimitiveCache(2, 16);

        if (!skipStandardLibraries) {
           addOperatorRegistry(new SSLLibrary());
           stackTracer.setIOAgent(getIOAgent());
        }
    }

    public IStrategoTerm current() {
        return current;
    }

    public void setCurrent(IStrategoTerm term) {
        current = term;
    }

    public StackTracer getStackTracer() {
        return stackTracer;
    }

    public IOAgent getIOAgent() {
        SSLLibrary op = (SSLLibrary) getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        return op == null ? null : op.getIOAgent();
    }

    public void setIOAgent(IOAgent ioAgent) {
        SSLLibrary op = (SSLLibrary) getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        if (op == null) throw new IllegalStateException("No SSL library");
        op.setIOAgent(ioAgent);
    }


    // These debug() overloads are for optimizations.
    // Prevents allocating an array for the arguments
    // in the most common cases.

    /**
     * Prints the string representation of the given object on the standard OUT
     * if debugging is enabled.
     *
     * Note: it is not needed to check {@link DebugUtil#isDebugging()} before calling this method.
     *
     * @param s0 the object to print
     */
    public static void debug(Object s0) {
        // A bit of a hack but saves 17% of time (according to JProfiler)...
        if(!DebugUtil.isDebugging()) return;
        DebugUtil.debug(DebugUtil.buildIndent(indentation), s0);
    }

    /**
     * Prints the string representation of the given objects on the standard OUT
     * if debugging is enabled.
     *
     * Note: it is not needed to check {@link DebugUtil#isDebugging()} before calling this method.
     *
     * @param s0 the first object to print
     * @param s1 the second object to print
     */
    public static void debug(Object s0, Object s1) {
        // A bit of a hack but saves 17% of time (according to JProfiler)...
        if(!DebugUtil.isDebugging()) return;
        DebugUtil.debug(DebugUtil.buildIndent(indentation), s0, s1);
    }

    /**
     * Prints the string representation of the given objects on the standard OUT
     * if debugging is enabled.
     *
     * Note: it is not needed to check {@link DebugUtil#isDebugging()} before calling this method.
     *
     * @param s0 the first object to print
     * @param s1 the second object to print
     * @param s2 the third object to print
     */
    public static void debug(Object s0, Object s1, Object s2) {
        // A bit of a hack but saves 17% of time (according to JProfiler)...
        if(!DebugUtil.isDebugging()) return;
        DebugUtil.debug(DebugUtil.buildIndent(indentation), s0, s1, s2);
    }

    /**
     * Prints the string representation of the given objects on the standard OUT
     * if debugging is enabled.
     *
     * Note: it is not needed to check {@link DebugUtil#isDebugging()} before calling this method.
     *
     * @param s the objects to print
     */
    public static void debug(Object... s) {
        // A bit of a hack but saves 17% of time (according to JProfiler)...
        if(!DebugUtil.isDebugging()) return;
        DebugUtil.debug(DebugUtil.buildIndent(indentation), s);
    }

    /**
     * Looks up the value of a variable with the given name.
     *
     * @param name the name of the variable
     * @return the term value of the variable; or {@code null} if not found
     */
    public @Nullable IStrategoTerm lookupVar(String name) throws InterpreterException {
        return varScope.lookup(name);
    }

    public @Nullable SDefT lookupSVar(String n) throws InterpreterException {
        return varScope.lookupSVar(n);
    }

    public ITermFactory getFactory() {
        if (asyncCancelled) cancel();
        return factory;
    }

    public void setFactory(ITermFactory factory) {
        this.factory = factory;
    }

    public boolean bindVars(Results r) {

        for (int i = 0; i < r.size(); i++) {
            Binding x = r.get(i);

            VarScope s = varScope.scopeOf(x.first);

            if (s == null) {
                varScope.add(x.first, x.second);
            } else if (s.hasVarInLocalScope(x.first)) {
                IStrategoTerm t = s.lookup(x.first);
                if (!t.equals(x.second)) {
                    debug(" no bind : ", x.first, " already bound to ", t, ", new: ", x.second);
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
        assert newVarScope != null;
        varScope = newVarScope;
    }

    public void popVarScope() {
        setVarScope(varScope.getParent());
    }

    @Deprecated
    public void restoreVarScope(VarScope anotherVarScope) {
        varScope = anotherVarScope;
    }

    void addOpDecl(String name, OpDecl decl) {
        opdecls.put(name, decl);
    }

    public Collection<OpDecl> getOperatorDeclarations() {
        return Collections.unmodifiableCollection(opdecls.values());
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

    public @Nullable IOperatorRegistry getOperatorRegistry(String name) {
        return operatorRegistries.get(name);
    }

    public AbstractPrimitive lookupOperator(String name) {
        if (asyncCancelled) cancel();
        AbstractPrimitive p = null;
        if((p = operatorCache.get(name)) != null) {
            return p;
        }
        for(IOperatorRegistry or : operatorRegistries.values()) {
            if((p = or.get(name)) != null) {
                break;
            }
        }
        operatorCache.put(name, p);
        return p;
    }

    final void internalAddOperatorRegistry(IOperatorRegistry or) {
        operatorRegistries.put(or.getOperatorRegistryName(), or);
    }

    public void addOperatorRegistry(IOperatorRegistry registry) {
        internalAddOperatorRegistry(registry);
    }

    public Collection<String> getStrategyNames() {
        VarScope v = getVarScope();
        while(v.getParent() != null)
            v = v.getParent();

        Collection<String> r = new HashSet<String>();
        for(SDefT s : v.getStrategyDefinitions())
            r.add(s.getName());
        return r;
    }

    public void asyncCancel() {
        asyncCancelled = true;
    }

    public void asyncCancelReset() {
        asyncCancelled = false;
    }

    protected void cancel() {
        asyncCancelled = false;
        getIOAgent().closeAllFiles();
        throw new CancellationException("Stratego interpreter cancelled");
    }
    
    @Override public Object contextObject() {
        return contextObject;
    }

    @Override public void setContextObject(Object context) {
        this.contextObject = context;
    }
}
