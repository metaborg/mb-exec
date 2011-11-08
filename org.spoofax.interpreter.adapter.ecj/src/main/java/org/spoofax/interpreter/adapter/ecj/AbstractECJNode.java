/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.terms.StrategoTerm;

public abstract class AbstractECJNode extends StrategoTerm {

    private static final long serialVersionUID = 1L;

    protected AbstractECJNode(IStrategoList annotations, int storageType) {
        super(annotations, storageType);
    }

}
