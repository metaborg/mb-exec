package org.spoofax.interpreter.library.ssl;

import org.junit.Test;
import org.metaborg.util.log.ILogger;
import org.metaborg.util.log.LoggerUtils;
import org.metaborg.util.log.CapturingLogger;
import org.metaborg.util.log.ILoggerProvider;
import org.spoofax.interpreter.core.Context;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.TermFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests the {@link SSL_log} Stratego primitive.
 */
public final class SSL_logTests {

    //@Test
    public void logsStringMessageToLogger() {
        // Arrange
        final IContext context = new Context(new TermFactory(), new TermFactory());
        final ITermFactory factory = context.getFactory();
        final IStrategoString msg = factory.makeString("My message");
        final IStrategoAppl severity = factory.makeAppl("Error");
        final String src = "SSL-test";
        final IStrategoTerm term = null;
        final IStrategoTerm currentTerm = factory.makeString("Some value");
        final CapturingLogger logger = new CapturingLogger();
        final SSL_log sut = new SSL_log(new TestLoggerProvider(src, logger));

        // Act
        context.setCurrent(currentTerm);
        final boolean success = sut.call(context, new Strategy[0], new IStrategoTerm[]{
                msg, severity, factory.makeString(src), term});

        // Assert
        assertTrue(success);
        assertEquals(currentTerm, context.current());
        assertEquals("[ERROR] My message", logger.toString());
    }

    /**
     * A test logger provider that provides a given logger with a given name,
     * or the default logger otherwise.
     */
    private final class TestLoggerProvider implements ILoggerProvider {
        private final String name;
        private final ILogger logger;

        /**
         * Initializes a new instance of the {@code TestLoggerProvider} class.
         *
         * @param name the name of the logger to respond to
         * @param logger the logger to respond with
         */
        public TestLoggerProvider(String name, ILogger logger) {
            this.name = name;
            this.logger = logger;
        }

        @Override public ILogger getLogger(String name) {
            if (this.name.equals(name)) {
                return this.logger;
            } else {
                return LoggerUtils.logger(name);
            }
        }

        @Override public ILogger getLogger(Class<?> cls) {
            final String name = cls.getName();
            if (this.name.equals(name)) {
                return this.logger;
            } else {
                return LoggerUtils.logger(cls);
            }
        }
    }
}
