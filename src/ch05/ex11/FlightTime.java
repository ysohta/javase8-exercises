package ch05.ex11;

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
     * Returns duration time from departure and arrival time.
     *
     * @param departure departure time
     * @param arrival   arrival time
     * @return duration time
     * @throws NullPointerException if departure or arrival is null
     */
    public Duration getDuration(LocalDateTime departure, LocalDateTime arrival) {
        Objects.requireNonNull(departure, "departure must not be null");
        Objects.requireNonNull(arrival, "arrival must not be null");

        return Duration.between(
                ZonedDateTime.of(departure, departureZone),
                ZonedDateTime.of(arrival, arrivalZone));
    }
}
