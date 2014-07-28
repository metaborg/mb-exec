/*
 * Created on 03.mar.2014
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_iori extends AbstractPrimitive {

    protected SSL_iori() {
        super("SSL_iori", 0, 2);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {
        
        if(!Tools.isTermInt(targs[0]))
            return false;
        if(!Tools.isTermInt(targs[1]))
            return false;

        IStrategoInt a = (IStrategoInt) targs[0];
        IStrategoInt b = (IStrategoInt) targs[1];
        env.setCurrent(env.getFactory().makeInt(a.intValue() | b.intValue()));
        return true;
    }
}
