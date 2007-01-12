/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_indexedSet_create extends AbstractPrimitive {

    class IndexedSet  {
        
        private static final long serialVersionUID = -4514696890481283123L;
        private int counter;
        Map<IStrategoTerm, Integer> map;
        
        IndexedSet(int initialSize, int maxLoad) {
            map = new HashMap<IStrategoTerm, Integer>(initialSize, 1.0f*maxLoad/100);
            counter = 0;
        }
        
        public int put(IStrategoTerm value) {
            int idx = counter++; 
            map.put(value, idx);
            return idx;
        }

        public int getIndex(IStrategoTerm t) {
            Integer r = map.get(t);
            return r == null ? -1 : r;
        }

        public boolean containsValue(IStrategoTerm t) {
            return map.containsKey(t);
        }

        public Collection<Integer> values() {
            return map.values();
        }

        public boolean remove(IStrategoTerm t) {
            return map.remove(t) != null;
        }

        public void clear() {
            map.clear();
        }

        public Collection<IStrategoTerm> keySet() {
            // FIXME this could easily be more efficient
            class X implements Comparable<X> {
                IStrategoTerm t;
                Integer i;
                X(IStrategoTerm t, Integer i) {
                    this.t = t;
                    this.i = i;
                }
                public int compareTo(X o) {
                    return i.compareTo(o.i);
                }
            }
            List<X> xs = new ArrayList<X>();
            for(Entry<IStrategoTerm,Integer> e : map.entrySet()) { 
                xs.add(new X(e.getKey(), e.getValue()));
            }
            Collections.sort(xs);
            List<IStrategoTerm> r = new ArrayList<IStrategoTerm>();
            for(X x : xs)
                r.add(x.t);
            return r;
        }

        public boolean containsKey(IStrategoTerm t) {
            return map.containsKey(t);
        }

        public int size() {
            return map.size();
        }
        
        public Set<Entry<IStrategoTerm, Integer>> entrySet() {
            return map.entrySet();
        }
    }

    protected SSL_indexedSet_create() {
        super("SSL_indexedSet_create", 0, 2);
    }
    
    public boolean call(IContext env, List<IConstruct> sargs, IStrategoTerm[] targs) throws InterpreterException {

        if(!(Tools.isTermInt(targs[0])))
            return false;
        if(!(Tools.isTermInt(targs[1])))
            return false;

        int initialSize = ((IStrategoInt)targs[0]).getValue();
        int maxLoad = ((IStrategoInt)targs[1]).getValue();

        int ref = SSLLibrary.instance(env).registerIndexedSet(new IndexedSet(initialSize, maxLoad));
        env.setCurrent(env.getFactory().makeInt(ref));
        
        return true;
    }
}
