/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interpreter.stratego;

import java.util.List;

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;

import aterm.ATermAppl;
import aterm.ATermList;


public class SDefT implements IConstruct {
    protected String name;
    protected List<String> svars;
    protected List<String> tvars;
    protected Strategy body;
    
    public SDefT(String name, List<String> svars, List<String> tvars, Strategy body) {
        this.name = name;
        this.svars = svars;
        this.tvars = tvars;
        this.body = body;
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
}
