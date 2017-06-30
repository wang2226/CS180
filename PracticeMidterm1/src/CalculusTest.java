import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test cases for Exam 1, Question 1: Calculus
 */
public class CalculusTest {
    private Calculus c;

    @Before
    public void setUp() {
        c = new Calculus();
    }

    @Test(timeout = 100)
    public void testFuncNullString() {
        String func = null;

        String message = "differentiate(): You must return null if the input is null.";
        String expected = null;
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testFuncEmptyString() {
        String func = "";

        String message = "differentiate(): You must return an empty string if the input is an empty string.";
        String expected = "";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testNegativeNegative1() {
        String func = "-10x^-10";

        String message = "differentiate(): Check when a and b are negative.";
        String expected = "100x^-11";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testNegativeNegative2() {
        String func = "-10x^-1";

        String message = "differentiate(): Check when a and b are negative.";
        String expected = "10x^-2";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testNegativeNegative3() {
        String func = "-9x^-10";

        String message = "differentiate(): Check when a and b are negative.";
        String expected = "90x^-11";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testNegativeNegative4() {
        String func = "-9x^-1";

        String message = "differentiate(): Check when a and b are negative.";
        String expected = "9x^-2";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testNegativeZero1() {
        String func = "-10x^0";

        String message = "differentiate(): Check when a is negative and b is 0.";
        String expected = "0x^-1";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testNegativeZero2() {
        String func = "-9x^0";

        String message = "differentiate(): Check when a is negative and b is 0.";
        String expected = "0x^-1";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testNegativePositive1() {
        String func = "-10x^1";

        String message = "differentiate(): Check when a is negative and b is positive.";
        String expected = "-10x^0";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testNegativePositive2() {
        String func = "-10x^2";

        String message = "differentiate(): Check when a is negative and b is positive.";
        String expected = "-20x^1";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testNegativePositive3() {
        String func = "-10x^10";

        String message = "differentiate(): Check when a is negative and b is positive.";
        String expected = "-100x^9";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testNegativePositive4() {
        String func = "-9x^1";

        String message = "differentiate(): Check when a is negative and b is positive.";
        String expected = "-9x^0";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testNegativePositive5() {
        String func = "-9x^2";

        String message = "differentiate(): Check when a is negative and b is positive.";
        String expected = "-18x^1";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testNegativePositive6() {
        String func = "-9x^10";

        String message = "differentiate(): Check when a is negative and b is positive.";
        String expected = "-90x^9";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testZeroNegative1() {
        String func = "0x^-10";

        String message = "differentiate(): Check when a is 0 and b is negative.";
        String expected = "0x^-11";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testZeroZero() {
        String func = "0x^0";

        String message = "differentiate(): Check when a and b are both 0.";
        String expected = "0x^-1";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testZeroPositive1() {
        String func = "0x^1";

        String message = "differentiate(): Check when a is 0 and b is positive.";
        String expected = "0x^0";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testZeroPositive2() {
        String func = "0x^10";

        String message = "differentiate(): Check when a is 0 and b is positive.";
        String expected = "0x^9";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testPositiveNegative1() {
        String func = "1x^-10";

        String message = "differentiate(): Check when a is positive and b is negative.";
        String expected = "-10x^-11";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testPositiveNegative2() {
        String func = "1x^-1";

        String message = "differentiate(): Check when a is positive and b is negative.";
        String expected = "-1x^-2";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testPositiveNegative3() {
        String func = "10x^-10";

        String message = "differentiate(): Check when a is positive and b is negative.";
        String expected = "-100x^-11";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testPositiveNegative4() {
        String func = "10x^-1";

        String message = "differentiate(): Check when a is positive and b is negative.";
        String expected = "-10x^-2";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testPositiveZero1() {
        String func = "1x^0";

        String message = "differentiate(): Check when a is positive and b is 0.";
        String expected = "0x^-1";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testPositiveZero2() {
        String func = "10x^0";

        String message = "differentiate(): Check when a is positive and b is 0.";
        String expected = "0x^-1";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testPositivePositive1() {
        String func = "1x^1";

        String message = "differentiate(): Check when a is positive and b is positive.";
        String expected = "1x^0";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testPositivePositive2() {
        String func = "1x^2";

        String message = "differentiate(): Check when a is positive and b is positive.";
        String expected = "2x^1";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testPositivePositive3() {
        String func = "1x^10";

        String message = "differentiate(): Check when a is positive and b is positive.";
        String expected = "10x^9";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testPositivePositive4() {
        String func = "10x^1";

        String message = "differentiate(): Check when a is positive and b is positive.";
        String expected = "10x^0";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testPositivePositive5() {
        String func = "10x^2";

        String message = "differentiate(): Check when a is positive and b is positive.";
        String expected = "20x^1";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }

    @Test(timeout = 100)
    public void testPositivePositive6() {
        String func = "10x^10";

        String message = "differentiate(): Check when a is positive and b is positive.";
        String expected = "100x^9";
        String actual = c.differentiate(func);

        assertEquals(message, expected, actual);
    }
}