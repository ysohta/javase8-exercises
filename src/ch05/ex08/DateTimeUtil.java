package ch05.ex08;


import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
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
    public static List<ZoneOffset> listAvailableOffset() {
        return ZoneId.getAvailableZoneIds()
                .stream()
                .map(ZoneId::of)
                .map(e -> ZonedDateTime.now(e).getOffset())
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
