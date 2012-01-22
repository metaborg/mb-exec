/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Label;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.objectweb.asm.util.Printer;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoTuple;
import org.spoofax.terms.skeleton.SkeletonTermFactory;

public class ASMFactory extends SkeletonTermFactory {

	public ASMFactory() {
		super(IStrategoTerm.IMMUTABLE);
	}

	public IStrategoTerm parseFromFile(String path) throws IOException {
		return parseFromStream(new FileInputStream(path));
	}

	public IStrategoTerm parseFromStream(InputStream inputStream)
			throws IOException {
		ClassReader cr = new ClassReader(inputStream);
		ClassNode cn = new ClassNode();
		cr.accept(cn, 0);
		return wrap(cn);
	}

	private static IStrategoTerm wrap(ClassNode cn) {
		return new WrappedClassNode(cn);
	}

	@Override
	public IStrategoTerm parseFromString(String text) {
		throw new NotImplementedException();
	}

	@Override
	public IStrategoInt makeInt(int value) {
		return wrap(value);
	}

	@Override
	public IStrategoString makeString(String value) {
		return (IStrategoString) wrap(value);
	}

	public static IStrategoInt wrap(int value) {
		return new WrappedInt(value);
	}

	public static IStrategoTerm wrap(List<Object> list) {
		if(list == null)
			return None.INSTANCE;
		else 
			return new WrappedASMList(list);
	}

	public static IStrategoTerm genericWrap(Object node) {
		
		System.out.println(" -  " + (node == null ? "null" : node.getClass().getName()));
		
		if(node == null)
			return None.INSTANCE;
		if(node instanceof String) {
			return wrap((String)node);
		} else if(node instanceof LocalVariableNode) {
			return wrap((LocalVariableNode) node);
		} else if(node instanceof AbstractInsnNode) {
			return wrap((AbstractInsnNode) node);
		} else if(node instanceof MethodNode) {
			return wrap((MethodNode) node);
		} else if(node instanceof ClassNode) {
			return wrap((ClassNode) node);
		} else if(node instanceof AnnotationNode) {
			return wrap((AnnotationNode) node);
		}
		
        throw new NotImplementedException("Unknown ASM node type " + node.getClass());
	}

	private static IStrategoTerm wrap(AnnotationNode node) {
		if(node == null)
			return None.INSTANCE;
		return new ASMAnnotationNode(node);
	}

	public static IStrategoTerm wrap(AbstractInsnNode node) {
		if(node == null)
			return None.INSTANCE;
		dumpChain(node);
		switch(node.getType()) {
		case AbstractInsnNode.FIELD_INSN:
			return wrap((FieldInsnNode) node);
		case AbstractInsnNode.FRAME:
			throw new NotImplementedException();
		case AbstractInsnNode.IINC_INSN:
			throw new NotImplementedException();
		case AbstractInsnNode.INSN:
			return wrap((InsnNode) node);
		case AbstractInsnNode.INT_INSN:
			return wrap((IntInsnNode) node); 
		case AbstractInsnNode.INVOKE_DYNAMIC_INSN:
			throw new NotImplementedException();
		case AbstractInsnNode.JUMP_INSN:
			throw new NotImplementedException();
		case AbstractInsnNode.LABEL:
			return wrap((LabelNode) node);
		case AbstractInsnNode.LDC_INSN:
			return wrap((LdcInsnNode) node);
		case AbstractInsnNode.LINE:
			return wrap((LineNumberNode) node);
		case AbstractInsnNode.LOOKUPSWITCH_INSN:
			throw new NotImplementedException();
		case AbstractInsnNode.METHOD_INSN:
			return wrap((MethodInsnNode) node);
		case AbstractInsnNode.MULTIANEWARRAY_INSN:
			throw new NotImplementedException();
		case AbstractInsnNode.TABLESWITCH_INSN:
			throw new NotImplementedException();
		case AbstractInsnNode.TYPE_INSN:
			return wrap((TypeInsnNode) node);
		case AbstractInsnNode.VAR_INSN:
			return wrap((VarInsnNode) node);
		case -1:
			System.err.println("Bogus " + node.getClass().getName());
			return None.INSTANCE;
		default:
			throw new IllegalArgumentException("Unknown type " + node.getOpcode() + " for " + node.getClass().getName());
		}
	}

	private static void dumpChain(AbstractInsnNode node) {
		if(node == null)
			return;
		do {
			System.out.println(node.getClass().getName());
			node = node.getNext();
		} while(node != null);
	}

	private static IStrategoTerm wrap(FieldInsnNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new WrappedFieldInsnNode(node);
	}

	private static IStrategoTerm wrap(IntInsnNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new WrappedIntInsnNode(node);
	}

	private static IStrategoTerm wrap(LdcInsnNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new WrappedLdcInsnNode(node);
	}

	private static IStrategoTerm wrap(TypeInsnNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new WrappedTypeInsnNode(node);
	}

	private static IStrategoTerm wrap(InsnNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new WrappedInsnNode(node);
	}

	private static IStrategoTerm wrap(MethodInsnNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new WrappedMethodInsnNode(node);
	}

	private static IStrategoTerm wrap(VarInsnNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new WrappedVarInsnNode(node);
	}

	private static IStrategoTerm wrap(LineNumberNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new WrappedLineNumberNode(node);
	}

	private static IStrategoTerm wrap(LocalVariableNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new WrappedLocalVariable(node);
	}

	private static IStrategoTerm wrap(MethodNode node) {
		if(node == null)
			return None.INSTANCE;
		else 
			return new WrappedMethodNode(node);
	}

	public static IStrategoTerm wrap(String node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new WrappedASMString(node);
	}

	public static IStrategoTerm wrap(InsnList instructions) {
		if(instructions == null)
			return None.INSTANCE;
		else
			return new WrappedInsnList(instructions);
	}

	public static IStrategoTerm wrap(List<Object>[] node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new WrappedASMArray(node);
	}

	public static IStrategoTerm wrap(LabelNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new WrappedLabelNode(node);
	}

	public static IStrategoTerm wrap(Label node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new WrappedLabel(node);
	}

	@Override
	public IStrategoList makeList() {
		throw new NotImplementedException();
	}

	@Override
	public IStrategoList makeList(Collection<? extends IStrategoTerm> terms) {
		throw new NotImplementedException();
	}

	@Override
	public IStrategoAppl makeAppl(IStrategoConstructor constructor,
			IStrategoTerm[] kids, IStrategoList annotations) {
		throw new NotImplementedException();
	}

	@Override
	public IStrategoList makeList(IStrategoTerm[] kids,
			IStrategoList annotations) {
		throw new NotImplementedException();
	}

	@Override
	public IStrategoList makeListCons(IStrategoTerm head, IStrategoList tail,
			IStrategoList annotations) {
		throw new NotImplementedException();
	}

	@Override
	public IStrategoTuple makeTuple(IStrategoTerm[] kids,
			IStrategoList annotations) {
		throw new NotImplementedException();
	}

	@Override
	public IStrategoTerm copyAttachments(IStrategoTerm from, IStrategoTerm to) {
		throw new NotImplementedException();
	}

	@Override
	public IStrategoAppl replaceAppl(IStrategoConstructor constructor,
			IStrategoTerm[] kids, IStrategoAppl old) {
		throw new NotImplementedException();
	}

	@Override
	public IStrategoList replaceList(IStrategoTerm[] kids, IStrategoList old) {
		throw new NotImplementedException();
	}

	@Override
	public IStrategoList replaceListCons(IStrategoTerm head,
			IStrategoList tail, IStrategoTerm oldHead, IStrategoList oldTail) {
		throw new NotImplementedException();
	}

	@Override
	public IStrategoTerm replaceTerm(IStrategoTerm term, IStrategoTerm old) {
		throw new NotImplementedException();
	}

	@Override
	public IStrategoTuple replaceTuple(IStrategoTerm[] kids, IStrategoTuple old) {
		throw new NotImplementedException();
	}

	public static IStrategoTerm wrapOpcode(int opcode) {
		if(opcode < 0)
			return None.INSTANCE;
		return wrap(Printer.OPCODES[opcode]);
	}

	public static IStrategoTerm wrapAccessFlags(int access) {
		return new WrappedAccessFlags(access);
	}

}
