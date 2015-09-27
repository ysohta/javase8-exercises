package ch05.ex09;

import org.junit.Test;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch05.ex09.DateTimeUtil}.
 * Created by yukiohta on 2015/09/13.
 */
public class DateTimeUtilTest {

    @Test
    public void testListAvailableOffsetFilterBy() throws Exception {
        List<ZoneOffset> actual = DateTimeUtil.listAvailableOffsetFilterBy(e -> e.getTotalSeconds() % 3600 != 0);

        for (ZoneOffset offset : actual) {
            assertThat(offset.toString(), not(endsWith(":00")));
        }
    }

    @Test
    public void testListAvailableOffsetFilterByAlwaysTrue() throws Exception {
        List<ZoneOffset> actual = DateTimeUtil.listAvailableOffsetFilterBy(e -> true);

        assertThat(actual.size(), is(ZoneId.getAvailableZoneIds().size()));
    }
}