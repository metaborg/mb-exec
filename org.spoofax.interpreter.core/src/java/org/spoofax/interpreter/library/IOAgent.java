/*
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
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

    private final static String FILE_ENCODING = "UTF-8";

    private final static Charset FILE_CHARSET = Charset.forName(FILE_ENCODING);

    private final Map<Integer, FileHandle> openFiles = new HashMap<Integer, FileHandle>();

    private final Writer stdoutWriter = new PrintStreamWriter(System.out);

    private final Writer stderrWriter = new PrintStreamWriter(System.err);

    private final Reader stdinReader = new InputStreamReader(System.in, FILE_CHARSET);

    private final OutputStream stdout = System.out;

    private final OutputStream stderr = System.err;

    private final InputStream stdin = System.in;

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

    /**
     * Gets the writer for a file.
     * Should not be used together with getReader() for the same file.
     */
    public Writer getWriter(int fd) {
        if (fd == CONST_STDOUT) {
            return stdoutWriter;
        } else if (fd == CONST_STDERR) {
            return stderrWriter;
        } else {
            FileHandle file = openFiles.get(fd);
            if (file.writer == null) {
                assert file.outputStream == null;
                try {
                    file.writer = new BufferedWriter(new OutputStreamWriter(internalGetOutputStream(fd), FILE_ENCODING));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
            return file.writer;
        }
    }

    /**
     * Gets the output stream for a file.
     * Should not be used together with getWriter() for the same file.
     */
    public OutputStream internalGetOutputStream(int fd) {
        if (fd == CONST_STDOUT) {
            return stdout;
        } else if (fd == CONST_STDERR) {
            return stderr;
        } else {
            FileHandle file = openFiles.get(fd);
            if (file.outputStream == null) {
                assert file.writer == null;
                file.outputStream = new RandomAccessOutputStream(file.file);
            }
            return file.outputStream;
        }
    }

    /**
     * Write a single byte character to a file, using a Writer.
     */
    public void writeChar(int fd, int c) throws IOException {
        if (fd == CONST_STDOUT || fd == CONST_STDERR) {
            getWriter(fd).append((char) c);
        } else {
            getWriter(fd).append((char) c);
        }
    }

    public boolean closeRandomAccessFile(int fd) throws InterpreterException {
        if (fd == CONST_STDOUT || fd == CONST_STDERR || fd == CONST_STDIN)
            return true;

        FileHandle file = openFiles.remove(fd);
        if(file == null)
            return true; // already closed: be forgiving
        try {
            if (file.writer != null) file.writer.close();
            if (file.outputStream != null) file.outputStream.close();
            file.file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void closeAllFiles() {
        for (FileHandle file : openFiles.values()) {
            try {
                if (file.writer != null) file.writer.close();
                if (file.outputStream != null) file.outputStream.close();
                file.file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        openFiles.clear();
    }

    public int openRandomAccessFile(String fn, String mode) throws FileNotFoundException, IOException {
        boolean appendMode = mode.indexOf('a') >= 0;
        boolean writeMode = appendMode || mode.indexOf('w') >= 0;
        boolean clearFile = false;

        if (writeMode) {
            File file = new File(getAbsolutePath(getWorkingDir(), fn));
            if (!file.exists()) {
                file.createNewFile();
            } else if (!appendMode) {
                clearFile = true;
            }
        }
        RandomAccessFile file = new RandomAccessFile(getAbsolutePath(getWorkingDir(), fn), writeMode ? "rw" : "r");
        if (clearFile) file.setLength(0);
        openFiles.put(fileCounter, new FileHandle(file));

        return fileCounter++;
    }

    /**
     * Gets the input stream for a file.
     * Should not be used together with getReader() for the same file.
     */
    public InputStream internalGetInputStream(int fd) {
        if (fd == CONST_STDIN) {
            return stdin;
        } else {
            FileHandle file = openFiles.get(fd);
            if (file.inputStream == null)
                file.inputStream = new RandomAccessInputStream(file.file);
            return file.inputStream;
        }
    }

    /* UNDONE: memory-mapped IO considered harmful (Spoofax/106)
     * Gets the file channel for a file descriptor.
     * Should only be used for reading data
     * (since inheritor classes may depend on logging writes).
     *
    public FileChannel getInputChannel(int fd) {
        if (fd == CONST_STDIN || fd == CONST_STDOUT || fd == CONST_STDERR) {
            throw new UnsupportedOperationException();
        } else {
            return openFiles.get(fd).file.getChannel();
        }
    }
    */

    /**
     * Gets the reader for a file.
     * Should not be used together with getInputStream() for the same file.
     */
    public Reader getReader(int fd) {
        if (fd == CONST_STDIN)
            return stdinReader;
        FileHandle file = openFiles.get(fd);
        try {
            if (file.reader == null)
                file.reader = new BufferedReader(new InputStreamReader(internalGetInputStream(fd), FILE_ENCODING));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return file.reader;
    }

    public String readString(int fd) throws IOException {
        char[] buffer = new char[2048];
        StringBuilder result = new StringBuilder();
        Reader reader = getReader(fd);
        for (int read = 0; read != -1; read = reader.read(buffer))
            result.append(buffer, 0, read);
        return result.toString();
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

    public void printError(String error) {
        try {
            getWriter(CONST_STDERR).write(error + "\n");
        } catch (IOException e) {
            // Like System.err.println, we swallow excpetions
        }
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

    public String createTempDir(String prefix) throws IOException {
        File result;
        do {
            result = File.createTempFile(prefix, null);
            result.delete();
        } while (!result.mkdir());
        result.deleteOnExit();
        return result.getPath();
    }

    public boolean mkdir(String fn) {
        return openFile(fn).mkdir();
    }

    @Deprecated // this is not a Stratego primitive; use mkdir instead
    public boolean mkDirs(String fn) {
        return openFile(fn).mkdirs();
    }

    /**
     * An open file handle.
     *
     * @author Lennart Kats <lennart add lclnet.nl>
     */
    private static class FileHandle {
        final RandomAccessFile file;
        Reader reader;
        Writer writer;
        InputStream inputStream;
        OutputStream outputStream;

        FileHandle(RandomAccessFile file) {
            this.file = file;
        }
    }

    /**
     * A class for writing to a PrintStream.
     *
     * @author Lennart Kats <lennart add lclnet.nl>
     */
    private static class PrintStreamWriter extends Writer {

        private final PrintStream stream;

        PrintStreamWriter(PrintStream stream) {
            this.stream = stream;
        }

        @Override
        public void close() throws IOException {
            stream.close();
        }

        @Override
        public void flush() throws IOException {
            stream.flush();
        }

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            if (off == 0 && len == cbuf.length)
                stream.print(cbuf);
            else
                stream.append(CharBuffer.wrap(cbuf, off, len));
        }

        @Override
        public void write(String str, int off, int len) throws IOException {
            stream.append(str, off, len);
        }

    }
}
