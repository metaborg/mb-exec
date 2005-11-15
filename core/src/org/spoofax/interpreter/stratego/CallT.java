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

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.VarScope;

import aterm.ATerm;
import aterm.ATermAppl;

public class CallT extends Strategy {

    protected String name;
    protected List<Strategy> svars;
    protected List<ATerm> tvars;
    
    private static int counter = 0;
    
    public CallT(String name, List<Strategy> svars, List<ATerm> tvars) {
        this.name = name;
        this.svars = svars;
        this.tvars = tvars;
    }

    public boolean eval(IContext env) throws FatalError {
        debug("CallT.eval() - " + env.current());
        SDefT sdef = env.lookupSVar(name);
        
        if(sdef == null)
            throw new FatalError("Not found '" + name + "'");
        
        debug(" call : " + name + " " + sdef);
        if(name.equals("w_89")) {
            debug("foop");
        }
        List<String> formalTVars = sdef.getTermParams();
        List<String> formalSVars = sdef.getStrategyParams();

        debug(" args : " + svars);
        debug(" svars: " + formalSVars);

        VarScope newVarScope = new VarScope(sdef.getScope());

        if (formalSVars.size() != svars.size()) {
            debug(" takes : " + formalSVars.size());
            debug(" have  : " + svars.size());

            throw new FatalError("Parameter length mismatch!");
        }

        for (int i = 0; i < svars.size(); i++) {
            String formal = formalSVars.get(i);
            Strategy actual = svars.get(i);
            SDefT def = new SDefT("<anon_" + counter + ">", 
                                  new ArrayList<String>(0), 
                                  new ArrayList<String>(0), 
                                  actual,
                                  env.getVarScope());
            counter++;
            debug("  " + formal + " points to " + actual);
            newVarScope.addSVar(formal, def);
        }

        for (int i = 0; i < tvars.size(); i++) {
            String formal = formalTVars.get(i);
            ATerm actual = tvars.get(i);
            // FIXME: This should not be here
            if(((ATermAppl)actual).getName().equals("Var"))
                actual = env.lookupVar(Tools.stringAt(actual, 0));
            newVarScope.add(formal, actual);
        }
        
        VarScope oldVarScope = env.getVarScope();
        env.setVarScope(newVarScope);

        bump();
        boolean r = sdef.getBody().eval(env);
        env.setVarScope(oldVarScope);
        unbump();
        debug("<return: " + name + " (" + ( r ? "ok" : "failed") + ") - " + env.current());
        return r;
    }

    @Override
    public String toString() {
     return "CallT(\"" + name + "\"," + svars + "," + tvars + ")";
    }
}
