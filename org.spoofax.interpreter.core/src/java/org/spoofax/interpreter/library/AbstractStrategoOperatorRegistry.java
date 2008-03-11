/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractStrategoOperatorRegistry implements IOperatorRegistry {

    protected Map<String, AbstractPrimitive> registry;
    
    protected AbstractStrategoOperatorRegistry() {
        registry = new HashMap<String, AbstractPrimitive>();
    }
    
    // FIXME kill - this is superflouse
    @Deprecated
    protected void add(String name, AbstractPrimitive prim) {
        registry.put(name, prim);
    }

    protected void add(AbstractPrimitive prim) {
        registry.put(prim.getName(), prim);
    }
    
    public AbstractPrimitive get(String name) {
        return registry.get(name);
    }
}
