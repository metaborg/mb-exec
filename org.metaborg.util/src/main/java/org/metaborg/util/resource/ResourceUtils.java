package org.metaborg.util.resource;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.vfs2.FileName;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.FileTypeSelector;
import org.apache.commons.vfs2.NameScope;
import org.metaborg.util.log.ILogger;
import org.metaborg.util.log.LoggerUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class ResourceUtils {
    private static final ILogger logger = LoggerUtils.logger(ResourceUtils.class);

    /**
     * Returns all files in given location, recursively.
     * 
     * @param location
     *            Location to search for files in.
     * @return All files in given location.
     * @throws RuntimeException
     *             When finding files fails.
     */
    public static FileObject[] expand(FileObject location) {
        try {
            if(location.exists()) {
                final FileObject[] files = location.findFiles(new FileTypeSelector(FileType.FILE));
                return files;
            }
        } catch(FileSystemException e) {
            final String message = logger.format("Failed to get all files at location {}", location);
            throw new RuntimeException(message, e);
        }
        return new FileObject[0];
    }

    /**
     * Returns all files in given locations, recursively. Files contained in multiple locations are only included once.
     * 
     * @param locations
     *            Locations to search for files in.
     * @return All files in given locations.
     * @throws RuntimeException
     *             When finding files fails.
     */
    public static Collection<FileObject> expand(Iterable<FileObject> locations) {
        final Set<FileName> names = Sets.newHashSet();
        final Collection<FileObject> resources = Lists.newArrayList();
        for(FileObject location : locations) {
            final FileObject[] files = expand(location);
            for(FileObject file : files) {
                final FileName name = file.getName();
                if(!names.contains(name)) {
                    names.add(name);
                    resources.add(file);
                }
            }
        }
        return resources;
    }

    /**
     * Get a relative name with respect to a base, or the absolute URI when not relative to base.
     * 
     * The following invariant holds: {@code resource.equals(base.resolveFile(relativeName(resource, base, strict))) }
     * 
     * @param resource
     *            The resource name to get the relative name of
     * @param base
     *            The base name with respect to which the result name must be relative
     * @param strict
     *            If true, only return a relative name if resource is a descendent of base. If false, also return a
     *            relative name if resource is not a descendent of base, but in the same filesystem.
     * @return Relative name if possible, otherwise absolute URI
     */
    public static String relativeName(FileName resource, FileName base, boolean strict) {
        final boolean isEqualOrAncestor = resource.equals(base) || resource.isAncestor(base);
        final boolean isInSameFilesystem = resource.getRoot().equals(base.getRoot());
        if(isEqualOrAncestor || (!strict && isInSameFilesystem)) {
            try {
                return base.getRelativeName(resource);
            } catch(FileSystemException ex) {
            }
        }
        return resource.getURI();
    }

    /**
     * Finds a file, relative to this file. The path can be either relative, or absolute. Unlike
     * {@code FileObject::resolveFile( path )}, this method also resolves absolute paths into different file systems
     * than that of the baseFile.
     *
     * @param path
     *            The path of the file to locate. Can either be a relative path or an absolute path.
     * @return The file.
     * @throws FileSystemException
     *             On error parsing the path, or on error finding the file.
     */
    public static FileObject resolveFile(FileObject baseFile, String name) throws FileSystemException {
        return baseFile.getFileSystem().getFileSystemManager().resolveFile(baseFile, name);
    }
}
