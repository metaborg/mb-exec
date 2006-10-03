import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.spoofax.interpreter.adapters.ecj.ECJFactory;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.PrettyPrinter;

/*
 * Created on 27. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */

public class ParseTest {

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

    static void parse(String file) throws FileNotFoundException, IOException {
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        System.out.println("Reading " + file);
        parser.setSource(getBytes(file));
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        System.out.println(cu);
        ECJFactory wef = new ECJFactory();
        IStrategoTerm t = wef.parseFromTree(cu);
        PrettyPrinter pp = new PrettyPrinter();
        t.prettyPrint(pp);
        //System.out.println(pp.getString());
    }
    
    static void recurse(File base) throws FileNotFoundException, IOException {
        for(String s : base.list()) {
            if(s.endsWith(".java"))
                parse(base.getAbsolutePath() + "/" + s);
            else {
                File x = new File(base.getAbsolutePath() + "/" + s);
                if(x.isDirectory())
                    recurse(x);
            }
            //System.out.println(s);
        }
        
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File f = new File(args[0]);
        
        recurse(f);
        System.out.println("Finished");
    }
    
}
