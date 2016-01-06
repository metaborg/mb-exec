package org.metaborg.util.cmd;

import java.io.File;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;

public class Arguments implements Iterable<String>, Serializable {
    private static final long serialVersionUID = -5031843820289891138L;
    private static final Pattern spaces = Pattern.compile("[\\s]");

    private Collection<String> arguments;


    public Arguments() {
        this.arguments = Lists.newLinkedList();
    }

    public Arguments(Arguments other) {
        this.arguments = Lists.newLinkedList(other.arguments);
    }


    public Arguments add(String arg) {
        if(arg == null || arg.isEmpty()) {
            return this;
        }
        arguments.add(arg);
        return this;
    }

    public Arguments addLine(String line) {
        addAll(spaces.split(line));
        return this;
    }

    public Arguments addFile(File file) {
        add(StringUtils.fixFileSeparatorChar(file.toString()));
        return this;
    }

    public Arguments addFile(String flag, File file) {
        add(flag);
        addFile(file);
        return this;
    }

    public Arguments addFiles(File... files) {
        for(File file : files) {
            addFile(file);
        }
        return this;
    }

    public Arguments addAll(String... args) {
        for(String arg : args) {
            add(arg);
        }
        return this;
    }

    public Arguments addAll(Object... args) {
        for(Object obj : args) {
            add(obj.toString());
        }
        return this;
    }

    public Arguments addAll(Arguments args) {
        addAll(args.arguments);
        return this;
    }

    public Arguments addAll(Iterable<String> args) {
        for(String arg : args) {
            add(arg);
        }
        return this;
    }

    public Arguments addAllObjs(Iterable<Object> args) {
        for(Object obj : args) {
            add(obj.toString());
        }
        return this;
    }


    public Arguments clear() {
        arguments.clear();
        return this;
    }


    public String[] toArray() {
        return arguments.toArray(new String[0]);
    }

    @Override public Iterator<String> iterator() {
        return arguments.iterator();
    }

    @Override public String toString() {
        boolean first = true;
        final StringBuilder sb = new StringBuilder();
        for(String arg : arguments) {
            if(!first) {
                sb.append(' ');
            }
            sb.append(StringUtils.quoteArgument(arg));
            first = true;
        }
        return sb.toString();
    }


    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + arguments.hashCode();
        return result;
    }

    @Override public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        final Arguments other = (Arguments) obj;
        if(!arguments.equals(other.arguments))
            return false;
        return true;
    }
}
