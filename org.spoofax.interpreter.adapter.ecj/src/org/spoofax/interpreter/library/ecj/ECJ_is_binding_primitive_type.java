/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.jdt.core.dom.ITypeBinding;
import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_is_binding_primitive_type extends ECJPrimitive {

    public ECJ_is_binding_primitive_type() {
        super("ECJ_is_binding_primitive_type", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, IConstruct[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
    	if(!ECJTools.isITypeBinding(tvars[0]))
    		return false;
    	
    	ITypeBinding tb = ECJTools.asITypeBinding(tvars[0]);
    	
    	return tb.isPrimitive();
    }

}
