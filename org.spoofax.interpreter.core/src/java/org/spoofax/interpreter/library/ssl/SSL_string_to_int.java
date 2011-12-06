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
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_string_to_int extends AbstractPrimitive {

    protected SSL_string_to_int() {
        super("SSL_string_to_int", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) throws InterpreterException {
        
        if(!Tools.isTermString(tvars[0]))
            return false;

        
        String s = Tools.javaString(tvars[0]);
        
        String s0 = s;
        try {
            env.setCurrent(env.getFactory().makeInt(Integer.parseInt(s0)));
            return true;
        } catch(NumberFormatException e) {
            // do nothing
        }
        
        try {
            s0 = s.trim();
            if(s0.length() > 0 && s0.charAt(0) == '+') 
                s0 = s0.substring(1);
            env.setCurrent(env.getFactory().makeInt(Integer.parseInt(s0)));
            return true;
        } catch (NumberFormatException e) {
            // do nothing
        }
        
//        try {
//            if(s0.startsWith("0x")) {
//                env.setCurrent(env.getFactory().makeInt(Integer.parseInt(s0.substring(2), 16)));
//                return true;
//            }
//        } catch(NumberFormatException e) {
//            
//        }
        return false;
    }
}
