package ch05.ex08;

import org.junit.Test;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch05.ex08.DateTimeUtil}.
 * Created by yukiohta on 2015/09/13.
 */
public class DateTimeUtilTest {

    @Test
    public void testListAvailableOffset() throws Exception {
        List<ZoneOffset> actual = DateTimeUtil.listAvailableOffset();

        assertThat(actual.size(), is(ZoneId.getAvailableZoneIds().size()));
    }
}