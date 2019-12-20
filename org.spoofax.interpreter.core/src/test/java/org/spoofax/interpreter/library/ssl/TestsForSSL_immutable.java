package org.spoofax.interpreter.library.ssl;

import org.junit.Before;
import org.spoofax.interpreter.core.Context;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.attachments.OriginAttachment;

public class TestsForSSL_immutable {
    protected Context context;
    protected ITermFactory f;
    protected IStrategoTerm one;
    protected IStrategoTerm two;
    protected IStrategoTerm three;
    protected IStrategoTerm four;
    protected IStrategoTerm five;
    protected IStrategoTerm a;
    protected IStrategoTerm b;
    protected IStrategoTerm c;
    protected IStrategoTerm d;
    protected IStrategoTerm e;
    protected IStrategoTerm oneIsh;

    @Before public void createContext() {
        context = new Context(new TermFactory(), new TermFactory());
        f = context.getFactory();
        one = f.makeInt(1);
        oneIsh = f.makeInt(1);
        assert one != oneIsh;
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
