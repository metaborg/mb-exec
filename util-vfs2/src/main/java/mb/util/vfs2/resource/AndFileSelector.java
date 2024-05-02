package mb.util.vfs2.resource;

import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSelector;

public class AndFileSelector implements FileSelector {
    private final Iterable<FileSelector> selectors;


    public AndFileSelector(Iterable<FileSelector> selectors) {
        this.selectors = selectors;
    }


    @Override public boolean includeFile(FileSelectInfo fileInfo) throws Exception {
        boolean include = true;
        for(FileSelector selector : selectors) {
            include &= selector.includeFile(fileInfo);
        }
        return include;
    }

    @Override public boolean traverseDescendents(FileSelectInfo fileInfo) throws Exception {
        boolean include = true;
        for(FileSelector selector : selectors) {
            include &= selector.traverseDescendents(fileInfo);
        }
        return include;
    }
}
