package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;
import org.spoofax.interpreter.terms.*;
import org.spoofax.jsglr.client.imploder.ImploderAttachment;
import org.spoofax.interpreter.core.Tools;

public class Generic_I_Type extends AbstractNode implements I_Type,
		IGenericNode {
	public IStrategoTerm aterm;

	public Generic_I_Type(INodeSource source, IStrategoTerm term) {
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
	public I_Type specialize(int depth) {
		if (aterm instanceof IStrategoAppl) {
			final IStrategoAppl term = (IStrategoAppl) aterm;
			final String name = term.getName();
			final ImploderNodeSource source = term
					.getAttachment(ImploderAttachment.TYPE) != null ? new ImploderNodeSource(
					term.getAttachment(ImploderAttachment.TYPE)) : null;
		}
		IGenericNode replacement = null;
		try {
			if (replacement != null) {
				replacement.replace(this);
			}
			replacement = new Generic_I_ConstType(getSourceInfo(), aterm);
			return (I_ConstType) replace(replacement).specialize(1);
		} catch (RewritingException z_12395) {
			try {
				if (replacement != null) {
					replacement.replace(this);
				}
				replacement = new Generic_I_FunType(getSourceInfo(), aterm);
				return (I_FunType) replace(replacement).specialize(1);
			} catch (RewritingException y_12395) {
			}
		}
		throw new RewritingException();
	}
}