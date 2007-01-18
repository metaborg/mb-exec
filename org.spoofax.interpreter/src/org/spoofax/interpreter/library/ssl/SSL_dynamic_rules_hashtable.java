/*
 * Created on 9. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_dynamic_rules_hashtable extends AbstractPrimitive {

    protected SSL_dynamic_rules_hashtable() {
        super("SSL_dynamic_rules_hashtable", 0, 0);
    }

    @Override
    public boolean call(IContext env, IConstruct[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {

        SSLLibrary or = (SSLLibrary) env.getOperatorRegistry(SSLLibrary.REGISTRY_NAME);
        or.getDynamicRuleHashtableRef();

        env.setCurrent(env.getFactory().makeInt(or.getDynamicRuleHashtableRef()));
        return true;
    }

}
