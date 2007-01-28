/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ecj;

import java.io.IOException;
import java.io.InputStream;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.spoofax.interpreter.Interpreter;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class TestECJAdapter extends TestCase {
    
    protected InputStream findFile(String name) throws CoreException {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();
        IProject project  = root.getProject("demo1");
        project.open(null);
        project.refreshLocal(IProject.DEPTH_INFINITE, null);
        IFile file = (IFile) project.findMember("str/" + name + ".rtree");
        return file.getContents();
    }
    
    public void testTest1() throws IOException, InterpreterException, CoreException {
        interpTest("test1", "()", "()");
    }

    public void testTest2() throws IOException, InterpreterException, CoreException {
        interpTest("test2", "\"src/org/spoofax/interpreter/demo/ECJ.java\"", "()");
    }

    public void testTest3() throws IOException, InterpreterException, CoreException {
        interpTest("test3", "()", "()");
    }

    public void testTest4() throws IOException, InterpreterException, CoreException {
        interpTest("test4", "\"HelloWorld.java\"", "()");
    }

    public void testTest5() throws IOException, InterpreterException, CoreException {
        interpTest("test5", "\"HelloWorld.java\"", "()");
    }

    public void testTest6() throws IOException, InterpreterException, CoreException {
        interpTest("test6", "\"HelloWorld.java\"", "()");
    }

    public void testSWTCheck() throws IOException, InterpreterException, CoreException {
        interpTest("swt-check", "\"examples/SWTTest.java\"", "()");
    }

    public void testArrayFields() throws IOException, InterpreterException, CoreException {
        interpTest("arrayfields-check", "\"examples/FieldTest.java\"", "()");
    }

    public void testFor() throws IOException, InterpreterException, CoreException {
        interpTest("for-check", "\"examples/ForTest.java\"", "()");
    }

    protected void interpTestFail(String string, String in) throws IOException, InterpreterException {
        ECJFactory f = new ECJFactory();
        Interpreter itp = new Interpreter(f);
        itp.addOperatorRegistry(ECJLibrary.REGISTRY_NAME, new ECJLibrary());
        itp.load("str/" + string + ".rtree");
        IStrategoTerm inTerm = f.parseFromString(in);
        itp.setCurrent(inTerm);
        assertTrue(!itp.invoke("main_0_0"));
    }

    private void interpTest(String string, String in, String out) throws IOException, InterpreterException, CoreException {
        ECJFactory f = new ECJFactory();
        Interpreter itp = new Interpreter(f);
        //DebugUtil.debugging = true;
        itp.addOperatorRegistry(ECJLibrary.REGISTRY_NAME, new ECJLibrary());
        itp.load(findFile(string));
        IStrategoTerm inTerm = f.parseFromString(in);
        IStrategoTerm outTerm = f.parseFromString(out);
        itp.setCurrent(inTerm);
        assertTrue(itp.invoke("main_0_0"));
        System.err.println("Want: " + outTerm);
        System.err.println("Got : " + itp.current());
        assertTrue(itp.current().match(outTerm));
    }
}
