/*
 * Created on 15. des. 2011
 *
 * Copyright (c) 2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.core.runtime.CoreException;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

class ECJ_delete_project extends AbstractPrimitive {

    ECJ_delete_project() {
        super("ECJ_delete_project", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        ECJLibrary lib = ECJLibrary.registry(env);
        ProjectUtils pu = lib.getProjectUtils();
        if(!ECJTools.isIProject(tvars[0]))
            return false;
        try {
            return pu.deleteProject(ECJTools.asIProject(tvars[0]));
        } catch(CoreException e) {
            return ECJLibrary.invokeExceptionHandler(env, e);
        }
    }

}
