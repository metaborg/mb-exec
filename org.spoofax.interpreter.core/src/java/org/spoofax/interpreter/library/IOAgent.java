package org.spoofax.interpreter.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.ssl.RandomAccessInputStream;
import org.spoofax.interpreter.library.ssl.RandomAccessOutputStream;

/**
 * The IOAgent class provides access to filesystem from Stratego programs,
 * handles the notion of a current directory, and may be overridden
 * to redirect console or file I/O streams. 
 * 
 * @author Lennart Kats <lennart add lclnet.nl>
 * @author Karl Trygve Kalleberg <karltk near strategoxt.org>
 */
public class IOAgent {
    public final static int CONST_STDIN = 0;
    public final static int CONST_STDOUT = 1;
    public final static int CONST_STDERR = 2;
    
    private InputStream stdinStream;
    private OutputStream stdoutStream;
    private OutputStream stderrStream;
    
    private Map<Integer, RandomAccessFile> fileMap = new HashMap<Integer, RandomAccessFile>();
    
    private String workingDir;
    private String definitionDir;
    private int fileCounter = 3;
    
    public IOAgent() {
        stdinStream = System.in;
        stdoutStream = System.out;
        stderrStream = System.err;
        
        try {
            String dir = System.getProperty("user.dir");
            if (dir == null) dir = ".";
            setWorkingDir(dir);
            setDefinitionDir(dir);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    // DIRECTORIES AND PATHS
    
    public String getWorkingDir() {
        return workingDir;
    }
    
    public String getDefinitionDir() {
        return definitionDir;
    }
    
    public String getTempDir() {
        return System.getProperty("java.io.tmpdir");
    }
    
    public void setWorkingDir(String workingDir) throws FileNotFoundException {
        File workingDirFile = new File(getAbsolutePath(getWorkingDir(), workingDir));
        if (!workingDirFile.exists() || !workingDirFile.isDirectory()) {
            throw new FileNotFoundException(workingDir);
        }
        this.workingDir = workingDirFile.getAbsolutePath();
    }
    
    public void setDefinitionDir(String definitionDir) throws FileNotFoundException {
        File definitionDirFile = new File(getAbsolutePath(getDefinitionDir(), definitionDir));
        if (!definitionDirFile.exists() || !definitionDirFile.isDirectory()) {
            throw new FileNotFoundException(definitionDir);
        }
        this.definitionDir = definitionDirFile.getAbsolutePath();
    }
    
    @Deprecated // use getAbsolutePath instead
    protected String adaptFilePath(String fn) {
        return getAbsolutePath(getWorkingDir(), fn);
    }
    
    /**
     * Converts a path relative to a given directory
     * to an absolute path.
     */
    protected String getAbsolutePath(String dir, String fn) {
        File f = new File(fn);
        return f.isAbsolute()
            ? f.getAbsolutePath()
            : new File(dir, fn).getAbsolutePath();
    }
    
    // OPENING, MANIPULATING FILES
    
    public OutputStream getOutputStream(int fd) {
        if(fd == CONST_STDOUT) {
            return stdoutStream;
        } else if (fd == CONST_STDERR) {
            return stderrStream;
        }
        RandomAccessFile raf = fileMap.get(fd);
        if(raf == null)
            return null;
        
        return new RandomAccessOutputStream(raf);
    }

    public boolean closeRandomAccessFile(int fd) throws InterpreterException {
        RandomAccessFile raf = fileMap.get(fd);
        if(raf == null)
            return false;
        try {
            raf.close();
        } catch(IOException e) {
            throw new InterpreterException(e);
        }
        fileMap.remove(fd);
        return true;
    }

    public int openRandomAccessFile(String fn, String mode) throws FileNotFoundException {
        String m = mode.indexOf('w') >= 0 ? "rw" : "r";
        fileMap.put(fileCounter, new RandomAccessFile(getAbsolutePath(getWorkingDir(), fn), m));
        return fileCounter++;
    }

    public InputStream getInputStream(int fd) {
        if(fd == CONST_STDIN) {
            return stdinStream;
        }
        RandomAccessFile raf = fileMap.get(fd);
        if(raf == null)
            return null;
        
        return new RandomAccessInputStream(raf);
    }
    
    /**
     * Opens an input stream given a file path.
     * 
     * @param isDefinitionFile
     *            Indicates the path is relative to the Stratego source tree or
     *            imports, e.g., a .pp.af file included by import-term.
     */
    public InputStream openInputStream(String fn, boolean isDefinitionFile) throws FileNotFoundException {
        String dir = isDefinitionFile ? getDefinitionDir() : getWorkingDir();
        return new FileInputStream(getAbsolutePath(dir, fn));
    }
    
    public final InputStream openInputStream(String fn) throws FileNotFoundException {
        return openInputStream(fn, false);
    }
    
    public OutputStream openFileOutputStream(String fn) throws FileNotFoundException {
        return new FileOutputStream(getAbsolutePath(getWorkingDir(), fn));
    }
    
    public File openFile(String fn) {
        return new File(getAbsolutePath(getWorkingDir(), fn));
    }
    
    public String createTempFile(String prefix) throws IOException {
        return File.createTempFile(prefix, null).getPath(); 
    }
    
    public boolean mkdir(String fn) {
        return openFile(fn).mkdir();
    }
    
    @Deprecated // this is not a Stratego primitive; use mkdir instead
    public boolean mkDirs(String fn) {
        return openFile(fn).mkdirs();
    }
}
