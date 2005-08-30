/*
 * Created on 30.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.xlet;

import java.io.IOException;

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.Interpreter;

public class Instance {
    
    private String libPath;
    private String xletPath;
    
    protected Interpreter itp;
    
    public Instance(String libPath, String xletPath) throws IOException, FatalError {
        this.libPath = libPath;
        this.xletPath = xletPath;
        itp = new Interpreter();
        itp.load(libPath + "/spoofax.rtree.core");
    }

    public boolean loadXlet(String name) throws FatalError {
        itp.setCurrent(itp.makeString(name));
        return itp.invoke("xlet-load-xlet");
    }
}
