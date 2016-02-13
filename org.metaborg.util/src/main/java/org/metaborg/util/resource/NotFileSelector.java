package org.metaborg.util.resource;

import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSelector;

public class NotFileSelector implements FileSelector {
    private final FileSelector selector;


    public NotFileSelector(FileSelector selector) {
        this.selector = selector;
    }


    @Override public boolean includeFile(FileSelectInfo fileInfo) throws Exception {
        return !selector.includeFile(fileInfo);
    }

    @Override public boolean traverseDescendents(FileSelectInfo fileInfo) throws Exception {
        return !selector.traverseDescendents(fileInfo);
    }
}
