/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter;

import java.util.HashMap;
import java.util.Map;

import shared.SharedObject;

import aterm.AFun;
import aterm.ATerm;
import aterm.pure.PureFactory;

public class TermFactory extends PureFactory {

    Map<String, Object>  funny;
    private static final Object marker = "foo";
    
    TermFactory() {
        super();
        funny = new HashMap<String, Object>();
    }
    
    @Override
    public SharedObject build(SharedObject arg0) {
        if(arg0 instanceof aterm.AFun) {
            funny.put(((AFun)arg0).getName(), marker);
        }
        
        return super.build(arg0);
    }
    
    public boolean hasAFun(String name, int arity) {
        return funny.get(name) != null;
    }
}
