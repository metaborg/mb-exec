/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.VarScope;

public class ExtSDef extends SDefT {

    public ExtSDef(String name, SVar[] realsvars, String[] realtvars,
            VarScope scope) {
        super(name, realsvars, realtvars, null, scope);
    }

    @Override
    public String toString() {
        return "ExtSDefT(\"" + name + "\")";
    }

    @Override
    public Strategy getBody() {
        return null;
    }
}
