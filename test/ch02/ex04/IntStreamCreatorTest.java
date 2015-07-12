package ch02.ex04;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class IntStreamCreatorTest {

	@Test
	public void testCreateUnexpectedIntStream() {
		int[] values = { 1, 4, 9, 16 };
		Stream<int[]> actual = IntStreamCreator.createUnexpectedIntStream(values);
		assertThat(actual.count(), is(1L));
	}

	@Test(expected = NullPointerException.class)
	public void testCreateUnexpectedIntStreamNull() {
		IntStreamCreator.createUnexpectedIntStream(null);
	}

	@Test
	public void testCreateIntStream() {
		int[] values = { 1, 4, 9, 16 };
		IntStream actual = IntStreamCreator.createIntStream(values);
		int[] actuals = actual.toArray();
		assertArrayEquals(values, actuals);
	}

	@Test(expected = NullPointerException.class)
	public void testCreateIntStreamNull() {
		IntStreamCreator.createIntStream(null);
	}
}
