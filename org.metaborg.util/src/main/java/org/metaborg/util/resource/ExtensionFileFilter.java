package org.metaborg.util.resource;

import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSelector;

import com.google.common.collect.Iterables;


public class ExtensionFileFilter implements FileSelector {
    private final Iterable<String> extensions;


    public ExtensionFileFilter(Iterable<String> extensions) {
        this.extensions = extensions;
    }


    @Override public boolean includeFile(FileSelectInfo fileInfo) throws Exception {
        final String extension = fileInfo.getFile().getName().getExtension();
        return Iterables.contains(extensions, extension);
    }

    @Override public boolean traverseDescendents(FileSelectInfo fileInfo) throws Exception {
        return true;
    }
}
