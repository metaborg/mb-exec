package org.metaborg.util.resource;

import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSelector;

public class NoneFileSelector implements FileSelector {
    @Override public boolean includeFile(FileSelectInfo fileInfo) throws Exception {
        return false;
    }

    @Override public boolean traverseDescendents(FileSelectInfo fileInfo) throws Exception {
        return false;
    }
}
