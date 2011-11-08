/*
 * Created on 10. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.Type;
import org.spoofax.interpreter.adapter.ecj.ECJAnnoWrapper;
import org.spoofax.interpreter.adapter.ecj.WrappedASTNode;
import org.spoofax.interpreter.adapter.ecj.WrappedAbstractTypeDeclaration;
import org.spoofax.interpreter.adapter.ecj.WrappedCompilationUnit;
import org.spoofax.interpreter.adapter.ecj.WrappedICompilationUnit;
import org.spoofax.interpreter.adapter.ecj.WrappedIFile;
import org.spoofax.interpreter.adapter.ecj.WrappedIJavaElement;
import org.spoofax.interpreter.adapter.ecj.WrappedIJavaProject;
import org.spoofax.interpreter.adapter.ecj.WrappedIProject;
import org.spoofax.interpreter.adapter.ecj.WrappedIType;
import org.spoofax.interpreter.adapter.ecj.WrappedITypeBinding;
import org.spoofax.interpreter.adapter.ecj.WrappedName;
import org.spoofax.interpreter.adapter.ecj.WrappedType;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJTools {

    public static boolean isProject(IStrategoTerm t) {
        return unannotate(t) instanceof WrappedIProject;
    }

    private static IStrategoTerm unannotate(IStrategoTerm t) {
    	if(t instanceof ECJAnnoWrapper) 
    		return ((ECJAnnoWrapper)t).getWrappee();
    	return t;
	}

	public static boolean isIJavaElement(IStrategoTerm term) {
        return unannotate(term) instanceof WrappedIJavaElement;
    }

    public static boolean isIJavaProject(IStrategoTerm term) {
        return unannotate(term) instanceof WrappedIJavaProject;
    }

    public static IJavaElement asIJavaElement(IStrategoTerm term) {
        return ((WrappedIJavaElement)unannotate(term)).getWrappee();
    }

    public static IProject asIProject(IStrategoTerm term) {
        return ((WrappedIProject)unannotate(term)).getWrappee();
    }

    public static IJavaProject asIJavaProject(IStrategoTerm term) {
        return ((WrappedIJavaProject)unannotate(term)).getWrappee();
    }

    public static boolean isIType(IStrategoTerm term) {
        return unannotate(term) instanceof WrappedIType;
    }

    public static IType asIType(IStrategoTerm term) {
        return ((WrappedIType)term).getWrappee();
    }

    public static boolean isICompilationUnit(IStrategoTerm term) {
        return unannotate(term) instanceof WrappedICompilationUnit;
    }

    public static ICompilationUnit asICompilationUnit(IStrategoTerm term) {
        return ((WrappedICompilationUnit)unannotate(term)).getWrappee();
    }

    public static boolean isASTNode(IStrategoTerm term) {
        return unannotate(term) instanceof WrappedASTNode;
    }

    public static ASTNode asASTNode(IStrategoTerm term) {
        return ((WrappedASTNode)unannotate(term)).getWrappee();
    }

    public static boolean isIFile(IStrategoTerm term) {
        return unannotate(term) instanceof WrappedIFile;
    }

    public static IFile asIFile(IStrategoTerm term) {
        return ((WrappedIFile)unannotate(term)).getWrappee();
    }

    public static boolean isCompilationUnit(IStrategoTerm term) {
        return unannotate(term) instanceof WrappedCompilationUnit;
    }

    public static CompilationUnit asCompilationUnit(IStrategoTerm term) {
        return ((WrappedCompilationUnit)unannotate(term)).getWrappee();
    }

    public static boolean isName(IStrategoTerm term) {
        return unannotate(term) instanceof WrappedName;
    }

    public static Name asName(IStrategoTerm term) {
        return ((WrappedName)unannotate(term)).getWrappee();
    }

	public static boolean isITypeBinding(IStrategoTerm term) {
		return unannotate(term) instanceof WrappedITypeBinding;
	}

	public static ITypeBinding asITypeBinding(IStrategoTerm term) {
		return ((WrappedITypeBinding)unannotate(term)).getWrappee();
	}

	public static AbstractTypeDeclaration asAbstractTypeDeclaration(
			IStrategoTerm term) {
		return ((WrappedAbstractTypeDeclaration)unannotate(term)).getWrappee();
	}

	public static boolean isAbstractTypeDeclaration(IStrategoTerm term) {
		return unannotate(term) instanceof WrappedAbstractTypeDeclaration;
	}

	public static boolean isType(IStrategoTerm term) {
		return unannotate(term) instanceof WrappedType;
	}

	public static Type asType(IStrategoTerm term) {
		return ((WrappedType)unannotate(term)).getWrappee();
	}

}
