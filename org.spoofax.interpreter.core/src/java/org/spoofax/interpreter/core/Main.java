/*
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.core;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.library.IOperatorRegistry;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class Main {

    public static void main(String args[]) throws IOException {

        String[] files = null;
        String factoryName = null;
        boolean waitForProfiler = false;
        boolean inProgramArgs = false;
        final List<String> programArgs = new LinkedList<String>();
        final List<String> libraryArgs = new LinkedList<String>();

        for (int i = 0; i < args.length; i++) {
        	if(inProgramArgs) {
        		programArgs.add(args[i]);
        	} else if (args[i].equals("--debug")) {
                DebugUtil.setDebug(true);
            } else if (args[i].equals("-i")) {
                files = args[i + 1].split(",");
            } else if(args[i].equals("-l")) {
                libraryArgs.add(args[i + 1]);
            } else if(args[i].equals("-f")) {
                factoryName = args[i + 1];
            } else if (args[i].equals("--wait-for-profiler")) {
                waitForProfiler = true;
            } else if (args[i].equals("--trace")) {
                DebugUtil.tracing = true;
            } else if(args[i].equals("--")) {
            	inProgramArgs = true;
            }
        }

        final Interpreter itp = createInterpreter(factoryName);
        loadLibraries(itp, libraryArgs, itp.getFactory());

        try {
//            long loadTime = System.nanoTime();
            for(String fn : files) {
                System.err.println("Loading " + fn);
                itp.load(fn);
            }
//            System.out.println("Load time: " + (System.nanoTime() - loadTime)/1000/1000 + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterpreterException e) {
            e.printStackTrace();
        }

        try {
//            long runTime = System.nanoTime();
        	final IStrategoTerm[] as = new IStrategoTerm[programArgs.size()];
        	final ITermFactory factory = itp.getFactory();
        	for(int i = 0; i < as.length; i++)
        		as[i] = factory.makeString(programArgs.get(i));
            itp.setCurrent(itp.getFactory().makeList(as));

            boolean r = itp.invoke("main_0_0");

//            System.out.println("Run time: " + (System.nanoTime() - runTime)/1000/1000 + "ms");

            if(r) {
                System.out.println("" + itp.current());
            } else {
                System.err.println("rewriting failed");
                System.exit(-1);
            }
        } catch (InterpreterExit e) {
            System.out.println("Exit with status: "  + e.getValue());
        } catch (InterpreterException e) {
            e.printStackTrace();
        }

        if(waitForProfiler)
            System.in.read();
    }

    @SuppressWarnings("unchecked")
    private static Interpreter createInterpreter(String factoryName) {
        if(factoryName == null)
            return new Interpreter();
        try {
            Class<ITermFactory> clazz = (Class<ITermFactory>) Class.forName(factoryName);
            Constructor<ITermFactory> ctor = clazz.getConstructor();
            ITermFactory factory = ctor.newInstance();
            return new Interpreter(factory, factory); // new BasicTermFactory());
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.err.println("Fatal: failed to instantiate term factory '" + factoryName + "'");
        System.exit(-2);
        return null;
    }

    private static void loadLibraries(Interpreter itp, List<String> libraryArgs, ITermFactory factory) {
        for(String s : libraryArgs) {
            final IOperatorRegistry ior = findOperatorRegistry(s, factory);
            if(ior == null) {
                System.err.println("Unable to load '" + s + "'");
            } else {
                itp.addOperatorRegistry(ior);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static IOperatorRegistry findOperatorRegistry(String s, ITermFactory factory) {
        try {
            final Class<?> clazz = Class.forName(s);
            final Constructor<IOperatorRegistry> m = (Constructor<IOperatorRegistry>) clazz.getConstructor(ITermFactory.class);
            return m.newInstance(factory);
        } catch(ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}