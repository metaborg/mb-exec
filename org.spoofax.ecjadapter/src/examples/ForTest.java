/*
 * Created on 12. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package examples;

public class ForTest {

    public static void badFor() {
        String x = "foo";
        for(int i = 0; i < x.length(); i++)
            x.charAt(i);
    }
    
    public static void goodFor() {
        String x = "foo";
        {
            final int sz = x.length();
            for(int i = 0; i < sz; i++)
                x.charAt(i);
        }
    }

    public static void main(String[] args) {
    }
}
