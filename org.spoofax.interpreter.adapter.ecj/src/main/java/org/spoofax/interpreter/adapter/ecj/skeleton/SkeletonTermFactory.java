package org.spoofax.interpreter.adapter.ecj.skeleton;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoPlaceholder;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.AbstractTermFactory;

public abstract class SkeletonTermFactory extends AbstractTermFactory {

    public SkeletonTermFactory(int defaultStorageType) {
        super(defaultStorageType);
    }

    @Override
    final public ITermFactory getFactoryWithStorageType(int storageType) {
        throw new NotImplementedException();
    }

    @Override
    final public IStrategoPlaceholder makePlaceholder(IStrategoTerm template) {
        throw new NotImplementedException();
    }

    @Override
    final public IStrategoReal makeReal(double d) {
        throw new NotImplementedException();
    }

    @Override
    final public IStrategoTerm annotateTerm(IStrategoTerm term,
            IStrategoList annotations) {
        throw new NotImplementedException();
    }

    @Override
    final public IStrategoString tryMakeUniqueString(String name) {
        throw new NotImplementedException();
    }

}
