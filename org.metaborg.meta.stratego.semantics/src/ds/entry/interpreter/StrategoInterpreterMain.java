package ds.entry.interpreter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.ParseError;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.io.binary.TermReader;

public class StrategoInterpreterMain {

	public static void main(String[] args) throws ParseError,
			FileNotFoundException, IOException {
		TermFactory tf = new TermFactory();
		
		System.out.println("Preping interpreter");
		
		StrategoCoreInterpreter interpreter = new StrategoCoreInterpreter();
		interpreter.reset();
		
		// 0. determine the number of ctrees to load
		int numCtrees = Integer.parseInt(args[0]);

		// 1. load ctrees
		for (int i = 1; i <= numCtrees; i++) {
			String ctreeFile = args[i];
			IStrategoTerm ctree = new TermReader(tf)
					.parseFromStream(new FileInputStream(ctreeFile));
			interpreter.loadCtree(ctree);
		}

		// 2. Read current term into ATerm
		IStrategoTerm currentTerm = args.length == numCtrees + 2 ? new TermReader(
				tf).parseFromString(args[numCtrees + 1]) : null;
		interpreter.setCurrentTerm(currentTerm);
		System.out.println("Done preping");
		
		// 4. Call the main_0_0 strategy
		System.out.println("Executing");
		interpreter.invoke("main_0_0");
		
		System.out.println("Completed with: " + interpreter.getCurrentTerm());
	}

}
