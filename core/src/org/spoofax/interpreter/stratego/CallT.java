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
import org.spoofax.interpreter.stratego.SDefT.SVar;

import aterm.ATerm;
import aterm.ATermAppl;

public class CallT extends Strategy {

    protected String name;

    protected List<Strategy> actualStrategyArgs;

    protected List<ATerm> actualTermArgs;

    private static int counter = 0;

    public CallT(String name, List<Strategy> svars, List<ATerm> tvars) {
        this.name = name;
        this.actualStrategyArgs = svars;
        this.actualTermArgs = tvars;
    }

    public boolean eval(IContext env) throws FatalError {

        debug("CallT.eval() - " + env.current());

        SDefT sdef = env.lookupSVar(name);

        if (sdef == null)
            throw new FatalError("Not found '" + name + "'");

        List<String> formalTermArgs = sdef.getTermParams();
        List<SVar> formalStrategyArgs = sdef.getStrategyParams();

        debug(" call : " + name + " (" + formalStrategyArgs.size() + "|" + formalTermArgs.size() + ") " + sdef);
        debug("" + sdef);

        debug(" actualStrategyArgs : " + actualStrategyArgs);
        debug(" formalStrategyArgs : " + formalStrategyArgs);
        
        debug(" actualTermArgs : " + actualTermArgs);
        debug(" formalTermArgs : " + formalTermArgs);

        if (actualStrategyArgs.size() != formalStrategyArgs.size())
            throw new FatalError("Incorrect strategy arguments, expected " + formalStrategyArgs.size()
                    + " got " + actualStrategyArgs.size());

        if (actualTermArgs.size() != formalTermArgs.size())
            throw new FatalError("Incorrect aterm arguments, expected " + formalTermArgs.size()
                    + " got " + actualTermArgs.size());

        VarScope newVarScope = new VarScope(sdef.getScope());

        for (int i = 0; i < actualStrategyArgs.size(); i++) {
            SVar formal = formalStrategyArgs.get(i);
            Strategy actual = actualStrategyArgs.get(i);

            debug("actual : " + actual);

            SDefT target = null;
            if(actual instanceof CallT && 
                    ((CallT)actual).getStrategyArguments().size() == 0
                    && ((CallT)actual).getTermArguments().size() == 0) {
                target = env.lookupSVar(((CallT)actual).getTargetStrategyName());
                
            } else {
                List<SVar> stratArgs = new ArrayList<SVar>();
                List<String> termArgs = new ArrayList<String>();
                target = new SDefT(makeTempName(formal.name), stratArgs, termArgs, actual, env.getVarScope());
            }

            newVarScope.addSVar(formal.name, target);
        }

        for (int i = 0; i < actualTermArgs.size(); i++) {
            String formal = formalTermArgs.get(i);
            ATerm actual = actualTermArgs.get(i);
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

    private List<ATerm> getTermArguments() {
        return actualTermArgs;
    }

    private String makeTempName(String s) {
        return "<anon_" + s + "_" + counter + ">";
    }

    @Override
    public String toString() {
        return "CallT(\"" + name + "\"," + actualStrategyArgs + "," + actualTermArgs + ")";
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.append("CallT(\n");
        sf.bump(6);
        sf.append("  \"" + name + "\"\n");
        sf.append(", " + actualStrategyArgs + "\n");
        sf.append(", " + actualTermArgs + "\n");
        sf.append(")");
    }

    public String getTargetStrategyName() {
        return name;
    }
    
    public List<Strategy> getStrategyArguments() {
        return actualStrategyArgs;
    }
}
