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

import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
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
            return map.keySet();
        }

        public boolean containsKey(IStrategoTerm t) {
            return map.containsKey(t);
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
