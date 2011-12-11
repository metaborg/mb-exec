package org.spoofax.interpreter.cli;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.spoofax.interpreter.core.IContext;

import jline.Completor;

public class SpoofaxCompletor implements Completor {

	private final IContext context;
	private final SortedSet<String> strategies = new TreeSet<String>();

	public SpoofaxCompletor(IContext context) {
		this.context = context;
		refreshStrategies();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int complete(String prefix, int index, List alternatives) {
		//System.out.println(prefix + "/" + index + "/" + alternatives);
		
		for(String name : strategies.tailSet(prefix)) {
			if(name.startsWith(prefix))
				alternatives.add(name);
		}
		return alternatives.size() == 0 ? -1 : 0;
	}
	
	public void refreshStrategies() {
		strategies.clear();
		for(String s : context.getStrategyNames()) {
			strategies.add(uncify(s));
		}
	}

	public static String uncify(String name) {
		int underlineCount = 0;
		int i;
		for(i = name.length() - 1; i >= 0; i--) {
			if(name.charAt(i) == '_')
				underlineCount++;
			if(underlineCount == 2)
				break;
		}
		return name.substring(0, i).replace("_p_", "'").replace('_', '-');
	}

}
