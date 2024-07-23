package mb.util.vfs2.resource;

import static org.junit.Assert.assertEquals;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSelector;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class AntPatternFileSelectorTest {
    private static FileObject baseFolder;


    @BeforeClass public static void setUpBeforeClass() throws Exception {
        baseFolder = VFS.getManager().resolveFile("ram://" + AntPatternFileSelectorTest.class.getName());
        baseFolder.resolveFile("file1").createFile();
        baseFolder.resolveFile("file2").createFile();
        baseFolder.resolveFile("dir1/file1").createFile();
        baseFolder.resolveFile("dir1/file2").createFile();
        baseFolder.resolveFile("dir1/subdir/file10").createFile();
        baseFolder.resolveFile("dir1/subdir/file20").createFile();
        baseFolder.resolveFile("dir2").createFolder();
        baseFolder.resolveFile("dir3/file1").createFile();
        baseFolder.resolveFile("dir3/file2").createFile();
    }

    @AfterClass public static void tearDownAfterClass() throws Exception {
        if(baseFolder != null) {
            baseFolder.delete(Selectors.SELECT_ALL);
        }
    }


    @Test public void testOneLevel() throws FileSystemException {
        final FileSelector selector = new AntPatternFileSelector("*");
        final FileObject[] files = baseFolder.findFiles(selector);
        assertEquals(2, files.length);
    }

    @Test public void testRecursive() throws FileSystemException {
        final FileSelector selector = new AntPatternFileSelector("**");
        final FileObject[] files = baseFolder.findFiles(selector);
        assertEquals(8, files.length);
    }

    @Test public void testFolder() throws FileSystemException {
        final FileSelector selector = new AntPatternFileSelector("dir1");
        final FileObject[] files = baseFolder.findFiles(selector);
        assertEquals(0, files.length);
    }

    @Test public void testInFolder() throws FileSystemException {
        final FileSelector selector = new AntPatternFileSelector("dir1/*");
        final FileObject[] files = baseFolder.findFiles(selector);
        assertEquals(2, files.length);
    }

    @Test public void testInFolderRecursive() throws FileSystemException {
        final FileSelector selector = new AntPatternFileSelector("dir1/**");
        final FileObject[] files = baseFolder.findFiles(selector);
        assertEquals(4, files.length);
    }

    @Test public void testImplicitlyInFolder() throws FileSystemException {
        final FileSelector selector = new AntPatternFileSelector("dir1/");
        final FileObject[] files = baseFolder.findFiles(selector);
        assertEquals(4, files.length);
    }

    @Test public void testQuestionMark() throws FileSystemException {
        final FileSelector selector = new AntPatternFileSelector("**/file?");
        final FileObject[] files = baseFolder.findFiles(selector);
        assertEquals(6, files.length);
    }

    @Test public void testTypeFileAndFolders() throws FileSystemException {
        final FileSelector selector = new AntPatternFileSelector("dir1/", FileType.FILE_OR_FOLDER);
        final FileObject[] files = baseFolder.findFiles(selector);
        assertEquals(6, files.length);
    }

    @Test public void testTypeFolders() throws FileSystemException {
        final FileSelector selector = new AntPatternFileSelector("dir1/", FileType.FOLDER);
        final FileObject[] files = baseFolder.findFiles(selector);
        assertEquals(2, files.length);
    }
}
