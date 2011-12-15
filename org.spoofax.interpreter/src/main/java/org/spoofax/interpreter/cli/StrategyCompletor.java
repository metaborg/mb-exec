/*
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.cli;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import jline.Completor;

import org.spoofax.interpreter.core.IContext;

public class StrategyCompletor implements Completor {

	private final IContext context;
	private final SortedSet<String> strategies = new TreeSet<String>();

	public StrategyCompletor(IContext context) {
		this.context = context;
		refreshStrategies();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int complete(String lookup, int index, List alternatives) {
		// System.out.println(lookup + "/" + index + "/" + alternatives);

		String sep = "<;([+";
		int start = -1;
		for (int i = lookup.length() - 1; i >= 0; i--)
			if (sep.indexOf(lookup.charAt(i)) != -1)
				start = i;
		String prefix = lookup.substring(start + 1);
		// System.out.println('|' + prefix + '|');

		for (String name : strategies.tailSet(prefix)) {
			if (name.startsWith(prefix))
				alternatives.add(name);
		}

		return alternatives.size() == 0 ? -1 : start + 1;
	}

	public void refreshStrategies() {
		strategies.clear();
		for (String s : context.getStrategyNames()) {
			strategies.add(uncify(s));
		}
	}

	private static String unescape(String name) {
		return name.replace("_p_", "'").replace("__", "+")
		.replace('_', '-').replace("+", "_");
	}

	public static String uncify(String name) {
		return unescape(name.substring(0, indexOfArity(name)));
	}

	private static int indexOfArity(String name) {
		int underlineCount = 0;
		int i;
		for (i = name.length() - 1; i >= 0; i--) {
			if (name.charAt(i) == '_')
				underlineCount++;
			if (underlineCount == 2)
				break;
		}
		return i;
	}

	public static String uncifyComplete(String s) {
		int aritySplit = indexOfArity(s);
		int i = aritySplit + 1;
		for(; i < s.length(); i++) {
			if(!Character.isDigit(s.charAt(i)))
				break;	
		}
		String sarity = s.substring(aritySplit + 1, i);
		String tarity = s.substring(i + 1, s.length());
		return unescape(s.substring(0, aritySplit)) + "/(" + sarity + "," + tarity + ")";
	}

	public static String cify(String s) {
		int aritySplit = s.indexOf("/");
		int i = s.indexOf(',', aritySplit);
		String sarity = s.substring(aritySplit + 2, i);
		String tarity = s.substring(i + 1, s.length() - 1);
		return escape(s.substring(0, aritySplit)) + "_" + sarity + "_" + tarity;
	}

	private static String escape(String s) {
		return s.replace("_", "__").replace("-", "_").replace("'", "_p_");
	}

}
