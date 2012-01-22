/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import org.objectweb.asm.tree.TableSwitchInsnNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoConstructor;

public class ASMTableSwitchInsnNode extends WrappedASMNode {

	private static final long serialVersionUID = -1621829275457344564L;
	private static final IStrategoConstructor CTOR = new StrategoConstructor("TableSwitchNode", 5);
	private final TableSwitchInsnNode wrappee;

	ASMTableSwitchInsnNode(TableSwitchInsnNode wrappee) {
		super(CTOR);
		this.wrappee = wrappee;
	}
	@Override
	public IStrategoTerm getSubterm(int index) {
		switch (index) {
		case 0: 
			return ASMFactory.wrapOpcode(wrappee.getOpcode());
		case 1:
			return ASMFactory.wrap(wrappee.dflt);
		case 2: 
			return ASMFactory.wrap(wrappee.labels);
		case 3: 
			return ASMFactory.wrap(wrappee.max);
		case 4: 
			return ASMFactory.wrap(wrappee.min);
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

}
