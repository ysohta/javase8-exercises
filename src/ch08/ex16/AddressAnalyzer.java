package ch08.ex16;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class consists of utility methods to analyze address.
 * Created by yukiohta on 2015/10/29.
 */
public class AddressAnalyzer {

    /**
     * Extracts summarized information from the address.
     *
     * @param address US address with 5 or 9 digit zipcode
     * @return summary of address
     * @throws NullPointerException     if address is null
     * @throws IllegalArgumentException if address is invalid format
     */
    public static AddressSummary extract(String address) {
        Objects.requireNonNull(address, "address must not be null");

        Pattern pattern = Pattern.compile(".*,\\s*(?<city>[\\p{L} ]+),\\s*(?<state>[A-Z]{2})\\s*(?<zip>[0-9]{5}|[0-9]{5}-[0-9]{4})");
        Matcher matcher = pattern.matcher(address);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("invalid address format: " + address);
        }

        String city = matcher.group("city");
        String state = matcher.group("state");
        String zip = matcher.group("zip");
        return AddressSummary.of(city, state, zip);
    }
}
