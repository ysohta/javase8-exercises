package ch03.ex18;

import java.util.Objects;
import java.util.function.Function;

/**
 * This class consists of utility methods to handle exceptions.
 * 
 * @author yukiohta
 *
 */
public class ExceptionHandler {

	/**
	 * Returns {@link Function} to mask any checked exception in
	 * {@link ThrowableFunction}.
	 * 
	 * @param f
	 *            operation to suppress checked exception
	 * @return function to give the result
	 * @throws NullPointerException
	 *             if f is null
	 * @throws RuntimeException
	 *             if any exception is occurred during call operation
	 */
	public static <T, R> Function<T, R> unchecked(ThrowableFunction<T, R> f) {
		Objects.requireNonNull(f, "f must not be null");

		return (T v) -> {
			try {
				return f.accept(v);
			} catch (Exception e) {
				throw new RuntimeException("exception is occurred during call operation", e);
			} catch (Throwable t) {
				throw t;
			}
		};
	}
}
