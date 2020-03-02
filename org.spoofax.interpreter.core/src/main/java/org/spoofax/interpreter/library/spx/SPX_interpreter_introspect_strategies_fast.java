/*
 * Created on 02. mar.. 2012
 *
 * Copyright (c) 2012, Tobi Vollebregt
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.spx;

import java.util.HashSet;
import java.util.LinkedList;

import org.spoofax.interpreter.core.Context;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.StrategoSignature;
import org.spoofax.interpreter.core.VarScope;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.SDefT;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.TermVisitor;
import org.spoofax.terms.util.TermUtils;

/**
 * Slightly more scalable version of SPX_interpreter_introspect_strategies.
 * Only returns ExtSDefs for strategies used (SVar) in the single term argument.
 * May include false positives.
 */
public class SPX_interpreter_introspect_strategies_fast extends AbstractPrimitive {

    public SPX_interpreter_introspect_strategies_fast() {
        super("SPX_interpreter_introspect_strategies_fast", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        final ITermFactory f = env.getFactory();
        final LinkedList<IStrategoAppl> as = new LinkedList<IStrategoAppl>();

        // see also Context.getStrategyNames()
        VarScope v = env.getVarScope();
        while (v.getParent() != null)
            v = v.getParent();

        // FIXME: ugh
        if (env instanceof Context) {
            final StrategoSignature sign = ((Context) env).getStrategoSignature();
            final HashSet<String> names = new HashSet<String>();

            final TermVisitor collectSVars = new TermVisitor() {
                public void preVisit(IStrategoTerm term) {
                    if (term.getTermType() == IStrategoTerm.APPL
                            && ((IStrategoAppl) term).getConstructor() == sign.CTOR_SVar) {
                        names.add(TermUtils.toJavaStringAt(term, 0));
                    }
                }
            };
            collectSVars.visit(tvars[0]);

            for (SDefT sdef : v.getSVars()) {
                if (names.contains(sdef.getUncifiedName())) {
                    as.addFirst(sdef.toExternalDef(f, sign));
                }
            }
        }

        env.setCurrent(f.makeList(as));
        return true;
    }

}
