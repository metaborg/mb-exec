/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;

public abstract class BasicStrategoTuple implements IStrategoTuple {

    protected final IStrategoTerm[] kids;
    
    protected BasicStrategoTuple(IStrategoTerm[] kids) {
        this.kids = kids;
    }
    
    public IStrategoTerm get(int index) {
        return kids[index];
    }

    public IStrategoTerm[] getAllSubterms() {
        return kids;
    }
    
    public int size() {
        return kids.length;
    }

    public IStrategoTerm getSubterm(int index) {
        return kids[index];
    }

    public int getSubtermCount() {
        return kids.length;
    }

    public int getTermType() {
        return IStrategoTerm.TUPLE;
    }

    public abstract boolean match(IStrategoTerm second);
    
    protected boolean doSlowMatch(IStrategoTerm second) {
        if(second instanceof BasicStrategoTuple) {
            BasicStrategoTuple snd = (BasicStrategoTuple) second;
            if(kids.length != snd.kids.length)
                return false;
            for(int i = 0, sz = kids.length; i < sz; i++)
                if(!kids[i].match(snd.kids[i]))
                    return false;
            return true;
        } else if(second instanceof IStrategoTuple) {
            IStrategoTuple snd = (IStrategoTuple) second;
            if(size() != snd.size())
                return false;
            for(int i = 0, sz = size(); i < sz; i++) {
                if(!kids[i].match(second.getSubterm(i)))
                    return false;
            }
            return true;
        }
        return false;
    }

    public void prettyPrint(ITermPrinter pp) {
        int sz = size();
        if(sz > 0) {
            pp.println("(");
            pp.indent(2);
            get(0).prettyPrint(pp);
            for(int i = 1; i < sz; i++) {
                pp.print(", ");
                pp.nextIndentOff();
                get(i).prettyPrint(pp);
                pp.println("");
            }
            pp.println("");
            pp.print(")");
            pp.outdent(2);

        } else {
            pp.print("()");
        }
    }
}
