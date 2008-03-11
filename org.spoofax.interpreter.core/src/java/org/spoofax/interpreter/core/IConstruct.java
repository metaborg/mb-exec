/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.core;

import org.spoofax.interpreter.stratego.StupidFormatter;

public interface IConstruct {

    public IConstruct eval(IContext e) throws InterpreterException;
    public void prettyPrint(StupidFormatter fmt);
}
