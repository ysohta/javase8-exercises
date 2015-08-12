package ch03.ex02;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * This class consists of utility methods to handle {@link ReentrantLock}
 * 
 * @author yukiohta
 * @see ReentrantLock
 */
public class ReentrantLockUtil {
	private ReentrantLockUtil() {
	}

	/**
	 * Provides automatic lock and unlock sequence while supplier is executing.
	 * It works even if exception is thrown in the operation in supplier.
	 * 
	 * @param lock
	 *            lock object
	 * @param supplier
	 *            action to be executed if lock is enabled
	 * @return result from supplier
	 * @throws NullPointerException
	 *             if lock or supplier is null
	 */
	public static <T> T withLock(ReentrantLock lock, Supplier<T> supplier) {
		Objects.requireNonNull(lock, "lock must not be null");
		Objects.requireNonNull(supplier, "supplier must not be null");

		lock.lock();
		try {
			return supplier.get();
		} finally {
			lock.unlock();
		}
	}
}
