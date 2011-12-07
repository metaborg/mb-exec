/*
 * Copyright (c) 2007-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.library.ecj;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.TextEdit;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.adapter.ecj.WrappedASTNode;
import org.spoofax.interpreter.adapter.ecj.WrappedImportDeclaration;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.CallT;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_rewrite_file extends AbstractPrimitive {

	public ECJ_rewrite_file() {
		super("ECJ_rewrite_file", 2, 1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
	throws InterpreterException {

		if(!ECJTools.isIFile(tvars[0]))
			return false;

		final IFile file = ECJTools.asIFile(tvars[0]);
		final ICompilationUnit cu = JavaCore.createCompilationUnitFrom(file);
		final ECJLibrary ecj = (ECJLibrary) env.getOperatorRegistry(ECJLibrary.REGISTRY_NAME);
		final ECJFactory factory = (ECJFactory)env.getFactory();

		AST oldAST = factory.getAST();

		ecj.setCurrentProject(cu.getJavaProject().getProject());
		ecj.setCurrentJavaProject(cu.getJavaProject());

		try {
			final IBuffer buffer = cu.getBuffer();
			final boolean previouslyModified = buffer.hasUnsavedChanges();

			Document document= new Document(buffer.getContents());
			ASTParser p = ecj.getParser();
			p.setSource(cu);
			CompilationUnit root = (CompilationUnit) p.createAST(null);
			factory.setAST(root.getAST());

			//System.out.println("before: " + root);
			root.recordModifications();
			List newTds = new ArrayList();
			for(Object ob : root.types()) {
				TypeDeclaration td = (TypeDeclaration) ob;

				CallT s = (CallT)svars[0];
				env.setCurrent(ECJFactory.wrap(td));
				if(s.evaluate(env)) {
					final IStrategoTerm term = env.current();
					if(term instanceof WrappedASTNode)
						newTds.add(((WrappedASTNode)term).getWrappee());
					else 
						ecj.log("Rewriting types resulted in an invalid tree");
				} else {
					newTds.add(td);
				}
			}
			root.types().clear();
			root.types().addAll(newTds);
			
			List newImports = new ArrayList();
			env.setCurrent(ECJFactory.wrap(root.imports()));
			CallT s = (CallT)svars[1];
			if(s.evaluate(env)) {
				final IStrategoTerm term = env.current();
				if(term instanceof IStrategoList) {
					for(IStrategoTerm t : ((IStrategoList)term).getAllSubterms()) {
						if(t instanceof WrappedImportDeclaration) {
							ImportDeclaration id = ((WrappedImportDeclaration)t).getWrappee();
							// FIXME should this be handled elsewhere?
							if(id.getAST() != root.getAST()) {
								id = (ImportDeclaration) ASTNode.copySubtree(root.getAST(), id);
							}
							newImports.add(id);
						} else {
							ecj.log("Rewriting import did not give an ImportDeclaration");
						}
					}
				} else {
					ecj.log("Rewriting imports did not give a list");
				}
			}
			root.imports().clear();
			root.imports().addAll(newImports);
			
			//System.out.println("after: " + root);
			TextEdit te = root.rewrite(document, cu.getJavaProject().getOptions(true));
			te.apply(document);
			cu.getBuffer().setContents(document.get());
			if(!previouslyModified)
				cu.getBuffer().save(null, false);
		}
		catch(JavaModelException e) {
			e.printStackTrace();
			ecj.log("Model exception");
		}
		catch(BadLocationException e) {
			e.printStackTrace();
			ecj.log("Bad location exception");
		}
		factory.setAST(oldAST);
		return true;
	}

}
