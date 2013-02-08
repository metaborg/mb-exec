/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import static java.lang.Math.min;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Label;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.IincInsnNode;
import org.objectweb.asm.tree.InnerClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.LookupSwitchInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.MultiANewArrayInsnNode;
import org.objectweb.asm.tree.TableSwitchInsnNode;
import org.objectweb.asm.tree.TryCatchBlockNode;
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
import org.spoofax.terms.StrategoAppl;
import org.spoofax.terms.StrategoList;
import org.spoofax.terms.StrategoReal;
import org.spoofax.terms.StrategoTuple;
import org.spoofax.terms.TermFactory;
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

	public static IStrategoInt wrap(long value) {
		return new ASMInt(value);
	}

	public static IStrategoTerm wrap(List<?> list) {
		if(list == null)
			return None.INSTANCE;
		else 
			return new ASMList(list);
	}

	public static IStrategoTerm genericWrap(Object node) {
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
		} else if(node instanceof Integer) {
			return wrap((Integer) node);
		} else if(node instanceof TryCatchBlockNode) {
			return wrap((TryCatchBlockNode) node);
		} else if(node instanceof FieldNode) {
			return wrap((FieldNode) node);
		} else if(node instanceof InnerClassNode) {
			return wrap((InnerClassNode) node);
		} else if(node instanceof Long) {
			return wrapJava((Long) node);
		} else if(node instanceof Float) {
			return wrapJava((Float) node);
		} else if(node instanceof Double) {
			return wrapJava((Double) node);
		} else if(node instanceof Type) {
			return wrap((Type) node);
		} else if(node instanceof Boolean) {
			return wrapJava((Boolean) node);
		} else if(node instanceof List) {
			return wrap((List<?>) node);
		} else if(node instanceof String[]) {
			return wrap((String[]) node);
		} else if(node instanceof Byte) {
			return wrapJava((Byte) node);
		} else if(node instanceof Character) {
			return wrapJava((Character) node);
		} else if(node instanceof Short) {
			return wrapJava((Short) node);
		} else if(node instanceof Attribute) {
			return wrap((Attribute) node);
		}
		 
        throw new NotImplementedException("Unknown ASM node type " + node.getClass());
	}
	
	
	private static IStrategoTerm wrap(Attribute node) {
		if(node == null)
			return None.INSTANCE;
		return new ASMAttribute(node);
	}

	private static IStrategoTerm wrap(String[] node) {
		if(node == null)
			return None.INSTANCE;
		return new ASMArray(node);
	}


	private static IStrategoTerm wrap(Type node) {
		if(node == null)
			return None.INSTANCE;
		return new ASMType(node);
	}

	private static IStrategoTerm wrap(double value) {
		return new StrategoReal(value, TermFactory.EMPTY_LIST, IStrategoTerm.IMMUTABLE);	}

	private static IStrategoTerm wrap(ClassNode node) {
		if(node == null)
			return None.INSTANCE;
		return new ASMClassNode(node);
	}

	

	private static IStrategoTerm wrap(InnerClassNode node) {
		if(node == null)
			return None.INSTANCE;
		return new ASMInnerClassNode(node);
	}

	private static IStrategoTerm wrap(FieldNode node) {
		if(node == null)
			return None.INSTANCE;
		return new ASMFieldNode(node);
	}

	private static IStrategoTerm wrap(TryCatchBlockNode node) {
		if(node == null)
			return None.INSTANCE;
		return new ASMTryCatchBlocNode(node);
	}

	private static IStrategoTerm wrap(AnnotationNode node) {
		if(node == null)
			return None.INSTANCE;
		return new ASMAnnotationNode(node);
	}

	public static IStrategoTerm wrap(AbstractInsnNode node) {
		if(node == null)
			return None.INSTANCE;
		switch(node.getType()) {
		case AbstractInsnNode.FIELD_INSN:
			return wrap((FieldInsnNode) node);
		case AbstractInsnNode.FRAME:
			return wrap((FrameNode) node);
		case AbstractInsnNode.IINC_INSN:
			return wrap((IincInsnNode) node);
		case AbstractInsnNode.INSN:
			return wrap((InsnNode) node);
		case AbstractInsnNode.INT_INSN:
			return wrap((IntInsnNode) node); 
		case AbstractInsnNode.INVOKE_DYNAMIC_INSN:
			throw new NotImplementedException();
		case AbstractInsnNode.JUMP_INSN:
			return wrap((JumpInsnNode) node);
		case AbstractInsnNode.LABEL:
			return wrap((LabelNode) node);
		case AbstractInsnNode.LDC_INSN:
			return wrap((LdcInsnNode) node);
		case AbstractInsnNode.LINE:
			return wrap((LineNumberNode) node);
		case AbstractInsnNode.LOOKUPSWITCH_INSN:
			return wrap((LookupSwitchInsnNode) node);
		case AbstractInsnNode.METHOD_INSN:
			return wrap((MethodInsnNode) node);
		case AbstractInsnNode.MULTIANEWARRAY_INSN:
			return wrap((MultiANewArrayInsnNode) node);
		case AbstractInsnNode.TABLESWITCH_INSN:
			return wrap((TableSwitchInsnNode) node);
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
			return new ASMFieldInsnNode(node);
	}

	private static IStrategoTerm wrap(LookupSwitchInsnNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new ASMLookupSwitchInsnNode(node);
	}

	private static IStrategoTerm wrap(MultiANewArrayInsnNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new ASMMultiANewArrayInsnNode(node);
	}

	private static IStrategoTerm wrap(IntInsnNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new ASMIntInsnNode(node);
	}

	private static IStrategoTerm wrap(IincInsnNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new ASMIincInsnNode(node);
	}

	private static IStrategoTerm wrap(TableSwitchInsnNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new ASMTableSwitchInsnNode(node);
	}

	private static IStrategoTerm wrap(LdcInsnNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new ASMLdcInsnNode(node);
	}

	private static IStrategoTerm wrap(TypeInsnNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new ASMTypeInsnNode(node);
	}

	private static IStrategoTerm wrap(InsnNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new ASMInsnNode(node);
	}

	private static IStrategoTerm wrap(MethodInsnNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new ASMMethodInsnNode(node);
	}

	private static IStrategoTerm wrap(VarInsnNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new ASMVarInsnNode(node);
	}

	private static IStrategoTerm wrap(FrameNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new ASMFrameNode(node);
	}

	private static IStrategoTerm wrap(JumpInsnNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new ASMJumpInsnNode(node);
	}

	private static IStrategoTerm wrap(LineNumberNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new ASMLineNumberNode(node);
	}

	private static IStrategoTerm wrap(LocalVariableNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new ASMLocalVariable(node);
	}

	private static IStrategoTerm wrap(MethodNode node) {
		if(node == null)
			return None.INSTANCE;
		else 
			return new ASMMethodNode(node);
	}

	public static IStrategoTerm wrap(String node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new ASMString(node);
	}

	public static IStrategoTerm wrap(InsnList instructions) {
		if(instructions == null)
			return None.INSTANCE;
		else
			return new ASMInsnList(instructions);
	}

	public static IStrategoTerm wrap(List<?>[] node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new ASMArray(node);
	}

	public static IStrategoTerm wrap(LabelNode node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new ASMLabelNode(node);
	}

	public static IStrategoTerm wrap(Label node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new ASMLabel(node);
	}

	@Override
	public IStrategoList makeList() {
    	return isTermSharingAllowed() ? TermFactory.EMPTY_LIST : new StrategoList(null, null, null, defaultStorageType);
	}

	@Override
	public IStrategoList makeList(Collection<? extends IStrategoTerm> terms) {
		throw new NotImplementedException();
	}

	@Override
	public IStrategoAppl makeAppl(IStrategoConstructor ctr,
			IStrategoTerm[] terms, IStrategoList annotations) {
    	int storageType = defaultStorageType;
		storageType = min(storageType, getStorageType(terms));
    	if (storageType != 0)
        	storageType = min(storageType, getStorageType(annotations));
    	assert ctr.getArity() == terms.length;
        return new StrategoAppl(ctr, terms, annotations, storageType);
	}

	@Override
	public IStrategoList makeList(IStrategoTerm[] kids,
			IStrategoList annotations) {
		throw new NotImplementedException();
	}

	@Override
	public IStrategoList makeListCons(IStrategoTerm head, IStrategoList tail,
			IStrategoList annotations) {
    	int storageType = min(defaultStorageType, getStorageType(head, tail));
    	
    	if (head == null) return makeList();
    	return new StrategoList(head, tail, annotations, storageType);
	}

	@Override
	public IStrategoTuple makeTuple(IStrategoTerm[] terms,
			IStrategoList annos) {
        int storageType = min(defaultStorageType, getStorageType(terms));
		return new StrategoTuple(terms, annos, storageType);
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
		return new ASMAccessFlags(access);
	}

	static IStrategoTerm wrapJava(boolean value) {
		if(value)
			return new ASMJavaBoolean(True.INSTANCE);
		else
			return new ASMJavaBoolean(False.INSTANCE);
	}

	static IStrategoTerm wrapJava(double value) {
		return new ASMJavaDouble(value);
	}

	static IStrategoTerm wrapJava(float value) {
		return new ASMJavaFloat(value);
	}

	static IStrategoTerm wrapJava(byte value) {
		return new ASMJavaByte(value);
	}

	static IStrategoTerm wrapJava(char value) {
		return new ASMJavaCharacter(value);
	}

	static IStrategoTerm wrapJava(short value) {
		return new ASMJavaShort(value);
	}

	static IStrategoTerm wrapJava(int value) {
		return new ASMJavaInt(value);
	}

	static IStrategoTerm wrapJava(long value) {
		return new ASMJavaLong(value);
	}

	public static IStrategoTerm wrap(boolean value) {
		if(value)
			return True.INSTANCE;
		else
			return False.INSTANCE;
	}

}
