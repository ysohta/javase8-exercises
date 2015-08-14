package ch03.ex23;

import java.util.Objects;
import java.util.function.Function;

/**
 * This class represents an object having one pair of elements.
 * 
 * @author yukiohta
 *
 * @param <T>
 *            the type of paired elements
 */
public class Pair<T> {
	public final T first;
	public final T second;

	/**
	 * Constructs object.
	 * 
	 * @param first
	 *            first element
	 * @param second
	 *            second element
	 * @throws NullPointerException
	 *             if first or second is null
	 */
	public Pair(T first, T second) {
		Objects.requireNonNull(first, "first must not be null");
		Objects.requireNonNull(second, "second must not be null");

		this.first = first;
		this.second = second;
	}

	/**
	 * Returns mapped Pair object converted by the function.
	 * 
	 * @param f
	 *            function to convert
	 * @return mapped pair
	 * @throws NullPointerException
	 *             if f is null
	 */
	public <R> Pair<R> map(Function<? super T, ? extends R> f) {
		Objects.requireNonNull(f, "f must not be null");

		return new Pair<>(f.apply(first), f.apply(second));
	}
}
