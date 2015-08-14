package ch03.ex18;

/**
 * Represents a function that accepts one argument and produces a result. It
 * could throw Exception.
 * 
 * @author yukiohta
 *
 * @param <T>the type of the input to the function
 * @param <U>the type of the result of the function
 */
@FunctionalInterface
public interface ThrowableFunction<T, R> {

	/**
	 * Applies this function to the given argument.
	 * 
	 * @param t
	 *            the function argument
	 * @return the function result
	 * @throws Exception
	 *             if unable to compute a result
	 */
	R accept(T t) throws Exception;
}
