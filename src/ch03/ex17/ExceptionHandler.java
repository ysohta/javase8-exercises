package ch03.ex17;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * This class consists of utility methods to handle exceptions.
 * 
 * @author yukiohta
 *
 */
public class ExceptionHandler {

	/**
	 * Executes first and second operations in parallel. If any exception
	 * occurred in one of two operations, handler is called to handle exception.
	 * 
	 * @param first
	 *            first operation
	 * @param second
	 *            second operation
	 * @param handler
	 *            handler to deal with exceptions
	 * @throws NullPointerException
	 *             if first, second or handler is null
	 */
	public static void doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {
		Objects.requireNonNull(first, "first must not be null");
		Objects.requireNonNull(second, "second must not be null");
		Objects.requireNonNull(handler, "handler must not be null");

		ExecutorService pool = Executors.newCachedThreadPool();
		pool.execute(() -> {
			try {
				first.run();
			} catch (Throwable t) {
				handler.accept(t);
			}
		});
		pool.execute(() -> {
			try {
				second.run();
			} catch (Throwable t) {
				handler.accept(t);
			}
		});
	}
}
