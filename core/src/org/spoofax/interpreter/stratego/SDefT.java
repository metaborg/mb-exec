/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import java.util.List;

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.VarScope;

public class SDefT implements IConstruct {
    protected String name;

    protected List<String> svars;

    protected List<String> tvars;

    protected Strategy body;

    protected VarScope scope;

    public SDefT(String name, List<String> svars, List<String> tvars,
            Strategy body, VarScope scope) {
        this.name = name;
        this.svars = svars;
        this.tvars = tvars;
        this.body = body;
        this.scope = scope;
    }

    public boolean eval(IContext e) throws FatalError {
        throw new FatalError("Unimplemented");
    }

    public String getName() {
        return name;
    }

    public Strategy getBody() {
        return body;
    }

    public List<String> getTermParams() {
        return tvars;
    }

    public List<String> getStrategyParams() {
        return svars;
    }

    public VarScope getScope() {
        return scope;
    }

    @Override
    public String toString() {
        return "SDefT(\"" + name + "\", " + getBody() + ")";
    }

    public void setScope(VarScope newScope) {
        this.scope = newScope;
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.first("SDefT(");
        sf.bump(6);
        sf.line("  \"" + name + "\"");
        sf.line(", " + svars);
        sf.line(", " + tvars);
        sf.append(", ");
        sf.bump(2);
        body.prettyPrint(sf);
        sf.unbump(2);
        sf.append(")");
        sf.unbump(6);
    }
}
