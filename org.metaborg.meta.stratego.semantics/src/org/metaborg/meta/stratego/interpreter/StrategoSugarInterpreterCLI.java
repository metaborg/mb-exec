package org.metaborg.meta.stratego.interpreter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.metaborg.meta.interpreter.framework.InterpreterException;
import org.metaborg.meta.interpreter.framework.InterpreterExitException;
import org.spoofax.interpreter.library.IOperatorRegistry;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class StrategoSugarInterpreterCLI {

	public static void main(String[] args) throws IOException {

		String mainStrategyName = "main_0_0";
		List<String> programFiles = new LinkedList<>();
		List<String> libraryFiles = new LinkedList<>();
		List<String> programArgs = new LinkedList<>();
		String libraryLocation = "";
		String frontendLocation = "";

		boolean inProgramArgsSection = false;
		for (int i = 0; i < args.length; i++) {
			if (inProgramArgsSection) {
				programArgs.add(args[i]);
			} else if (args[i].equals("-Ilib")) {
				libraryLocation = args[i + 1];
			} else if (args[i].equals("-Ifront")) {
				frontendLocation = args[i + 1];
			} else if (args[i].equals("-i")) {
				programFiles.addAll(Arrays.asList(args[i + 1].split(",")));
			} else if (args[i].equals("-l")) {
				libraryFiles.addAll(Arrays.asList(args[i + 1].split(",")));
			} else if (args[i].equals("-m")) {
				mainStrategyName = args[i + 1];
			} else if (args[i].equals("--")) {
				inProgramArgsSection = true;
			}
		}

		StrategoInterpreter interp = new StrategoSugarInterpreter(new File(
				libraryLocation).toPath(), new File(frontendLocation).toPath());

		// load libraries
		for (String lib : libraryFiles) {
			interp.addOperatorRegistry(findOperatorRegistry(lib,
					interp.getTermFactory()));
		}

		// load program files
		for (String prg : programFiles) {
			interp.load(new File(prg).toPath());
		}

		final IStrategoTerm[] pa = new IStrategoTerm[programArgs.size()];
		for (int i = 0; i < pa.length; i++)
			pa[i] = interp.getTermFactory().makeString(programArgs.get(i));

		interp.setCurrentTerm(interp.getTermFactory().makeList(pa));
		try {
			boolean r = interp.invoke(mainStrategyName);
			if (r) {
				System.out.println(interp.getCurrentTerm());
			} else {
				System.out.println("rewriting failed");
				System.exit(-1);
			}
		} catch (InterpreterExitException iex) {
			System.out.println("Exit with status: " + iex.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static IOperatorRegistry findOperatorRegistry(String s,
			ITermFactory factory) throws InterpreterException {
		try {
			Class<?> clazz = Class.forName(s);
			final Constructor<?> m = (Constructor<?>) clazz
					.getConstructor(ITermFactory.class);
			return (IOperatorRegistry) m.newInstance(factory);
		} catch (ReflectiveOperationException e) {
			throw new InterpreterException("Operator registry load failed", e);
		}
	}
}
