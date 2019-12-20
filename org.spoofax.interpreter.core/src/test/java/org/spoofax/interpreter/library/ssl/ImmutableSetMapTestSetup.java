package org.spoofax.interpreter.library.ssl;

import org.junit.BeforeClass;
import org.spoofax.interpreter.core.Context;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.TermFactory;

public class ImmutableSetMapTestSetup {
    protected static Context context;
    protected static ITermFactory f;
    protected static IStrategoTerm one;
    protected static IStrategoTerm two;
    protected static IStrategoTerm three;
    protected static IStrategoTerm four;
    protected static IStrategoTerm five;
    protected static IStrategoTerm a;
    protected static IStrategoTerm b;
    protected static IStrategoTerm c;
    protected static IStrategoTerm d;
    protected static IStrategoTerm e;
    protected static IStrategoTerm oneIsh;
    protected static IStrategoTerm oneWithAnno;

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
