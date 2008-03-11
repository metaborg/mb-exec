/*
 * Created on 02.mar.2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.spoofax.interpreter.test.language.TestBuild;
import org.spoofax.interpreter.test.language.TestClosure;
import org.spoofax.interpreter.test.language.TestCongruence;
import org.spoofax.interpreter.test.language.TestDefinition;
import org.spoofax.interpreter.test.language.TestGuard;
import org.spoofax.interpreter.test.language.TestHigherOrderParameters;
import org.spoofax.interpreter.test.language.TestMatch;
import org.spoofax.interpreter.test.language.TestOverloading;
import org.spoofax.interpreter.test.language.TestScoping;
import org.spoofax.interpreter.test.language.TestTermConstruction;
import org.spoofax.interpreter.test.language.TestTermDeconstruction;

public class AllLanguageTests {

    public static Test suite() throws IOException {
        TestSuite suite = new TestSuite("Test for org.spoofax.interpreter.test");
        //$JUnit-BEGIN$
        suite.addTestSuite(TestDefinition.class);
        suite.addTestSuite(TestTermDeconstruction.class);
        suite.addTestSuite(TestTermConstruction.class);
        suite.addTestSuite(TestScoping.class);
        suite.addTestSuite(TestBuild.class);
        suite.addTestSuite(TestCongruence.class);
        suite.addTestSuite(TestOverloading.class);
        suite.addTestSuite(TestGuard.class);
        suite.addTestSuite(ArithmeticTest.class);
        suite.addTestSuite(TestClosure.class);
        suite.addTestSuite(TestMatch.class);
        suite.addTestSuite(TestHigherOrderParameters.class);
        suite.addTestSuite(Pause.class);
        //$JUnit-END$
        return suite;
    }

}
