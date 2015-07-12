package ch02.ex12;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.Stream;

/**
 * This class consists of utility method to count word length.
 * 
 * @author yukiohta
 *
 */
public class WordLengthCounter {
	private WordLengthCounter() {
	}

	/**
	 * Returns count of word lengths under the maximum length.
	 * 
	 * @param words
	 *            words to count
	 * @param maxLength
	 *            maximum length of words
	 * @return count of word lengths
	 * @throws NullPointerException
	 *             if words is null
	 * @throws IllegalArgumentException
	 *             if maxLength is smaller than 0
	 */
	public static int[] countWordLengthsBelow(Stream<String> words, int maxLength) {
		Objects.requireNonNull(words);
		if (maxLength < 0) {
			throw new IllegalArgumentException("maxLength must be larger than or equals to 0.");
		}

		AtomicIntegerArray counter = new AtomicIntegerArray(maxLength);
		words.parallel().forEach((s) -> {
			if (s.length() < maxLength) {
				counter.incrementAndGet(s.length());
			}
		});

		int[] shortWords = new int[maxLength];
		for (int i = 0; i < maxLength; i++) {
			shortWords[i] = counter.get(i);
		}

		return shortWords;
	}
}
