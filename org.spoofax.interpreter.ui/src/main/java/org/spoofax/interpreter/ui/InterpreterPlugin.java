/*
 * Copyright (c) 2011, Karl Trygve Kalleberg <karltk at strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.ui;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class InterpreterPlugin extends AbstractUIPlugin {

	private static InterpreterPlugin instance;

	public static InterpreterPlugin instance() {
		if(instance == null) {
			instance = new InterpreterPlugin();
		}
		return instance;
	}
	
	public static void logError(final String message, final Throwable t) {
		final ILog log = instance().getLog();
		final String pluginId = "org.spoofax.interpreter.ui";
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
			public void run() {
				log.log(new Status(Status.ERROR, pluginId, message, t));
			}
		});
	}
}
