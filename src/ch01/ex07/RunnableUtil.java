package ch01.ex07;

/**
 * This class consists of utility methods for dealing Runnable objects.
 * 
 * @author yukiohta
 * @see Runnable
 */
public class RunnableUtil {

	private RunnableUtil() {
	}

	/**
	 * This method runs Runnable object and returns next Runnable object. 
	 * @param r1 object to be run
	 * @param r2 object to be returned
	 * @return next object to be run
	 */
	public static Runnable andThen(Runnable r1, Runnable r2) {
		r1.run();
		return r2;
	}

	/**
	 * Main method for the entry point.
	 * 
	 * @param args
	 *            arguments
	 */
	public static void main(String[] args) {
		System.out.println("call andThen");
		Runnable r = andThen(() -> System.out.println("first"), () -> System.out.println("second"));
		System.out.println("Thread starts");
		new Thread(r).start();
	}

}
