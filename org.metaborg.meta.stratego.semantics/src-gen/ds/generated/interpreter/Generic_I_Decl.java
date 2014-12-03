package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;
import org.spoofax.interpreter.terms.*;
import org.spoofax.jsglr.client.imploder.ImploderAttachment;
import org.spoofax.interpreter.core.Tools;

public class Generic_I_Decl extends AbstractNode implements I_Decl,
		IGenericNode {
	public IStrategoTerm aterm;

	public Generic_I_Decl(INodeSource source, IStrategoTerm term) {
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
	public I_Decl specialize(int depth) {
		if (aterm instanceof IStrategoAppl) {
			final IStrategoAppl term = (IStrategoAppl) aterm;
			final String name = term.getName();
			final ImploderNodeSource source = term
					.getAttachment(ImploderAttachment.TYPE) != null ? new ImploderNodeSource(
					term.getAttachment(ImploderAttachment.TYPE)) : null;
			if (name.equals("Signature") && term.getSubtermCount() == 1) {
				I_Decl replacement = replace(new Signature_1(source,
						(INodeList) NodeUtils.makeList(term.getSubterm(0)
								.getSubtermCount(), term.getSubterm(0),
								Generic_I_Sdecl.class)));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("Strategies") && term.getSubtermCount() == 1) {
				I_Decl replacement = replace(new Strategies_1(source,
						(INodeList) NodeUtils.makeList(term.getSubterm(0)
								.getSubtermCount(), term.getSubterm(0),
								Generic_I_Def.class)));
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