/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;

public class BasicStrategoTuple extends BasicStrategoTerm implements IStrategoTuple {

    protected final IStrategoTerm[] kids;
    
    protected BasicStrategoTuple(IStrategoTerm[] kids) {
        this.kids = kids;
    }
    
    public IStrategoTerm get(int index) {
        return kids[index];
    }

    public IStrategoTerm[] getAllSubterms() {
        IStrategoTerm[] r = new IStrategoTerm[kids.length];
        for(int i=0; i<kids.length; i++)
            r[i] = kids[i];
        return r;
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

    protected boolean doSlowMatch(IStrategoTerm second) {
        if(second.getTermType() != IStrategoTerm.TUPLE)
            return false;

        IStrategoTuple snd = (IStrategoTuple) second;
        if(size() != snd.size())
            return false;
        IStrategoTerm[] otherkids = snd.getAllSubterms(); 
        for(int i = 0, sz = kids.length; i < sz; i++) {
            if(!kids[i].match(otherkids[i]))
                return false;
        }
        return true;
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
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        if(kids.length > 0) {
            sb.append(kids[0].toString());
            for(int i = 1; i < kids.length; i++) {
                sb.append(",");
                sb.append(kids[i].toString());
            }
        }
        sb.append(")");
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        long hc = 4831;
        for(int i=0; i<kids.length;i++) {
            hc *= kids[i].hashCode();
        }
        return (int)(hc >> 10);
    }

}
