package ch06.ex07;

import org.junit.Test;

import java.util.OptionalLong;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.LongStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch06.ex07.ConcurrentHashMapUtil}.
 * Created by yukiohta on 2015/10/05.
 */
public class ConcurrentHashMapUtilTest {

    @Test
    public void testFindKeyWithLargestValue() throws Exception {
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        map.put("one", 1L);
        map.put("seven", 7L);
        map.put("minus three", -3L);

        String actual = ConcurrentHashMapUtil.findKeyWithLargestValue(map);

        assertThat(actual, is("seven"));
    }

    @Test
    public void testFindKeyWithLargestValueRandom() throws Exception {
        LongStream longStream = new Random().longs();
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();

        OptionalLong maxVal = longStream.limit(1_000).peek(l -> map.put(String.valueOf(l), l)).max();
        String actual = ConcurrentHashMapUtil.findKeyWithLargestValue(map);

        assertThat(actual, is(String.valueOf(maxVal.getAsLong())));
    }

    @Test(expected = NullPointerException.class)
    public void testFindKeyWithLargestValueNullMap() throws Exception {
        ConcurrentHashMapUtil.findKeyWithLargestValue(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindKeyWithLargestValueEmptyMap() throws Exception {
        ConcurrentHashMapUtil.findKeyWithLargestValue(new ConcurrentHashMap<>());
    }
}