/*
 * Created on 30.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.xlet;

import java.io.IOException;

import org.spoofax.interpreter.core.Interpreter;
import org.spoofax.interpreter.core.InterpreterException;

public class Instance {
    
    @SuppressWarnings("unused")
    private String libPath;
    @SuppressWarnings("unused")
    private String xletPath;
    
    protected Interpreter itp;
    
    public Instance(String libPath, String xletPath) throws IOException, InterpreterException {
        this.libPath = libPath;
        this.xletPath = xletPath;
        itp = new Interpreter();
        itp.load(libPath + "/spoofax.rtree-core");
    }

    public boolean loadXlet(String name) throws InterpreterException {
        itp.setCurrent(itp.getFactory().makeString(name));
        return itp.invoke("xlet_load_xlet_0_0");
    }
}
