package org.spoofax.interpreter.library;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
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
    
    private final Map<Integer, InputStream> inStreams = new HashMap<Integer, InputStream>();
    
    private final Map<Integer, PrintStream> outStreams = new HashMap<Integer, PrintStream>();
    
    private String workingDir;
    
    private String definitionDir;
    
    private int fileCounter = 3;
    
    public IOAgent() {
        try {
            String dir = System.getProperty("user.dir");
            if (dir == null) dir = ".";
            setWorkingDir(dir);
            setDefinitionDir(dir);
        } catch (IOException e) {
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
    
    public void setWorkingDir(String workingDir) throws FileNotFoundException, IOException {
        File workingDirFile = new File(getAbsolutePath(getWorkingDir(), workingDir));
        if (!workingDirFile.exists() || !workingDirFile.isDirectory()) {
            throw new FileNotFoundException(workingDir);
        }
        this.workingDir = workingDirFile.getCanonicalPath();
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
    
    public PrintStream getOutputStream(int fd) {
        if(fd == CONST_STDOUT) {
            return System.out;
        } else if (fd == CONST_STDERR) {
            return System.err;
        }
        return outStreams.get(fd);
    }

    public boolean closeRandomAccessFile(int fd) throws InterpreterException {
        if (fd == CONST_STDOUT || fd == CONST_STDERR || fd == CONST_STDIN)
            return true;
        
        OutputStream stream = outStreams.get(fd);
        if(stream == null)
            return true; // already closed: be forgiving
        try {
            stream.close();
        } catch (IOException e) {
            return true;
        }
        inStreams.remove(fd);
        outStreams.remove(fd);
        return true;
    }

    public int openRandomAccessFile(String fn, String mode) throws FileNotFoundException, IOException {
        String m = mode.indexOf('w') >= 0 ? "rw" : "r";
        RandomAccessFile file = new RandomAccessFile(getAbsolutePath(getWorkingDir(), fn), m);
        
        inStreams.put(fileCounter, new BufferedInputStream(new RandomAccessInputStream(file)));
        outStreams.put(fileCounter, new PrintStream(new BufferedOutputStream(new RandomAccessOutputStream(file))));
        
        if (m.equals("rw")) file.setLength(0); // HACK
        
        return fileCounter++;
    }

    public InputStream getInputStream(int fd) {
        return fd == CONST_STDIN ? System.in : inStreams.get(fd);
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
