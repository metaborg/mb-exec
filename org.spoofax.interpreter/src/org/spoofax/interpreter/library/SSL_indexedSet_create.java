/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_indexedSet_create extends Primitive {

    class IndexedSet  {
        
        private static final long serialVersionUID = -4514696890481283123L;
        private int counter;
        Map<Integer, IStrategoTerm> map;
        
        IndexedSet(int initialSize, int maxLoad) {
            map = new HashMap<Integer, IStrategoTerm>(initialSize, 1.0f*maxLoad/100);
            counter = 0;
        }
        
        public int put(IStrategoTerm value) {
            int idx = counter++; 
            map.put(idx, value);
            return idx;
        }

        public int getIndex(IStrategoTerm t) {
            Set<Map.Entry<Integer,IStrategoTerm>> entries = map.entrySet();
            
            for(Map.Entry<Integer,IStrategoTerm> x : entries) {
                if(x.getValue() == t) //todo: for no max sharing use equals
                    return x.getKey();
            }
            
            return -1;
        }

        public boolean containsValue(IStrategoTerm t) {
            return map.containsValue(t);
        }

        public Collection<IStrategoTerm> values() {
            return map.values();
        }

        public boolean remove(IStrategoTerm t) {
            int idx = getIndex(t);

            if(idx == -1)
                return false;
            map.remove(idx);
            return true;
        }

        public void clear() {
            map.clear();
        }
    }

    // FIXME: Must have state per-interpreter, not per-JVM //@todo fix
    protected static Map<Integer, IndexedSet> map;
    protected static int setCounter;

    static {
        init();
    }

    public static Map init() {
        if(map != null) {

            for (IndexedSet indexedSet : map.values()) {
                 indexedSet.clear();
            }
            map.clear();
        }
        map = new HashMap<Integer, IndexedSet>();
        setCounter = 0;
        return map;
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
        
        IndexedSet is = new IndexedSet(initialSize, maxLoad);
        int n = setCounter++;
        map.put(n, is);
        
        env.setCurrent(env.getFactory().makeInt(n));
        return true;
    }
}
