package ch08.ex13;

import java.lang.annotation.*;

/**
 * This annotation is repeating annotation for {@link ch08.ex13.TestCase}.
 * Created by yukiohta on 2015/10/26.
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD})
public @interface TestCases {
    /**
     * Returns array of {@link ch08.ex13.TestCase}.
     *
     * @return test cases
     */
    TestCase[] value();
}