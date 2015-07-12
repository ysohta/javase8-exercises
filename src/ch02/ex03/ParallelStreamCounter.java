package ch02.ex03;

import java.util.List;
import java.util.Objects;

/**
 * This class consists of method to count strings in the stream
 * 
 * @author yukiohta
 *
 */
public class ParallelStreamCounter {
	private ParallelStreamCounter() {
	}

	/**
	 * Returns counts of strings over the length in stream.
	 * 
	 * @param words
	 *            stream of words
	 * @param minLength
	 * @return counts
	 * @throws NullPointerException
	 *             if words is null
	 * @throws IllegalArgumentException
	 *             if words is empty or length is smaller than 0
	 */
	public static long countStringsOverLength(List<String> words, int minLength) {
		Objects.requireNonNull(words);
		if (words.size() == 0) {
			throw new IllegalArgumentException("words is empty");
		}
		if (minLength < 0) {
			throw new IllegalArgumentException("length must be larger than or equals to 0");
		}

		return words.stream().parallel().filter(w -> w.length() > minLength).count();
	}
}
