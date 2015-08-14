package ch03.ex20;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class ListUtilTest {

	@Test
	public void testMap() {
		List<String> list = Arrays.asList("12", "-3", "010");
		List<Integer> actuals = ListUtil.map(list, Integer::parseInt);
		assertArrayEquals(new Integer[] { 12, -3, 10 }, actuals.toArray(new Integer[0]));
	}

	@Test
	public void testMapEmpty() {
		List<String> list = Collections.emptyList();
		List<Integer> actuals = ListUtil.map(list, Integer::parseInt);
		assertThat(actuals.isEmpty(), is(true));
	}

	@Test(expected = NullPointerException.class)
	public void testMapNullPointerExceptionList() {
		ListUtil.map(null, (t) -> "");
	}

	@Test(expected = NullPointerException.class)
	public void testMapNullPointerExceptionF() {
		ListUtil.map(Collections.emptyList(), null);
	}
}
