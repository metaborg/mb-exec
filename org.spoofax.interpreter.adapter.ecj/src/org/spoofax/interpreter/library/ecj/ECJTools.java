/*
 * Created on 10. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.dom.ASTNode;
import org.spoofax.interpreter.adapter.ecj.WrappedASTNode;
import org.spoofax.interpreter.adapter.ecj.WrappedICompilationUnit;
import org.spoofax.interpreter.adapter.ecj.WrappedIFile;
import org.spoofax.interpreter.adapter.ecj.WrappedIJavaElement;
import org.spoofax.interpreter.adapter.ecj.WrappedIJavaProject;
import org.spoofax.interpreter.adapter.ecj.WrappedIProject;
import org.spoofax.interpreter.adapter.ecj.WrappedIType;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJTools {

    public static boolean isProject(IStrategoTerm t) {
        return t instanceof WrappedIProject;
    }

    public static boolean isIJavaElement(IStrategoTerm term) {
        return term instanceof WrappedIJavaElement;
    }

    public static boolean isIJavaProject(IStrategoTerm term) {
        return term instanceof WrappedIJavaProject;
    }

    public static IJavaElement asIJavaElement(IStrategoTerm term) {
        return ((WrappedIJavaElement)term).getWrappee();
    }

    public static IProject asIProject(IStrategoTerm term) {
        return ((WrappedIProject)term).getWrappee();
    }

    public static IJavaProject asIJavaProject(IStrategoTerm term) {
        return ((WrappedIJavaProject)term).getWrappee();
    }

    public static boolean isIType(IStrategoTerm term) {
        return term instanceof WrappedIType;
    }

    public static IType asIType(IStrategoTerm term) {
        return ((WrappedIType)term).getWrappee();
    }

    public static boolean isICompilationUnit(IStrategoTerm term) {
        return term instanceof WrappedICompilationUnit;
    }

    public static ICompilationUnit asICompilationUnit(IStrategoTerm term) {
        return ((WrappedICompilationUnit)term).getWrappee();
    }

    public static boolean isASTNode(IStrategoTerm term) {
        return term instanceof WrappedASTNode;
    }

    public static ASTNode asASTNode(IStrategoTerm term) {
        return ((WrappedASTNode)term).getWrappee();
    }

    public static boolean isIFile(IStrategoTerm term) {
        return term instanceof WrappedIFile;
    }

    public static IFile asIFile(IStrategoTerm term) {
        return ((WrappedIFile)term).getWrappee();
    }

}
