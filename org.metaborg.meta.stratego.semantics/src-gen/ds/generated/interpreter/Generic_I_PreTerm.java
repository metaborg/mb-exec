package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;
import org.spoofax.interpreter.terms.*;
import org.spoofax.jsglr.client.imploder.ImploderAttachment;
import org.spoofax.interpreter.core.Tools;

public class Generic_I_PreTerm extends AbstractNode implements I_PreTerm,
		IGenericNode {
	public IStrategoTerm aterm;

	public Generic_I_PreTerm(INodeSource source, IStrategoTerm term) {
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
	public I_PreTerm specialize(int depth) {
		if (aterm instanceof IStrategoAppl) {
			final IStrategoAppl term = (IStrategoAppl) aterm;
			final String name = term.getName();
			final ImploderNodeSource source = term
					.getAttachment(ImploderAttachment.TYPE) != null ? new ImploderNodeSource(
					term.getAttachment(ImploderAttachment.TYPE)) : null;
			if (name.equals("As") && term.getSubtermCount() == 2) {
				I_PreTerm replacement = replace(new As_2(source,
						new Generic_I_Var(source, term.getSubterm(0)),
						new Generic_I_PreTerm(source, term.getSubterm(1))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("Explode") && term.getSubtermCount() == 2) {
				I_PreTerm replacement = replace(new Explode_2(source,
						new Generic_I_STerm(source, term.getSubterm(0)),
						new Generic_I_STerm(source, term.getSubterm(1))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("Op") && term.getSubtermCount() == 2) {
				I_PreTerm replacement = replace(new Op_2(source,
						Tools.asJavaString(term.getSubterm(0)),
						(INodeList) NodeUtils.makeList(term.getSubterm(1)
								.getSubtermCount(), term.getSubterm(1),
								Generic_I_STerm.class)));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("Str") && term.getSubtermCount() == 1) {
				I_PreTerm replacement = replace(new Str_1(source,
						Tools.asJavaString(term.getSubterm(0))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("Int") && term.getSubtermCount() == 1) {
				I_PreTerm replacement = replace(new Int_1(source,
						Tools.asJavaString(term.getSubterm(0))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("Real") && term.getSubtermCount() == 1) {
				I_PreTerm replacement = replace(new Real_1(source,
						Tools.asJavaString(term.getSubterm(0))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("Anno") && term.getSubtermCount() == 2) {
				I_PreTerm replacement = replace(new Anno_2(source,
						new Generic_I_PreTerm(source, term.getSubterm(0)),
						new Generic_I_PreTerm(source, term.getSubterm(1))));
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
			replacement = new Generic_I_Var(getSourceInfo(), aterm);
			return (I_Var) replace(replacement).specialize(1);
		} catch (RewritingException w_12395) {
			try {
				if (replacement != null) {
					replacement.replace(this);
				}
				replacement = new Generic_I_Wld(getSourceInfo(), aterm);
				return (I_Wld) replace(replacement).specialize(1);
			} catch (RewritingException v_12395) {
			}
		}
		throw new RewritingException();
	}
}