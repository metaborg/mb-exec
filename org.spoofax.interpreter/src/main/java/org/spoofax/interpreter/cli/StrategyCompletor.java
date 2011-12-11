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

	public static String uncify(String name) {
		int underlineCount = 0;
		int i;
		for (i = name.length() - 1; i >= 0; i--) {
			if (name.charAt(i) == '_')
				underlineCount++;
			if (underlineCount == 2)
				break;
		}
		// FIXME make this cleaner!
		return name.substring(0, i).replace("_p_", "'").replace("__", "+")
				.replace('_', '-').replace("+", "_");
	}

}
