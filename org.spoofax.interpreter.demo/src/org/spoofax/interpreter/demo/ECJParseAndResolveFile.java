package org.spoofax.interpreter.demo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.spoofax.interpreter.Interpreter;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.adapters.ecj.ECJFactory;
import org.spoofax.interpreter.terms.IStrategoTerm;

/*
 * Created on 27. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */

public class ECJParseAndResolveFile {

    static void parse(String prg, String fileName) throws FileNotFoundException, IOException, InterpreterException, CoreException {

        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();
        IProject project  = root.getProject("demo1");
        IFile file = (IFile) project.findMember(fileName);
        if (!project.isOpen()) project.open(null);
        ICompilationUnit cu = JavaCore.createCompilationUnitFrom(file);
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setResolveBindings(true);
        parser.setSource(cu);
        CompilationUnit n = (CompilationUnit)parser.createAST(null);

        ECJFactory wef = new ECJFactory();
        Interpreter itp = new Interpreter(wef);
        itp.addOperatorRegistry(ECJ.REGISTRY_NAME, new ECJ());
        itp.load(prg);
        IStrategoTerm t = wef.parseFromTree(n);
        itp.setCurrent(t);
        itp.invoke("main_0_0");

    }
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException, InterpreterException, CoreException {
       if(args.length > 1)
            parse(args[0], args[1]);
        else
            parse("str/parse-and-dump.rtree", args[0]);
    }
    
}
