/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_ast_for_compilation_unit extends ECJPrimitive {

    public ECJ_ast_for_compilation_unit() {
        super("ECJ_ast_for_compilation_unit", 0, 2);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        if(!ECJTools.isICompilationUnit(tvars[0]))
            return false;
        if(!Tools.isTermInt(tvars[1]))
            return false;
        
        final int resolveTypes = Tools.asJavaInt(tvars[1]);
        
        ICompilationUnit t = ECJTools.asICompilationUnit(tvars[0]);
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setResolveBindings(resolveTypes == 1);
        parser.setSource(t);
        env.setCurrent(ECJFactory.wrap((CompilationUnit)parser.createAST(null)));
        return true;
    }

}
