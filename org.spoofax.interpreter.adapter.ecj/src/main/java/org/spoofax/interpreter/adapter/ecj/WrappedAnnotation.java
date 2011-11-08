/*
 * Created on 25. des.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.Annotation;
import org.spoofax.interpreter.terms.IStrategoConstructor;

public abstract class WrappedAnnotation extends WrappedExpression implements IWrappedExtendedModifier {
    
    private static final long serialVersionUID = 1L;

    protected WrappedAnnotation(IStrategoConstructor constructor) {
        super(constructor);
    }

    @Override
    public abstract Annotation getWrappee();
}
