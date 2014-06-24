package ds.entry.interpreter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.metaborg.meta.interpreter.framework.AValue;
import org.metaborg.meta.interpreter.framework.ImploderNodeSource;
import org.metaborg.meta.interpreter.framework.NodeUtils;
import org.metaborg.meta.interpreter.framework.PersistentMap;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.jsglr.client.imploder.ImploderAttachment;
import org.spoofax.terms.ParseError;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.io.binary.TermReader;

import ds.generated.interpreter.Generic_Module;
import ds.generated.interpreter.topdefs_1;

public class StrategoInterpreter {

	public static void main(String[] args) throws ParseError,
			FileNotFoundException, IOException {
		TermFactory tf = new TermFactory();

		// 1. Read .ctree into ATerm
		IStrategoTerm programATerm = new TermReader(tf)
				.parseFromStream(new FileInputStream(args[0]));
		System.out.println("PROGRAM " + programATerm);

		// 2. Read current term into ATerm
		IStrategoTerm currentTerm = args.length > 1 ? new TermReader(tf)
				.parseFromString(args[1]) : null;
		System.out.println("CURTERM " + currentTerm);

		// 2. Construct the root node of the interpreter
		Generic_Module programNode = new Generic_Module(
				programATerm.getAttachment(ImploderAttachment.TYPE) != null ? new ImploderNodeSource(
						programATerm.getAttachment(ImploderAttachment.TYPE))
						: null, programATerm);
		System.out.println("PROGRAM NODE " + NodeUtils.toString(programNode));

		// 3. Invoke the exec_defs method on the root node
		PersistentMap<Object, Object> sdefs = new topdefs_1(programNode.getSourceInfo(), programNode).exec_sdefs().value;
		System.out.println("SDEFS " + sdefs);

		// 4. Call the main_0_0 strategy
		PersistentMap<Object, Object> env = new PersistentMap<>();

		AValue result = programNode.exec_default(sdefs, tf, currentTerm, env).value;
		System.out.println("Completed with: " + result + "\n"
				+ NodeUtils.toString(result));
	}

}
