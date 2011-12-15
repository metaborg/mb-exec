/*
 * Created on 9. okt. 2006
 *
 * Copyright (c) 2006-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.spoofax.interpreter.core.Interpreter;
import org.spoofax.interpreter.core.InterpreterException;

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

    protected static void attach(Interpreter intp, AbstractStrategoOperatorRegistry op, String ctreeFile) throws IOException, InterpreterException {
        InputStream ins = op.getClass().getClassLoader().getResourceAsStream(ctreeFile);
        if(ins == null) {
            throw new IOException("Failed to load internal library " + ctreeFile);
        }
        intp.load(ins);
        intp.addOperatorRegistry(op);
    }

}
