package org.spoofax.interpreter;

import java.util.HashMap;
import java.util.Map;

import aterm.ATerm;
import aterm.pure.PureFactory;

class Foo {
    public static void main(String args[]) {
        PureFactory factory = new PureFactory();
        ATerm t = factory.parse("Foo(0, Foo(Baz, Zap))");

        ATerm p = factory.parse("Foo(<id>, Foo(<fun>, <fun>))");
        
        System.out.println(t.match(p));
                           
        
        //ATerm t = factory.parse("OpDecl(\"Nil\",ConstType(Sort(\"Nil\",[])))");
/*
        ATerm p = factory.parse("OpDecl(<term>,<term>)");
        System.out.println(p.toString());
        List r = t.match(p);
        System.out.println(t);
        System.out.println(r);
*/
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("foo", "bar");
        System.out.println(m.containsKey("foo"));
        }
}
