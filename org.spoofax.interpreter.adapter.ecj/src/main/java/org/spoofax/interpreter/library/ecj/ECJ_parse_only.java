/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.adapter.ecj.WrappedIProject;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_parse_only extends AbstractPrimitive {

    public ECJ_parse_only() {
        super("ECJ_parse_only", 0, 2);
    }
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {

        if(!ECJTools.isIProject(tvars[0]))
            return false;
        if(!Tools.isTermString(tvars[1]))
            return false;
        
        String path = ((IStrategoString)tvars[1]).stringValue();
        
        IProject project = ((WrappedIProject)tvars[0]).getWrappee();
        if(project == null)
            return false;
        
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        IFile file = (IFile) project.findMember(path);
        if(file == null)
            return false;
        
        ICompilationUnit cu = JavaCore.createCompilationUnitFrom(file);
        if(cu == null)
            return false;
        
        parser.setResolveBindings(false);
        parser.setSource(cu);
        ASTNode n = parser.createAST(null);
        if(n == null)
            return false;
        
        env.setCurrent(ECJFactory.genericWrap(n));
        return true;
    }

}
