import static org.junit.Assert.*;

import java.util.Random;

import org.junit.*;

public class StringManipulateTest {

    private StringManipulate sm;

    @Before
    public void setUp() {
        sm = new StringManipulate();
    }
    @Test(timeout = 100)
    public void testS1Null() {
        String s1 = null;
        String s2 = "return";
        String message = "Should return null if either s1 or s2 is null";

        assertFalse(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testS2Null() {
        String s1 = "return";
        String s2 = null;
        String message = "Should return null if either s1 or s2 is null";

        assertFalse(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testS1andS2Null() {
        String s1 = null;
        String s2 = null;
        String message = "Should return null if either s1 or s2 is null";

        assertFalse(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testS1Empty() {
        String s1 = "";
        String s2 = "question";
        String message = "Check if one string is empty and the other is not";

        assertFalse(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testS2Empty() {
        String s1 = "question";
        String s2 = "";
        String message = "Check if one string is empty and the other is not";

        assertFalse(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testS1andS2Empty() {
        String s1 = "";
        String s2 = "";
        String message = "Check if both strings are empty, should return true";

        assertTrue(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testS2LongerAndContainsS1() {
        String s1 = "computer";
        String s2 = "computerwiz";
        String message = "Check if s2 contains s1 along with extra characters";

        assertFalse(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testS1LongerAndContainsS2() {
        String s1 = "computerwiz";
        String s2 = "computer";
        String message = "Check if s1 contains s2 along with extra characters";

        assertFalse(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testS2LongerAndContainsS1Scrambled() {
        String s1 = "computer";
        String s2 = "uwmepzcriot";
        String message = "Check if s2 contains s1 along with extra characters";

        assertFalse(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testS1LongerAndContainsS2Scrambled() {
        String s1 = "uwmepzcriot";
        String s2 = "computer";
        String message = "Check if s2 contains s1 along with extra characters";

        assertFalse(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testS1andS2SameStringLowerNoSpace1() {
        String s1 = "comp";
        String s2 = "comp";
        String message = "Check if s1 and s2 are equal";

        assertTrue(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testS1andS2SameStringLowerNoSpace2() {
        String s1 = "sprinkle";
        String s2 = "sprinkle";
        String message = "Check if s1 and s2 are equal";

        assertTrue(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testS1andS2SameStringLowerSpace() {
        String s1 = "togh surface";
        String s2 = "togh surface";
        String message = "Check if s1 and s2 are equal with a space";

        assertTrue(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testS1andS2SameStringMixedCaseNoSpace() {
        String s1 = "lNuyARd";
        String s2 = "yldARNu";
        String message = "Check if s1 and s2 contain the same characters";

        assertTrue(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testS1andS2SameStringMixedCaseSpace() {
        String s1 = "tfgreo ahcus";
        String s2 = "ohrsae tucfg";
        String message = "Check if s1 and s2 contain the same characters with a space";

        assertTrue(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testS1andS2SameStringAllNumbersScrambled() {
        String s1 = "197462835";
        String s2 = "475698312";
        String message = "Check if s1 and s2 are strings containing only numbers";

        assertTrue(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testS1andS2SameStringAllNumbers() {
        String s1 = "987654321";
        String s2 = "987654321";
        String message = "Check if s1 and s2 are string containing only numbers";

        assertTrue(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testS1andS2SameStringUpToLastChar() {
        String s1 = "xvna6dgj3lou1teq0";
        String s2 = "xvna6dgj3lou1teq2";
        String message = "Check when the strings are the same but the last character";

        assertFalse(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testS1andS2SameStringAfterFirstChar() {
        String s1 = "bqr7up8dhl2cnm1";
        String s2 = "aqr7up8dhl2cnm1";
        String message = "Check when the strings are the same but the first character";

        assertFalse(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testS1andS2SameStringUpToLastCharOfS1() {
        String s1 = "a2ld3jgq6pei1y8tmzbcv9";
        String s2 = "a2ld3jgq6pei1y8tmzbcv";
        String message = "Check when the strings are the same except an extra character on s1";

        assertFalse(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testS1andS2SameStringUpToLastCharOfS2() {
        String s1 = "a2ld3jgq6pei1y8tmzbcv";
        String s2 = "a2ld3jgq6pei1y8tmzbcv9";
        String message = "Check when the strings are the same except an extra character on s2";

        assertFalse(message, sm.haveSameChars(s1, s2));
    }

    @Test(timeout = 100)
    public void testRandomSameString() {
        Random r = new Random();
        char c1 = (char)(r.nextInt(25) + 65);
        char c2 = (char)(r.nextInt(25) + 65);
        char c3 = (char)(r.nextInt(25) + 65);
        char c4 = (char)(r.nextInt(25) + 65);

        String s1 = "" + c1 + c2 + c3 + c4;
        String s2 = "" + c4 + c2 + c1 + c3;
        String message = "Check when the strings are the same but letters scrambled";

        assertTrue(message, sm.haveSameChars(s1, s2));
    }
}