package mb.util.vfs2.resource;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSelector;
import org.apache.commons.vfs2.FileType;
import org.metaborg.util.iterators.Iterables2;

/**
 * AntPatternFileSelector is a VFS2 file selector that is configured using Ant patterns. Examples of patterns are:
 * <ul>
 * <li><i>somedir&#47;**</i> recursively selects all files under directory <i>somedir</i>; same as <i>somedir/</i>
 * <li><i>somedir&#47;*</i> selects all files directly in directory <i>somedir</i>
 * <li><i>**&#47;somedir&#47;*</i> selects all files in directories named <i>somedir</i>
 * <li><i>file?</i> selects all files name <i>file</i> followed by one more character
 * </ul>
 * <p>
 * To keep the exact same behavior as Ant, by default only files, but not folders are selected. * This behavior can be
 * changed by passing a FileType argument to the constructor.
 * <p>
 * The file selector is inclusive. To create an exclusive selector, or to build more complicated selectors, see the
 * combinators in the {@link FileSelectorUtils} class.
 */
public class AntPatternFileSelector implements FileSelector {
    private static final FileType DEFAULT_FILETYPE = FileType.FILE;

    private final Set<AntPattern> patterns = new HashSet<>();
    private final FileType fileType;

    
    /**
     * Create a file selector that selects files that match the pattern.
     *
     * @param pattern
     *            Pattern to match against
     */
    public AntPatternFileSelector(String pattern) {
        this(Iterables2.singleton(pattern), DEFAULT_FILETYPE);
    }

    /**
     * Create a file selector that selects files of the specified type, that match the pattern.
     * 
     * @param pattern
     *            Pattern to match against
     * @param fileType
     *            Type of files to match
     */
    public AntPatternFileSelector(String pattern, FileType fileType) {
        this(Iterables2.singleton(pattern), fileType);
    }

    /**
     * Create a file selector that selects files that match any of the patterns.
     * 
     * @param patterns
     *            Patterns to match against
     */
    public AntPatternFileSelector(Iterable<String> patterns) {
        this(patterns, DEFAULT_FILETYPE);
    }

    /**
     * Create a file selector that selects files of the specified type, that match any of the patterns.
     * 
     * @param patterns
     *            Patterns to match against
     * @param fileType
     *            Type of files to match
     */
    public AntPatternFileSelector(Iterable<String> patterns, FileType fileType) {
        for(String pattern : patterns) {
            this.patterns.add(new AntPattern(pattern));
        }
        this.fileType = fileType;
    }


    @Override public boolean includeFile(FileSelectInfo fileInfo) throws Exception {
        if(FileSelectorUtils.typeMatches(fileInfo.getFile().getType(), this.fileType)) {
            final String relativePath = fileInfo.getBaseFolder().getName().getRelativeName(fileInfo.getFile().getName());
            for(AntPattern pattern : patterns) {
                if(pattern.match(relativePath)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override public boolean traverseDescendents(FileSelectInfo fileInfo) throws Exception {
        return true;
    }
}
