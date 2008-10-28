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
 * handles the notion of a current directory, and may provide for caching
 * or 
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
    
    private String workingDir = ".";
    private int fileCounter = 3;
    
    public IOAgent() {
        stdinStream = System.in;
        stdoutStream = System.out;
        stderrStream = System.err;
    }
    
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

    public int openRandomAccessFile(String fn, String mode) throws InterpreterException {
        String m = mode.indexOf('w') >= 0 ? "rw" : "r";
        try {
            fileMap.put(fileCounter, new RandomAccessFile(adaptFilePath(fn), m));
        } catch(FileNotFoundException e) {
            throw new InterpreterException(e); 
        }
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
     * @param isInternalFile  Indicates the path is relative to the Stratego source tree or imports,
     *                        e.g., a .pp.af file included by import-term.
     */
    public InputStream openInputStream(String fn, boolean isInternalFile) throws FileNotFoundException {
        // TODO: Handle isSourceRelative paths (for import-term)
        return new FileInputStream(adaptFilePath(fn));
    }
    
    public final InputStream openInputStream(String fn) throws FileNotFoundException {
        return openInputStream(fn, false);
    }
    
    public OutputStream openFileOutputStream(String fn) throws FileNotFoundException {
        return new FileOutputStream(adaptFilePath(fn));
    }
    
    public File openFile(String fn) {
        return new File(adaptFilePath(fn));
    }
    
    protected String adaptFilePath(String fn) {
        return new File(getWorkingDir(), fn).getAbsolutePath();
    }
    
    public String getWorkingDir() {
        return workingDir;
    }
    
    public void setWorkingDir(String workingDir) throws FileNotFoundException {
        if (!new File(workingDir).exists()) {
            throw new FileNotFoundException(workingDir);
        }
        this.workingDir = workingDir;
    }
}
