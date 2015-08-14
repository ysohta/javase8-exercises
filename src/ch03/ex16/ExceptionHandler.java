package ch03.ex16;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * This class consists of utility methods to handle exceptions.
 * 
 * @author yukiohta
 *
 */
public class ExceptionHandler {
	private ExceptionHandler() {
	}

	/**
	 * Execute asynchronously two operations subsequently.
	 * 
	 * @param first
	 *            first operation to supply an object
	 * @param second
	 *            second operation to consume object or exception supplied by
	 *            first parameter
	 * @throws NullPointerException
	 *             if first or second is null
	 */
	public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second) {
		Objects.requireNonNull(first, "first must not be null");
		Objects.requireNonNull(second, "second must not be null");

		Thread thread = new Thread(() -> {
			T result = null;
			try {
				result = first.get();
				second.accept(result, null);
			} catch (Throwable t) {
				second.accept(result, t);
			}
		});

		thread.start();
	}

	/**
	 * Returns {@link Supplier} to mask any checked exception in
	 * {@link Callable#call()}.
	 * 
	 * @param f
	 *            operation to suppress checked exception
	 * @return supplier to give the result
	 * @throws NullPointerException
	 *             if f is null
	 * @throws RuntimeException
	 *             if any exception is occurred during call operation
	 */
	public static <T> Supplier<T> unchecked(Callable<T> f) {
		Objects.requireNonNull(f, "f must not be null");

		return () -> {
			try {
				return f.call();
			} catch (Exception e) {
				throw new RuntimeException("exception is occurred during call operation", e);
			} catch (Throwable t) {
				throw t;
			}
		};
	}
}
