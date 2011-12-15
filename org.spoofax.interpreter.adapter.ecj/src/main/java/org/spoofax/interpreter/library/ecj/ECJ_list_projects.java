/*
 * Created on 15. des. 2011
 *
 * Copyright (c) 2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.core.runtime.CoreException;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

class ECJ_list_projects extends AbstractPrimitive {

    ECJ_list_projects() {
        super("ECJ_list_projects", 0, 0);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        final ECJLibrary lib = ECJLibrary.registry(env);
        final ProjectUtils pu = lib.getProjectUtils();
        try {
            env.setCurrent(ECJFactory.wrap(pu.listProjects()));
        } catch(CoreException e) {
            return ECJLibrary.invokeExceptionHandler(env, e);
        }
        return true;
    }

}
