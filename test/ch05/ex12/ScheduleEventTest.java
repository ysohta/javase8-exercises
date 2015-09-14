package ch05.ex12;

import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch05.ex12.ScheduleEvent}.
 * Created by yukiohta on 2015/09/14.
 */
public class ScheduleEventTest {
    ScheduleEvent.Builder builder;
    ScheduleEvent defaultEvent;

    @Before
    public void setup() {
        builder = new ScheduleEvent.Builder();
        defaultEvent = new ScheduleEvent.Builder().create();
    }

    @Test
    public void testGetStart() throws Exception {
        ScheduleEvent target = builder.setStart(LocalDateTime.of(2015, 1, 2, 3, 4))
                .setEnd(LocalDateTime.of(2015, 1, 2, 3, 5))
                .create();

        assertThat(target.getStart(), is(LocalDateTime.of(2015, 1, 2, 3, 4)));
    }


    @Test
    public void testGetStartDefault() throws Exception {
        LocalDateTime now = LocalDateTime.now();

        assertThat(defaultEvent.getStart().isAfter(now.minusSeconds(1)), is(true));
        assertThat(defaultEvent.getStart().isBefore(now.plusSeconds(1)), is(true));
    }

    @Test
    public void testGetEnd() throws Exception {
        ScheduleEvent target = builder.setStart(LocalDateTime.of(2015, 1, 2, 3, 4))
                .setEnd(LocalDateTime.of(2015, 1, 2, 3, 5))
                .create();

        assertThat(target.getEnd(), is(LocalDateTime.of(2015, 1, 2, 3, 5)));
    }

    @Test
    public void testGetEndDefault() throws Exception {
        LocalDateTime inAnHour = LocalDateTime.now().plusHours(1);

        assertThat(defaultEvent.getEnd().isAfter(inAnHour.minusSeconds(1)), is(true));
        assertThat(defaultEvent.getEnd().isBefore(inAnHour.plusSeconds(1)), is(true));
    }

    @Test
    public void testGetNotification() throws Exception {
        ScheduleEvent target = builder.setNotification(Duration.ofMinutes(30)).create();

        assertThat(target.getNotification(), is(Duration.ofMinutes(30)));
    }

    @Test
    public void testGetNotificationDefault() throws Exception {
        assertThat(defaultEvent.getNotification(), is(Duration.ofHours(1)));
    }

    @Test
    public void testGetTimezone() throws Exception {
        ScheduleEvent target = builder.setTimezone(ZoneId.of("America/Los_Angeles")).create();

        assertThat(target.getTimezone(), is(ZoneId.of("America/Los_Angeles")));
    }

    @Test
    public void testGetNotificationZonedTime() throws Exception {
        assertThat(defaultEvent.getTimezone(), is(ZoneId.systemDefault()));
    }

    @Test
    public void testCompareTo() throws Exception {
        List<ScheduleEvent> list = new ArrayList<>();
        list.add(builder.setNotification(Duration.ofHours(1)).create());
        list.add(builder.setNotification(Duration.ofHours(3)).create());
        list.add(builder.setNotification(Duration.ofMinutes(30)).create());

        Collections.sort(list);
        int index = 0;
        assertThat(list.get(index++).getNotification(), is(Duration.ofHours(3)));
        assertThat(list.get(index++).getNotification(), is(Duration.ofHours(1)));
        assertThat(list.get(index++).getNotification(), is(Duration.ofMinutes(30)));
    }

}