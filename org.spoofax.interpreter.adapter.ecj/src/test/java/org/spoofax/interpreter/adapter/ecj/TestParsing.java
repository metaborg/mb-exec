/*
 * Created on 27. sep.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Before;
import org.junit.Test;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;


public class TestParsing {

    private ECJFactory wef;

    @Before
    public void setUp() {
        wef = new ECJFactory();
    }
    
    private static char[] getBytes(String fileName) throws FileNotFoundException, IOException {

        BufferedReader r = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String s = r.readLine();
        while(s != null) {
            sb.append(s);
            s = r.readLine();
        }
            
        return sb.toString().toCharArray();
    }

    private static CompilationUnit parseCompilationUnit(String fileName) throws FileNotFoundException, IOException {
        //  System.out.println("Reading " + file);
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setSource(getBytes(fileName));
        return (CompilationUnit) parser.createAST(null);
    }
      
    private IStrategoTerm parse(String fileName) throws FileNotFoundException, IOException {
        return wef.parseFromTree(parseCompilationUnit(fileName));
    }

    private String asString(IStrategoTerm t) throws IOException {
        StringWriter sw = new StringWriter();
        t.writeAsString(sw, IStrategoTerm.INFINITE);
        return sw.toString();
    }
    
    private void findAllFiles(File base, Collection<String> acc) throws FileNotFoundException, IOException {
        for(String s : base.list()) {
            if(s.endsWith(".java"))
                acc.add(base.getAbsolutePath() + "/" + s);
            else {
                File x = new File(base.getAbsolutePath() + "/" + s);
                if(x.isDirectory())
                    findAllFiles(x, acc);
            }
        }
        
    }
    
    private Collection<String> findAllFiles(File base) throws FileNotFoundException, IOException {
        ArrayList<String> r = new ArrayList<String>();
        findAllFiles(base, r);
        return r;
    }
    
    @Test
    public void parse_all_ecj_source_code() throws FileNotFoundException, IOException {
        for(String file : findAllFiles(new File("src/main/java")))
            assertTrue(asString(parse(file)).length() > 0);
    }

    @Test
    public void apply_term_equals_against_self_for_all_source_files() throws FileNotFoundException, IOException {
        for(String file : findAllFiles(new File("src/main/java")))
            assertEquals(parse(file), parse(file));
    }

    @Test
    public void term_match_against_self_for_all_source_files() throws FileNotFoundException, IOException {
        for(String file : findAllFiles(new File("src/main/java")))
            assertTrue(parse(file).match(parse(file)));
    }

    @Test
    public void validate_top_level_term_structure() throws FileNotFoundException, IOException {
        IStrategoTerm cu = parse("src/main/java/org/spoofax/interpreter/adapter/ecj/ECJFactory.java");
        System.out.println(cu);

        assertEquals(3, cu.getSubtermCount());
        assertEquals("CompilationUnit", ((IStrategoAppl)cu).getName());
        
        IStrategoAppl pkg = (IStrategoAppl) cu.getSubterm(0);
        assertEquals(3, pkg.getSubtermCount());
        assertEquals("PackageDeclaration", pkg.getName());
    }

}
