/*
 * Created on 25.nov.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

public class StupidFormatter {
    
    private int indentation;
    private StringBuilder sb;
    private static char[] dummySpace = null;
    
    public StupidFormatter() {
        indentation = 0;
        sb = new StringBuilder();
        if(dummySpace == null) {
            String s = "                                      ";
            dummySpace = new char[s.length()];
            s.getChars(0, s.length(), dummySpace, 0);
        }
    }
    
    public int bump(int w) {
        indentation += w;
        if(indentation > dummySpace.length)
            indentation = dummySpace.length;
        return indentation;
    }
    
    public int unbump(int w) {
        indentation -= w;
        if(indentation < 0)
            indentation = 0;
        return indentation;
    }
    
    public void append(String s) {
        sb.append(dummySpace, 0, indentation);
        sb.append(s);
    }
    
    @Override
    public String toString() {
        return sb.toString();
    }

    public void line(String s) {
        append(s + "\n");
    }

    public void first(String s) {
        sb.append(s + "\n");
    }
}
