package ch02.ex13;

import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.*;

import java.util.stream.Stream;

import ch02.ex12.WordLengthCounter;

/**
 * This class consists of utility method to count word length. It is improved
 * version of WordLengthCounter.
 * 
 * @author yukiohta
 * @see WordLengthCounter#countWordLengthsBelow(Stream, int)
 */
public class WordLengthCounterImproved {
	private WordLengthCounterImproved() {
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

		Map<Integer, Long> counter = words.parallel().filter(s -> s.length() < maxLength)
				.collect(groupingBy(s -> s.length(), counting()));

		int[] shortWords = new int[maxLength];
		counter.forEach((k, v) -> shortWords[k] = v.intValue());
		return shortWords;
	}
}
