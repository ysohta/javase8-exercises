package ch08.ex04;

import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch08.ex04.MinSeedFinder}.
 * Created by yukiohta on 2015/10/20.
 */
public class MinSeedFinderTest {

    @Test
    public void testGetMinSeed() throws Exception {
        long s = MinSeedFinder.getMinSeed(1_000_000L);
        Random random = new Random(s);
        double val = 0.0;
        for (int i = 0; i < 376_050; i++) {
            val = random.nextDouble();
        }

        assertThat(val, is(0.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMinSeedZero() throws Exception {
        MinSeedFinder.getMinSeed(0);
    }

    @Test
    public void testGetNthSeed() throws Exception {
        double val = 0.0;
        for (long n = 1L; n < 10L; n++) {
            long s = MinSeedFinder.getNthSeed(n);

            Random random = new Random(s);
            for (long i = 0L; i < n; i++)
                val = random.nextDouble();

            assertThat(val, is(0.0));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNthSeedZero() throws Exception {
        MinSeedFinder.getMinSeed(0);
    }
}