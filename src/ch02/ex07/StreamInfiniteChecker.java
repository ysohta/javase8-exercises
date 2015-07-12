package ch02.ex07;

import java.util.stream.Stream;

/**
 * This class consists of utility method to check infinite of stream.
 * 
 * @author yukiohta
 *
 */
public class StreamInfiniteChecker {
	private StreamInfiniteChecker() {
	}

	/**
	 * Returns true if stream is infinite. It is bad idea to have this method
	 * because stream size is not determinate until terminal-operation is
	 * called. Once terminal-operation is called on a stream object, it is not
	 * reusable.
	 * 
	 * @param stream
	 *            stream to check infinite
	 * @return true if stream is infinite
	 */
	@Deprecated
	public static <T> boolean isInfinite(Stream<T> stream) {
		return stream.count() == Long.MAX_VALUE;
	}

	@SuppressWarnings("unused")
	public static void main(String... args) {
		Stream<Integer> infiniteStream = Stream.iterate(0, i -> i + 1);

		// fall into infinite loop
		boolean isInfinite = StreamInfiniteChecker.isInfinite(infiniteStream);
	}
}
