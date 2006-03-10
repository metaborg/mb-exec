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
import org.spoofax.interpreter.VarScope;

public class Let extends Strategy {

    protected List<SDefT> defs;
    protected Strategy body;
    
    public Let(List<SDefT> defs, Strategy body) {
        assert defs != null;
        this.defs = defs;
        this.body = body;
    }
    
    public boolean eval(IContext env) throws FatalError {
        
        debug("Let.eval() - " + env.current());
        
        VarScope oldScope = env.getVarScope();
        VarScope newScope = new VarScope(oldScope);
        
        List<SDefT> newDefs = new ArrayList<SDefT>(defs.size());
        
        for(SDefT def : defs) {
            SDefT newDef = new SDefT(def.getName(), 
                                     def.getStrategyParams(), 
                                     def.getTermParams(),
                                     def.getBody(),
                                     newScope);
            newDefs.add(newDef);
        }
        
        newScope.addSVars(newDefs);
        env.setVarScope(newScope);
        
        boolean r = body.eval(env);
        
        env.setVarScope(oldScope);
        
        return r;
    }
    
    public void prettyPrint(StupidFormatter sf) {
        sf.append("Let(\n");
        sf.bump(4);
        sf.append(", " + defs + "\n");
        sf.append(", ");
        body.prettyPrint(sf);
        sf.append(")");
        sf.unbump(4);
    }
}
