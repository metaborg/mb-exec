/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_unbacked_compilation_unit_for_type extends AbstractPrimitive {

    public ECJ_unbacked_compilation_unit_for_type() {
        super("ECJ_unbacked_compilation_unit_for_type", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {

    	if(!ECJTools.isIType(tvars[0]))
            return false;
        
        try {
            final IType t = ECJTools.asIType(tvars[0]);
            final ECJLibrary ecj = (ECJLibrary) env.getOperatorRegistry(ECJLibrary.REGISTRY_NAME);
            final ASTParser parser = ecj.getParser();
            parser.setResolveBindings(true);
			parser.setSource(t.getSource().toCharArray());
	        CompilationUnit cu = (CompilationUnit)parser.createAST(null);
	        env.setCurrent(ECJFactory.wrap(cu));
	        return true;
		} catch (JavaModelException e) {
			return false;
		}
    }

}
