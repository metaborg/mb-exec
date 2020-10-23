package org.spoofax.interpreter.library.ssl;

import org.metaborg.util.log.ILogger;
import org.metaborg.util.log.Level;
import org.metaborg.util.log.ILoggerProvider;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.io.binary.TermReader;
import org.spoofax.terms.util.TermUtils;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Logs messages to the internal logger.
 *
 * SSL_log(|msg : String | List(String), severity : Severity, src : String, term : ?)
 */
public final class SSL_log extends AbstractPrimitive {

    private static final String DEFAULT_LOGGER_SRC = "Stratego";
    private final ILoggerProvider loggerProvider;

    protected SSL_log(ILoggerProvider loggerProvider) {
        super("SSL_log", 0, 4);
        this.loggerProvider = loggerProvider;
    }
    
    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) {
        // The `severity` term is a constructor application
        final Level level = targs.length >= 2 ? severityTermToLogLevel(targs[1]) : Level.Info;

        // The `src` should be the result of calling <log-src>, which is a string
        final String src = targs.length >= 3 ? TermUtils.asJavaString(targs[2]).orElse(DEFAULT_LOGGER_SRC) : DEFAULT_LOGGER_SRC;

        // Get the logger (using `src` as the name). Loggers are reused, so this will often return an existing logger.
        final ILogger logger = loggerProvider.getLogger(src);

        if (!logger.enabled(level)) {
            // The log level is not enabled. We can exit. Just smile and wave boys, smile and wave...
            return true;
        }

        // The `msg` term is either a string or a list of strings
        final IStrategoList msgList = targs.length >= 1 ? TermUtils.asList(targs[0]).orElse(env.getFactory().makeList(targs[1])) : env.getFactory().makeList();

        // The `term` is a Stratego term that is printed after the message, if present
        @Nullable final IStrategoTerm term = targs.length >= 4 ? targs[3] : null;

        // Build the log message
        String msg;
        try (StringWriter msgWriter = new StringWriter()) {
            // Concatenate all strings in the list
            for (IStrategoTerm t : msgList.getSubterms()) {
                TermUtils.asJavaString(t).map(msgWriter::append);
            }
            // Append the term, if any
            if (term != null) {
                msgWriter.append("\n");
                new TermReader(env.getFactory()).unparseToFile(term, msgWriter);
            }
            msg = msgWriter.toString();
        } catch (IOException e) {
            // Ignore and discard. This exception can never happen,
            // and any exception during logging should not impact the program.
            msg = "<Logging exception>";    // Dummy value
        }

        // Log to the logger
        logger.log(level, msg);

        // NOTE: The current term remains unchanged.
        return true;
    }

    /**
     * Converts a severity term to a log level.
     *
     * Unknown severities are mapped to INFO.
     *
     * @param term the term, one of: Emergency(), Alert(), Critical(), Error(), Warning(), Notice(), Info(), Debug(), Vomit()
     * @return the corresponding log level
     */
    private Level severityTermToLogLevel(IStrategoTerm term) {
        final Level defaultLevel = Level.Info;

        if (!TermUtils.isAppl(term)) return defaultLevel;
        final IStrategoAppl appl = TermUtils.toAppl(term);
        if (appl.getSubtermCount() != 0) return defaultLevel;

        switch (appl.getName()) {
            // In increasing order of verbosity:
            case "Emergency":
            case "Alert":
            case "Critical":
            case "Error":
                return Level.Error;
            case "Warning":
                return Level.Warn;
            case "Notice":
            case "Info":
                return Level.Info;
            case "Debug":
                return Level.Debug;
            case "Vomit":
                return Level.Trace;
            default:
                return defaultLevel;
        }
    }
}
