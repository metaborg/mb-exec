package org.spoofax.interpreter.terms.aterm;

import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;

import aterm.ATerm;
import aterm.ATermAppl;

class WrappedATermString extends WrappedATerm implements IStrategoString {

    private final ATermAppl value;

    WrappedATermString(ATermAppl value) {
        this.value = value;
    }

    public String getValue() {
        return value.getName();
    }

    public IStrategoTerm getSubterm(int index) {
        return null;
    }

    public int getSubtermCount() {
        return 0;
    }

    public int getTermType() {
        return IStrategoTerm.STRING;
    }

    public boolean match(IStrategoTerm other) {
        return equals(other);
    }

    @Override
    public boolean equals(Object second) {
        if(second instanceof WrappedATerm) {
            if(second instanceof WrappedATermString)
                return ((WrappedATermString)second).value ==  value;
            return false;
        }
        return slowCompare(second);
    }

    @Override
    ATerm getATerm() {
        return value;
    }

    @Override
    public String toString() {
        return value.getName();
    }
}
