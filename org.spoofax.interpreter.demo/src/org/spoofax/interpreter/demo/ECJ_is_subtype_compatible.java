/*
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.demo;

import java.util.List;

import org.eclipse.jdt.core.dom.ITypeBinding;
import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.adapters.ecj.WrappedITypeBinding;
import org.spoofax.interpreter.library.Primitive;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_is_subtype_compatible extends Primitive {

    protected ECJ_is_subtype_compatible() {
        super("ECJ_is_subtype_compatible", 0, 1);
    }

    @Override
    public boolean call(IContext env, List<IConstruct> svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!(tvars[0] instanceof WrappedITypeBinding))
            return true;
        if(!(tvars[1] instanceof WrappedITypeBinding))
            return true;
            
        WrappedITypeBinding wb0 = (WrappedITypeBinding) tvars[0];
        ITypeBinding b0 = wb0.getWrappee();

        WrappedITypeBinding wb1 = (WrappedITypeBinding) tvars[0];
        ITypeBinding b1 = wb1.getWrappee();
        
        return b0.isSubTypeCompatible(b1);
    }

}
