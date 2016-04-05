package org.metaborg.util.resource;

import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSelector;


public class EndsWithFileSelector implements FileSelector {
    private final String endsWith;


    public EndsWithFileSelector(String endsWith) {
        this.endsWith = endsWith;
    }


    @Override public boolean includeFile(FileSelectInfo fileInfo) throws Exception {
        return fileInfo.getFile().getName().getBaseName().endsWith(endsWith);
    }

    @Override public boolean traverseDescendents(FileSelectInfo fileInfo) throws Exception {
        return true;
    }
}
