package org.metaborg.util.log;

public enum Level {
    Trace, Debug, Info, Warn, Error;

    public static Level parse(String name) {
        for(Level level : Level.values()) {
            if(level.name().equalsIgnoreCase(name)) {
                return level;
            }
        }
        throw new IllegalArgumentException("Unknown level: " + name);
    }

}
