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
     * Returns list of available time offset with filter.
     * @param filter filter to select offset
     * @return list of time offset
     */
    public static List<ZoneOffset> listAvailableOffsetFilterBy(Predicate<ZoneOffset> filter) {
        return ZoneId.getAvailableZoneIds()
                .stream()
                .map(ZoneId::of)
                .map(e -> ZonedDateTime.now(e).getOffset())
                .filter(filter)
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
