package ch08.ex04;

import java.util.Random;
import java.util.stream.LongStream;

/**
 * This class consists of utility methods to find the minimum seed for {@link Random#nextDouble()}.
 * Created by yukiohta on 2015/10/20.
 */
public class MinSeedFinder {
    private static long m = 25214903917L;
    private static long a = 11L;
    private static long n = 281_474_976_710_656L; // 2^48
    private static long v = 246_154_705_703_781L;

    private MinSeedFinder() {
    }

    public static void main(String... args) {
        long nSeeds = 1_000_000L;
        long min = getMinSeed(nSeeds);
        Random random = new Random(min);
        long cnt;
        for (cnt = 1; cnt <= nSeeds; cnt++) {
            if (random.nextDouble() == 0.0)
                break;
        }

        // min=881498 cnt=376050
        System.out.println("min=" + min + " cnt=" + cnt);
    }

    public static long getMinSeed(long nSeeds) {
        if (n < 1)
            throw new IllegalArgumentException("nSeed must not be smaller than 1");

        return createSeeds().limit(nSeeds)
                .boxed().min(Long::compareUnsigned).get();
    }

    public static long getNthSeed(long n) {
        if (n < 1)
            throw new IllegalArgumentException("n must not be smaller than 1");

        return createSeeds().limit(n).skip(n - 1)
                .findFirst().getAsLong();
    }

    private static LongStream createSeeds() {
        return LongStream.iterate(prev(0L), x -> prev(prev(x))).map(x -> x ^ m);
    }

    private static long prev(long x) {
        return (x - a) * v % n;
    }
}
