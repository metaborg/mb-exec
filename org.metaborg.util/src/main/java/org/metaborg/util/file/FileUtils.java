package org.metaborg.util.file;

import java.io.File;
import java.util.Collection;

import org.apache.commons.vfs2.FileObject;

import com.google.common.collect.Lists;

public class FileUtils {
    public static File toFile(FileObject fileObject) {
        return new File(toPath(fileObject));
    }
    
    public static Iterable<File> toFiles(Iterable<FileObject> fileObjects) {
        final Collection<File> files = Lists.newLinkedList();
        for(FileObject fileObject : fileObjects) {
            files.add(toFile(fileObject));
        }
        return files;
    }
    
    public static String toPath(FileObject fileObject) {
        return fileObject.getName().getPath();
    }
}
