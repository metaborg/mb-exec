/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.library;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.adapters.ecj.ECJFactory;
import org.spoofax.interpreter.adapters.ecj.WrappedProject;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_parse_and_resolve extends AbstractPrimitive {

    public ECJ_parse_and_resolve() {
        super("ECJ_parse_and_resolve", 0, 2);
    }
    @Override
    public boolean call(IContext env, List<IConstruct> svars, IStrategoTerm[] tvars)
            throws InterpreterException {

        if(!ECJTools.isProject(tvars[0]))
            return false;
        if(!Tools.isTermString(tvars[1]))
            return false;
        
        String path = ((IStrategoString)tvars[1]).getValue();
        
        IProject project = ((WrappedProject)tvars[0]).getWrappee();
        if(project == null)
            return false;
        
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        IFile file = (IFile) project.findMember(path);
        if(file == null)
            return false;
        
        ICompilationUnit cu = JavaCore.createCompilationUnitFrom(file);
        if(cu == null)
            return false;
        
        parser.setResolveBindings(true);
        parser.setSource(cu);
        ASTNode n = parser.createAST(null);
        if(n == null)
            return false;
        
        env.setCurrent(ECJFactory.genericWrap(n));
        return true;
    }

}
