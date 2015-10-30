package ch08.ex16;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class for {@link ch08.ex16.AddressAnalyzer}.
 * Created by yukiohta on 2015/10/29.
 */
public class AddressAnalyzerTest {

    @Test
    public void testExtract5digitZipcode() {
        AddressSummary actual = AddressAnalyzer.extract("1912 Pike Place, Seattle, WA 98101");

        assertThat(actual.city, is("Seattle"));
        assertThat(actual.state, is("WA"));
        assertThat(actual.zipcode, is("98101"));
    }

    @Test
    public void testExtract9digitZipcode() {
        AddressSummary actual = AddressAnalyzer.extract("1912 Pike Place, Seattle, WA  98101-1013");

        assertThat(actual.city, is("Seattle"));
        assertThat(actual.state, is("WA"));
        assertThat(actual.zipcode, is("98101-1013"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExtract8digitZipcode() {
        AddressAnalyzer.extract("1912 Pike Place, Seattle, WA  9810-1013");
    }
}