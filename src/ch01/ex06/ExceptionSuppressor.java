package ch01.ex06;

import java.util.concurrent.Callable;

/**
 * This class consists of utility method to suppress checked exception for
 * Runnable interface.
 * 
 * @author yukiohta
 *
 */
public class ExceptionSuppressor {

	private ExceptionSuppressor() {
	}

	/**
	 * Returns wrapper Runnable object from parameter
	 * 
	 * @param runner
	 *            object to be called run method
	 * @return wrapper Runnable object
	 * @throws RuntimeException
	 *             if any checked exception is thrown
	 */
	public static Runnable uncheck(RunnableEx runner) {
		return () -> {
			try {
				runner.run();
			} catch (Exception e) {
				// convert checked exception to unchecked one
				throw new RuntimeException(e);
			}
		};
	}

	/**
	 * Returns wrapper Runnable object from parameter
	 * 
	 * @param runner
	 *            object to be called call method
	 * @return wrapper Runnable object
	 * @throws RuntimeException
	 *             if any checked exception is thrown
	 */
	public static Runnable uncheck2(Callable<Void> runner) {
		return () -> {
			try {
				runner.call();
			} catch (Exception e) {
				// convert checked exception to unchecked one
				throw new RuntimeException(e);
			}
		};
	}

	public static void main(String... args) {
		new Thread(uncheck(() -> {
			System.out.println("Zzz");
			Thread.sleep(1000);
		})).start();

		new Thread(uncheck2(() -> {
			System.out.println("Zzz");
			Thread.sleep(1000);
			// must return null in case of Callable<Void>
				return null;
			})).start();
	}
}
