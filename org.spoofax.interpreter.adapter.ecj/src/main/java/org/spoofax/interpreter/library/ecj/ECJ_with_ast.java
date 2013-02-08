/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_with_ast extends AbstractPrimitive {

	public ECJ_with_ast() {
		super("ECJ_with_ast", 1, 1);
	}

	@Override
	public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
	throws InterpreterException {

		if(!ECJTools.isASTNode(tvars[0]))
			return false;

		final ASTNode astNode = ECJTools.asASTNode(tvars[0]);
		final ECJFactory factory = (ECJFactory)env.getFactory();
		final AST oldAST = factory.getAST();
		
		System.err.println("ECJ_with_ast before");
		factory.setAST(astNode.getAST());
		boolean r = svars[0].evaluate(env);
		factory.setAST(oldAST);
		System.err.println("ECJ_with_ast after");
		
		return r;
	}

}
