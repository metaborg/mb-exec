package org.metaborg.util.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.vfs2.FileName;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.FileTypeSelector;
import org.metaborg.util.log.ILogger;
import org.metaborg.util.log.LoggerUtils;

import static com.google.common.base.Preconditions.checkArgument;

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
        final Set<FileName> names = new HashSet<FileName>();
        final Collection<FileObject> resources = new ArrayList<>();
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

    /**
     * Resolve a "resource" in Java, such as from src/main/resources.
     */
    public static URL resolveJavaResource(String name) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if(loader == null) {
            loader = ResourceUtils.class.getClassLoader();
        }
        return loader.getResource(name);
    }

    /**
     * Read a Java "resource" to string.
     */
    public static String readJavaResource(String name, Charset charset) throws IOException {
        return readInputStream(resolveJavaResource(name).openStream(), charset);
    }

    /**
     * source: https://stackoverflow.com/a/35446009
     */
    public static String readInputStream(InputStream inputStream, Charset charset) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString(charset.name());
    }
}
