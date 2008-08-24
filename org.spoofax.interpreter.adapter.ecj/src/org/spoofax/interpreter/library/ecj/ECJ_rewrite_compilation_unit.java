package org.spoofax.interpreter.library.ecj;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.TextEdit;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.adapter.ecj.WrappedASTNode;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.CallT;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_rewrite_compilation_unit extends AbstractPrimitive {

	public ECJ_rewrite_compilation_unit() {
		super("ECJ_rewrite_compilation_unit", 1, 1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
	throws InterpreterException {

		if(!ECJTools.isICompilationUnit(tvars[0]))
			return false;

		final ICompilationUnit cu = ECJTools.asICompilationUnit(tvars[0]);
		final ECJLibrary ecj = (ECJLibrary) env.getOperatorRegistry(ECJLibrary.REGISTRY_NAME);

		ecj.setCurrentProject(cu.getJavaProject().getProject());
		ecj.setCurrentJavaProject(cu.getJavaProject());

		try {
			final IBuffer buffer = cu.getBuffer();
			final boolean previouslyModified = buffer.hasUnsavedChanges();

			Document document= new Document(buffer.getContents());
			ASTParser p = ecj.getParser();
			p.setSource(cu);
			CompilationUnit root = (CompilationUnit) p.createAST(null);

			//System.out.println("before: " + root);
			root.recordModifications();
			List newTds = new ArrayList();
			for(Object ob : root.types()) {
				TypeDeclaration td = (TypeDeclaration) ob;
				((ECJFactory)env.getFactory()).setAST(td.getAST());
				CallT s = (CallT)svars[0];
				env.setCurrent(ECJFactory.wrap(td));
				if(s.evaluate(env)) {
					final IStrategoTerm term = env.current();
					if(term instanceof WrappedASTNode)
						newTds.add(((WrappedASTNode)term).getWrappee());
					else 
						ecj.log("Rewriting resulted in an invalid tree");
				}
			}
			root.types().clear();
			root.types().addAll(newTds);
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
		return true;
	}

}
