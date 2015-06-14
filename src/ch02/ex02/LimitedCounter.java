package ch02.ex02;

import java.util.stream.Stream;

/**
 * 
 * @author yukiohta
 *
 */
public class LimitedCounter {

	public static void main(String... strings) {
		int minLength = 2;
		Stream<String> words = Stream.of("1", "12", "123", "1234", "12345", "123456", "1234567", "12345678");
		
		// print "accepted" only 5 times
		words.filter(s -> {
			boolean accept = s.length() >= minLength;
			if (accept) {
				System.out.println("accepted:	" + s);
			} else {
				System.out.println("not accepted:	" + s);
			}

			return accept;
		}).limit(5).toArray(String[]::new);
	}
}
