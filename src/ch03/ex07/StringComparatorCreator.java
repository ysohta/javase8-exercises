package ch03.ex07;

import static ch03.ex07.StringOrderingRules.*;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.Objects;

/**
 * This class consists of utility methods to create Comparator for String.
 * 
 * @author yukiohta
 *
 */
public class StringComparatorCreator {

	private StringComparatorCreator() {
	}

	/**
	 * Create Comparator for String based on the ordering rules.
	 * 
	 * @param rules
	 *            combination of ordering rules
	 * @return comparator for String
	 * @throws NullPointerException
	 *             if rules is null
	 */
	public static Comparator<String> create(EnumSet<StringOrderingRules> rules) {
		Objects.requireNonNull(rules, "rules must not be null");

		return (e1, e2) -> {
			String s1 = e1;
			String s2 = e2;

			if (rules.contains(Reverse)) {
				// swap elements
				s1 = e2;
				s2 = e1;
			}

			if (rules.contains(SpaceExcluded)) {
				// replace white spaces
				s1 = s1.replaceAll("\\s+", "");
				s2 = s2.replaceAll("\\s+", "");
			}

			if (rules.contains(CaseInsensitive)) {
				return s1.compareToIgnoreCase(s2);
			} else {
				return s1.compareTo(s2);
			}
		};
	}
}
