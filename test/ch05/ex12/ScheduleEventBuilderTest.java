package ch05.ex12;

import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * This is test class of {@link ch05.ex12.ScheduleEvent.Builder}.
 * Created by yukiohta on 2015/09/14.
 */
public class ScheduleEventBuilderTest {
    ScheduleEvent.Builder target;

    @Before
    public void setup() {
        target = new ScheduleEvent.Builder();
    }

    @Test(expected = NullPointerException.class)
    public void testSetStartNull() throws Exception {
        target.setStart(null);
    }

    @Test(expected = NullPointerException.class)
    public void testSetEndNull() throws Exception {
        target.setEnd(null);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNotificationNull() throws Exception {
        target.setNotification(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNotificationNegativeValue() throws Exception {
        target.setNotification(Duration.ofHours(-1));
    }

    @Test(expected = NullPointerException.class)
    public void testSetTimezoneNull() throws Exception {
        target.setTimezone(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testCreateStartAfterEnd() throws Exception {
        target.setStart(LocalDateTime.of(2015, 9, 14, 12, 1))
                .setEnd(LocalDateTime.of(2015, 9, 14, 12, 0))
                .create();
    }

    @Test(expected = IllegalStateException.class)
    public void testCreateStartEqualsToEnd() throws Exception {
        target.setStart(LocalDateTime.of(2015, 9, 14, 12, 0))
                .setEnd(LocalDateTime.of(2015, 9, 14, 12, 0))
                .create();
    }
}