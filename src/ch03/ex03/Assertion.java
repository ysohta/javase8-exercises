package ch03.ex03;

import java.util.Objects;
import java.util.function.Supplier;

public class Assertion {
	/**
	 * This value becomes true if assertion is enabled.
	 */
	private static boolean enabledAssert = false;

	static {
		assert enabledAssert = true;
	}

	private Assertion() {
	}

	/**
	 * Assert that the operation meets the condition.
	 * 
	 * @param condition
	 *            condition to be checked
	 * @throws AssertionError
	 *             if the condition is failed
	 * @throws NullPointerException
	 *             if condition is null
	 */
	public static void check(Supplier<Boolean> condition) {
		if (!enabledAssert) {
			return;
		}

		Objects.requireNonNull(condition, "condition must not be null");

		if (!condition.get()) {
			throw new AssertionError();
		}
	}

	public static void main(String... args) {
		int val = 3;
		check(() -> val == 3);
		check(() -> val != 3); // throws AssertionError if assertion is enabled
	}
}
