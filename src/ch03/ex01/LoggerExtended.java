package ch03.ex01;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is extended version of {@link Logger} to provide conditional
 * logging.
 * 
 * @author yukiohta
 * @see Logger
 *
 */
public class LoggerExtended {
	/**
	 * Index value in stack trace to access class and method which called the
	 * methods in this class.
	 */
	private static final int targetStackTrace = 2;
	private final Logger logger;

	public LoggerExtended(Logger logger) {
		this.logger = logger;
	}

	/**
	 * Log a message if it meets the specified condition. If the logger is
	 * currently enabled for the given message level then the given message is
	 * forwarded to all the registered output Handler objects.
	 * 
	 * @param level
	 *            one of the message level identifier
	 * @param condition
	 *            condition whether or not log is enabled
	 * @param msg
	 *            the string message
	 * @throws NullPointerException
	 *             if level, condition or msg is null
	 */
	public <T> void logIf(Level level, Supplier<Boolean> condition, Supplier<String> msg) {
		Objects.requireNonNull(level);
		Objects.requireNonNull(condition);
		Objects.requireNonNull(msg);

		if (!condition.get())
			return;

		StackTraceElement stackTraceTarget = Thread.currentThread().getStackTrace()[targetStackTrace];
		logger.logp(level, stackTraceTarget.getClassName(), stackTraceTarget.getMethodName(), msg.get());
	}
}
