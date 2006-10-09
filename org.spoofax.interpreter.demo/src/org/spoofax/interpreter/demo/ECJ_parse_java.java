/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.demo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.adapters.ecj.ECJFactory;
import org.spoofax.interpreter.library.Primitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_parse_java extends Primitive {

    protected ECJ_parse_java() {
        super("ECJ_parse_java", 0, 1);
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
    
    @Override
    public boolean call(IContext env, List<Strategy> svars, IStrategoTerm[] tvars) throws InterpreterException {
         
        if(!Tools.isTermString(tvars[0]))
             return false;
        
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        try {
            parser.setSource(getBytes(Tools.javaString(tvars[0])));
        } catch(FileNotFoundException e) {
            return false;
        } catch(IOException e) {
            return false;
        }
        
        ASTNode ast = parser.createAST(null);
        ECJFactory f = ((ECJFactory)env.getFactory());
        IStrategoTerm t = f.parseFromTree(ast);
        
        env.setCurrent(t);
        return true;
    }

}
