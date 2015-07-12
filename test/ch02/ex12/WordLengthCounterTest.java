package ch02.ex12;

import static org.junit.Assert.assertArrayEquals;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class WordLengthCounterTest {

	@Test
	public void testCountWordLengthsBelow() {
		Stream<String> words = Stream.of("1234", "12345", "1234", "12", "123456789", "123456789", "12345", "12345678",
				"1234567890", "", "12345");
		int[] actuals = WordLengthCounter.countWordLengthsBelow(words, 12);
		int[] expecteds = { 1, 0, 1, 0, 2, 3, 0, 0, 1, 2, 1, 0, };

		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testCountWordLengthsBelowUpperBoundary() {
		Stream<String> words = Stream.of("123", "1234");
		int[] actuals = WordLengthCounter.countWordLengthsBelow(words, 4);
		int[] expecteds = { 0, 0, 0, 1, };

		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testCountWordLengthsBelowRandomWord() {
		int nWords = 1000;
		int maxLength = 12;
		Stream<String> words = Stream.generate(() -> generateRandomWordBelow(maxLength)).limit(nWords);

		int[] actuals = WordLengthCounter.countWordLengthsBelow(words, maxLength);

		int sum = IntStream.of(actuals).sum();
		assertThat(sum, is(nWords));

	}

	@Test(expected = NullPointerException.class)
	public void testCountWordLengthsBelowNull() {
		WordLengthCounter.countWordLengthsBelow(null, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCountWordLengthsBelowSmallerMaxLength() {
		WordLengthCounter.countWordLengthsBelow(Stream.empty(), -1);
	}

	private String generateRandomWordBelow(int maxLength) {
		int length = (int) Math.pow(2.0, Math.random() * (maxLength - 1));
		return Integer.toBinaryString(length);
	}
}
