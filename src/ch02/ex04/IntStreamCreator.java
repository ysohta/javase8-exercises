package ch02.ex04;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This class consists of utility methods to create integer related stream.
 * 
 * @author yukiohta
 *
 */
public class IntStreamCreator {
	private IntStreamCreator() {
	}

	/**
	 * Returns stream of integer array generated by {@link Stream#of(Object)}.
	 * 
	 * @param values
	 *            integer array
	 * @return stream of integer array
	 * @throws NullPointerException
	 *             if values is null
	 */
	public static Stream<int[]> createUnexpectedIntStream(int[] values) {
		Objects.requireNonNull(values);
		return Stream.of(values);
	}

	/**
	 * Returns IntStream from integer array.
	 * 
	 * @param values
	 *            integer array
	 * @return stream of integer
	 * @throws NullPointerException
	 *             if values is null
	 */
	public static IntStream createIntStream(int[] values) {
		Objects.requireNonNull(values);
		return Arrays.stream(values);
	}
}
