package ch02.ex06;

import static org.junit.Assert.assertArrayEquals;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.stream.Stream;

import org.junit.Test;

public class CharStreamCreatorTest {

	@Test
	public void testCharacterStream() {
		Stream<Character> stream = CharStreamCreator.characterStream("boat");

		Character[] expecteds = { 'b', 'o', 'a', 't' };
		Character[] actuals = stream.toArray(Character[]::new);
		assertArrayEquals(expecteds, actuals);
	}

	@Test(expected = NullPointerException.class)
	public void testCharacterStreamNull() {
		CharStreamCreator.characterStream(null);
	}

	@Test
	public void testCharacterStreamEmptyString() {
		Stream<Character> stream = CharStreamCreator.characterStream("");

		assertThat(stream.count(), is(0L));
	}
}
