package org.spoofax.interpreter.terms;

public class InlinePrinter implements ITermPrinter {
	String out;
	
	public InlinePrinter() {
		out = "";
	}
	
	public String getString() {
		return out;
	}

	public void print(String string) {
		if (string.matches("^ *$")) {
			return ;
		}
		out += string;
	}

	public void indent(int i) {
	}

	public void nextIndentOff() {
	}

	public void outdent(int i) {
	}

	public void println(String string, boolean b) {
		if (string.matches("^ *$")) {
			return ;
		}
		out += string;
	}

	public void println(String string) {
		out += string;
	}
}
