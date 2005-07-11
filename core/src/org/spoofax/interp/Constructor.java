/*
 * Created on 24.jun.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interp;

import java.util.List;

import aterm.ATerm;
import aterm.ATermAppl;

public class Constructor extends NamedDecl {
    
    private List<Constructor> lhs;
    private Constructor rhs;
    
    public Constructor(ATerm t) {
        List x = t.match("OpDecl(<term>,<term>)");

        setName(((ATermAppl)x.get(0)).getName());
        this.lhs = null;
        this.rhs = null;
    }
    
    Constructor[] getLhs() { return lhs.toArray(new Constructor[0]); }
    Constructor getType() { return rhs; }
}
