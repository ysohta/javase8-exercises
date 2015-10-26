package ch08.ex13;

import java.lang.annotation.*;

/**
 * This annotation is used for testing target method.
 * Created by yukiohta on 2015/10/23.
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
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

