package ch08.ex03;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This is test class of {@link ch08.ex03.EuclideanAlgorithm}.
 * Created by yukiohta on 2015/10/18.
 */
public class EuclideanAlgorithmTest {

    @Test
    public void testCreateByRemainder() throws Exception {
        EuclideanAlgorithm target = EuclideanAlgorithm.createByRemainder();
        assertThat(target.gcd(8, 6), is(2));
        assertThat(target.gcd(8, 0), is(8));
        assertThat(target.gcd(8, -6), is(2));
        assertThat("% could return negative value", target.gcd(-8, -6), is(-2));
    }

    @Test
    public void testCreateByFloorMod() throws Exception {
        EuclideanAlgorithm target = EuclideanAlgorithm.createByFloorMod();
        assertThat(target.gcd(8, 6), is(2));
        assertThat(target.gcd(8, 0), is(8));
        assertThat("floorMod could return negative value", target.gcd(8, -6), is(-2));
        assertThat("floorMod could return negative value", target.gcd(-8, -6), is(-2));
    }

    @Test
    public void testCreateByAlithmeticRemainder() throws Exception {
        EuclideanAlgorithm target = EuclideanAlgorithm.createByAlithmeticRemainder();
        assertThat(target.gcd(8, 6), is(2));
        assertThat(target.gcd(8, 0), is(8));
        assertThat(target.gcd(8, -6), is(2));
        assertThat(target.gcd(-8, -6), is(2));
    }
}