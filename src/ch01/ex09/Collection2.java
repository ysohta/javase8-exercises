package ch01.ex09;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Collection2<T> extends Collection<T> {

	/**
	 * Performs the given action if filter meets condition for each element of
	 * the Iterable until all elements have been processed or the action throws
	 * an exception.
	 * 
	 * @param action
	 *            The action to be performed for each element
	 * @param filter
	 *            The filter whether to perform action
	 * @throws NullPointerException
	 *             if action or filter is null
	 * @see Collection#forEach(Consumer)
	 */
	default void forEachIf(Consumer<T> action, Predicate<T> filter) {
		Objects.requireNonNull(action);
		Objects.requireNonNull(filter);
		for (T t : this) {
			if (filter.test(t)) {
				action.accept(t);
			}
		}
	}
}
