package ds.entry.interpreter;

import java.io.FileInputStream;
import java.io.IOException;

import org.spoofax.terms.TermFactory;

public class StrategoInterpreterMain {

	private static final int numRuns = 50;

	public static void main(String[] args) throws IOException {
		System.out.println("Preping interpreter");

		StrategoCoreInterpreter interpreter = new StrategoCoreInterpreter();
		interpreter.reset();

		// 0. determine the number of ctrees to load
		int numCtrees = Integer.parseInt(args[0]);

		// 1. load ctrees
		for (int i = 1; i <= numCtrees; i++) {
			String ctreeFile = args[i];
			interpreter.loadCTree(new FileInputStream(ctreeFile));
		}

		// 2. Read current term into ATerm
		TermFactory tf = new TermFactory();

		System.out.println("Done preping");

		// 4. Call the main_0_0 strategy
		for (int i = 0; i < numRuns; i++) {
			interpreter.setCurrentTerm(tf.makeList(tf.makeString("Main")));
			System.out.println("Executing run " + (i + 1) + "/" + numRuns);
			long st = System.currentTimeMillis();
			interpreter.invoke("main_0_0");
			long et = System.currentTimeMillis();
			System.out.println("Execution duration: " + (et - st)
					/ (double) 1000);
		}
		System.out.println("Completed with: " + interpreter.getCurrentTerm());
	}

}
