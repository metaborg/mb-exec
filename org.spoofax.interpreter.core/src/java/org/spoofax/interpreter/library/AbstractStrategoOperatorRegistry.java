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

    private final Map<String, AbstractPrimitive> registry;
    
    protected AbstractStrategoOperatorRegistry() {
        this(16);
    }
    
    protected AbstractStrategoOperatorRegistry(int initialCapacity) {
        registry = new HashMap<String, AbstractPrimitive>(initialCapacity);
    }

    @Deprecated
    protected Map<String, AbstractPrimitive> getRegistry() {
        return registry;
    }
    
    // FIXME kill - this is superflouse
    @Deprecated
    protected void add(String name, AbstractPrimitive prim) {
        registry.put(name, prim);
    }

    public void add(AbstractPrimitive prim) {
        registry.put(prim.getName(), prim);
    }
    
    public AbstractPrimitive get(String name) {
        return registry.get(name);
    }
}
