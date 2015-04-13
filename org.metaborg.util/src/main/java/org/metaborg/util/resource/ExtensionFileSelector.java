package org.metaborg.util.resource;

import java.util.Set;

import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSelector;

import com.google.common.collect.Sets;


public class ExtensionFileSelector implements FileSelector {
    private final Set<String> extensions;


    public ExtensionFileSelector(String extension) {
        this.extensions = Sets.newHashSet(extension);
    }

    public ExtensionFileSelector(Iterable<String> extensions) {
        this.extensions = Sets.newHashSet(extensions);
    }


    @Override public boolean includeFile(FileSelectInfo fileInfo) throws Exception {
        final String extension = fileInfo.getFile().getName().getExtension();
        return extensions.contains(extension);
    }

    @Override public boolean traverseDescendents(FileSelectInfo fileInfo) throws Exception {
        return true;
    }
}
