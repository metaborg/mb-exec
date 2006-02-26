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

    protected List<SVar> svars;

    protected List<String> tvars;

    protected Strategy body;

    protected VarScope scope;

    public static class SVar {
        public final String name;
        public final ArgType type;
        public SVar(String name, ArgType type) {
            this.name = name;
            this.type = type;
        }
    }
    public interface ArgType {
    }

    public static class FunType implements ArgType {
        protected List<ArgType> args;

        public FunType(List<ArgType> args) {
            this.args = args;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof FunType))
                return false;
            FunType other = (FunType) obj;
            if (args.size() != other.args.size())
                return false;
            for (int i = 0; i < args.size(); i++)
                if (!args.get(i).equals(other.args.get(i)))
                    return false;
            return true;
        }
    }

    public static class ConstType implements ArgType {
    }

    public SDefT(String name, List<SVar> svars, List<String> tvars, Strategy body, VarScope scope) {
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

    public List<SVar> getStrategyParams() {
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
