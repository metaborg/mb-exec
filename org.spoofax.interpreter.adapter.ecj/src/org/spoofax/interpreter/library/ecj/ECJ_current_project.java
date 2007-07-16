/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ecj;

import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_current_project extends AbstractPrimitive {

    public ECJ_current_project() {
        super("ECJ_current_project", 0, 0);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        ECJLibrary ecj = (ECJLibrary) env.getOperatorRegistry(ECJLibrary.REGISTRY_NAME);
        if(ecj == null)
            return false;
        
        env.setCurrent(ECJFactory.wrap(ecj.getCurrentProject()));
        return true;
    }

}
