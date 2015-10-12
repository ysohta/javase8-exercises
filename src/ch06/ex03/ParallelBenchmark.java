package ch06.ex03;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * This class has methods to run benchmarks in parallel.
 * Created by yukiohta on 2015/10/01.
 */
public class ParallelBenchmark {
    private final int nThreads;
    private final int count;

    /**
     * Constructs ParallelBenchmark object.
     *
     * @param nThreads the number of threads
     * @param count    the number to be counted
     * @throws IllegalArgumentException if nThreads or count is smaller than 1
     */
    public ParallelBenchmark(int nThreads, int count) {
        if (nThreads < 1)
            throw new IllegalArgumentException("nThreads must not be smaller than 1");

        if (count < 1)
            throw new IllegalArgumentException("count must not be smaller than 1");

        this.nThreads = nThreads;
        this.count = count;
    }

    /**
     * Returns elapsed time using {@link AtomicLong} as counter.
     *
     * @return time
     * @throws InterruptedException if termination is timed out
     */
    public Duration measureTimeByAtomicLong() throws InterruptedException {
        AtomicLong counter = new AtomicLong();
        ExecutorService service = Executors.newFixedThreadPool(nThreads);

        long start = System.nanoTime();
        for (int i = 0; i < nThreads; i++) {
            service.submit(() -> {
                for (int j = 0; j < count; j++) {
                    counter.incrementAndGet();
                }
            });
        }

        service.shutdown();
        service.awaitTermination(1, TimeUnit.SECONDS);

        return Duration.ofNanos(System.nanoTime() - start);
    }

    /**
     * Returns elapsed time using {@link LongAdder} as counter.
     *
     * @return time
     * @throws InterruptedException if termination is timed out
     */
    public Duration measureTimeByLongAdder() throws InterruptedException {
        LongAdder counter = new LongAdder();
        ExecutorService service = Executors.newFixedThreadPool(nThreads);

        long start = System.nanoTime();
        for (int i = 0; i < nThreads; i++) {
            service.submit(() -> {
                for (int j = 0; j < count; j++) {
                    counter.increment();
                }
            });
        }

        service.shutdown();
        service.awaitTermination(1, TimeUnit.SECONDS);

        return Duration.ofNanos(System.nanoTime() - start);
    }

    public static void main(String... args) {
        ParallelBenchmark benchmark = new ParallelBenchmark(1_000, 100_000);

        try {
            System.out.println("Counted by AtomicLong: " + benchmark.measureTimeByAtomicLong().toMillis() + " msec");
            System.out.println("Counted by LongAdder: " + benchmark.measureTimeByLongAdder().toMillis() + " msec");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
