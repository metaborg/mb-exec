/*
 * Created on 24.jun.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interp;

import java.util.ArrayList;
import java.util.List;

import aterm.AFun;
import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermInt;
import aterm.ATermList;
import aterm.pure.PureFactory;

public class Tools {

    private static List<Pair<String,ATerm>> emptyList = new ArrayList<Pair<String,ATerm>>();
    
    public static List<Pair<String,ATerm>> match(ATermAppl t, ATermAppl p) throws FatalError {
        
        System.out.println("?: " + t.getName() + " / " + p.getName());
        
        if(p.getName().equals("Anno"))
            return match(t, (ATermAppl)p.getChildAt(0));
        else if(p.getName().equals("Op")) {

            ATermList l = (ATermList)p.getChildAt(1);
            if(l.getChildCount() != t.getChildCount())
                return null;
            
            ATermAppl c = (ATermAppl)p.getChildAt(0);
            if(!t.getName().equals(c.getName()))
                return null;
            
            List<Pair<String,ATerm>> r = new ArrayList<Pair<String,ATerm>>();
            for(int i=0;i<t.getChildCount();i++) {
                List<Pair<String,ATerm>> m = match((ATerm)t.getChildAt(i), (ATermAppl)l.getChildAt(i));
                if(m != null)
                    r.addAll(m);
            }
            return r;
        } else if(p.getName().equals("Int")) {
            if(t.getType() == ATerm.INT)
                return match(Tools.intAt(t, 0), Tools.applAt(p, 0));
            return null;
        } else if(p.getName().equals("Str")) {
            System.out.println("!" + t + " ?" + p);
            if(t.getName().equals(stringAt(p, 0)))
                return emptyList;
            return null;
        }
        
        throw new FatalError("What?" + p);
    }

    public static List<Pair<String, ATerm>> match(ATermInt t, ATermAppl p) {
        
        System.out.println("!" + t + " ?" + p);
        
        if(p.getName().equals("Anno")) {
            return match(t, applAt(p, 0));
        }
        
        if(p.getName().equals("Int")) {
            Integer i = new Integer(stringAt(p, 0));
            if(i == t.getInt())
                return emptyList;
        }
        
        if(p.getName().equals("Var")) {
            List<Pair<String,ATerm>> r = new ArrayList<Pair<String,ATerm>>();
            r.add(new Pair<String, ATerm>(((ATermAppl)p.getChildAt(0)).getName(), t));
            return r;
        }
        
        return null;
    }
    
    public static List<Pair<String, ATerm>> match(ATerm t, ATermAppl p) throws FatalError {
        if(t.getType() == ATerm.APPL)
            return match((ATermAppl)t, p);
        else if(t.getType() == ATerm.INT)
            return match((ATermInt)t, p);
            
        throw new FatalError("Current term is not an ATermAppl term [" + t.getClass().toString() + " " + ATerm.APPL + " " + t.getType() + "]");
    }

    public static String stringAt(ATerm t, int i) {
        return ((ATermAppl)t.getChildAt(i)).getName();
    }
    
    public static ATermAppl applAt(ATerm t, int i) {
        return (ATermAppl)((ATermAppl)t).getChildAt(i);
    }

    private static ATermInt intAt(ATerm t, int i) {
        return (ATermInt)((ATermAppl)t).getChildAt(i);
    }


    public ATerm implode(PureFactory factory, ATermAppl t) throws FatalError {
        if (t.getName().equals("Anno")) {
            return implode(factory, applAt(t, 0));
        } else if (t.getName().equals("Op")) {
            String ctr = stringAt(t, 0);
            ATermList children = (ATermList) t.getChildAt(1);

            AFun afun = factory.makeAFun(ctr, children.getLength(), false);
            ATermList kids = factory.makeList();

            for (int i = 0; i < children.getLength(); i++) {
                kids = kids.append(implode(factory, (ATermAppl) children.elementAt(i)));
            }
            return factory.makeApplList(afun, kids);
        } else if (t.getName().equals("Int")) {
            ATermAppl x = (ATermAppl) t.getChildAt(0);
            return factory.makeInt(new Integer(x.getName()));
        } else if (t.getName().equals("Str")) {
            ATermAppl x = (ATermAppl) t.getChildAt(0);
            return x;
        } 
        
        throw new FatalError("Unknown build constituent '" + t.getName() + "'");
    }

    public static ATermList listAt(ATerm t, int i) {
        return (ATermList)((ATermAppl)t).getChildAt(i);
    }

    public static SVar svarAt(ATermList l, int i) {
        return (SVar)l.getChildAt(i);
    }

}
