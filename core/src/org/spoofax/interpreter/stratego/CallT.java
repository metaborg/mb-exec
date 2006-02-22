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
import org.spoofax.interpreter.stratego.SDefT.FunType;

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

        if (sdef == null)
            throw new FatalError("Not found '" + name + "'");

        int sd = sdef.svars.size();
        int td = sdef.tvars.size();

        debug(" call : " + name + " (" + sd + "|" + td + ") " + sdef);
        debug("" + sdef);

        List<String> formalTVars = sdef.getTermParams();
        List<FunType> formalSVars = sdef.getStrategyParams();

        debug(" args : " + svars);
        debug(" svars: " + formalSVars);

        if (svars.size() != formalSVars.size())
            throw new FatalError("Incorrect strategy arguments, expected " + formalSVars.size()
                    + " got " + svars.size());

        if (tvars.size() != formalTVars.size())
            throw new FatalError("Incorrect aterm arguments, expected " + formalTVars.size()
                    + " got " + tvars.size());

        VarScope newVarScope = new VarScope(sdef.getScope());

        if (formalSVars.size() != svars.size()) {
            debug(" takes : " + formalSVars.size());
            debug(" have  : " + svars.size());

            throw new FatalError("Parameter length mismatch when calling '" + name + "'!");
        }

        for (int i = 0; i < svars.size(); i++) {
            FunType formal = formalSVars.get(i);
            Strategy actual = svars.get(i);
            List<FunType> svars = new ArrayList<FunType>(formal.svars);
            for (int j = 0; j < formal.svars; j++) {
                // FIXME: Not an accurate assumption
                svars.add(new FunType(makeTempName(), 0, 0));
            }
            List<String> tvars = new ArrayList<String>(formal.tvars);
            for (int j = 0; j < formal.tvars; j++) {
                tvars.add(makeTempName());
            }
            // FIXME: If svars.size > 0, actual is CallT, must patch in vars  
            SDefT def = new SDefT(makeTempName(), svars, tvars, actual, env.getVarScope());
            counter++;
            debug("  " + formal + " points to " + actual);
            newVarScope.addSVar(formal.name, def);
        }

        for (int i = 0; i < tvars.size(); i++) {
            String formal = formalTVars.get(i);
            ATerm actual = tvars.get(i);
            // FIXME: This should not be here
            if (Tools.isVar((ATermAppl) actual))
                actual = env.lookupVar(Tools.stringAt(actual, 0));
            newVarScope.add(formal, actual);
        }

        VarScope oldVarScope = env.getVarScope();
        env.setVarScope(newVarScope);

        bump();
        boolean r = sdef.getBody().eval(env);
        env.setVarScope(oldVarScope);
        unbump();

        debug("<return: " + name + " (" + (r ? "ok" : "failed") + ") - " + env.current());

        return r;
    }

    private String makeTempName() {
        return "<anon_" + counter + ">";
    }

    @Override
    public String toString() {
        return "CallT(\"" + name + "\"," + svars + "," + tvars + ")";
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.append("CallT(\n");
        sf.bump(6);
        sf.append("  \"" + name + "\"\n");
        sf.append(", " + svars + "\n");
        sf.append(", " + tvars + "\n");
        sf.append(")");
    }

}
