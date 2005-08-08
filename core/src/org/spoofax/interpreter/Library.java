/*
 * Created on 17.jul.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import aterm.ATerm;
import aterm.ATermInt;
import aterm.ATermList;
import aterm.ATermReal;

public class Library {

    private static final Class[] defaultSignature = new Class[] {
            Context.class, ATermList.class, ATermList.class };

    private static class MethodEntry {
        MethodEntry(String name, int svarArity, int tvarArity) {
            this.name = name;
            this.svarArity = svarArity;
            this.tvarArity = tvarArity;
        }

        String name;

        int svarArity;

        int tvarArity;
    }

    private static final MethodEntry[] library = new MethodEntry[] {
            new MethodEntry("SSL_addi", 0, 2),
            new MethodEntry("SSL_addr", 0, 2),
            new MethodEntry("SSL_subti", 0, 2),
            new MethodEntry("SSL_printnl", 0, 2),
            new MethodEntry("SSL_gti", 0, 2),
            new MethodEntry("SSL_gtr", 0, 2),
            new MethodEntry("SSL_muli", 0, 2),
            new MethodEntry("SSL_int_to_string", 0, 1),
            new MethodEntry("SSL_explode_string", 0, 1),
            new MethodEntry("SSL_is_int", 0, 1),
    };
    
    private static Object hashtable;

    public static List<ExtStrategy> getStrategies() {
        List<ExtStrategy> r = new ArrayList<ExtStrategy>(library.length);
        for (int i = 0; i < library.length; i++) {
            r.add(new ExtStrategy(library[i].name, library[i].svarArity,
                                  library[i].tvarArity, 
                                  lookup(library[i].name,
                                         library[i].svarArity,
                                         library[i].tvarArity)));
        }
        return r;
    }

    public static Method lookup(String name, int tvarArity, int svarArity) {
        try {
            return Library.class.getMethod(name, defaultSignature);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static boolean SSL_addi(Context itp, ATermList svars, ATermList tvars) {
        if(Tools.termAt(tvars, 0).getType() != ATerm.INT)
            return false;
        if(Tools.termAt(tvars, 1).getType() != ATerm.INT)
            return false;

        ATermInt a = Tools.intAt(tvars, 0);
        ATermInt b = Tools.intAt(tvars, 1);
        itp.setCurrent(itp.makeTerm(a.getInt() + b.getInt()));
        return true;
    }

    public static boolean SSL_addr(Context itp, ATermList svars, ATermList tvars) {
        if(Tools.termAt(tvars, 0).getType() != ATerm.REAL)
            return false;
        if(Tools.termAt(tvars, 1).getType() != ATerm.REAL)
            return false;
        
        ATermReal a = Tools.realAt(tvars, 0);
        ATermReal b = Tools.realAt(tvars, 1);
        itp.setCurrent(itp.makeTerm(a.getReal() + b.getReal()));
        return true;
    }

    public static boolean SSL_printnl(Context itp, ATermList svars, ATermList tvars) {
        ATerm file = Tools.termAt(tvars, 0);
        ATerm term = Tools.termAt(tvars, 1);
        
        // FIXME: Use different files (stdout, stderr, ...)
        System.out.println("" + term);
        return true;
    }
    
    public static boolean SSL_table_hastable(Context itp, ATermList svars, ATermList tvars) {
        if(hashtable == null) {
            hashtable = new HashMap(); 
        }
        itp.setCurrent(itp.makeTerm(10000000));
        return true;
    }
    
    public static boolean SSL_gti(Context itp, ATermList svars, ATermList tvars) {
        if(Tools.termAt(tvars, 0).getType() != ATerm.INT)
            return false;
        if(Tools.termAt(tvars, 1).getType() != ATerm.INT)
            return false;
        
        ATermInt a = Tools.intAt(tvars, 0);
        ATermInt b = Tools.intAt(tvars, 1);
        
        return a.getInt() >  b.getInt();
    }

    public static boolean SSL_gtr(Context itp, ATermList svars, ATermList tvars) {
        if(Tools.termAt(tvars, 0).getType() != ATerm.REAL)
            return false;
        if(Tools.termAt(tvars, 1).getType() != ATerm.REAL)
            return false;
        
        ATermReal a = Tools.realAt(tvars, 0);
        ATermReal b = Tools.realAt(tvars, 1);
        
        return a.getReal() >  b.getReal();
    }
    
    public static boolean SSL_muli(Context itp, ATermList svars, ATermList tvars) {
        if(Tools.termAt(tvars, 0).getType() != ATerm.INT)
            return false;
        if(Tools.termAt(tvars, 1).getType() != ATerm.INT)
            return false;
        
        ATermInt a = Tools.intAt(tvars, 0);
        ATermInt b = Tools.intAt(tvars, 1);
        
        itp.setCurrent(itp.makeTerm(a.getInt()*b.getInt()));
        return true;
    }

    public static boolean SSL_subti(Context itp, ATermList svars, ATermList tvars) {
        if(Tools.termAt(tvars, 0).getType() != ATerm.INT)
            return false;
        if(Tools.termAt(tvars, 1).getType() != ATerm.INT)
            return false;
        
        ATermInt a = Tools.intAt(tvars, 0);
        ATermInt b = Tools.intAt(tvars, 1);
        
        itp.setCurrent(itp.makeTerm(a.getInt()-b.getInt()));
        return true;
    }

    public static boolean SSL_int_to_string(Context itp, ATermList svars, ATermList tvars) {
        if(Tools.termAt(tvars, 0).getType() != ATerm.INT)
            return false;
        
        ATermInt a = Tools.intAt(tvars, 0);
        itp.setCurrent(itp.makeTerm("\"" + a.getInt() + "\""));
        return true;
    }

    public static boolean SSL_explode_string(Context itp, ATermList svars, ATermList tvars) {
        if(Tools.termAt(tvars, 0).getType() != ATerm.APPL)
            return false;
        
        String s = Tools.stringAt(tvars, 0);
        ATerm[] r = new ATermInt[s.length()];
        byte[] bs = s.getBytes();
        
        for(int i=0;i<bs.length;i++)
            r[i] = itp.makeTerm(bs[i]);
        
        itp.setCurrent(itp.makeList(r));
        return true;
    }
    
    public static boolean SSL_is_int(Context itp, ATermList svars, ATermList tvars) {
        return Tools.termAt(tvars, 0).getType() == ATerm.INT;
    }
}