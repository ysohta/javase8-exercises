package ch06.ex01;

import org.junit.Test;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * This is test class of {@link LongestStringManager}.
 * Created by yukiohta on 2015/09/21.
 */
public class LongestStringManagerTest {

    private Random random = new Random();

    @Test
    public void testAdd() throws Exception {
        LongestStringManager target = new LongestStringManager();
        target.add("");
        assertThat(target.getLongest(), is(""));

        target.add("123");
        assertThat(target.getLongest(), is("123"));

        target.add("12");
        assertThat(target.getLongest(), is("123"));
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullString() throws Exception {
        LongestStringManager target = new LongestStringManager();
        target.add(null);
    }

    @Test(timeout = 10000)
    public void testAddMultiThread() throws Exception {
        LongestStringManager target = new LongestStringManager();
        Queue<String> words = new ConcurrentLinkedQueue<>();

        int trials = 100;
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(cores);

        for (int i = 0; i < cores; i++) {
            service.submit(() -> {
                for (int n = 0; n < trials; n++) {
                    String str = createStringWithLength(random.nextInt(1000));

                    Thread.sleep(random.nextInt(10));

                    target.add(str);
                    words.add(str);
                }

                return null;
            });
        }

        service.shutdown();
        service.awaitTermination(1, TimeUnit.SECONDS);

        assertThat(words.size(), is(100 * cores));

        String actual = target.getLongest();
        String expected = words.stream().max((o1, o2) -> o1.length() - o2.length()).get();

        assertThat(actual.length(), is(expected.length()));
    }

    private static String createStringWithLength(int length) {
        char[] chars = new char[length];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = '*';
        }

        return String.copyValueOf(chars);
    }
}