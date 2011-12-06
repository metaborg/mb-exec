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
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class SSL_explode_string extends AbstractPrimitive {

    protected SSL_explode_string() {
        super("SSL_explode_string", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) throws InterpreterException {
        IStrategoTerm t = tvars[0];
        
        if (!Tools.isTermString(t))
            return false;
        
        String s = ((IStrategoString) t).stringValue();
        
        ITermFactory factory = env.getFactory();
        IStrategoList result = factory.makeList();
        
        for (int i = s.length() - 1; i >= 0; i--) {
            result = factory.makeListCons(factory.makeInt(s.charAt(i)), result);
        }
        
        env.setCurrent(result);
        return true;
    }
}
