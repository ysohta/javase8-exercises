package ch02.ex09;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * This class consists of utility methods to reduce Stream to ArrayList in some
 * types.
 * 
 * @author yukiohta
 *
 */
public class StreamReducer {
	private StreamReducer() {
	}

	/**
	 * Returns ArrayList reduced from Stream of ArrayList using
	 * {@link Stream#reduce(java.util.function.BinaryOperator)}.
	 * 
	 * @param stream
	 *            stream of AraryList
	 * @return reduced ArrayList
	 * @throws NullPointerException
	 *             if stream is null
	 * @See {@link Stream#reduce(java.util.function.BinaryOperator)}
	 */
	public static <T> ArrayList<T> reduceStream1(Stream<ArrayList<T>> stream) {
		Objects.requireNonNull(stream);
		return stream.reduce((x, y) -> {
			x.addAll(y);
			return x;
		}).get();
	}

	/**
	 * Returns ArrayList reduced from Stream of ArrayList using
	 * {@link Stream#reduce(Object, java.util.function.BinaryOperator)}.
	 * 
	 * @param stream
	 *            stream of AraryList
	 * @return reduced ArrayList
	 * @throws NullPointerException
	 *             if stream is null
	 * @See {@link Stream#reduce(Object, java.util.function.BinaryOperator)}
	 */
	public static <T> ArrayList<T> reduceStream2(Stream<ArrayList<T>> stream) {
		Objects.requireNonNull(stream);
		return stream.reduce(new ArrayList<>(), (x, y) -> {
			x.addAll(y);
			return x;
		});
	}

	/**
	 * Returns ArrayList reduced from Stream of ArrayList using
	 * {@link Stream#reduce(Object, java.util.function.BiFunction, java.util.function.BinaryOperator)}
	 * 
	 * @param stream
	 *            stream of AraryList
	 * @return reduced ArrayList
	 * @throws NullPointerException
	 *             if stream is null
	 * @See {@link Stream#reduce(Object, java.util.function.BiFunction, java.util.function.BinaryOperator)}
	 */
	public static <T> ArrayList<T> reduceStream3(Stream<ArrayList<T>> stream) {
		Objects.requireNonNull(stream);
		return stream.reduce(new ArrayList<>(), (list, x) -> {
			list.addAll(x);
			return list;
		}, (list1, list2) -> {
			list1.addAll(list2);
			return list1;
		});
	}
}
