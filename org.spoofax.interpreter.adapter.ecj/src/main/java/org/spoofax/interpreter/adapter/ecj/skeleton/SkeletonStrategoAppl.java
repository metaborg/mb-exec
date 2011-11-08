package org.spoofax.interpreter.adapter.ecj.skeleton;

import java.io.IOException;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoTerm;

public abstract class SkeletonStrategoAppl extends StrategoTerm implements IStrategoAppl {

  private static final long serialVersionUID = -2522680523775044390L;

    public SkeletonStrategoAppl(IStrategoList annotations, int storageType) {
        super(annotations, storageType);
    }
    
    @Deprecated
    final public IStrategoTerm[] getArguments() {
        throw new NotImplementedException();
    }

    @Override
    public abstract IStrategoConstructor getConstructor();
    
    @Override
    public abstract IStrategoTerm[] getAllSubterms();

    @Override
    public abstract IStrategoTerm getSubterm(int index);

    @Override
    final public String getName() {
        return getConstructor().getName();
    }

    @Override
    final public int getSubtermCount() {
        return getConstructor().getArity();
    }

    @Override
    final public int getTermType() {
        return IStrategoTerm.APPL;
    }

    @Override
    final protected boolean doSlowMatch(IStrategoTerm second, int commonStorageType) {
        if (second.getTermType() != IStrategoTerm.APPL)
            return false;
        IStrategoAppl o = (IStrategoAppl)second;
        if (getConstructor() != o.getConstructor())
            return false;
        
        IStrategoTerm[] kids = getAllSubterms();
        IStrategoTerm[] secondKids = o.getAllSubterms();
        if (kids != secondKids) {
            for (int i = 0, sz = kids.length; i < sz; i++) {
                IStrategoTerm kid = kids[i];
                IStrategoTerm secondKid = secondKids[i];
                if (kid != secondKid && !kid.match(secondKid)) {
                    if (commonStorageType == SHARABLE && i != 0)
                        System.arraycopy(secondKids, 0, kids, 0, i);
                    return false;
                }
            }
  
// FIXME should update sharing when possible            
//            if (commonStorageType == SHARABLE)
//                this.kids = secondKids;
        }
        
        IStrategoList annotations = getAnnotations();
        IStrategoList secondAnnotations = second.getAnnotations();
        if (annotations == secondAnnotations) {
            return true;
        } else if (annotations.match(secondAnnotations)) {
            if (commonStorageType == SHARABLE) internalSetAnnotations(secondAnnotations);
            return true;
        } else {
            return false;
        }
    }

    @Override
    final public void writeAsString(Appendable output, int maxDepth) throws IOException {
        output.append(getName());
        IStrategoTerm[] kids = getAllSubterms();
        if(kids.length > 0) {
            output.append('(');
            if (maxDepth == 0) {
            	output.append("...");
            } else {
	            kids[0].writeAsString(output, maxDepth - 1);
	            for(int i = 1; i < kids.length; i++) {
	                output.append(',');
	                kids[i].writeAsString(output, maxDepth - 1);
	            }
            }
            output.append(')');
        }
        appendAnnotations(output, maxDepth);
    }

    @Override
    final protected int hashFunction() {
        long r = getConstructor().hashCode();
        int accum = 6673;
        IStrategoTerm[] kids = getAllSubterms();
        for(int i = 0; i < kids.length; i++) {
            r += kids[i].hashCode() * accum;
            accum *= 7703;
        }
        return (int)(r >> 12);
    }
    
}
