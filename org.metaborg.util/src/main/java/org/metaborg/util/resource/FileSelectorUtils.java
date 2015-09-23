package org.metaborg.util.resource;

import java.util.Collection;
import java.util.regex.Pattern;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSelector;
import org.apache.commons.vfs2.FileSystemException;
import org.metaborg.util.iterators.Iterables2;

import com.google.common.collect.Lists;

public class FileSelectorUtils {
    public static FileSelector and(Iterable<FileSelector> selectors) {
        return new AndFileSelector(Iterables2.from(selectors));
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


    public static FileSelector contains(String contains) {
        return new ContainsFileSelector(contains);
    }

    public static FileSelector extensions(Iterable<String> extensions) {
        return new ExtensionFileSelector(extensions);
    }

    public static FileSelector extensions(String... extensions) {
        return new ExtensionFileSelector(Iterables2.from(extensions));
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
        final Collection<FileObject> filteredResources = Lists.newLinkedList();
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
}
