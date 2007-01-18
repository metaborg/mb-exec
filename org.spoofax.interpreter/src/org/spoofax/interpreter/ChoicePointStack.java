/*
 * Created on 16. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter;

public class ChoicePointStack {

    public static class Entry {
        IConstruct construct;
        VarScope scope;
        Entry prev;
        Entry(IConstruct construct, VarScope scope, Entry prev) {
            this.construct = construct;
            this.scope = scope;
            this.prev = prev; 
        }
        public IConstruct getConstruct() { return construct; }
        public VarScope getScope() { return scope; }
    }
    
    private static final Entry SENTINEL = new Entry(null, null , null);
    private Entry currentSentinel;
    private Entry top;
    
    public ChoicePointStack() {
        currentSentinel = SENTINEL;
        top = SENTINEL;
    }
    
    public void addNext(IConstruct c, VarScope scope) {
        top = new Entry(c, scope, top);
    }
    
    public Entry getNext() {
        Entry r = top;
        top = top.prev;
        r.prev = null;
        return r;
    }
    
    public Entry newChoicePoint() {
        Entry oldSentinel = currentSentinel;
        top = currentSentinel = new Entry(null, null, top);
        return oldSentinel;
    }
    
    public void restoreChoicePoint(Entry oldSentinel) {
        top = currentSentinel.prev;
        currentSentinel.prev = null;
        currentSentinel = oldSentinel;
    }
    
    public boolean hasMore() {
        return top != currentSentinel;
    }
}
