/*
 * Created on 16. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter;

import java.util.Stack;

public class EvaluationStack {

    private Stack<Pair<IConstruct,VarScope>> nextStates;
    
    public EvaluationStack() {
        nextStates = new Stack<Pair<IConstruct,VarScope>>();
    }
    
    public void addNext(IConstruct c, VarScope scope) {
        nextStates.push(new Pair<IConstruct,VarScope>(c, scope));
    }
    
    public Pair<IConstruct,VarScope> getNext() {
        return nextStates.pop();
    }
    
    public boolean hasMore() {
        return !nextStates.isEmpty();
    }
}
