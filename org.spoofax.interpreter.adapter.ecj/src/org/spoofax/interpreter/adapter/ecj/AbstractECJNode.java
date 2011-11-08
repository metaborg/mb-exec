/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import java.io.IOException;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.terms.StrategoTerm;

public abstract class AbstractECJNode extends StrategoTerm {

    private static final long serialVersionUID = 1L;

    protected AbstractECJNode(IStrategoList annotations, int storageType) {
        super(annotations, storageType);
    }

    @Override
    public void writeAsString(Appendable output, int maxDepth)
            throws IOException {
        throw new NotImplementedException();
    }
}
