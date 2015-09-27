package ch05.ex10;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch05.ex10.FlightTime}.
 * Created by yukiohta on 2015/09/13.
 */
public class FlightTimeTest {

    FlightTime flightTime = FlightTime.createFlightTime(
            ZoneId.of("America/Los_Angeles"),
            ZoneId.of("Europe/Berlin"));

    @Test
    public void testGetArrivalTime() throws Exception {
        LocalDateTime actual = flightTime.getArrivalTime(
                LocalDateTime.of(2015, 9, 13, 15, 5),
                Duration.ofHours(10).plusMinutes(50));

        assertThat(actual, is(LocalDateTime.of(2015, 9, 14, 10, 55)));
    }

    @Test(expected = NullPointerException.class)
    public void testGetArrivalTimeNullArrival() throws Exception {
        flightTime.getArrivalTime(null, Duration.ofHours(1));
    }

    @Test(expected = NullPointerException.class)
    public void testGetArrivalTimeNullDuration() throws Exception {
        flightTime.getArrivalTime(LocalDateTime.now(), null);
    }
}