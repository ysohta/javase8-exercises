package ch02.ex10;

import static org.junit.Assert.*;

import java.util.stream.Stream;

import org.junit.Test;

public class DoubleStreamUtilTest {

	@Test
	public void testMean() {
		Stream<Double> stream = Stream.of(3.0, 7.0, -1.0, 8.0, 8.0);
		double actual = DoubleStreamUtil.mean(stream);

		assertEquals(5.0, actual, 0.01);
	}

	@Test
	public void testMeanRandomStream() {
		Stream<Double> stream = Stream.generate(Math::random).limit(1_000_000);
		double actual = DoubleStreamUtil.mean(stream);

		assertEquals(0.5, actual, 0.01);
	}
	
	@Test(expected = NullPointerException.class)
	public void testMeanNull() {
		DoubleStreamUtil.mean(null);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testMeanEmptyStream() {
		DoubleStreamUtil.mean(Stream.of());
	}
}
