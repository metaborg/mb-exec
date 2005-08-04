/*
 * Created on 17.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import aterm.ATermList;

public class ExtStrategy extends Strategy {

    private String name;
    private Method methodPointer;
    
    public ExtStrategy(String name, int svarArity, int tvarArity, Method method) {
        this.name = name;
        termParams = new ArrayList<String>(tvarArity);
        stratParams = new ArrayList<String>(svarArity);

        methodPointer = method;
    }

    public String getName() { return name; }
    
    public boolean invoke(Interpreter itp, ATermList svars, ATermList tvars) throws FatalError {
        try {
            Object ob = methodPointer.invoke(null, new Object[] { itp, svars, tvars });
            return ((Boolean)ob).booleanValue();
        } catch (SecurityException e) {
            e.printStackTrace();
            throw new FatalError("SecurityException");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new FatalError("IllegalArgumentException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new FatalError("IllegalAccessException");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new FatalError("InvocationTargetException");
        }
    }
}
