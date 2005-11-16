/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import java.util.ArrayList;
import java.util.List;

import org.spoofax.interpreter.Context;
import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Pair;
import org.spoofax.interpreter.Tools;

import aterm.AFun;
import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermInt;
import aterm.ATermList;
import aterm.ATermReal;

public class Match extends Strategy {

    protected ATermAppl pattern;
    
    public Match(ATermAppl pattern) {
        this.pattern = pattern;
    }

    public boolean eval(IContext env) throws FatalError {
        debug("Match.eval() - " + env.current());
        
        ATerm current = env.current();
        
        debug(" pattern : " + pattern);
        debug(" current : " + current);

        ATermAppl p = pattern;
        List<Pair<String, ATerm>> r = match(env, current, p);

        debug(" to bind: " + r );
        
        Context.debug(" !" + current + " ; ?" + p);

        if (r != null) {

            boolean b = env.bindVars(r);

            if(b)
                debug(" success : " + r);
            else
                debug(" failure : no match!");
            
            return b;
        }
        debug(" failure : no match!");
        return false;
    }

    public List<Pair<String, ATerm>> emptyList() { return new ArrayList<Pair<String, ATerm>>(); }
    
    public List<Pair<String, ATerm>> match(IContext env, ATermAppl t, ATermAppl p)
            throws FatalError {
        debug(" ?: '" + t.getName() + "' / " + p.getName());

        if (p.getName().equals("Anno"))
            return match(env, t, Tools.applAt(p, 0));
        else if (p.getName().equals("Op")) {

            ATermList l = Tools.listAt(p, 1);
            if (l.getChildCount() != t.getChildCount())
                return null;

            ATermAppl c = Tools.applAt(p, 0);
            if (!t.getName().equals(c.getName()))
                return null;

            List<Pair<String, ATerm>> r = new ArrayList<Pair<String, ATerm>>();
            for (int i = 0; i < t.getChildCount(); i++) {
                List<Pair<String, ATerm>> m = match(env, (ATerm) t.getChildAt(i),
                                                    (ATermAppl) l.getChildAt(i));
                if (m != null)
                    r.addAll(m);
                else
                    return null;
            }
            return r;
        } else if (p.getName().equals("Int")) {
            if (t.getType() == ATerm.INT)
                return match(env, Tools.intAt(t, 0), Tools.applAt(p, 0));
            return null;
        } else if (p.getName().equals("Str")) {
            debug(" !" + t + " ?" + p);
            if (t.getName().equals(Tools.stringAt(p, 0)))
                return emptyList();
            return null;
        } else if (Tools.termType(p, "Var")) {
            List<Pair<String, ATerm>> r = new ArrayList<Pair<String, ATerm>>();
            r.add(new Pair<String, ATerm>(Tools.stringAt(p, 0), t));
            return r;
        } else if (Tools.termType(p, "Explode")) {
            debug("Explode case");
            
            AFun ctor = t.getAFun();
            ATermList args = t.getArguments();

            ATermAppl ctor_p = Tools.applAt(p, 0);
            ATerm ctor_t = env.makeTerm("\"" + ctor.getName() + "\"");

            debug(" " + ctor_t + " / " + ctor_p); 
            
            List<Pair<String, ATerm>> r = match(env, ctor_t, ctor_p);
            if (r == null)
                return null;

            ATermAppl appl_p = Tools.applAt(p, 1);

            List<Pair<String, ATerm>> x = match(env, env.makeList(args), appl_p);
            if ( x == null )
                return null;
            r.addAll(x);
            return r;
        } else if (Tools.termType(p, "As")) {
            List<Pair<String, ATerm>> r = match(env, t, Tools.applAt(p, 1));
            if (r == null)
                return null;
            debug("" + p);
            String varName = Tools.stringAt(Tools.applAt(p, 0), 0);
            r.add(new Pair<String, ATerm>(varName, t));
            return r;
        } else if(Tools.termType(p, "Wld")) {
            return emptyList();
        }

        throw new FatalError("What?" + p);
    }

    public List<Pair<String, ATerm>> match(IContext env, ATermInt t, ATermAppl p)
            throws FatalError {
        debug(" !" + t + " ?" + p);

        if (p.getName().equals("Anno")) {
            return match(env, t, Tools.applAt(p, 0));
        } else if (p.getName().equals("Int")) {
            Integer i = new Integer(Tools.stringAt(p, 0));
            if (i == t.getInt())
                return emptyList();
            return null;
        } else if (p.getName().equals("Var")) {
            List<Pair<String, ATerm>> r = new ArrayList<Pair<String, ATerm>>();
            r.add(new Pair<String, ATerm>(((ATermAppl) p.getChildAt(0))
                    .getName(), t));
            return r;
        } else if (p.getName().equals("Op")) {
            return null;
        } else if (p.getName().equals("Explode")) {
            if(Tools.applAt(p,0).getName().equals("Wld")) {
                List<Pair<String, ATerm>> r = new ArrayList<Pair<String, ATerm>>();
                String varName = Tools.stringAt(Tools.applAt(p, 1), 0);
                r.add(new Pair<String, ATerm>(varName, env.makeList()));
                return r;
            }
            else
                return null;
        } else if (p.getName().equals("Wld")) {
            return emptyList();
        } else if (p.getName().equals("As")) {
            List<Pair<String, ATerm>> r = new ArrayList<Pair<String, ATerm>>();
            String varName = Tools.stringAt(Tools.applAt(p, 0), 0);
            r.add(new Pair<String, ATerm>(varName, t));
            return r;
        }

        throw new FatalError("Unknown type '" + p.getName());
    }

    public List<Pair<String, ATerm>> match(IContext env, ATerm t, ATermAppl p)
            throws FatalError {
        if (t.getType() == ATerm.APPL)
            return match(env, (ATermAppl) t, p);
        else if (t.getType() == ATerm.INT)
            return match(env, (ATermInt) t, p);
        else if (t.getType() == ATerm.INT)
            return match(env, (ATermReal) t, p);

        throw new FatalError("Current term is not an ATermAppl term ["
                + t.getClass().toString() + " " + ATerm.APPL + " "
                + t.getType() + "]");
    }
}
