package org.metaborg.util.resource;

import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSelector;

public class FilterFileSelector implements FileSelector {
    private final FileSelector includeSelector;
    private final FileSelector excludeSelector;


    public FilterFileSelector(FileSelector includeSelector, FileSelector excludeSelector) {
        this.includeSelector = includeSelector;
        this.excludeSelector = excludeSelector;
    }


    @Override public boolean includeFile(FileSelectInfo fileInfo) throws Exception {
        if(!excludeSelector.includeFile(fileInfo) && includeSelector.includeFile(fileInfo)) {
            return true;
        }
        return false;
    }

    @Override public boolean traverseDescendents(FileSelectInfo fileInfo) throws Exception {
        if(!excludeSelector.traverseDescendents(fileInfo) && includeSelector.traverseDescendents(fileInfo)) {
            return true;
        }
        return false;
    }
}
