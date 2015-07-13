package ch02.ex11;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * This class consists of utility methods to convert stream into ArrayList.
 * 
 * @author yukiohta
 *
 */
public class ArrayListCollector {
	private ArrayListCollector() {
	}

	/**
	 * Returns ArrayList reduced from stream.
	 * 
	 * @param stream
	 *            stream to be reduced
	 * @return ArrayList
	 * @see Stream#collect(java.util.stream.Collector)
	 * @see ArrayList#set(int, Object)
	 */
	public static <T> ArrayList<T> collectToArrayList(Stream<T> stream) {
		// cannot use ArrayList#set(int, Object) on collect method because size
		// of ArrayList varies while collecting elements.
		return stream.parallel().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
	}
}
