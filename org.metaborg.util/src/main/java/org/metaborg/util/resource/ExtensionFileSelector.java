package org.metaborg.util.resource;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSelector;


public class ExtensionFileSelector implements FileSelector {
    private final Set<String> extensions;


    public ExtensionFileSelector(String extension) {
        this.extensions = new HashSet<>(Collections.singleton(extension));
    }

    public ExtensionFileSelector(Collection<String> extensions) {
        this.extensions = new HashSet<>(extensions);
    }


    @Override public boolean includeFile(FileSelectInfo fileInfo) throws Exception {
        final String extension = fileInfo.getFile().getName().getExtension();
        return extensions.contains(extension);
    }

    @Override public boolean traverseDescendents(FileSelectInfo fileInfo) throws Exception {
        return true;
    }
}
