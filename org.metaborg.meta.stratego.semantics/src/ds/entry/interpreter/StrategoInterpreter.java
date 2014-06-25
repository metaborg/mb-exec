package ds.entry.interpreter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.metaborg.meta.interpreter.framework.AValue;
import org.metaborg.meta.interpreter.framework.INodeSource;
import org.metaborg.meta.interpreter.framework.ImploderNodeSource;
import org.metaborg.meta.interpreter.framework.NodeList;
import org.metaborg.meta.interpreter.framework.NodeUtils;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.jsglr.client.imploder.ImploderAttachment;
import org.spoofax.terms.ParseError;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.io.binary.TermReader;

import com.github.krukow.clj_ds.PersistentMap;
import com.github.krukow.clj_lang.PersistentTreeMap;

import ds.generated.interpreter.CallT_3;
import ds.generated.interpreter.Generic_Module;
import ds.generated.interpreter.I_Module;
import ds.generated.interpreter.I_STerm;
import ds.generated.interpreter.I_Strategy;
import ds.generated.interpreter.SVar_1;
import ds.generated.interpreter.topdefs_1;

public class StrategoInterpreter {

	public static void main(String[] args) throws ParseError,
			FileNotFoundException, IOException {
		TermFactory tf = new TermFactory();

		// 0. determine the number of ctrees to load
		int numCtrees = Integer.parseInt(args[0]);

		// 1. load ctrees
		PersistentMap<Object, Object> sdefs = new PersistentTreeMap<>();
		for (int i = 1; i <= numCtrees; i++) {
			String ctreeFile = args[i];
			System.out.println("Reading ctree " + ctreeFile);
			IStrategoTerm ctree = new TermReader(tf)
					.parseFromStream(new FileInputStream(ctreeFile));
			INodeSource ctreeSource = ctree
					.getAttachment(ImploderAttachment.TYPE) != null ? new ImploderNodeSource(
					ctree.getAttachment(ImploderAttachment.TYPE)) : null;
			I_Module ctreeNode = new Generic_Module(ctreeSource, ctree);
			System.out.println("Specializing ctree " + ctreeFile);
			ctreeNode = (I_Module) NodeUtils.eagerReplacement(ctreeNode);
			System.out.println("Loading ctree " + ctreeFile);
			sdefs = new topdefs_1(ctreeSource, ctreeNode).exec_sdefs(sdefs).value;
			System.out.println("Done processing " + ctreeFile);
		}
		System.out.println("Loaded " + sdefs.size() + " strategy definitions");

		// 2. Read current term into ATerm
		IStrategoTerm currentTerm = args.length == numCtrees + 2 ? new TermReader(
				tf).parseFromString(args[numCtrees + 1]) : null;
		System.out.println("CURTERM " + currentTerm);

		// 4. Call the main_0_0 strategy
		PersistentMap<Object, Object> env = new PersistentTreeMap<>();

		CallT_3 mainCall = new CallT_3(null, new SVar_1(null, "main_0_0"),
				NodeList.NIL(I_Strategy.class), NodeList.NIL(I_STerm.class));

		AValue result = mainCall.exec_default(sdefs, tf, currentTerm, env).value;
		System.out.println("Completed with: " + result + "\n"
				+ NodeUtils.toString(result));
	}

}
