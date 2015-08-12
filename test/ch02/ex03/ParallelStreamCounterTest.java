package ch02.ex03;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class ParallelStreamCounterTest {

	@Test
	public void testCountStringsOverLength() {
		List<String> words = Arrays.asList("1234567890", "12345678901", "123456789012", "1234567890123",
				"12345678901234", "123456789012345");
		long actual = ParallelStreamCounter.countStringsOverLength(words, 12);
		assertThat(actual, is(3L));
	}

	@Test
	public void testCountStringsOverLengthZero() {
		long actual = ParallelStreamCounter.countStringsOverLength(Arrays.asList("a"), 0);
		assertThat(actual, is(1L));
	}

	@Test(expected = NullPointerException.class)
	public void testCountStringsOverLengthNullWords() {
		ParallelStreamCounter.countStringsOverLength(null, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCountStringsOverLengthEmptyWords() {
		ParallelStreamCounter.countStringsOverLength(Arrays.asList(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCountStringsOverLengthIllegalLength() {
		ParallelStreamCounter.countStringsOverLength(Arrays.asList(), -1);
	}

	@Test
	public void testCountStringsOverLengthLargeFile() throws IOException {
		List<String> words = Files.readAllLines(Paths.get("res/pg2600.txt")).stream()
				.map((s) -> s.split("[\\p{Punct}\\s]+")).flatMap(Arrays::stream).collect(Collectors.toList());
		
		long elapsed = System.nanoTime();
		long actual = ParallelStreamCounter.countStringsOverLength(words, 12);
		elapsed = System.nanoTime() - elapsed;
		
		System.out.println("[" + getClass() + "] elapsed time[nsec]=" + elapsed);
		assertThat(actual, is(1946L));
	}

}
