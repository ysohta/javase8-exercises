package ch08.ex16;

import java.util.Objects;

/**
 * This class represents summarized information for the address.
 * Created by yukiohta on 2015/10/29.
 */
public class AddressSummary {
    public final String city;
    public final String state;
    public final String zipcode;

    private AddressSummary(String city, String state, String zipcode) {
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    /**
     * Creates {@link AddressSummary} object.
     *
     * @param city    city
     * @param state   state
     * @param zipcode zipcode
     * @return address summary
     * @throws NullPointerException if any parameter is null
     */
    public static AddressSummary of(String city, String state, String zipcode) {
        Objects.requireNonNull(city, "city must not be null");
        Objects.requireNonNull(state, "state must not be null");
        Objects.requireNonNull(zipcode, "zipcode must not be null");

        return new AddressSummary(city, state, zipcode);
    }
}
