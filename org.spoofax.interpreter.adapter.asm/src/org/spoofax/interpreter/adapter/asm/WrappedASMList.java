package org.spoofax.interpreter.adapter.asm;

import java.util.ArrayList;
import java.util.List;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermPrinter;

public class WrappedASMList implements IStrategoList {

	private List<Object> wrappee;

	protected WrappedASMList(List<Object> wrappee) {
		this.wrappee = wrappee;
	}

	public IStrategoTerm get(int index) {
		return ASMFactory.genericWrap(wrappee.get(index));
	}

	public IStrategoTerm head() {
		return ASMFactory.genericWrap(wrappee.get(0));
	}

	public boolean isEmpty() {
		return wrappee.isEmpty();
	}

	public IStrategoList prepend(IStrategoTerm prefix) {
		List<Object> ret = new ArrayList<Object>(wrappee.size() + 1);
		ret.add(prefix);
		ret.addAll(wrappee);
		return new WrappedASMList(ret);
	}

	public int size() {
		return wrappee.size();
	}

	public IStrategoList tail() {
		List<Object> ret = new ArrayList<Object>(wrappee);
		ret.remove(ret.size() - 1);
		return new WrappedASMList(ret);
	}

	public IStrategoTerm[] getAllSubterms() {
		IStrategoTerm[] ret = new IStrategoTerm[wrappee.size()];
		int count = 0;
		for(Object o : wrappee) {
			ret[count++] = ASMFactory.genericWrap(o);
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
