package ch05.ex09;


import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class consists of utility methods to handle java.time API.
 * Created by yukiohta on 2015/09/13.
 */
public class DateTimeUtil {
    private DateTimeUtil() {
    }

    /**
     * Returns list of available time offset.
     *
     * @return list of time offset
     */
    public static List<ZoneOffset> listAvailableOffsetFilterBy(Predicate<ZoneOffset> predicate) {
        return ZoneId.getAvailableZoneIds()
                .stream()
                .map(ZoneId::of)
                .map(e -> ZonedDateTime.now(e).getOffset())
//                .filter(e -> e.compareTo(start) < 0 && e.compareTo(end) > 0)
//                .peek(e -> System.out.println(e.getTotalSeconds()))
//                .filter(e -> e.getTotalSeconds() % 3600 != 0)
                .filter(predicate)
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
