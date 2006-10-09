/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractStrategoOperatorRegistry implements IOperatorRegistry {

    Map<String, Primitive> registry;
    
    protected AbstractStrategoOperatorRegistry() {
        registry = new HashMap<String, Primitive>();
    }
    
    // FIXME kill - this is superflouse
    @Deprecated
    protected void add(String name, Primitive prim) {
        registry.put(name, prim);
    }

    protected void add(Primitive prim) {
        registry.put(prim.getName(), prim);
    }
    
    public Primitive get(String name) {
        return registry.get(name);
    }
}
