/*
 * Created on 11. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.util.Map.Entry;

import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.library.ssl.SSL_hashtable_create.Hashtable;
import org.spoofax.interpreter.stratego.CallT;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_table_fold extends AbstractPrimitive {

    protected SSL_table_fold() {
        super("SSL_table_fold", 1, 2);
    }

    @Override
    public boolean call(IContext env, IConstruct[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!Tools.isTermInt(tvars[1]))
            return true;
        
        SSLLibrary or = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        Hashtable ht = or.getHashtable(Tools.asJavaInt(tvars[1]));
        
        IStrategoTerm result = tvars[0];
        CallT sdef = (CallT) svars[0];
        IConstruct[] sv = new IConstruct[0];
        IStrategoTerm[] tv = new IStrategoTerm[2];

        env.setCurrent(tvars[0]);
        
        for(Entry<IStrategoTerm,IStrategoTerm> e : ht.entrySet()) {
            tv[0] = e.getKey();
            tv[1] = e.getValue();
            if(!sdef.evalWithArgs(env, sv, tv))
                return false;
            result = env.current();  
        }
        
        env.setCurrent(result);
        return true;
    }

}
