package ch02.ex05;

import static org.junit.Assert.*;

import java.util.stream.Stream;

import org.junit.Test;

public class RandomStreamCreatorTest {

	@Test
	public void testCreate() {
		Stream<Long> stream1 = RandomStreamCreator.create(0);
		Long[] rand1 = stream1.limit(101).skip(1).toArray(Long[]::new);

		// set next random value of 0 as seed
		Stream<Long> stream2 = RandomStreamCreator.create(rand1[0]);
		Long[] rand2 = stream2.limit(100).toArray(Long[]::new);

		assertArrayEquals(rand1, rand2);
	}

}
