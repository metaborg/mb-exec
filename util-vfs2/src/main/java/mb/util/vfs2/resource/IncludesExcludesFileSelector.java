package mb.util.vfs2.resource;

import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSelector;

public class IncludesExcludesFileSelector implements FileSelector {
    private final Iterable<FileSelector> includes;
    private final Iterable<FileSelector> excludes;


    public IncludesExcludesFileSelector(Iterable<FileSelector> includes, Iterable<FileSelector> excludes) {
        this.includes = includes;
        this.excludes = excludes;
    }


    @Override public boolean includeFile(FileSelectInfo fileInfo) throws Exception {
        for(FileSelector exclude : excludes) {
            if(exclude.includeFile(fileInfo)) {
                return false;
            }
        }
        for(FileSelector include : includes) {
            if(!include.includeFile(fileInfo)) {
                return false;
            }
        }
        return true;
    }

    @Override public boolean traverseDescendents(FileSelectInfo fileInfo) throws Exception {
        return true;
    }
}
