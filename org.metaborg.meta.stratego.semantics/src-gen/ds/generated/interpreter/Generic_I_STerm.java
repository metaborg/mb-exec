package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;
import org.spoofax.interpreter.terms.*;
import org.spoofax.jsglr.client.imploder.ImploderAttachment;
import org.spoofax.interpreter.core.Tools;

public class Generic_I_STerm extends AbstractNode implements I_STerm,
		IGenericNode {
	public IStrategoTerm aterm;

	public Generic_I_STerm(INodeSource source, IStrategoTerm term) {
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
	public I_STerm specialize(int depth) {
		if (aterm instanceof IStrategoAppl) {
			final IStrategoAppl term = (IStrategoAppl) aterm;
			final String name = term.getName();
			final ImploderNodeSource source = term
					.getAttachment(ImploderAttachment.TYPE) != null ? new ImploderNodeSource(
					term.getAttachment(ImploderAttachment.TYPE)) : null;
			if (name.equals("NoAnnoList") && term.getSubtermCount() == 1) {
				I_STerm replacement = replace(new NoAnnoList_1(source,
						new Generic_I_PreTerm(source, term.getSubterm(0))));
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
			replacement = new Generic_I_PreTerm(getSourceInfo(), aterm);
			return (I_PreTerm) replace(replacement).specialize(1);
		} catch (RewritingException u_12395) {
		}
		throw new RewritingException();
	}
}