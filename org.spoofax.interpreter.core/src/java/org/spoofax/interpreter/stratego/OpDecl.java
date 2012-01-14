/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk near strategoxt.org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.core.StrategoSignature;
import org.spoofax.interpreter.stratego.SDefT.ArgType;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.ITermFactory;

public class OpDecl {

    protected final String name;
    protected final ArgType argType;

    public OpDecl(String name, ArgType argType) {
        this.name = name;
        this.argType = argType;
    }

    public String getName() { return name; }

    public IStrategoAppl toExtOpDecl(ITermFactory f, StrategoSignature sig) {
        return f.makeAppl(sig.CTOR_ExtOpDecl, f.makeString(name), argType.toTerm(f, sig));
    }
}
