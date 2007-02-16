/*
 * Created on 16. feb.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.library.xlet;

import java.util.HashMap;
import java.util.Map;

import org.spoofax.interpreter.Interpreter;
import org.spoofax.interpreter.library.AbstractStrategoOperatorRegistry;

public class SPXLibrary extends AbstractStrategoOperatorRegistry {

    public static final String REGISTRY_NAME = "SPX";

    Map<Integer, Interpreter> interpreterMap;
    
    public SPXLibrary() {
        interpreterMap = new HashMap<Integer, Interpreter>();
        init();
    }
    
    private void init() {
        add(new SPX_interpreter_load());
        add(new SPX_ui_popup());
    }
    
    public Interpreter getInterpreter(int idx) {
        return interpreterMap.get(idx);
    }

}
