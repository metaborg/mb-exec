package mb.util.vfs2.resource;

import static org.apache.commons.vfs2.FileType.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.regex.Pattern;

import org.apache.commons.vfs2.AllFileSelector;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSelector;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileType;
import org.metaborg.util.iterators.Iterables2;

public class FileSelectorUtils {
    public static FileSelector all() {
        return new AllFileSelector();
    }

    public static FileSelector none() {
        return new NoneFileSelector();
    }


    public static FileSelector contains(String contains) {
        return new ContainsFileSelector(contains);
    }


    public static FileSelector endsWith(String endsWith) {
        return new EndsWithFileSelector(endsWith);
    }

    public static FileSelector extensions(Collection<String> extensions) {
        return new ExtensionFileSelector(extensions);
    }

    public static FileSelector extensions(String... extensions) {
        return new ExtensionFileSelector(Arrays.asList(extensions));
    }

    public static FileSelector extension(String extension) {
        return new ExtensionFileSelector(extension);
    }


    public static FileSelector regex(String regex) {
        return new PatternFileSelector(regex);
    }

    public static FileSelector regex(String regex, int flags) {
        return new PatternFileSelector(regex, flags);
    }

    public static FileSelector regex(Pattern pattern) {
        return new PatternFileSelector(pattern);
    }


    public static FileSelector ant(String pattern) {
        return new AntPatternFileSelector(pattern);
    }

    public static FileSelector ant(String pattern, FileType fileType) {
        return new AntPatternFileSelector(pattern, fileType);
    }

    public static FileSelector ant(Iterable<String> patterns) {
        return new AntPatternFileSelector(patterns);
    }

    public static FileSelector ant(String... patterns) {
        return new AntPatternFileSelector(Iterables2.from(patterns));
    }

    public static FileSelector ant(Iterable<String> patterns, FileType fileType) {
        return new AntPatternFileSelector(patterns, fileType);
    }


    public static FileSelector and(Iterable<FileSelector> selectors) {
        return new AndFileSelector(selectors);
    }

    public static FileSelector and(FileSelector... selectors) {
        return and(Iterables2.from(selectors));
    }


    public static FileSelector or(Iterable<FileSelector> selectors) {
        return new OrFileSelector(selectors);
    }

    public static FileSelector or(FileSelector... selectors) {
        return or(Iterables2.from(selectors));
    }


    public static FileSelector not(FileSelector selector) {
        return new NotFileSelector(selector);
    }


    public static FileSelector includeExclude(FileSelector include, FileSelector exclude) {
        return new IncludeExcludeFileSelector(include, exclude);
    }

    public static FileSelector includesExcludes(Iterable<FileSelector> includes, Iterable<FileSelector> excludes) {
        return new IncludesExcludesFileSelector(includes, excludes);
    }


    public static FileSelectInfo info(FileObject file, FileObject base, int depth) {
        return new DefaultFileSelectInfo(base, file, depth);
    }

    public static FileSelectInfo info(FileObject file, FileObject base) {
        return info(base, file, -1);
    }

    public static FileSelectInfo info(FileObject file) throws FileSystemException {
        return info(file, file.getParent());
    }


    public static boolean include(FileSelector selector, FileObject resource, FileObject base)
        throws FileSystemException {
        int depth = depth(resource, base);
        FileObject current = resource;
        FileSelectInfo info = info(current, base, depth);

        try {
            // If file is excluded beforehand, stop immediately
            if(!selector.includeFile(info)) {
                return false;
            }

            // Check only once if resource equals the base
            if(depth == 0) {
                return includeOne(selector, resource, info);
            }

            // Go over the file and its ancestors to check if it should be excluded
            while(depth >= 0) {
                if(!includeOne(selector, current, info)) {
                    return false;
                }

                current = current.getParent();
                if(current == null) {
                    throw new FileSystemException("vfs.provider/find-files.error", resource);
                }
                --depth;
                info = info(current, base, depth);
            }
        } catch(Exception e) {
            throw new FileSystemException("vfs.provider/find-files.error", resource, e);
        }

        return true;
    }

    public static boolean includeOne(FileSelector selector, FileObject resource, FileSelectInfo info)
        throws FileSystemException, Exception {
        switch(resource.getType()) {
            case FILE:
                if(!selector.includeFile(info)) {
                    return false;
                }
                break;
            case FOLDER:
                if(!selector.traverseDescendents(info)) {
                    return false;
                }
                break;
            case FILE_OR_FOLDER:
            case IMAGINARY:
                if(!selector.includeFile(info) || !selector.traverseDescendents(info)) {
                    return false;
                }
                break;
        }
        return true;
    }

    public static Iterable<FileObject> filter(FileSelector selector, Iterable<FileObject> resources, FileObject base)
        throws FileSystemException {
        final Collection<FileObject> filteredResources = new LinkedList<>();
        for(FileObject resource : resources) {
            if(include(selector, resource, base)) {
                filteredResources.add(resource);
            }
        }
        return filteredResources;
    }

    private static int depth(FileObject resource, FileObject base) {
        int depth = 0;
        FileObject current = resource;
        while(!current.getName().equals(base.getName())) {
            ++depth;
            try {
                current = current.getParent();
            } catch(FileSystemException e) {
                throw new RuntimeException("Cannot calculate depth", e);
            }
            if(current == null) {
                throw new RuntimeException("Cannot calculate depth, resource is not part of base");
            }
        }
        return depth;
    }


    public static boolean typeMatches(FileType type, FileType expectedType) {
        return type == expectedType || (expectedType == FILE_OR_FOLDER && (type == FILE || type == FOLDER));
    }
}
