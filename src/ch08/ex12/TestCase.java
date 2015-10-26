package ch08.ex12;

import java.lang.annotation.*;

/**
 * This annotation is used for testing target method.
 * Created by yukiohta on 2015/10/23.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Repeatable(TestCases.class)
public @interface TestCase {
    /**
     * Returns parameter value
     *
     * @return parameter
     */
    String params();

    /**
     * Returns expected value
     *
     * @return expected
     */
    String expected();
}

