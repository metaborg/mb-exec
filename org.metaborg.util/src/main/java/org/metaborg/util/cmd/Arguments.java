package org.metaborg.util.cmd;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;

import javax.annotation.Nullable;

public class Arguments implements Iterable<Object>, Serializable {
    private static final long serialVersionUID = -5031843820289891138L;
    private static final Pattern spaces = Pattern.compile("[\\s]");

    private final Collection<Object> arguments;

    public int size() {
        return this.arguments.size();
    }

    public Arguments() {
        this.arguments = Lists.newLinkedList();
    }

    public Arguments(Arguments other) {
        this.arguments = Lists.newLinkedList(other.arguments);
    }

    public Arguments add(Object arg) {
        if (arg == null || (arg instanceof String && ((String)arg).isEmpty()))
            return this;
        arguments.add(arg);
        return this;
    }

    public Arguments add(Object arg0, Object arg1) {
        add(arg0);
        add(arg1);
        return this;
    }

    public Arguments add(Object... args) {
        for (Object arg : args) {
            add(arg);
        }
        return this;
    }

    public Arguments addLine(String line) {
        add(spaces.split(line));
        return this;
    }

    public Arguments addFile(File file) {
        add(file);
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

    public Arguments addAll(Arguments args) {
        addAll(args.arguments);
        return this;
    }

    public Arguments addAll(Iterable<Object> args) {
        for(Object obj : args) {
            add(obj);
        }
        return this;
    }


    public Arguments clear() {
        arguments.clear();
        return this;
    }

    /**
     * Returns the arguments as an iterable of strings.
     *
     * @param workingDirectory The working directory relative to which the paths have to be;
     *                         or <code>null</code> to keep paths absolute.
     * @return An iterable of strings.
     */
    public List<String> asStrings(@Nullable Path workingDirectory) {
        List<String> result = new ArrayList<>(this.size());
        for (Object arg : this) {
            result.add(asString(arg, workingDirectory));
        }
        return result;
    }

    private String asString(Object arg, @Nullable Path workingDirectory) {
        if (arg instanceof File) {
            Path path = ((File)arg).toPath();
            if (workingDirectory != null) {
                path = workingDirectory.relativize(path);
            }
            return StringUtils.fixFileSeparatorChar(path.toString());
        } else {
            return arg.toString();
        }
    }

    @Override public Iterator<Object> iterator() {
        return arguments.iterator();
    }

    @Override public String toString() {
        boolean first = true;
        final StringBuilder sb = new StringBuilder();
        for(String arg : asStrings(null)) {
            if(!first) {
                sb.append(' ');
            }
            sb.append(StringUtils.quoteArgument(arg));
            first = false;
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
