/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.core;

import java.util.Collection;

import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.library.IOperatorRegistry;
import org.spoofax.interpreter.stratego.Match.Results;
import org.spoofax.interpreter.stratego.OpDecl;
import org.spoofax.interpreter.stratego.SDefT;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.interpreter.util.IAsyncCancellable;

import javax.annotation.Nullable;

/**
 * The term context.
 */
public interface IContext extends IAsyncCancellable {
    // FIXME: Should be named getCurrent() for Kotlin compatibility
    /**
     * Gets the current term.
     *
     * @return the current term
     */
    IStrategoTerm current();

    /**
     * Sets the current term.
     *
     * @param newCurrent the new current term
     */
    void setCurrent(IStrategoTerm newCurrent);

    /**
     * Gets the value of the variable with the specified name.
     *
     * @param n the name of the variable
     * @return the term value of the variable; or {@code null} if not found
     * @throws InterpreterException Never happens.
     */
    @Nullable IStrategoTerm lookupVar(String n) throws InterpreterException;

    /**
     * Gets the value of the strategy variable with the specified name.
     *
     * @param n the name of the strategy variable
     * @return the strategy value of the variable; or {@code null} if not found
     * @throws InterpreterException Never happens.
     */
    @Nullable SDefT lookupSVar(String n) throws InterpreterException;

    /**
     * Gets the term factory.
     *
     * @return the term factory
     */
    ITermFactory getFactory();

    /**
     * Sets the term factory.
     *
     * @param factory the term factory
     * @deprecated Do not change the term factory after the context has been created.
     */
    @Deprecated
    void setFactory(ITermFactory factory);

    /**
     * Gets the primitive registry with the specified name.
     *
     * @param name the name of the registry to look for
     * @return the primitive registry; or {@code null} when not found
     */
    @Nullable IOperatorRegistry getOperatorRegistry(String name);

    /**
     * Adds a primitive registry.
     *
     * @param registry the registry to add
     */
    void addOperatorRegistry(IOperatorRegistry registry);

    /**
     * Finds a primitive with the specified name in any of the primitive registries.
     *
     * @param name the name to look for
     * @return the primitive; or {@code null} if not found
     */
    @Nullable AbstractPrimitive lookupOperator(String name);

    /**
     * Binds the variables to their values as found in the specified {@link Results} object.
     *
     * @param r the results with the variable bindings
     * @return {@code true} when all bindings where successfully added;
     * otherwise, {@code false} when one or more variables was already bound
     */
    boolean bindVars(Results r);

    /**
     * Gets the variable scope in which variables are looked up.
     * @return the variable scope
     */
    VarScope getVarScope();

    /**
     * Sets the variable scope in which variables are looked up.
     *
     * @param newVarScope the new variable scope
     */
    void setVarScope(VarScope newVarScope);

    /**
     * Pops a variable scope.
     */
    void popVarScope();

    /** @deprecated Use {@link #setVarScope} instead. */
    @Deprecated
    void restoreVarScope(VarScope anotherVarScope);

    /**
     * Gets the Stratego constructor signatures.
     *
     * @return the {@link StrategoSignature} object
     */
    StrategoSignature getStrategoSignature();

    @Deprecated
    Collection<String> getStrategyNames();

    /**
     * Gets the term constructor declarations.
     *
     * @return the term constructor declarations
     */
    Collection<OpDecl> getOperatorDeclarations();

    /**
     * Gets the stack tracer, used to generate stack traces.
     *
     * @return the stack tracer
     */
    StackTracer getStackTracer();

    // FIXME: Should be named getContextObject() for Kotlin compatibility
    /**
     * Gets the platform-specific context object associated with this context.
     *
     * @return the context object; or {@code null} when none was set
     */
    @Nullable Object contextObject();

    /**
     * Sets the platform-specific context object associated with this context.
     *
     * @param context the new context object; or {@code null} to set none
     */
    void setContextObject(@Nullable Object context);
}
