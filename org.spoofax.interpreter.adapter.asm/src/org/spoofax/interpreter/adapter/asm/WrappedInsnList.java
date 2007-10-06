package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.InsnList;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermPrinter;

public class WrappedInsnList implements IStrategoList {

	private InsnList wrappee;

	protected WrappedInsnList(InsnList wrappee) {
		this.wrappee = wrappee;
	}

	public IStrategoTerm get(int index) {
		return ASMFactory.genericWrap(wrappee.get(index));
	}

	public IStrategoTerm head() {
		return ASMFactory.genericWrap(wrappee.get(0));
	}

	public boolean isEmpty() {
		return wrappee.size() == 0;
	}

	public IStrategoList prepend(IStrategoTerm prefix) {
		throw new NotImplementedException();
	}

	public int size() {
		return wrappee.size();
	}

	public IStrategoList tail() {
		throw new NotImplementedException();
	}

	public IStrategoTerm[] getAllSubterms() {
		IStrategoTerm[] ret = new IStrategoTerm[wrappee.size()];
		for(int i = 0; i < wrappee.size(); i++) {
			ret[i] = ASMFactory.genericWrap(wrappee.get(i));
		}
		return ret;
	}

	public IStrategoTerm getSubterm(int index) {
		return get(index);
	}

	public int getSubtermCount() {
		return size();
	}

	public int getTermType() {
		return IStrategoTerm.LIST;
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
