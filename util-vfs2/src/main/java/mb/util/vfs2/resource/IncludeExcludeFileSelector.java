package mb.util.vfs2.resource;

import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSelector;

public class IncludeExcludeFileSelector implements FileSelector {
    private final FileSelector include;
    private final FileSelector exclude;


    public IncludeExcludeFileSelector(FileSelector include, FileSelector exclude) {
        this.include = include;
        this.exclude = exclude;
    }


    @Override public boolean includeFile(FileSelectInfo fileInfo) throws Exception {
        if(exclude.includeFile(fileInfo)) {
            return false;
        }
        if(!include.includeFile(fileInfo)) {
            return false;
        }
        return true;
    }

    @Override public boolean traverseDescendents(FileSelectInfo fileInfo) throws Exception {
        return true;
    }
}
