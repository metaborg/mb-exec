package org.spoofax.interpreter.adapter.asm;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermPrinter;

public class WrappedASMArray implements IStrategoList {

	private final Object[] wrappee;

	public WrappedASMArray(Object[] wrappee) {
		this.wrappee = wrappee;
	}

	public IStrategoTerm get(int index) {
		return ASMFactory.genericWrap(wrappee[index]);
	}

	public IStrategoTerm head() {
		throw new NotImplementedException();
	}

	public boolean isEmpty() {
		return wrappee.length == 0;
	}

	public IStrategoList prepend(IStrategoTerm prefix) {
		throw new NotImplementedException();
	}

	public int size() {
		return wrappee.length;
	}

	public IStrategoList tail() {
		throw new NotImplementedException();
	}

	public IStrategoTerm[] getAllSubterms() {
		IStrategoTerm[] ret = new IStrategoTerm[wrappee.length];
		for(int i = 0; i < ret.length; i++)
			ret[i] = ASMFactory.genericWrap(wrappee[i]);
		return ret;
	}

	public IStrategoTerm getSubterm(int index) {
		return ASMFactory.genericWrap(wrappee[index]);
	}

	public int getSubtermCount() {
		return wrappee.length;
	}

	public int getTermType() {
		return IStrategoList.LIST;
	}

	public boolean match(IStrategoTerm second) {
		throw new NotImplementedException();
	}

    public void prettyPrint(ITermPrinter pp) {
        int sz = size();
        if(sz > 0) {
            pp.println("[");
            pp.indent(2);
            get(0).prettyPrint(pp);
            for(int i = 1; i < sz; i++) {
                pp.print(", ");
                pp.nextIndentOff();
                get(i).prettyPrint(pp);
                pp.println("");
            }
            pp.println("");
            pp.print("]");
            pp.outdent(2);

        } else {
            pp.print("[]");
        }
    }

}
