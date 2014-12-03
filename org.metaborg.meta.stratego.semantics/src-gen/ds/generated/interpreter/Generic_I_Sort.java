package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;
import org.spoofax.interpreter.terms.*;
import org.spoofax.jsglr.client.imploder.ImploderAttachment;
import org.spoofax.interpreter.core.Tools;

public class Generic_I_Sort extends AbstractNode implements I_Sort,
		IGenericNode {
	public IStrategoTerm aterm;

	public Generic_I_Sort(INodeSource source, IStrategoTerm term) {
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
	public I_Sort specialize(int depth) {
		if (aterm instanceof IStrategoAppl) {
			final IStrategoAppl term = (IStrategoAppl) aterm;
			final String name = term.getName();
			final ImploderNodeSource source = term
					.getAttachment(ImploderAttachment.TYPE) != null ? new ImploderNodeSource(
					term.getAttachment(ImploderAttachment.TYPE)) : null;
			if (name.equals("Sort") && term.getSubtermCount() == 2) {
				I_Sort replacement = replace(new Sort_2(source,
						Tools.asJavaString(term.getSubterm(0)),
						(INodeList) NodeUtils.makeList(term.getSubterm(1)
								.getSubtermCount(), term.getSubterm(1),
								Generic_I_Sort.class)));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("SortVar") && term.getSubtermCount() == 1) {
				I_Sort replacement = replace(new SortVar_1(source,
						Tools.asJavaString(term.getSubterm(0))));
				if (depth > 0) {
					replacement.specializeChildren(depth - 1);
				}
				return replacement;
			}
			if (name.equals("SortNoArgs") && term.getSubtermCount() == 1) {
				I_Sort replacement = replace(new SortNoArgs_1(source,
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
}