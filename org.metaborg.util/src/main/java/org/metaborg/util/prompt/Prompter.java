package org.metaborg.util.prompt;

import java.io.Console;
import java.io.IOException;

public class Prompter {

    private final Console console;

    private Prompter() throws IOException {
        this.console = System.console();
        if(console == null) {
            throw new IOException("No console found.");
        }
    }

    public String readString(String prompt) {
        String value = console.readLine("%s: ", prompt);
        return value == null ? "" : value;
    }

    // SINGLETON STUFF

    private static Prompter prompter;

    public static synchronized Prompter get() throws IOException {
        if(prompter == null) {
            prompter = new Prompter();
        }
        return prompter;
    }

}
