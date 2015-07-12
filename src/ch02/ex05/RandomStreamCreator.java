package ch02.ex05;

import java.util.stream.Stream;

/**
 * This class consists of method to create random stream using linear
 * congruential generator.
 * 
 * @author yukiohta
 *
 */
public class RandomStreamCreator {
	private static long a = 25214903917L;
	private static long c = 11L;
	private static long m = 281_474_976_710_656L; // 2^48

	private RandomStreamCreator() {
	}

	/**
	 * Returns random stream.
	 * 
	 * @param seed
	 *            seed to create random value
	 * @return stream of random values
	 */
	public static Stream<Long> create(long seed) {
		return Stream.iterate(seed, RandomStreamCreator::random);
	}

	private static long random(long x) {
		return (a * x + c) % m;
	}
}
