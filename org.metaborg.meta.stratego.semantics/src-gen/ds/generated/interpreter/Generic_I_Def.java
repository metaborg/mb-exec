package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;
import org.spoofax.interpreter.terms.*;
import org.spoofax.jsglr.client.imploder.ImploderAttachment;
import org.spoofax.interpreter.core.Tools;

public class Generic_I_Def extends AbstractNode implements I_Def, IGenericNode {
	public IStrategoTerm aterm;

	public Generic_I_Def(INodeSource source, IStrategoTerm term) {
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
	public I_Def specialize(int depth) {
		if (aterm instanceof IStrategoAppl) {
			final IStrategoAppl term = (IStrategoAppl) aterm;
			final String name = term.getName();
			final ImploderNodeSource source = term
					.getAttachment(ImploderAttachment.TYPE) != null ? new ImploderNodeSource(
					term.getAttachment(ImploderAttachment.TYPE)) : null;
			if (name.equals("AnnoDef") && term.getSubtermCount() == 2) {
				I_Def replacement = replace(new AnnoDef_2(source,
						(INodeList) NodeUtils.makeList(term.getSubterm(0)
								.getSubtermCount(), term.getSubterm(0),
								Generic_I_Anno.class),
						new Generic_I_StrategyDef(source, term.getSubterm(1))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
		}
		IGenericNode replacement = null;
		try {
			if (replacement != null) {
				replacement.replace(this);
			}
			replacement = new Generic_I_StrategyDef(getSourceInfo(), aterm);
			return (I_StrategyDef) replace(replacement).specialize(1);
		} catch (RewritingException x_12395) {
		}
		throw new RewritingException();
	}
}