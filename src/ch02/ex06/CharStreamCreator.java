package ch02.ex06;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * This class consists of utility method to generate character stream.
 * 
 * @author yukiohta
 *
 */
public class CharStreamCreator {
	private CharStreamCreator() {
	}

	/**
	 * Returns character stream of string.
	 * 
	 * @param s
	 *            string to be split
	 * @return character stream
	 * @throws NullPointerException
	 *             if s is null
	 */
	public static Stream<Character> characterStream(String s) {
		Objects.requireNonNull(s);
		return Stream.iterate(0, i -> i + 1).limit(s.length()).map(s::charAt);
	}
}
