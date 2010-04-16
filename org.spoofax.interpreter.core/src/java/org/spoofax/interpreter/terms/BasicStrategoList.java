/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;

import java.util.NoSuchElementException;

/**
 * A basic stratego list implementation using a linked-list data structure.
 */
public class BasicStrategoList extends BasicStrategoTerm implements IStrategoList {

    private final IStrategoTerm head;
    
    private final IStrategoList tail;

    /**
     * Creates a new list.
     * 
     * @see #prepend(IStrategoTerm) Adds a new head element to a list.
     */
    protected BasicStrategoList(IStrategoTerm head, IStrategoList tail, IStrategoList annotations) {
        super(annotations);
        this.head = head;
        this.tail = tail;
    }
    
    public IStrategoTerm head() {
        if (isEmpty())
            throw new NoSuchElementException();
        return head;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public IStrategoList tail() {
        if (tail == null)
            throw new NoSuchElementException();
        return tail;
    }
    
    @Deprecated
    public IStrategoList prepend(IStrategoTerm prefix) {
        return new BasicStrategoList(prefix, this, null);
    }
    
    public final IStrategoTerm get(int index) {
        return getSubterm(index);
    }
    
    public IStrategoTerm[] getAllSubterms() {
        int size = size();
        IStrategoTerm[] clone = new IStrategoTerm[size];
        IStrategoList list = this;
        for (int i = 0; i < size; i++) {
            clone[i] = list.head();
            list = list.tail();
        }
        return clone;
    }

    
    public final int size() {
        return getSubtermCount();
    }

    public IStrategoTerm getSubterm(int index) {
        IStrategoList list = this;
        for (int i = 0; i < index; i++)
            list = list.tail();
        return list.head();
    }

    public int getSubtermCount() {
        int result = 0;
        for (IStrategoList cur = this; !cur.isEmpty(); cur = cur.tail())
            result++;
        return result;
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
        if (!getAnnotations().match(second.getAnnotations()))
            return false;
        
        // TODO: test equality of annos on cons nodes (see StrategoList)
        for (IStrategoList cur = this; !cur.isEmpty(); cur = cur.tail(), snd = snd.tail()) {
            if(!cur.head().match(snd.head()))
                return false;
        }
        
        IStrategoList annotations = getAnnotations();
        IStrategoList secondAnnotations = second.getAnnotations();
        if (annotations == secondAnnotations) {
            return true;
        } else {
            return annotations.match(secondAnnotations);
        }
    }

    public void prettyPrint(ITermPrinter pp) {
        if(!isEmpty()) {
            pp.println("[");
            pp.indent(2);
            head().prettyPrint(pp);
            for (IStrategoList cur = tail(); !cur.isEmpty(); cur = cur.tail()) {
                pp.print(",");
                pp.nextIndentOff();
                cur.head().prettyPrint(pp);
                pp.println("");
            }
            pp.println("");
            pp.print("]");
            pp.outdent(2);

        } else {
            pp.print("[]");
        }
        printAnnotations(pp);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if(!isEmpty()) {
            sb.append(head().toString());
            for (IStrategoList cur = tail(); !cur.isEmpty(); cur = cur.tail()) {
                sb.append(",");
                sb.append(cur.head().toString());
            }
        }
        sb.append("]");
        appendAnnotations(sb);
        return sb.toString();
    }

    @Override
    public int hashCode() {
        long hc = 4787;
        for (IStrategoList cur = this; !cur.isEmpty(); cur = cur.tail()) {
            hc *= cur.head().hashCode();
        }
        return (int)(hc >> 2);
    }
}
