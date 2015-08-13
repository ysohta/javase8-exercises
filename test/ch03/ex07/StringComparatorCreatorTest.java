package ch03.ex07;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.EnumSet;

import org.junit.Test;

public class StringComparatorCreatorTest {

	@Test
	public void testCreateReverse() {
		String[] input = { "Orange", "Grape", "Apple", "Lemon", "Banana" };
		EnumSet<StringOrderingRules> rules;
		String[] actuals = null;

		rules = EnumSet.of(StringOrderingRules.Reverse);
		actuals = Arrays.copyOf(input, input.length);
		Arrays.sort(actuals, StringComparatorCreator.create(rules));
		assertArrayEquals(new String[] { "Orange", "Lemon", "Grape", "Banana", "Apple" }, actuals);

		rules = EnumSet.noneOf(StringOrderingRules.class);
		actuals = Arrays.copyOf(input, input.length);
		Arrays.sort(actuals, StringComparatorCreator.create(rules));
		assertArrayEquals(new String[] { "Apple", "Banana", "Grape", "Lemon", "Orange" }, actuals);
	}

	@Test
	public void testCreateCaseInsensitive() {
		String[] input = { "Orange", "Grape", "apple", "lemon", "Banana" };
		EnumSet<StringOrderingRules> rules;
		String[] actuals = null;

		rules = EnumSet.of(StringOrderingRules.CaseInsensitive);
		actuals = Arrays.copyOf(input, input.length);
		Arrays.sort(actuals, StringComparatorCreator.create(rules));
		assertArrayEquals(new String[] { "apple", "Banana", "Grape", "lemon", "Orange" }, actuals);

		rules = EnumSet.noneOf(StringOrderingRules.class);
		actuals = Arrays.copyOf(input, input.length);
		Arrays.sort(actuals, StringComparatorCreator.create(rules));
		assertArrayEquals(new String[] { "Banana", "Grape", "Orange", "apple", "lemon" }, actuals);
	}

	@Test
	public void testCreateSpaceExcluded() {
		String[] input = { " Orange", "Grape", "Apple", "Lemon", "	Banana" };
		EnumSet<StringOrderingRules> rules;
		String[] actuals = null;

		rules = EnumSet.of(StringOrderingRules.SpaceExcluded);
		actuals = Arrays.copyOf(input, input.length);
		Arrays.sort(actuals, StringComparatorCreator.create(rules));
		assertArrayEquals(new String[] { "Apple", "	Banana", "Grape", "Lemon", " Orange", }, actuals);

		rules = EnumSet.noneOf(StringOrderingRules.class);
		actuals = Arrays.copyOf(input, input.length);
		Arrays.sort(actuals, StringComparatorCreator.create(rules));
		assertArrayEquals(new String[] { "	Banana", " Orange", "Apple", "Grape", "Lemon", }, actuals);
	}

	@Test
	public void testCreateCombinatedRule() {
		String[] input = { " Orange", "Grape", "Apple", "lemon", "banana", };
		EnumSet<StringOrderingRules> rules;
		String[] actuals = null;

		rules = EnumSet.allOf(StringOrderingRules.class);
		actuals = Arrays.copyOf(input, input.length);
		Arrays.sort(actuals, StringComparatorCreator.create(rules));
		assertArrayEquals(new String[] { " Orange", "lemon", "Grape", "banana", "Apple", }, actuals);
	}

	@Test(expected = NullPointerException.class)
	public void testCreateNullPointerException() {
		StringComparatorCreator.create(null);
	}
}
