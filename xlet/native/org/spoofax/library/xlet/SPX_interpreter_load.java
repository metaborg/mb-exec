/*
 * Created on 16. feb.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.library.xlet;

import java.io.IOException;

import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Interpreter;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SPX_interpreter_load extends AbstractPrimitive {

    protected SPX_interpreter_load() {
        super("SPX_interpreter_load", 0, 2);
    }

    @Override
    public boolean call(IContext env, IConstruct[] svars, IStrategoTerm[] tvars) throws InterpreterException {
        
        if(!Tools.isTermInt(tvars[0]))
            return false;
        if(!Tools.isTermString(tvars[1]))
            return false;
        
        SPXLibrary spx = (SPXLibrary) env.getOperatorRegistry(SPXLibrary.REGISTRY_NAME);
        
        Interpreter itp = spx.getInterpreter(Tools.asJavaInt(tvars[0]));
        
        if(itp == null)
            return false;
        
        try {
            itp.load(Tools.asJavaString(tvars[1]));
        } catch(IOException e) {
            return false;
        } catch(InterpreterException e) {
            return false;
        }
        
        return true;
    }

}
