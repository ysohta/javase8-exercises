package ch06.ex11;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch06.ex11.CompletableFutureUtil}.
 * Created by yukiohta on 2015/10/10.
 */
public class CompletableFutureUtilTest {

    @Test
    public void testRepeat() throws Exception {
        Random random = new Random();

        CompletableFuture<Integer> actual = CompletableFutureUtil.repeat(() -> random.nextInt(), (i) -> i % 100 == 0);

        assertThat((actual.get() % 100) == 0, is(true));
    }

    @Test(expected = NullPointerException.class)
    public void testRepeatNullAction() throws Exception {
        CompletableFutureUtil.repeat(null, (t) -> true);
    }

    @Test(expected = NullPointerException.class)
    public void testRepeatNullUntil() throws Exception {
        CompletableFutureUtil.repeat(() -> 0, null);
    }
}