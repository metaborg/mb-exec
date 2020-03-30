package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.Context;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.TermFactory;

public class ImmutableCollectionTestSetup {
    protected static final Context context;
    protected static final ITermFactory f;
    protected static final IStrategoTerm one;
    protected static final IStrategoTerm two;
    protected static final IStrategoTerm three;
    protected static final IStrategoTerm four;
    protected static final IStrategoTerm five;
    protected static final IStrategoTerm a;
    protected static final IStrategoTerm b;
    protected static final IStrategoTerm c;
    protected static final IStrategoTerm d;
    protected static final IStrategoTerm e;
    protected static final IStrategoTerm oneIsh;
    protected static final IStrategoTerm oneWithAnno;

    // @BeforeClass is not run before @Parameterized.Parameters
    static {
        context = new Context(new TermFactory(), new TermFactory());
        f = context.getFactory();
        one = f.makeInt(1);
        oneIsh = f.makeInt(1);
        assert one != oneIsh;
        oneWithAnno = f.annotateTerm(one, f.makeList(one));
        assert !one.equals(oneWithAnno);
        two = f.makeInt(2);
        three = f.makeInt(3);
        four = f.makeInt(4);
        five = f.makeInt(5);
        a = f.makeString("a");
        b = f.makeString("b");
        c = f.makeString("c");
        d = f.makeString("d");
        e = f.makeString("e");
    }
}
