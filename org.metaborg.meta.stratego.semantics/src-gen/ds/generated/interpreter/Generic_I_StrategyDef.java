package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;
import org.spoofax.interpreter.terms.*;
import org.spoofax.jsglr.client.imploder.ImploderAttachment;
import org.spoofax.interpreter.core.Tools;

public class Generic_I_StrategyDef extends AbstractNode implements
		I_StrategyDef, IGenericNode {
	public IStrategoTerm aterm;

	public Generic_I_StrategyDef(INodeSource source, IStrategoTerm term) {
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
	public I_StrategyDef specialize(int depth) {
		if (aterm instanceof IStrategoAppl) {
			final IStrategoAppl term = (IStrategoAppl) aterm;
			final String name = term.getName();
			final ImploderNodeSource source = term
					.getAttachment(ImploderAttachment.TYPE) != null ? new ImploderNodeSource(
					term.getAttachment(ImploderAttachment.TYPE)) : null;
			if (name.equals("SDefT") && term.getSubtermCount() == 4) {
				I_StrategyDef replacement = replace(new SDefT_4(source,
						Tools.asJavaString(term.getSubterm(0)),
						(INodeList) NodeUtils.makeList(term.getSubterm(1)
								.getSubtermCount(), term.getSubterm(1),
								Generic_I_Typedid.class),
						(INodeList) NodeUtils.makeList(term.getSubterm(2)
								.getSubtermCount(), term.getSubterm(2),
								Generic_I_Typedid.class),
						new Generic_I_Strategy(source, term.getSubterm(3))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("ExtSDef") && term.getSubtermCount() == 3) {
				I_StrategyDef replacement = replace(new ExtSDef_3(source,
						Tools.asJavaString(term.getSubterm(0)),
						(INodeList) NodeUtils.makeList(term.getSubterm(1)
								.getSubtermCount(), term.getSubterm(1),
								Generic_I_Typedid.class),
						(INodeList) NodeUtils.makeList(term.getSubterm(2)
								.getSubtermCount(), term.getSubterm(2),
								Generic_I_Typedid.class)));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
		}
		IGenericNode replacement = null;
		throw new RewritingException();
	}
}