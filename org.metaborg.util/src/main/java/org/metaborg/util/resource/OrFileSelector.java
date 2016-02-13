package org.metaborg.util.resource;

import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSelector;

public class OrFileSelector implements FileSelector {
    private final Iterable<FileSelector> selectors;


    public OrFileSelector(Iterable<FileSelector> selectors) {
        this.selectors = selectors;
    }


    @Override public boolean includeFile(FileSelectInfo fileInfo) throws Exception {
        boolean include = false;
        for(FileSelector selector : selectors) {
            include |= selector.includeFile(fileInfo);
        }
        return include;
    }

    @Override public boolean traverseDescendents(FileSelectInfo fileInfo) throws Exception {
        boolean include = false;
        for(FileSelector selector : selectors) {
            include |= selector.traverseDescendents(fileInfo);
        }
        return include;
    }
}
