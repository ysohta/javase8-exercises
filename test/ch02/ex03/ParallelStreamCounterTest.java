package ch02.ex03;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ParallelStreamCounterTest {

	@Test
	public void testCountStringsOverLength() {
		List<String> words = Arrays.asList("1234567890", "12345678901", "123456789012", "1234567890123",
				"12345678901234", "123456789012345");
		long actual = ParallelStreamCounter.countStringsOverLength(words, 12);
		assertThat(actual, is(3L));
	}

	@Test
	public void testCountStringsOverLengthZero() {
		long actual = ParallelStreamCounter.countStringsOverLength(Arrays.asList("a"), 0);
		assertThat(actual, is(1L));
	}

	@Test(expected = NullPointerException.class)
	public void testCountStringsOverLengthNullWords() {
		ParallelStreamCounter.countStringsOverLength(null, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCountStringsOverLengthEmptyWords() {
		ParallelStreamCounter.countStringsOverLength(Arrays.asList(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCountStringsOverLengthIllegalLength() {
		ParallelStreamCounter.countStringsOverLength(Arrays.asList(), -1);
	}
	
	@Test
	public void testCountStringsOverLengthLargeFile() {
		long actual = 0;
		try (BufferedReader buf = Files.newBufferedReader(Paths.get("res/pg2600.txt"))) {
			String line;
			int nLines = 1000;
			int cntLines = 0;
			List<String> words = new ArrayList<>();
			long elapsed = System.nanoTime();
			while ((line = buf.readLine()) != null) {
				words.addAll(Arrays.asList(line.split("[\\p{Punct}\\s]+")));
				if(cntLines > nLines){
					actual += ParallelStreamCounter.countStringsOverLength(words, 12);
					cntLines = 0;
				}else{
					cntLines++;
				}
			}
			elapsed = System.nanoTime() - elapsed;
			// took 603,088,000 nanoseconds in my environment
			System.out.println("["+getClass()+"] elapsed time[nsec]="+ elapsed);
		} catch (IOException e) {
			fail();
		}
		assertThat(actual, is(52078L));
	}
	
	
}
