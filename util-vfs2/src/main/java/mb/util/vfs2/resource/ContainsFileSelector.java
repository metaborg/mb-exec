package mb.util.vfs2.resource;

import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSelector;


public class ContainsFileSelector implements FileSelector {
    private final String contains;


    public ContainsFileSelector(String contains) {
        this.contains = contains;
    }


    @Override public boolean includeFile(FileSelectInfo fileInfo) throws Exception {
        return fileInfo.getFile().getName().getBaseName().contains(contains);
    }

    @Override public boolean traverseDescendents(FileSelectInfo fileInfo) throws Exception {
        return true;
    }
}
