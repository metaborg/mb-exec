/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class SSL_new extends AbstractPrimitive {

    // static, like TermFactory.asyncStringPool
    private static int alphaCounter;
    private static int counter;
    private static int letterA = 'a';

    protected SSL_new() {
        super("SSL_new", 0, 0);
    }

    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {

        ITermFactory factory = env.getFactory();

        synchronized (SSL_new.class) { 
            String s = (char)(letterA + alphaCounter) + "_" + counter;
            while(factory.hasConstructor(s, 0)) {
                alphaCounter++;
                if(alphaCounter > 25) {
                    alphaCounter = 0;
                    counter++;
                    if (counter < 0) counter = 0;
                }
                s = (char)(letterA + alphaCounter) + "_" + counter;
            }
            env.setCurrent(env.getFactory().makeString(s));
        }

        return true;
    }
}
