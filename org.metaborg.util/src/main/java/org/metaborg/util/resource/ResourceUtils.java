package org.metaborg.util.resource;

import java.util.Collection;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.FileTypeSelector;
import org.metaborg.util.iterators.Iterables2;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class ResourceUtils {
    public static Iterable<FileObject> expand(FileObject location) {
        try {
            if(location.exists()) {
                return Iterables2.from(location.findFiles(new FileTypeSelector(FileType.FILE)));
            }
        } catch(FileSystemException e) {
            final String message = String.format("Failed to get all files at location %s", location);
            throw new RuntimeException(message, e);
        }
        return Iterables2.empty();
    }

    public static Iterable<FileObject> expand(Iterable<FileObject> locations) {
        final Collection<FileObject> resources = Lists.newLinkedList();
        for(FileObject location : locations) {
            Iterables.addAll(resources, expand(location));
        }
        return resources;
    }
}
