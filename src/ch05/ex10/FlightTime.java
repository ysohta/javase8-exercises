package ch05.ex10;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * This class represents flight time for two different time zones.
 * Created by yukiohta on 2015/09/13.
 */
public class FlightTime {
    private final ZoneId departureZone;
    private final ZoneId arrivalZone;

    private FlightTime(ZoneId departureZone, ZoneId arrivalZone) {
        this.departureZone = departureZone;
        this.arrivalZone = arrivalZone;
    }

    /**
     * Returns object of FlightTime from ZoneId of departure and arrival places.
     *
     * @param departureZone zone of departure place
     * @param arrivalZone   zone of arrival place
     * @return object of FlightTime
     * @throws NullPointerException if departureZone or arrivalZone is null
     */
    public static FlightTime createFlightTime(ZoneId departureZone, ZoneId arrivalZone) {
        Objects.requireNonNull(departureZone, "departureZone must not be null");
        Objects.requireNonNull(arrivalZone, "arrivalZone must not be null");

        return new FlightTime(departureZone, arrivalZone);
    }

    /**
     * Returns arrival time from departure time and duration of flight.
     *
     * @param departure departure time
     * @param duration  duration of flight
     * @return arrival time
     * @throws NullPointerException if departure or duration is null;
     */
    public LocalDateTime getArrivalTime(LocalDateTime departure, Duration duration) {
        Objects.requireNonNull(departure, "departure must not be null");
        Objects.requireNonNull(duration, "duration must not be null");

        ZonedDateTime departureTime = ZonedDateTime.of(departure, departureZone);
        ZonedDateTime arrivalTime = departureTime.withZoneSameInstant(arrivalZone).plus(duration);
        return arrivalTime.toLocalDateTime();
    }
}
