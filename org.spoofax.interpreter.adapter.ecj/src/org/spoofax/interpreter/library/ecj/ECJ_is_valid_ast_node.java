/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ecj;

import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.adapter.ecj.WrappedASTNode;
import org.spoofax.interpreter.adapter.ecj.WrappedModifierKeyword;
import org.spoofax.interpreter.adapter.ecj.WrappedPostfixExpressionOperator;
import org.spoofax.interpreter.adapter.ecj.WrappedPrefixExpressionOperator;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_is_valid_ast_node extends AbstractPrimitive {

    public ECJ_is_valid_ast_node() {
        super("ECJ_is_valid_ast_node", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, IConstruct[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        IStrategoTerm t = tvars[0];
        return t instanceof WrappedASTNode
            || t instanceof WrappedModifierKeyword
            || t instanceof WrappedPostfixExpressionOperator
            || t instanceof WrappedPrefixExpressionOperator;
    }

}
