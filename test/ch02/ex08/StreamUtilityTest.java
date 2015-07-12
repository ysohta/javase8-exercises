package ch02.ex08;

import static org.junit.Assert.*;

import java.util.stream.Stream;

import org.junit.Test;

public class StreamUtilityTest {

	@Test
	public void testZip() {
		Stream<String> first = Stream.of("one", "three");
		Stream<String> second = Stream.of("two", "four");
		
		Stream<String> actuals = StreamUtility.zip(first, second);

		String[] expecteds = {"one", "two", "three", "four"};
		assertArrayEquals(expecteds, actuals.toArray(String[]::new));
	}
	
	@Test
	public void testZipInfiniteStream() {
		Stream<Long> first = Stream.iterate(1L, i->i+2);
		Stream<Long> second = Stream.iterate(2L, i->i+2);
		
		Stream<Long> actuals = StreamUtility.zip(first, second).limit(5L);

		Long[] expecteds = {1L, 2L, 3L, 4L, 5L};
		assertArrayEquals(expecteds, actuals.toArray(Long[]::new));
	}

	@Test
	public void testZipWhenFirstHasMore() {
		Stream<String> first = Stream.of("one", "three", "five", "seven");
		Stream<String> second = Stream.of("two", "four");
		
		Stream<String> actuals = StreamUtility.zip(first, second);

		String[] expecteds = {"one", "two", "three", "four", "five"};
		assertArrayEquals(expecteds, actuals.toArray(String[]::new));
	}
	
	@Test
	public void testZipWhenSecondHasMore() {
		Stream<String> first = Stream.of("one", "three");
		Stream<String> second = Stream.of("two", "four", "six", "eight");
		
		Stream<String> actuals = StreamUtility.zip(first, second);

		String[] expecteds = {"one", "two", "three", "four"};
		assertArrayEquals(expecteds, actuals.toArray(String[]::new));
	}
	
	@Test
	public void testZipFirstEmptyStream() {
		Stream<String> first = Stream.of("one", "three");
		Stream<String> second = Stream.empty();
		
		Stream<String> actuals = StreamUtility.zip(first, second);

		String[] expecteds = {"one"};
		assertArrayEquals(expecteds, actuals.toArray(String[]::new));
	}
	
	@Test
	public void testZipSecondEmptyStream() {
		Stream<String> first = Stream.empty();
		Stream<String> second = Stream.of("two", "four");
		
		Stream<String> actuals = StreamUtility.zip(first, second);

		String[] expecteds =  new String[0];
		assertArrayEquals(expecteds, actuals.toArray(String[]::new));
	}
	
	@Test(expected = NullPointerException.class)
	public void testZipNullFirst(){
		StreamUtility.zip(null, Stream.of());
	}
	
	@Test(expected = NullPointerException.class)
	public void testZipNullSecond(){
		StreamUtility.zip(Stream.of(), null);
	}
}
