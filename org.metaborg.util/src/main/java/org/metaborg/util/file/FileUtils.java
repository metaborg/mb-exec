package org.metaborg.util.file;

import java.io.File;

import org.apache.commons.vfs2.FileObject;

public class FileUtils {
    public static File toFile(FileObject fileObject) {
        return new File(fileObject.getName().getPath());
    }
}
