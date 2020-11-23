package org.metaborg.util.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Nullable;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileName;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSelector;
import org.apache.commons.vfs2.FileType;
import org.metaborg.util.file.IFileAccess;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class ZipArchiver {
    public static class Entry implements Comparable<Entry> {
        public final String path;
        public final FileObject resource;


        public Entry(String path, FileObject resource) {
            this.path = path;
            this.resource = resource;
        }


        @Override public int hashCode() {
            return path.hashCode();
        }

        @Override public boolean equals(Object obj) {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            final Entry other = (Entry) obj;
            if(!path.equals(other.path))
                return false;
            return true;
        }
        
        @Override public int compareTo(Entry other) {
            return path.compareTo(other.path);
        }

        @Override public String toString() {
            return path;
        }
    }


    private final Set<Entry> entries;


    public ZipArchiver(Iterable<Entry> entries) {
        // Using TreeSet for alphabetical sorting of entries.
        this.entries = Sets.newTreeSet(entries);
    }

    public ZipArchiver() {
        this(Lists.<Entry>newArrayList());
    }


    public void addFile(String relPath, FileObject file) throws IOException {
        if(!file.exists()) {
            return;
        }

        if(file.getType() == FileType.FOLDER) {
            relPath = ensureTrailingSlash(relPath);
        }
        relPath = fixSlashes(relPath);

        final Entry entry = new Entry(relPath, file);
        entries.add(entry);
    }

    public void addFileTo(FileName root, FileObject file) throws IOException {
        while(!file.getName().equals(root)) {
            final String relPath = root.getRelativeName(file.getName());
            addFile(relPath, file);
            file = file.getParent();
        }
    }

    public void addFilesTo(FileName root, FileObject... files) throws IOException {
        for(FileObject file : files) {
            addFileTo(root, file);
        }
    }

    public void addFilesTo(FileName root, FileObject base, FileSelector fileSelector) throws IOException {
        final FileObject[] files = base.findFiles(fileSelector);
        if(files == null) {
            return;
        }
        addFilesTo(root, files);
    }


    public FileObject build(FileObject zipFile, @Nullable IFileAccess fileAccess) throws IOException {
        zipFile.createFile();
        final OutputStream zipOutputStream = zipFile.getContent().getOutputStream();
        try(final ZipOutputStream zip = new ZipOutputStream(zipOutputStream)) {
            for(Entry entry : entries) {
                final String entryPath = entry.path;
                final FileObject resource = entry.resource;
                final ZipEntry zipEntry = new ZipEntry(entryPath);

                if(resource.getType() == FileType.FILE) {
                    if(fileAccess != null) {
                        fileAccess.read(resource);
                    }

                    final long modified = resource.getContent().getLastModifiedTime();
                    if(modified != 0L) {
                        zipEntry.setTime(modified);
                    }
                    zip.putNextEntry(zipEntry);
                    try(final InputStream inputStream = resource.getContent().getInputStream()) {
                        IOUtils.copy(inputStream, zip);
                    }
                    zip.closeEntry();
                } else if(resource.getType() == FileType.FOLDER) {
                    zip.putNextEntry(zipEntry);
                    zip.closeEntry();
                }
            }
        } finally {
            if(fileAccess != null) {
                fileAccess.write(zipFile);
            }
        }
        return zipFile;
    }


    private static String fixSlashes(String path) {
        // Convert \ to / on Windows; ZIP/JAR files must use / for paths.
        return path.replace('\\', '/');
    }

    private static String ensureTrailingSlash(String path) {
        // Ensure trailing slash for directory paths.
        if(!path.endsWith("/")) {
            path = path + '/';
        }
        return path;
    }
}
