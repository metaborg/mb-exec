/*
 * Created on 17.jul.2005
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.core;

public class Pair<F, S> {
    public F first;
    public S second;
    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "<" + first.toString() + "," + second.toString() + ">";
    }
}
