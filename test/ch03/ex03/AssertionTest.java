package ch03.ex03;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

public class AssertionTest {
	/**
	 * This value becomes true if assertion is enabled.
	 */
	static boolean enabledAssert = false;

	@BeforeClass
	public static void setupClass() {
		assert enabledAssert = true;
	}

	@Test
	public void testCheckEnabledAssert() {
		if (!enabledAssert) {
			return;
		}

		Assertion.check(() -> true);

		try {
			Assertion.check(() -> false);
			fail();
		} catch (AssertionError e) {
			assertTrue(true);
		}
	}

	@Test
	public void testCheckDisabledAssert() {
		if (enabledAssert) {
			return;
		}

		// throws nothing
		Assertion.check(() -> true);
		Assertion.check(() -> false);
	}

	@Test
	public void testCheckDisabledAssertBlockNotExecuted() {
		if (enabledAssert) {
			return;
		}

		// confirm deferred execution
		// assert block is not executed
		Assertion.check(() -> {
			fail();
			return false;
		});
	}
	
	@Test
	public void testCheckNullPointerException() {
		if (!enabledAssert) {
			return;
		}

		try{
			Assertion.check(null);
			fail();
		}catch(NullPointerException e){
			assertTrue(e.getMessage(), true);
		}
	}
}
