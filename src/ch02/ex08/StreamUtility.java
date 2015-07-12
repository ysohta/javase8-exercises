package ch02.ex08;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * This class consists of utility method to handle streams.
 * 
 * @author yukiohta
 *
 */
public class StreamUtility {
	private StreamUtility() {
	}

	/**
	 * Returns zipped stream from two streams. Takes elements from first stream
	 * and second one until reaching to the end in one of them. For example,
	 * when {1, 3, 5} in first stream and {2, 4} in second stream are specified,
	 * {1, 2, 3, 4, 5} would be returned as a result.
	 * 
	 * @param first
	 *            first stream
	 * @param second
	 *            second stream
	 * @return zipped stream
	 * @throws NullPointerException
	 *             if first or second is null
	 */
	public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
		Objects.requireNonNull(first);
		Objects.requireNonNull(second);

		Iterator<T> it1 = first.iterator();
		Iterator<T> it2 = second.iterator();

		int characteristics = Spliterator.NONNULL;
		Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(new ZipIterator<>(it1, it2), characteristics);
		return StreamSupport.stream(spliterator, false);
	}

	static class ZipIterator<E> implements Iterator<E> {
		private final Iterator<? extends E> it1;
		private final Iterator<? extends E> it2;
		private Iterator<? extends E> current;

		public ZipIterator(Iterator<? extends E> it1, Iterator<? extends E> it2) {
			this.it1 = it1;
			this.it2 = it2;
			this.current = it1;
		}

		@Override
		public boolean hasNext() {
			return current.hasNext();
		}

		@Override
		public E next() {
			E ret = current.next();

			// flip current iterator
			if (current == it1) {
				current = it2;
			} else {
				current = it1;
			}

			return ret;
		}
	}
}
