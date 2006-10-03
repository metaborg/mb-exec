/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 *
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter;

import java.io.IOException;

import org.spoofax.interpreter.adapters.aterm.WrappedATermFactory;
import org.spoofax.interpreter.library.SSL;
import org.spoofax.interpreter.stratego.DebugUtil;
import org.spoofax.interpreter.stratego.SDefT;
import org.spoofax.interpreter.stratego.StupidFormatter;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class Interpreter {

    private Context context;
    private StrategoCoreLoader loader;

    public Interpreter() {
        doInit(new WrappedATermFactory());
    }

    public Interpreter(ITermFactory factory) {
        doInit(factory);
    }

    private void doInit(ITermFactory factory) {
        
        Context.indentation = 0;
        context = new Context(factory, new WrappedATermFactory());
        
        loader = new StrategoCoreLoader(context);

        if(DebugUtil.resetSSL) {
            SSL.init();//todo: temporary to verify the hypothesis that the global state causes trouble.
        }
    }
    

    public boolean invoke(String name) throws InterpreterException {
        SDefT def = context.lookupSVar(name);

        if (def == null) {
            throw new InterpreterException("Definition '" + name + "' not found");
        }

        return def.getBody().eval(context);
    }

    public IContext getContext() {
        return context;
    }

    public void setCurrent(IStrategoTerm inp) {
        context.setCurrent(inp);
    }

    public IStrategoTerm current() {
        return context.current();
    }

    public String prettyPrint() throws InterpreterException {
        StupidFormatter sf = new StupidFormatter();
        SDefT s = context.lookupSVar("main_0_0");
        s.prettyPrint(sf);
        return sf.toString();
    }

    public void shutdown() {
        //todo perf: this takes 2 secs overall
        //if(DebugUtil.cleanupInShutdown) {
        //    context.cleanup();
        //}
    }

    public ITermFactory getFactory() {
        return context.getFactory();
    }

    public void load(String file) throws IOException, InterpreterException {
        loader.load(file);
    }
}
