package ch01.ex09;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class Collection2ImplTest {
	Collection2<String> testee;

	@Before
	public void setUp() throws Exception {
		testee = new Collection2Impl<>();
	}

	@Test
	public void testForEachIf() {
		testee.add("foo");
		testee.add("bar");
		testee.add("baz");

		List<String> actuals = new ArrayList<>();
		testee.forEachIf(e -> actuals.add(e), (s) -> s.contains("a"));
		assertThat(actuals, hasItems("bar", "baz"));
	}

	@Test
	public void testForEachIfEmpty() {
		testee.add("foo");
		testee.add("bar");
		testee.add("baz");

		List<String> actuals = new ArrayList<>();
		testee.forEachIf(e -> actuals.add(e), (s) -> s.contains("B"));
		assertThat(actuals.size(), is(0));
	}

	@Test(expected = NullPointerException.class)
	public void testForEachIfNullPointerExceptionAction() {
		testee.forEachIf(null, (s) -> true);
	}

	@Test(expected = NullPointerException.class)
	public void testForEachIfNullPointerExceptionFilter() {
		testee.forEachIf(t -> {
		}, null);
	}
}
