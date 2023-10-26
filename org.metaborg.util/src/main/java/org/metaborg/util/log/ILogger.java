package org.metaborg.util.log;

import jakarta.annotation.Nullable;

public interface ILogger {
    public void trace(String msg);

    public void trace(String msg, @Nullable Throwable t);

    public void trace(String fmt, Object... args);

    public void trace(String fmt, Throwable t, Object... args);
    
    public boolean traceEnabled();


    public void debug(String msg);

    public void debug(String msg, @Nullable Throwable t);

    public void debug(String fmt, Object... args);

    public void debug(String fmt, Throwable t, Object... args);

    public boolean debugEnabled();
    

    public void info(String msg);

    public void info(String msg, @Nullable Throwable t);

    public void info(String fmt, Object... args);

    public void info(String fmt, Throwable t, Object... args);

    public boolean infoEnabled();
    

    public void warn(String msg);

    public void warn(String msg, @Nullable Throwable t);

    public void warn(String fmt, Object... args);

    public void warn(String fmt, Throwable t, Object... args);

    public boolean warnEnabled();
    

    public void error(String msg);

    public void error(String msg, @Nullable Throwable t);

    public void error(String fmt, Object... args);

    public void error(String fmt, Throwable t, Object... args);
    
    public boolean errorEnabled();


    public void log(Level level, String msg);

    public void log(Level level, String msg, @Nullable Throwable t);

    public void log(Level level, String fmt, Object... args);

    public void log(Level level, String fmt, Throwable t, Object... args);
    
    public boolean enabled(Level level);
    
    
    public String format(String fmt, Object... args);
}
