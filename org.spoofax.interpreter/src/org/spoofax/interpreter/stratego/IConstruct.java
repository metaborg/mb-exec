/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;

public interface IConstruct {

    boolean eval(IContext e) throws InterpreterException;
    void prettyPrint(StupidFormatter fmt);
}
