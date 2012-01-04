/*
 * Copyright (c) 2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.java;

import java.io.File;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class JFF_chmod_0_0 extends AbstractPrimitive {

	private static final int S_IRUSR = 0400;
	private static final int S_IWUSR = 0200;
	private static final int S_IXUSR = 0100;

	private static final int S_IROTH = 0004;
	private static final int S_IWOTH = 0002;
	private static final int S_IXOTH = 0001;

	public JFF_chmod_0_0() {
		super("JFF_chmod", 0, 1);
	}

	@Override
	public boolean call(IContext context, Strategy[] svars, IStrategoTerm[] tvars) {

		if(!Tools.isTermString(tvars[0]))
			return false;

		if(!Tools.isTermString(tvars[1]))
			return false;

		String fileName = Tools.asJavaString(tvars[0]);
		String modeString = Tools.asJavaString(tvars[1]);

		int mode = Integer.parseInt(modeString, 8);

		System.out.println(mode);

		if(fileName == null)
			return true;

		File f = new File(fileName);
		boolean r = true;

		boolean readable = (mode & (S_IRUSR | S_IROTH)) != 0;
		boolean readableUserOnly = (mode & S_IROTH) == 0;
		r &= f.setReadable(readable, readableUserOnly);

		boolean writable = (mode & (S_IWUSR | S_IWOTH)) != 0;
		boolean writableUserOnly = (mode & S_IWOTH) == 0;
		r &= f.setWritable(writable, writableUserOnly);

		boolean executable = (mode & (S_IXUSR | S_IXOTH)) != 0;
		boolean executableUserOnly = (mode & S_IXOTH) == 0;
		r &= f.setWritable(executable, executableUserOnly);

		return r;
	}


}
