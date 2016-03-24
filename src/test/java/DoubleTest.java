import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by inspiredjava on 3/23/16.
 */
public class DoubleTest {

    @Test
    public void checkNumbers() {
        Double actual = new Doubles().parse("12");
        assertThat(actual, is(12.0));
    }

    @Test
    public void checkNegativeNumbers() {
        Double actual = new Doubles().parse("-12");
        assertThat(actual, is(-12.0));
    }

    @Test
    public void checkPositiveNumbers() {
        Double actual = new Doubles().parse("+12.");
        assertThat(actual, is(12.0));
    }

    @Test
    public void checkDecimal() {
        Double actual = new Doubles().parse("12.641");
        assertThat(actual, is(12.641));
    }

    @Test
    public void checkOnlyDecimal() {
        Double actual = new Doubles().parse(".123");
        assertThat(actual, is(0.123));
    }

    @Test
    public void checkDot() {
        Double actual = new Doubles().parse(".");
        assertNull(actual);
    }

    @Test
    public void checkExpo() {
        Double actual = new Doubles().parse("2.e5");
        assertThat(actual, is(2.e5));
    }

    @Test
    public void checkComplicated() {
        Double actual = new Doubles().parse("-.56e-5");
        assertThat(actual, is(-.56e-5));
    }
}
