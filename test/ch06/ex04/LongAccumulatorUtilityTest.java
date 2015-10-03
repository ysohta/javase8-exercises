package ch06.ex04;

import org.junit.Test;

import java.util.concurrent.atomic.LongAccumulator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch06.ex04.LongAccumulatorUtility}.
 * Created by yukiohta on 2015/10/04.
 */
public class LongAccumulatorUtilityTest {

    @Test
    public void testCreateMaxFinder() throws Exception {
        LongAccumulator maxFinder = LongAccumulatorUtility.createMaxFinder();
        maxFinder.accumulate(10L);
        maxFinder.accumulate(-2L);
        maxFinder.accumulate(721L);
        maxFinder.accumulate(0L);

        assertThat(maxFinder.get(), is(721L));
    }

    @Test
    public void testCreateMaxFinderNoElement() throws Exception {
        LongAccumulator maxFinder = LongAccumulatorUtility.createMaxFinder();

        assertThat(maxFinder.get(), is(Long.MIN_VALUE));
    }

    @Test
    public void testCreateMinFinder() throws Exception {
        LongAccumulator minFinder = LongAccumulatorUtility.createMinFinder();
        minFinder.accumulate(10L);
        minFinder.accumulate(-2L);
        minFinder.accumulate(721L);
        minFinder.accumulate(0L);

        assertThat(minFinder.get(), is(-2L));
    }

    @Test
    public void testCreateMinFinderNoElement() throws Exception {
        LongAccumulator minFinder = LongAccumulatorUtility.createMinFinder();

        assertThat(minFinder.get(), is(Long.MAX_VALUE));
    }
}