package org.spoofax.compiler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.jsglr.InvalidParseTableException;

public class CompileStratego {

	/**
	 * @param args
	 * @throws InvalidParseTableException 
	 * @throws InterpreterException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, InterpreterException, InvalidParseTableException {
		Compiler compiler = new Compiler();
		String strcBasepath = System.getProperty("strc.basepath");
		if(strcBasepath == null)
			strcBasepath = System.getProperty("user.dir") + "/.nix-profile"; 
				
		String[] path = { "data/trunk/stratego-libraries/sglr/lib", 
				"data/trunk/stratego-front/sig",
				"data/trunk/strc-core/lib", 
				"data/trunk/stratego-libraries/lib/spec",
				"data/trunk/stratego-libraries/xtc/lib", 
				"data/trunk/c-tools/sig",
				"data/trunk/stratego-libraries/gpp/lib", 
				"data/trunk/stratego-libraries/rtg/lib",
				"data/trunk/xtc/tools"};
		IStrategoTerm out = null;
		try {
		  out = compiler.compile("data/jstrc.str", path, false);
		}
		catch (StackOverflowError e) {
			System.err.println("Size of the stack: " + e.getStackTrace().length);
			e.printStackTrace();
		}
		
		if (out == null)
			System.err.println("Compilation of jstrc failed!");
		else {
			FileOutputStream f = new FileOutputStream("data/comp-jstrc.ctree");
			PrintStream fs = new PrintStream(f);
			fs.print(out);
			f.close();
		}
		
		compiler.compile("data/trunk/strc-core/lib/strc.str", path, true);
		if (out == null)
			System.err.println("Compilation of libstrc failed!");
		else {
			FileOutputStream f = new FileOutputStream("data/comp-libstrc.ctree");
			PrintStream fs = new PrintStream(f);
			fs.print(out);
			f.close();
		}
	}

}
