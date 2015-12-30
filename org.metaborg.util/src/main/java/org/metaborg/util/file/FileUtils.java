package org.metaborg.util.file;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import org.apache.commons.vfs2.FileName;
import org.apache.commons.vfs2.FileObject;

import com.google.common.collect.Lists;

public class FileUtils {
    public static File toFile(FileObject fileObject) {
        try {
            final FileName name = fileObject.getName();
            final URI uri = new URI(name.getURI());
            final File file = new File(uri);
            return file;
        } catch(URISyntaxException e) {
            throw new RuntimeException("Could not convert FileObject to File", e);
        }
    }
    
    public static Iterable<File> toFiles(Iterable<FileObject> fileObjects) {
        final Collection<File> files = Lists.newLinkedList();
        for(FileObject fileObject : fileObjects) {
            files.add(toFile(fileObject));
        }
        return files;
    }
    
    public static URI toURI(FileObject fileObject) {
        try {
            return new URI(fileObject.getName().getURI());
        } catch(URISyntaxException e) {
            throw new RuntimeException("Could not convert FileObject to URI", e);
        }
    }
}
