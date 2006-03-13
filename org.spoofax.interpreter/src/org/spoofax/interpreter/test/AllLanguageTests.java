/*
 * Created on 02.mar.2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllLanguageTests {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for org.spoofax.interpreter.test");
        //$JUnit-BEGIN$
        suite.addTestSuite(DefinitionTest.class);
        suite.addTestSuite(TermDeconstructionTest.class);
        suite.addTestSuite(TermConstructionTest.class);
        suite.addTestSuite(ScopingTest.class);
        suite.addTestSuite(BuildTest.class);
        suite.addTestSuite(CongruenceTest.class);
        suite.addTestSuite(OverloadingTest.class);
        suite.addTestSuite(GuardTest.class);
        suite.addTestSuite(ArithmeticTest.class);
        suite.addTestSuite(ClosureTest.class);
        suite.addTestSuite(MatchTest.class);
        suite.addTestSuite(HigherOrderParamTest.class);
        //$JUnit-END$
        return suite;
    }

}
