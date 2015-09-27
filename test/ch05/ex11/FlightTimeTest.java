package ch05.ex11;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch05.ex11.FlightTime}.
 * Created by yukiohta on 2015/09/13.
 */
public class FlightTimeTest {
    FlightTime flightTime = FlightTime.createFlightTime(
            ZoneId.of("Europe/Berlin"),
            ZoneId.of("America/Los_Angeles"));

    @Test
    public void testGetDuration() throws Exception {
        Duration actual = flightTime.getDuration(
                LocalDateTime.of(2015, 9, 13, 14, 5),
                LocalDateTime.of(2015, 9, 13, 16, 40));

        assertThat(actual, is(Duration.ofHours(11).plusMinutes(35)));
    }

    @Test(expected = NullPointerException.class)
    public void testGetDurationNullDeparture() throws Exception {
        flightTime.getDuration(null, LocalDateTime.now());
    }

    @Test(expected = NullPointerException.class)
    public void testGetDurationNullArrival() throws Exception {
        flightTime.getDuration(LocalDateTime.now(), null);
    }
}