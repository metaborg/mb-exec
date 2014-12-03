package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;
import org.spoofax.interpreter.terms.*;
import org.spoofax.jsglr.client.imploder.ImploderAttachment;
import org.spoofax.interpreter.core.Tools;

public class Generic_I_Strategy extends AbstractNode implements I_Strategy,
		IGenericNode {
	public IStrategoTerm aterm;

	public Generic_I_Strategy(INodeSource source, IStrategoTerm term) {
		this.setSourceInfo(source);
		this.aterm = term;
	}

	@Override
	public <T> T match(Class<T> clazz) {
		return specialize(1).match(clazz);
	}

	@Override
	public void specializeChildren(int depth) {
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public I_Strategy specialize(int depth) {
		if (aterm instanceof IStrategoAppl) {
			final IStrategoAppl term = (IStrategoAppl) aterm;
			final String name = term.getName();
			final ImploderNodeSource source = term
					.getAttachment(ImploderAttachment.TYPE) != null ? new ImploderNodeSource(
					term.getAttachment(ImploderAttachment.TYPE)) : null;
			if (name.equals("LChoice") && term.getSubtermCount() == 2) {
				I_Strategy replacement = replace(new LChoice_2(source,
						new Generic_I_Strategy(source, term.getSubterm(0)),
						new Generic_I_Strategy(source, term.getSubterm(1))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("Call") && term.getSubtermCount() == 2) {
				I_Strategy replacement = replace(new Call_2(source,
						new Generic_I_SVar(source, term.getSubterm(0)),
						(INodeList) NodeUtils.makeList(term.getSubterm(1)
								.getSubtermCount(), term.getSubterm(1),
								Generic_I_Strategy.class)));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("All") && term.getSubtermCount() == 1) {
				I_Strategy replacement = replace(new All_1(source,
						new Generic_I_Strategy(source, term.getSubterm(0))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("One") && term.getSubtermCount() == 1) {
				I_Strategy replacement = replace(new One_1(source,
						new Generic_I_Strategy(source, term.getSubterm(0))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("Some") && term.getSubtermCount() == 1) {
				I_Strategy replacement = replace(new Some_1(source,
						new Generic_I_Strategy(source, term.getSubterm(0))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("PrimT") && term.getSubtermCount() == 3) {
				I_Strategy replacement = replace(new PrimT_3(source,
						Tools.asJavaString(term.getSubterm(0)),
						(INodeList) NodeUtils.makeList(term.getSubterm(1)
								.getSubtermCount(), term.getSubterm(1),
								Generic_I_Strategy.class),
						(INodeList) NodeUtils.makeList(term.getSubterm(2)
								.getSubtermCount(), term.getSubterm(2),
								Generic_I_STerm.class)));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("GuardedLChoice") && term.getSubtermCount() == 3) {
				I_Strategy replacement = replace(new GuardedLChoice_3(source,
						new Generic_I_Strategy(source, term.getSubterm(0)),
						new Generic_I_Strategy(source, term.getSubterm(1)),
						new Generic_I_Strategy(source, term.getSubterm(2))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("Seq") && term.getSubtermCount() == 2) {
				I_Strategy replacement = replace(new Seq_2(source,
						new Generic_I_Strategy(source, term.getSubterm(0)),
						new Generic_I_Strategy(source, term.getSubterm(1))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("Scope") && term.getSubtermCount() == 2) {
				I_Strategy replacement = replace(new Scope_2(source,
						(INodeList) NodeUtils.makeList(term.getSubterm(0)
								.getSubtermCount(), term.getSubterm(0),
								String.class), new Generic_I_Strategy(source,
								term.getSubterm(1))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("Build") && term.getSubtermCount() == 1) {
				I_Strategy replacement = replace(new Build_1(source,
						new Generic_I_STerm(source, term.getSubterm(0))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("Match") && term.getSubtermCount() == 1) {
				I_Strategy replacement = replace(new Match_1(source,
						new Generic_I_STerm(source, term.getSubterm(0))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("Id") && term.getSubtermCount() == 0) {
				I_Strategy replacement = replace(new Id_0(source));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("Fail") && term.getSubtermCount() == 0) {
				I_Strategy replacement = replace(new Fail_0(source));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("CallDynamic") && term.getSubtermCount() == 3) {
				I_Strategy replacement = replace(new CallDynamic_3(source,
						new Generic_I_STerm(source, term.getSubterm(0)),
						(INodeList) NodeUtils.makeList(term.getSubterm(1)
								.getSubtermCount(), term.getSubterm(1),
								Generic_I_Strategy.class),
						(INodeList) NodeUtils.makeList(term.getSubterm(2)
								.getSubtermCount(), term.getSubterm(2),
								Generic_I_STerm.class)));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("CallT") && term.getSubtermCount() == 3) {
				I_Strategy replacement = replace(new CallT_3(source,
						new Generic_I_SVar(source, term.getSubterm(0)),
						(INodeList) NodeUtils.makeList(term.getSubterm(1)
								.getSubtermCount(), term.getSubterm(1),
								Generic_I_Strategy.class),
						(INodeList) NodeUtils.makeList(term.getSubterm(2)
								.getSubtermCount(), term.getSubterm(2),
								Generic_I_STerm.class)));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("Let") && term.getSubtermCount() == 2) {
				I_Strategy replacement = replace(new Let_2(source,
						(INodeList) NodeUtils.makeList(term.getSubterm(0)
								.getSubtermCount(), term.getSubterm(0),
								Generic_I_Def.class), new Generic_I_Strategy(
								source, term.getSubterm(1))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("ImportTerm") && term.getSubtermCount() == 1) {
				I_Strategy replacement = replace(new ImportTerm_1(source,
						Tools.asJavaString(term.getSubterm(0))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
		}
		IGenericNode replacement = null;
		throw new RewritingException();
	}

	public R_default_Value exec_default(
			ds.manual.interpreter.AutoInterpInteropContext _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _3,
			org.spoofax.interpreter.terms.IStrategoTerm _4,
			org.spoofax.interpreter.terms.ITermFactory _5,
			ds.manual.interpreter.SState _6, ds.manual.interpreter.VState _7,
			boolean _8, org.spoofax.interpreter.core.StackTracer _9) {
		return specialize(1).exec_default(_1, _2, _3, _4, _5, _6, _7, _8, _9);
	}
}