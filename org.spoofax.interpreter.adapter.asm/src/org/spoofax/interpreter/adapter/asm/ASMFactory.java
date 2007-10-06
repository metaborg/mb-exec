package org.spoofax.interpreter.adapter.asm;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MemberNode;
import org.objectweb.asm.tree.MethodNode;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.BasicStrategoInt;
import org.spoofax.interpreter.terms.BasicStrategoList;
import org.spoofax.interpreter.terms.BasicStrategoString;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoTuple;
import org.spoofax.interpreter.terms.ITermFactory;

public class ASMFactory implements ITermFactory {

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

	public IStrategoTerm parseFromString(String text) {
		throw new NotImplementedException();
	}

	public IStrategoTerm replaceAppl(IStrategoConstructor constructor,
			IStrategoTerm[] kids, IStrategoTerm old) {
		// TODO Auto-generated method stub
		return null;
	}

	public void unparseToFile(IStrategoTerm t, OutputStream ous)
			throws IOException {
		// TODO Auto-generated method stub
	}

	public boolean hasConstructor(String ctorName, int arity) {
		// TODO Auto-generated method stub
		return false;
	}

	public IStrategoAppl makeAppl(IStrategoConstructor ctr, IStrategoList kids) {
		// TODO Auto-generated method stub
		return null;
	}

	public IStrategoAppl makeAppl(IStrategoConstructor ctr,
			IStrategoTerm... terms) {
		// TODO Auto-generated method stub
		return null;
	}

	public IStrategoConstructor makeConstructor(String string, int arity) {
		// TODO Auto-generated method stub
		return null;
	}

	public IStrategoInt makeInt(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public IStrategoList makeList(IStrategoTerm... terms) {
		// TODO Auto-generated method stub
		return null;
	}

	public IStrategoList makeList(Collection<IStrategoTerm> terms) {
		// TODO Auto-generated method stub
		return null;
	}

	public IStrategoReal makeReal(double d) {
		// TODO Auto-generated method stub
		return null;
	}

	public IStrategoString makeString(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	public IStrategoTuple makeTuple(IStrategoTerm... terms) {
		// TODO Auto-generated method stub
		return null;
	}

	public static IStrategoTerm wrap(int access) {
		return new WrappedInt(access);
	}

	public static IStrategoTerm wrap(List list) {
		if(list == null)
			return None.INSTANCE;
		else 
			return new WrappedASMList(list);
	}

	public static IStrategoTerm genericWrap(Object node) {
		if(node instanceof MemberNode) {
			return wrap((MemberNode)node);
		} else if(node instanceof String) {
			return wrap((String)node);
		}
		
		if(node == null)
			return None.INSTANCE;
		
        throw new NotImplementedException("Unknown ASM node type " + node.getClass());
	}

	private static IStrategoTerm wrap(MethodNode node) {
		if(node == null)
			return None.INSTANCE;
		else 
			return new WrappedMethodNode(node);
	}
	private static IStrategoTerm wrap(MemberNode node) {
		if(node instanceof ClassNode) {
			return wrap((ClassNode)node);
		} else if(node instanceof MethodNode) {
			return wrap((MethodNode)node);
		}
        throw new NotImplementedException("Unknown ASM MemberNode subtype " + node.getClass());
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

	public static IStrategoTerm wrap(List[] node) {
		if(node == null)
			return None.INSTANCE;
		else
			return new WrappedASMArray(node);
	}

}
