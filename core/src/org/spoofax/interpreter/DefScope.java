/*
 * Created on 30.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interpreter;

import java.util.HashMap;
import java.util.Map;

public class DefScope {

    private DefScope parent;
    
    private Map<String, Constructor> constructors;
    private Map<String, Strategy> strategies;
//    private Map<String, ExtStrategy> externalStrategies;

    public DefScope(DefScope parent) {
        this.parent = parent;
        
        constructors = new HashMap<String, Constructor>();
        strategies = new HashMap<String, Strategy>();
//        externalStrategies = new HashMap<String, ExtStrategy>();
    }
    

    public Constructor lookupConstructor(String name) {
        Constructor c = constructors.get(name);
        if(c == null && parent != null)
            return parent.lookupConstructor(name);
        return c;
    }

    public Strategy lookupStrategy(String name) {
        Strategy c = strategies.get(name);
        if(c == null && parent != null)
            return parent.lookupStrategy(name);
        return c;
    }
/*
    public Strategy lookupExtStrategy(String name) {
        ExtStrategy c = externalStrategies.get(name);
        if(c == null && parent != null)
            return parent.lookupExtStrategy(name);
        return c;
    }
*/
    public void addConstructor(Constructor c) {
        constructors.put(c.getName(), c);
    }    

    public void addStrategy(Strategy c) {
        strategies.put(c.getName(), c);
    }    
/*
    public void addExtStrategy(ExtStrategy c) {
        externalStrategies.put(c.getName(), c);
    }    
*/


    public DefScope getParent() {
        return parent;
    }
}
