/*
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.core;

import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.ITermFactory;

// FIXME should be moved to the interpreter
public class StrategoSignature {

    private final IStrategoConstructor CTOR_Match;

    private final IStrategoConstructor CTOR_Op;

    private final IStrategoConstructor CTOR_Cons;

    private final IStrategoConstructor CTOR_Nil;

    private final IStrategoConstructor CTOR_Anno;

    private final IStrategoConstructor CTOR_Int;

    private final IStrategoConstructor CTOR_Real;

    private final IStrategoConstructor CTOR_Str;

    private final IStrategoConstructor CTOR_Var;

    private final IStrategoConstructor CTOR_Explode;

    private final IStrategoConstructor CTOR_ConstType;

    private final IStrategoConstructor CTOR_FunType;

    private final IStrategoConstructor CTOR_ExtSDef;

    private final IStrategoConstructor CTOR_SDefT;

    private final IStrategoConstructor CTOR_AnnoDef;

    private final IStrategoConstructor CTOR_As;

    private final IStrategoConstructor CTOR_Wld;

    private final IStrategoConstructor CTOR_Build;

    private final IStrategoConstructor CTOR_Scope;

    private final IStrategoConstructor CTOR_Seq;

    private final IStrategoConstructor CTOR_GuardedLChoice;

    private final IStrategoConstructor CTOR_Id;

    private final IStrategoConstructor CTOR_CallT;

    private final IStrategoConstructor CTOR_PrimT;

    private final IStrategoConstructor CTOR_Let;

    private final IStrategoConstructor CTOR_Fail;

    private final IStrategoConstructor CTOR_All;

    private final IStrategoConstructor CTOR_One;

    private final IStrategoConstructor CTOR_Some;

    private final IStrategoConstructor CTOR_ImportTerm;

    public StrategoSignature(ITermFactory factory) {

        CTOR_Op = factory.makeConstructor("Op", 2);
        CTOR_Cons = factory.makeConstructor("Cons", 2);
        CTOR_Nil = factory.makeConstructor("Nil", 0);
        CTOR_Anno = factory.makeConstructor("Anno", 2);
        CTOR_Int = factory.makeConstructor("Int", 1);
        CTOR_Real = factory.makeConstructor("Real", 1);
        CTOR_Str = factory.makeConstructor("Str", 1);
        CTOR_Var = factory.makeConstructor("Var", 1);
        CTOR_Explode = factory.makeConstructor("Explode", 2);
        CTOR_ConstType = factory.makeConstructor("ConstType", 1);// todo
        CTOR_FunType = factory.makeConstructor("FunType", 2);// todo
        CTOR_SDefT = factory.makeConstructor("SDefT", 4);// todo
        CTOR_AnnoDef = factory.makeConstructor("AnnoDef", 2);
        CTOR_As = factory.makeConstructor("As", 2);// todo
        CTOR_Wld = factory.makeConstructor("Wld", 0);// todo
        CTOR_Build = factory.makeConstructor("Build", 1);
        CTOR_ExtSDef = factory.makeConstructor("ExtSDefT", 3);
        CTOR_Scope = factory.makeConstructor("Scope", 2);
        CTOR_Seq = factory.makeConstructor("Seq", 2);

        CTOR_GuardedLChoice = factory.makeConstructor("GuardedLChoice", 3);
        CTOR_CallT = factory.makeConstructor("CallT", 3);
        CTOR_Fail = factory.makeConstructor("Fail", 0);
        CTOR_Id = factory.makeConstructor("Id", 0);
        CTOR_Let = factory.makeConstructor("Let", 2);
        CTOR_Match = factory.makeConstructor("Match", 1);
        CTOR_PrimT = factory.makeConstructor("PrimT", 3);

        CTOR_ImportTerm = factory.makeConstructor("ImportTerm", 1);

        CTOR_All = factory.makeConstructor("All", 1);
        CTOR_Some = factory.makeConstructor("Some", 1);
        CTOR_One = factory.makeConstructor("One", 1);

    }

    public IStrategoConstructor getOp() {
        return CTOR_Op;
    }

    public IStrategoConstructor getCons() {
        return CTOR_Cons;
    }

    public IStrategoConstructor getNil() {
        return CTOR_Nil;
    }

    public IStrategoConstructor getAnno() {
        return CTOR_Anno;
    }

    public IStrategoConstructor getStr() {
        return CTOR_Str;
    }

    public IStrategoConstructor getVar() {
        return CTOR_Var;
    }

    public IStrategoConstructor getExplode() {
        return CTOR_Explode;
    }

    public IStrategoConstructor getReal() {
        return CTOR_Real;
    }

    public IStrategoConstructor getInt() {
        return CTOR_Int;
    }

    public IStrategoConstructor getConstType() {
        return CTOR_ConstType;
    }

    public IStrategoConstructor getFunType() {
        return CTOR_FunType;
    }

    public IStrategoConstructor getExtSDef() {
        return CTOR_ExtSDef;
    }

    public IStrategoConstructor getSDefT() {
        return CTOR_SDefT;
    }

    public IStrategoConstructor getAnnoDef() {
        return CTOR_AnnoDef;
    }

    public IStrategoConstructor getAs() {
        return CTOR_As;
    }

    public IStrategoConstructor getWld() {
        return CTOR_Wld;
    }

    public IStrategoConstructor getBuild() {
        return CTOR_Build;
    }

    public IStrategoConstructor getScope() {
        return CTOR_Scope;
    }

    public IStrategoConstructor getSeq() {
        return CTOR_Seq;
    }

    public IStrategoConstructor getOpAFun() {
        return CTOR_Op;
    }

    public IStrategoConstructor getGuardedLChoice() {
        return CTOR_GuardedLChoice;
    }

    public IStrategoConstructor getMatch() {
        return CTOR_Match;
    }

    public IStrategoConstructor getId() {
        return CTOR_Id;
    }

    public IStrategoConstructor getCallT() {
        return CTOR_CallT;
    }

    public IStrategoConstructor getPrimT() {
        return CTOR_PrimT;
    }

    public IStrategoConstructor getLet() {
        return CTOR_Let;
    }

    public IStrategoConstructor getFail() {
        return CTOR_Fail;
    }

    public IStrategoConstructor getAll() {
        return CTOR_All;
    }

    public IStrategoConstructor getOne() {
        return CTOR_One;
    }

    public IStrategoConstructor getSome() {
        return CTOR_Some;
    }

	public Object getImportTerm() {
		return CTOR_ImportTerm;
	}

}
