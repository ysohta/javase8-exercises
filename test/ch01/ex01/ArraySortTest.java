package ch01.ex01;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class ArraySortTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetThreadIds() {
		Set<Long> ids = ArraySort.getThreadIds();
		assertEquals("Confirms single thread.", 1, ids.size());
	}

}
