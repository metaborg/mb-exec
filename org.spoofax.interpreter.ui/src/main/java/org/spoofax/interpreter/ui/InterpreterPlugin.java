/*
 * Copyright (c) 2011, Karl Trygve Kalleberg <karltk at strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;

public class InterpreterPlugin extends AbstractUIPlugin {

	private static InterpreterPlugin instance;

	public static InterpreterPlugin instance() {
		if(instance == null) {
			instance = new InterpreterPlugin();
		}
		return instance;
	}
}
