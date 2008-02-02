/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ecj;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_create_compilation_unit_buffer extends ECJPrimitive {

    public ECJ_create_compilation_unit_buffer() {
        super("ECJ_create_compilation_unit_buffer", 0, 2);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {

        if (!Tools.isTermString(tvars[0]))
            return false;
        if (!ECJTools.isASTNode(tvars[1]))
            return false;

        IProject project = getLibrary(env).getCurrentProject();

        IFile file = project.getFile(Tools.asJavaString(tvars[0]));

        final String text = ECJTools.asASTNode(tvars[1]).toString();
        InputStream source = new ByteArrayInputStream(text.getBytes());
        try {
            if (!file.exists()) {
                file.create(source, IResource.NONE, null);
            } else {
                file.setContents(source, IFile.FORCE, null);
            }
        } catch (CoreException e) {
            e.printStackTrace();
            return false;
        }

        env.setCurrent(tvars[0]);
        return true;
    }

}
