package ch03.ex21;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

/**
 * This class consists of utility methods to handle {@link Future}.
 * 
 * @author yukiohta
 *
 */
public class FutureUtil {

	/**
	 * Returns mapped future converted by the function.
	 * 
	 * @param future
	 *            future to be converted
	 * @param f
	 *            function to convert
	 * @return mapped future
	 * @throws NullPointerException
	 *             if future or f is null
	 */
	public static <T, U> Future<U> map(Future<T> future, Function<T, U> f) {
		Objects.requireNonNull(future, "future must not be null");
		Objects.requireNonNull(f, "f must not be null");

		return new Future<U>() {

			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				return future.cancel(mayInterruptIfRunning);
			}

			@Override
			public boolean isCancelled() {
				return future.isCancelled();
			}

			@Override
			public boolean isDone() {
				return future.isDone();
			}

			@Override
			public U get() throws InterruptedException, ExecutionException {
				return f.apply(future.get());
			}

			@Override
			public U get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
				return f.apply(future.get(timeout, unit));
			}
		};
	}
}
