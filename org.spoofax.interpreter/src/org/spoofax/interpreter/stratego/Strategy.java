/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import java.util.ArrayList;
import java.util.List;

import org.spoofax.interpreter.Context;
import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.stratego.SDefT.ArgType;
import org.spoofax.interpreter.stratego.SDefT.ConstType;
import org.spoofax.interpreter.stratego.SDefT.FunType;


abstract public class Strategy implements IConstruct {

    private final static ArgType type;
    
    static {
        List<ArgType> l = new ArrayList<ArgType>(2);
        l.add(new ConstType());
        type = new FunType(l);
    }
    
    public static void debug(Object... s) {
        Context.debug(s);
    }
    
    public SDefT.ArgType getType() {
        return type;
    }

//    public String toString() {
//        StupidFormatter sf = new StupidFormatter();
//        prettyPrint(sf);
//        return sf.toString();
//    }

    protected String getTraceName() {
        return this.getClass().getSimpleName();
    }

    public String toString() {
        return getTraceName();
    }
}
