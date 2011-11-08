package org.spoofax.interpreter.library.ecj;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

public class ECJTest {

    public static void main(String[] args) throws Exception {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();
        IProject project  = root.getProject("MyProject");
        IFolder folder = project.getFolder("Folder1");
        IFile file = folder.getFile("hello.txt");
        //at this point, no resources have been created
        if (!project.exists()) project.create(null);
        if (!project.isOpen()) project.open(null);
        if (!folder.exists()) 
           folder.create(IResource.NONE, true, null);
        if (!file.exists()) {
           byte[] bytes = "File contents".getBytes();
           InputStream source = new ByteArrayInputStream(bytes);
           file.create(source, IResource.NONE, null);
        }
        ICompilationUnit cu = JavaCore.createCompilationUnitFrom(file);
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setSource(cu);
        
    }
}
