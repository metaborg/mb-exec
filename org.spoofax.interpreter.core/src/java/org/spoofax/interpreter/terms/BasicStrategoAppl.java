/*
 * Created on 28. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;


public class BasicStrategoAppl extends BasicStrategoTerm implements IStrategoAppl {

    private final IStrategoTerm[] kids;
    private final IStrategoConstructor ctor;
    
    protected BasicStrategoAppl(IStrategoConstructor ctor, IStrategoTerm[] kids, IStrategoList annotations) {
        super(annotations);
        this.ctor = ctor;
        this.kids = kids;
    }
    
    public BasicStrategoAppl(IStrategoConstructor ctor, IStrategoTerm[] kids) {
        this(ctor, kids, null);
    }
    
    public IStrategoTerm[] getArguments() {
        return kids;
    }

    public IStrategoConstructor getConstructor() {
        return ctor;
    }

    public IStrategoTerm[] getAllSubterms() {
        IStrategoTerm[] cloned = new IStrategoTerm[kids.length];
        for(int i = 0; i < cloned.length; i++)
            cloned[i] = kids[i];
        return cloned;
    }

    public IStrategoTerm getSubterm(int index) {
        return kids[index];
    }

    public int getSubtermCount() {
        return kids.length;
    }

    public int getTermType() {
        return IStrategoTerm.APPL;
    }

    @Override
    protected boolean doSlowMatch(IStrategoTerm second) {
        if(second.getTermType() != IStrategoTerm.APPL)
            return false;
        IStrategoAppl o = (IStrategoAppl)second;
        if(!ctor.match(o.getConstructor()))
            return false;
        if (!getAnnotations().match(second.getAnnotations()))
            return false;
        if (o.getSubtermCount() != getSubtermCount())
            return false;
        IStrategoTerm[] otherkids = o.getArguments();
        for(int i = 0, sz = kids.length; i < sz; i++) {
            if(!kids[i].match(otherkids[i])) {
                return false;
            }
        }
        return true;
    }

    public void prettyPrint(ITermPrinter pp) {
        pp.print(ctor.getName());
        if(kids.length > 0) {
            pp.println("(");
            pp.indent(ctor.getName().length());
            kids[0].prettyPrint(pp);
            for(int i = 1; i < kids.length; i++) {
                pp.print(", ");
                kids[i].prettyPrint(pp);
            }
            pp.println(")");
            pp.outdent(ctor.getName().length());
        }
        printAnnotations(pp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ctor.getName());
        sb.append("(");
        if(kids.length > 0) {
            sb.append(kids[0]);
            for(int i = 1; i < kids.length; i++) {
                sb.append(",");
                sb.append(kids[i].toString());
            }
        }
        sb.append(")");
        appendAnnotations(sb);
        return sb.toString();
    }

    @Override
    public int hashCode() {
        long r = ctor.hashCode();
        int accum = 6673;
        for(int i = 0; i < kids.length; i++) {
            r += kids[i].hashCode() * accum;
            accum *= 7703;
        }
        return (int)(r >> 12);
    }
    
    
}
