/*
 * Created on 05.jul.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interpreter.test;

import java.io.FileInputStream;
import java.io.IOException;

import junit.framework.TestCase;

import org.spoofax.interpreter.BAFReader;
import org.spoofax.interpreter.FatalError;

import aterm.ATerm;
import aterm.pure.PureFactory;

public class BAFReaderTest extends TestCase {

    protected String basePath;

    protected void setUp() {
        basePath = "/home/karltk/source/oss/spoofax/spoofax/core/tests/data/baf/";
    }

    public void testBAF1() throws IOException, FatalError {
        runReader("test1");
    }

    public void testBAF2() throws IOException, FatalError {
        runReader("test2");
    }

    public void testBAF3() throws IOException, FatalError {
        runReader("test3");
    }

    public void testBAF4() throws IOException, FatalError {
        runReader("test4");
    }

    public void testBAF5() throws IOException, FatalError {
        runReader("test5");
    }

    public void testBAF6() throws IOException, FatalError {
        runReader("test6");
    }

    private void runReader(String path) throws IOException, FatalError {
        PureFactory factory = new PureFactory();

        FileInputStream bafFile;

        bafFile = new FileInputStream(basePath + path + ".baf");
        BAFReader bafReader = new BAFReader(factory, bafFile);
        ATerm baf = bafReader.readBAF();

        FileInputStream tafFile = new FileInputStream(basePath + path + ".taf");
        ATerm taf = factory.readFromFile(tafFile);

        assertTrue(taf.match(baf) != null);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(BAFReaderTest.class);
    }
}
