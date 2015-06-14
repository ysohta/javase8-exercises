package ch01.ex06;

/**
 * This interface can be used instead of {@link Runnable} if checked exception
 * is thrown in run method.
 * 
 * @author yukiohta
 * @see Runnable
 */
@FunctionalInterface
public interface RunnableEx {

	/**
	 * This method is called in the other thread.
	 * 
	 * @throws Exception if any exception is thrown
	 */
	public void run() throws Exception;
}
