package ch03.ex20;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This class consists of utility methods to handle {@link List}.
 * 
 * @author yukiohta
 *
 */
public class ListUtil {

	/**
	 * Returns mapped list having elements converted by the function.
	 * 
	 * @param list
	 *            list of T
	 * @param f
	 *            function to convert
	 * @return list of U
	 */
	public static <T, U> List<U> map(List<T> list, Function<T, U> f) {
		Objects.requireNonNull(list, "list must not be null");
		Objects.requireNonNull(f, "f must not be null");

		return list.stream().map(f).collect(Collectors.toList());
	}
}
