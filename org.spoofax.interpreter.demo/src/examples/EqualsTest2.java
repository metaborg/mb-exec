/*
 * Created on 12. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package examples;

public class EqualsTest2 {
    public boolean equals(Object o) {
        if(!(o instanceof Object))
            return false;
        return true;
    }
}