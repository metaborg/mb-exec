/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk at strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.ui;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleFactory;

public class SpoofaxConsoleFactory implements IConsoleFactory {

	@Override
	public void openConsole() {
		SpoofaxConsole console = new SpoofaxConsole();
		ConsolePlugin.getDefault().getConsoleManager()
				.addConsoles(new IConsole[] { console });
		ConsolePlugin.getDefault().getConsoleManager().showConsoleView(console);
	}

}
