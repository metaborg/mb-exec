/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class TestASMLibrary {

	@Test
	public void shouldParseClassAndPrettyPrintWithoutExceptions() throws FileNotFoundException, IOException {
		parseAndPretty(new FileInputStream("bin/org/spoofax/interpreter/adapter/asm/TestASMLibrary.class"));
	}

	@Test
	public void shouldParseM2RepoAndPrettyPrintWithoutExceptions() throws FileNotFoundException, IOException {
		//File m2 = new File(System.getProperty("user.home") + "/.m2");
		File m2 = new File("/home/armijn/maven");
		if(m2.exists() && m2.isDirectory()) {
			Deque<File> q = new ArrayDeque<File>();
			q.addAll(Arrays.asList(m2.listFiles()));
			while(!q.isEmpty()) {
				File next = q.remove();
				if(next.isDirectory()) {
					q.addAll(Arrays.asList(next.listFiles()));
				} else if(next.getName().endsWith(".jar")) {
					System.out.println(next.getName());
					JarFile jar = new JarFile(next);
					Enumeration<JarEntry> es = jar.entries();
					while(es.hasMoreElements()) {
						JarEntry e = es.nextElement();
						if(e.getName().endsWith(".class"))
							parseAndPretty(jar.getInputStream(e));
					}
					
				}
			}
		}
	}

	private void parseAndPretty(InputStream stream) throws FileNotFoundException, IOException {
		ClassNode cn = null;
		try {
			ClassReader cr = new ClassReader(stream);
			cn = new ClassNode();
			cr.accept(cn, ClassReader.SKIP_FRAMES); //EXPAND_FRAMES);
		} catch(RuntimeException e) {
			e.printStackTrace();
			return;
		}
		IStrategoTerm term = ASMFactory.genericWrap(cn);
		String s = term.toString();
		assertTrue(s.length() > 100);
		//System.out.println(s);
	}

}
