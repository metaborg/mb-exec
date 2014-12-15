/**
 * 
 */
package org.metaborg.meta.stratego.interpreter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.spoofax.interpreter.library.spx.SPXInterpreterLibrary;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.jsglr.client.InvalidParseTableException;
import org.spoofax.jsglr.client.ParseException;
import org.spoofax.jsglr.client.ParseTable;
import org.spoofax.jsglr.client.SGLR;
import org.spoofax.jsglr.client.imploder.TreeBuilder;
import org.spoofax.jsglr.io.ParseTableManager;
import org.spoofax.jsglr.shared.BadTokenException;
import org.spoofax.jsglr.shared.SGLRException;
import org.spoofax.jsglr.shared.TokenExpectedException;
import org.spoofax.terms.ParseError;

/**
 * 
 * Provides an interpreter-based execution of the Stratego frontend on Stratego
 * modules.
 * 
 * This class is a wrapper over the Stratego interpreter. It loads the necessary
 * Stratego libraries, then loads the Stratego frontend code. When invoked on
 * Stratego modules this class applies the Stratego frontend. The Stratego
 * frontend that is applied can procees fine grained bits of Stratego such as
 * Strategy expressions.
 * 
 * @author vladvergu
 *
 */
public class StrategoFrontend {

	private final StrategoInterpreter interp;
	private final SGLR parser;

	public StrategoFrontend(Path libraryLocation, Path frontendLocation) {
		// create an Interpreter
		interp = new StrategoCoreInterpreter();
		interp.addOperatorRegistry(new SPXInterpreterLibrary());

		// load libraries
		try {
			System.out.println("Frontend loading library...");
			interp.load(makePath("libstratego-lib/libstratego-lib.ctree",
					libraryLocation));
			System.out.println("Frontend loading library...");
			interp.load(makePath("libstrc/libstrc.ctree", libraryLocation));
			System.out.println("Frontend loading library...");
			interp.load(makePath("libstratego-aterm/libstratego-aterm.ctree",
					libraryLocation));
			System.out.println("Frontend loading library...");
			interp.load(makePath("libstratego-gpp/libstratego-gpp.ctree",
					libraryLocation));
			System.out.println("Frontend loading library...");
			interp.load(makePath("libstratego-rtg/libstratego-rtg.ctree",
					libraryLocation));
			System.out.println("Frontend loading library...");
			interp.load(makePath("libstratego-sdf/libstratego-sdf.ctree",
					libraryLocation));
			System.out.println("Frontend loading library...");
			interp.load(makePath("libstratego-sglr/libstratego-sglr.ctree",
					libraryLocation));
			System.out.println("Frontend loading library...");
			interp.load(makePath(
					"libstratego-tool-doc/libstratego-tool-doc.ctree",
					libraryLocation));
			System.out.println("Frontend loading library...");

			interp.load(makePath("frontend.ctree", frontendLocation));
			System.out.println("Frontend loading library...");

			ParseTableManager tblMgr = new ParseTableManager();
			ParseTable table = tblMgr.loadFromFile(makePath(
					"Stratego-Shell.tbl", frontendLocation).toFile()
					.getAbsolutePath());
			parser = new SGLR(new TreeBuilder(), table);
			parser.setUseStructureRecovery(false);

		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ParseError e) {
			throw new RuntimeException(e);
		} catch (InvalidParseTableException e) {
			throw new RuntimeException(e);
		}

	}

	private static Path makePath(String name, Path inPath) throws IOException {
		return new File(inPath.toFile(), name).toPath();
	}

	public IStrategoAppl parseAndCompile(Path p) throws TokenExpectedException,
			BadTokenException, ParseException, SGLRException,
			InterruptedException, IOException {
		return compile((IStrategoTerm) parser.parse(
				new String(Files.readAllBytes(p)), p.toAbsolutePath()
						.toString(), "Toplevel"));

	}

	public IStrategoAppl parseAndCompile(String code)
			throws TokenExpectedException, BadTokenException, ParseException,
			SGLRException, InterruptedException {

		return compile((IStrategoTerm) parser.parse(code, "stdin", "TopLevel"));
	}

	public IStrategoAppl compile(IStrategoTerm term) {
		interp.setCurrentTerm(term);
		if (!interp.invoke("spx_shell_frontend_0_0")) {
			return null;
		}

		return (IStrategoAppl) interp.getCurrentTerm();
	}

}
