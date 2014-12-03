package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;
import org.spoofax.interpreter.terms.*;
import org.spoofax.jsglr.client.imploder.ImploderAttachment;
import org.spoofax.interpreter.core.Tools;

public class Generic_I_Opdecl extends AbstractNode implements I_Opdecl,
		IGenericNode {
	public IStrategoTerm aterm;

	public Generic_I_Opdecl(INodeSource source, IStrategoTerm term) {
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
	public I_Opdecl specialize(int depth) {
		if (aterm instanceof IStrategoAppl) {
			final IStrategoAppl term = (IStrategoAppl) aterm;
			final String name = term.getName();
			final ImploderNodeSource source = term
					.getAttachment(ImploderAttachment.TYPE) != null ? new ImploderNodeSource(
					term.getAttachment(ImploderAttachment.TYPE)) : null;
			if (name.equals("ExtOpDeclInj") && term.getSubtermCount() == 1) {
				I_Opdecl replacement = replace(new ExtOpDeclInj_1(source,
						new Generic_I_Type(source, term.getSubterm(0))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("ExtOpDeclQ") && term.getSubtermCount() == 2) {
				I_Opdecl replacement = replace(new ExtOpDeclQ_2(source,
						Tools.asJavaString(term.getSubterm(0)),
						new Generic_I_Type(source, term.getSubterm(1))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("ExtOpDecl") && term.getSubtermCount() == 2) {
				I_Opdecl replacement = replace(new ExtOpDecl_2(source,
						Tools.asJavaString(term.getSubterm(0)),
						new Generic_I_Type(source, term.getSubterm(1))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("OpDeclInj") && term.getSubtermCount() == 1) {
				I_Opdecl replacement = replace(new OpDeclInj_1(source,
						new Generic_I_Type(source, term.getSubterm(0))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("OpDeclQ") && term.getSubtermCount() == 2) {
				I_Opdecl replacement = replace(new OpDeclQ_2(source,
						Tools.asJavaString(term.getSubterm(0)),
						new Generic_I_Type(source, term.getSubterm(1))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("OpDecl") && term.getSubtermCount() == 2) {
				I_Opdecl replacement = replace(new OpDecl_2(source,
						Tools.asJavaString(term.getSubterm(0)),
						new Generic_I_Type(source, term.getSubterm(1))));
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