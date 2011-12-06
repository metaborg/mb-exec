/*
 * Copyright (c) 2006-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1 
 */

package org.spoofax.interpreter.library.ecj;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.launching.JavaRuntime;

public class ProjectUtils {

	protected NullProgressMonitor nullProgress = new NullProgressMonitor();
	protected ASTParser astParser = ASTParser.newParser(AST.JLS3);
	private int counter = 0;

	protected ICompilationUnit addCompilationUnit(IProject project, final String fileName, final String cu)
	throws CoreException {
		final char[] chs = new char[cu.length()];
		cu.getChars(0, chs.length, chs, 0);

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(cu.getBytes()); 
		IFile file = project.getFile(new Path(fileName));
		file.create(byteArrayInputStream, true, nullProgress);
		if(!file.exists())
			return null;

		return (ICompilationUnit) JavaCore.create(file);
	}

	public ICompilationUnit createCompilationUnits(IJavaProject javaProject, String[] compilationUnits) throws CoreException {
		ICompilationUnit icu = null;
		for(String cu : compilationUnits) {
			ICompilationUnit c = addCompilationUnit(javaProject.getProject(), "Test" + counter + ".java", cu);
			counter++;
			if(icu == null)
				icu = c;
		}

		return icu;
	}

	public IJavaProject createDummyJavaProject() throws CoreException {
		
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = root.getProject("DummyProject");
		project.delete(true, nullProgress);
		project.create(nullProgress);
		project.open(nullProgress);
		
		IProjectDescription description = project.getDescription();
		String[] natures = description.getNatureIds();
		String[] newNatures = new String[natures.length + 1];
		System.arraycopy(natures, 0, newNatures, 0, natures.length);
		newNatures[natures.length] = JavaCore.NATURE_ID;
		description.setNatureIds(newNatures);
		project.setDescription(description, nullProgress);
		
		IJavaProject javaProject = JavaCore.create(project);
		addClasspathEntry(javaProject, JavaRuntime.getDefaultJREContainerEntry());
		
		return javaProject;
	}

	public void addClasspathEntry(IJavaProject javaProject, IClasspathEntry e) throws JavaModelException {
		Set<IClasspathEntry> entries = new HashSet<IClasspathEntry>();
		entries.addAll(Arrays.asList(javaProject.getRawClasspath()));
		entries.add(e);
		javaProject.setRawClasspath(entries.toArray(new IClasspathEntry[entries.size()]), nullProgress);
	}

	public ASTParser getParser() {
		return astParser;
	}

	public IProgressMonitor getMonitor() {
		return nullProgress;
	}

	public IJavaElement createPackage(IJavaProject javaProject, String packageName) throws CoreException {
		
		IProject project = javaProject.getProject();
		IContainer container = project;
		if(!packageName.equals("")) {
			String[] xs = packageName.split("\\.");
			for(int i = 0; i < xs.length; i++) {
				container = container.getFolder(new Path(xs[i]));	
				if(!container.exists()) {
					((IFolder)container).create(true, true, nullProgress);
				}
			}
		}
		return JavaCore.create(container);
		
	}

	public ICompilationUnit createCompilationUnit(IProject project,
			String fileName, String source) throws CoreException {
		return addCompilationUnit(project, fileName, source);
	}

}
