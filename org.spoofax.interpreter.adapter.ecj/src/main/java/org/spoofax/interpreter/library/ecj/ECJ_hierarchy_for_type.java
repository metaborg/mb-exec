/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaModelException;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_hierarchy_for_type extends AbstractPrimitive {

    public ECJ_hierarchy_for_type() {
        super("ECJ_hierarchy_for_type", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {

        if(!ECJTools.isIType(tvars[0]))
            return false;

        IType type = ECJTools.asIType(tvars[0]);
        try {
			ITypeHierarchy th = type.newTypeHierarchy(null);
			env.setCurrent(ECJFactory.wrap(th));
		} catch (JavaModelException e) {
			e.printStackTrace();
			return false;
		}
        
        return true;
    }

}
