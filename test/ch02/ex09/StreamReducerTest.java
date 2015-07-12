package ch02.ex09;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamReducerTest {

	@Test
	public void testReduceStream1() {
		ArrayList<String> list1 = new ArrayList<>(Arrays.asList("one", "two"));
		ArrayList<String> list2 = new ArrayList<>(Arrays.asList("three", "four", "five"));
		ArrayList<String> list3 = new ArrayList<>(Arrays.asList("six"));
		Stream<ArrayList<String>> stream = Stream.of(list1, list2, list3);
		ArrayList<String> actuals = StreamReducer.reduceStream1(stream);
		String[] expecteds = { "one", "two", "three", "four", "five", "six" };
		assertArrayEquals(expecteds, actuals.toArray(new String[0]));
	}

	@Test(expected = NullPointerException.class)
	public void testReduceStream1Null() {
		StreamReducer.reduceStream1(null);
	}

	@Test
	public void testReduceStream2() {
		ArrayList<String> list1 = new ArrayList<>(Arrays.asList("one", "two"));
		ArrayList<String> list2 = new ArrayList<>(Arrays.asList("three", "four", "five"));
		ArrayList<String> list3 = new ArrayList<>(Arrays.asList("six"));
		Stream<ArrayList<String>> stream = Stream.of(list1, list2, list3);
		ArrayList<String> actuals = StreamReducer.reduceStream2(stream);
		String[] expecteds = { "one", "two", "three", "four", "five", "six" };
		assertArrayEquals(expecteds, actuals.toArray(new String[0]));
	}

	@Test(expected = NullPointerException.class)
	public void testReduceStream2Null() {
		StreamReducer.reduceStream2(null);
	}

	@Test
	public void testReduceStream3() {
		ArrayList<String> list1 = new ArrayList<>(Arrays.asList("one", "two"));
		ArrayList<String> list2 = new ArrayList<>(Arrays.asList("three", "four", "five"));
		ArrayList<String> list3 = new ArrayList<>(Arrays.asList("six"));
		Stream<ArrayList<String>> stream = Stream.of(list1, list2, list3);
		ArrayList<String> actuals = StreamReducer.reduceStream3(stream);
		String[] expecteds = { "one", "two", "three", "four", "five", "six" };
		assertArrayEquals(expecteds, actuals.toArray(new String[0]));
	}

	@Test(expected = NullPointerException.class)
	public void testReduceStream3Null() {
		StreamReducer.reduceStream3(null);
	}
}
