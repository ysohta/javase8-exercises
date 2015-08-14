package ch03.ex23;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class PairTest {

	@Test
	public void testMap() {
		Pair<String> p = new Pair<>("3", "-4");
		Pair<Integer> actual = p.map(Integer::parseInt);

		assertThat(actual.first, is(3));
		assertThat(actual.second, is(-4));
	}

	@Test(expected = NullPointerException.class)
	public void testMapNullPointerException() {
		Pair<String> p = new Pair<>("3", "-4");
		p.map(null);
	}

	@Test(expected = NullPointerException.class)
	public void testConstructorNullPointerExceptionFirst() {
		new Pair<>(null, "");
	}

	@Test(expected = NullPointerException.class)
	public void testConstructorNullPointerExceptionSecond() {
		new Pair<>("", null);
	}
}
