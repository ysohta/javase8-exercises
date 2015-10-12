package ch06.ex04;

import java.util.concurrent.atomic.LongAccumulator;

/**
 * This class consists of utility methods to create {@link LongAccumulator} object.
 * Created by yukiohta on 2015/10/04.
 */
public class LongAccumulatorUtility {
    private LongAccumulatorUtility() {
    }

    /**
     * Returns LongAccumulator object to find maximum value.
     *
     * @return max finder
     */
    public static LongAccumulator createMaxFinder() {
        return new LongAccumulator(
                (e1, e2) -> e1 > e2 ? e1 : e2,
                Long.MIN_VALUE
        );
    }

    /**
     * Returns LongAccumulator object to find minimum value.
     *
     * @return min finder
     */
    public static LongAccumulator createMinFinder() {
        return new LongAccumulator(
                (e1, e2) -> e1 < e2 ? e1 : e2,
                Long.MAX_VALUE
        );
    }
}
