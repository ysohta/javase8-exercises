package ch02.ex10;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * This class consists of utility method to handle double stream.
 * 
 * @author yukiohta
 *
 */
public class DoubleStreamUtil {
	private DoubleStreamUtil() {
	}

	/**
	 * Returns mean value of double stream.
	 * 
	 * @param stream
	 *            double stream to calculate
	 * @return mean value
	 * @throws NullPointerException
	 *             if stream is null
	 * @throws IllegalStateException
	 *             if stream is empty
	 */
	public static double mean(Stream<Double> stream) {
		Objects.requireNonNull(stream);

		// Have to use DoubleSum to add and count at the same time on reducing
		DoubleSum doubleSum = stream.reduce(new DoubleSum(0.0, 0L), (sum, x) -> sum.add(new DoubleSum(x, 1L)), (sum1,
				sum2) -> sum1.add(sum2));

		if (doubleSum.getCount() == 0) {
			throw new IllegalStateException("stream is empty");
		}

		return doubleSum.getSum() / doubleSum.getCount();
	}

	static class DoubleSum {
		private final double sum;
		private final long count;

		public DoubleSum(double sum, long count) {
			this.sum = sum;
			this.count = count;
		}

		public DoubleSum add(DoubleSum other) {
			double sum = this.sum + other.sum;
			long count = this.count + other.count;
			return new DoubleSum(sum, count);
		}

		public double getSum() {
			return sum;
		}

		public long getCount() {
			return count;
		}
	}
}
