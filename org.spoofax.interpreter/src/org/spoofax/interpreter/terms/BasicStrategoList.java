/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;

public class BasicStrategoList extends BasicStrategoTerm implements IStrategoList {

    protected final IStrategoTerm[] kids;
    
    protected BasicStrategoList(IStrategoTerm[] kids) {
        this.kids = kids;
    }
    
    public IStrategoTerm head() {
        return kids[0];
    }
    
    public boolean isEmpty() {
        return kids.length == 0;
    }
    
    public IStrategoList tail() {
        return new BasicStrategoList(doTail());
    }
    
    protected IStrategoTerm[] doTail() {
        IStrategoTerm[] r = new IStrategoTerm[kids.length - 1];
        for(int i = 1, sz = kids.length; i < sz; i++) {
            r[i - 1] = kids[i];
        }
        return r;
    }
    
    public IStrategoList prepend(IStrategoTerm prefix) {
        return new BasicStrategoList(doPrepend(prefix));
    }
    
    protected IStrategoTerm[] doPrepend(IStrategoTerm prefix) {
        IStrategoTerm[] r = new IStrategoTerm[kids.length + 1];
        r[0] = prefix;
        for(int i = 0, sz = kids.length; i < sz; i++)
            r[i + 1] = kids[i];
        return r;
    }
    
    public IStrategoTerm get(int index) {
        return kids[index];
    }
    
    public IStrategoTerm[] getAllSubterms() {
        IStrategoTerm[] clone = new IStrategoTerm[kids.length];
        for(int i = 0; i < kids.length; i++)
            clone[i] = kids[i];
        return clone;
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
        return IStrategoTerm.LIST;
    }

    @Override
    protected boolean doSlowMatch(IStrategoTerm second) {
        if(second.getTermType() != IStrategoTerm.LIST)
            return false;
        
        IStrategoList snd = (IStrategoList) second;
        if(size() != snd.size())
            return false;
        IStrategoTerm[] otherkids = second.getAllSubterms();
        for(int i = 0, sz = size(); i < sz; i++) {
            if(!kids[i].match(otherkids[i]))
                return false;
        }
        return true;
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
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if(kids.length > 0) {
            sb.append(kids[0].toString());
            for(int i=1; i<kids.length; i++) {
                sb.append(",");
                sb.append(kids[i].toString());
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
