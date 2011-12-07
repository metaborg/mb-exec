/*
 * Created on 29. may. 2009
 *
 * Copyright (c) 2009-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_binding_of_methoddecl extends AbstractPrimitive {

    public ECJ_binding_of_methoddecl() {
        super("ECJ_binding_of_methoddecl", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {

        if(!ECJTools.isMethodDeclaration(tvars[0]))
            return false;

        MethodDeclaration t = ECJTools.asMethodDeclaration(tvars[0]);

        IMethodBinding tb = t.resolveBinding();
        if(tb == null)
            return false;

        env.setCurrent(ECJFactory.wrap(tb));
        return true;
    }

}
