package org.spoofax.interpreter.compiler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.adapters.aterm.WrappedATermFactory;
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
		Compiler compiler = new Compiler(new WrappedATermFactory());
		String[] path = { "data/sglr", "data/sig",
					      "data/libstrlib", "data/xtc",
				          "data/c-tools", "data/gpp",
						  "data/rtg", "data/xtc-tools" };
		IStrategoTerm out = compiler.compile("data/strc-core/dump-strc.str", path);

		if (out == null)
			System.err.println("Had an error!");
		else {
			FileOutputStream f = new FileOutputStream("data/out.ctree");
			PrintStream fs = new PrintStream(f);
			fs.print(out);
			f.close();
		}
	}

}
