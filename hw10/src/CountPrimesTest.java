import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Random;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * JUnit tests for CS18000-F16 concurrency homework, CountPrimes.
 *
 * @author Neil Allison
 * @version November 06, 2016
 */
public class CountPrimesTest {
    public static Random r;
    public static CountPrimesExecutor countPrimesExecutor;

    /**
     * Generate a random prime with composite probability no more than 2^-100. The probable random prime will be
     * between 3 and 32 bits in length.
     *
     * @return The randomly generated probable prime
     */
    public static long generateRandomPrime() {
        int bitLength = r.nextInt(30) + 3;
        BigInteger probablePrime = BigInteger.probablePrime(bitLength, r);
        return probablePrime.longValue();
    }

    @BeforeClass
    public static void setUp() {
        r = new Random();
        countPrimesExecutor = new CountPrimesExecutor(1, 0, 0);
    }

    @Test(timeout = 100)
    //@ScoringWeight(3)
    public void testCountPrimesConstructor() {
        String message = "CountPrimes constructor must have a single, two-argument constructor which initializes the " +
                "corresponding instance variables 'lower' and 'upper'";
        long lower = r.nextLong();
        long upper = r.nextLong();
        CountPrimes countPrimes = new CountPrimes(lower, upper);
        Class<?> clazz = countPrimes.getClass();

        try {
            Field lowerField = clazz.getDeclaredField("lower");
            lowerField.setAccessible(true);
            assertEquals(message, lower, lowerField.getLong(countPrimes));
            lowerField.setAccessible(false);

            Field upperField = clazz.getDeclaredField("upper");
            upperField.setAccessible(true);
            assertEquals(message, upper, upperField.getLong(countPrimes));
            upperField.setAccessible(false);
        } catch (NoSuchFieldException e) {
            fail("One or more of your instance variables is spelled incorrectly. " +
                    "Make sure you are using the correct case and spelling");
        } catch (IllegalAccessException e) {
            fail("Instance variables must be declared private");
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(2)
    public void testCountPrimesExtendsThread() {
        assertTrue("CountPrimes must be a subclass of Thread", Thread.class.isAssignableFrom(CountPrimes.class));
    }

    @Test(timeout = 1000)
    //@ScoringWeight(3.75)
    public void testCountPrimesIsPrimeTrueRandom() {
        String message = "isPrime must return true if the argument is prime and false otherwise";
        CountPrimes countPrimes = new CountPrimes(0, 0);
        Class<?> clazz = countPrimes.getClass();

        try {
            Method isPrime = clazz.getDeclaredMethod("isPrime", Long.TYPE);
            isPrime.setAccessible(true);
            long randomPrime = generateRandomPrime();
            boolean result = (boolean) isPrime.invoke(countPrimes, randomPrime);
            isPrime.setAccessible(false);
            assertTrue(message, result);
        } catch (NoSuchMethodException e) {
            fail("You must implement a method with the name 'isPrime'");
        } catch (InvocationTargetException e) {
            fail("Method isPrime must take a single argument of type long");
        } catch (IllegalAccessException e) {
            fail("Method isPrime must be declared private");
        }
    }

    @Test(timeout = 1000)
    //@ScoringWeight(3.75)
    public void testCountPrimesIsPrimeTrueFixed() {
        String message = "isPrime must return true if the argument is prime and false otherwise";
        CountPrimes countPrimes = new CountPrimes(0, 0);
        Class<?> clazz = countPrimes.getClass();

        try {
            Method isPrime = clazz.getDeclaredMethod("isPrime", Long.TYPE);
            isPrime.setAccessible(true);
            long fixedPrime = 32416189777L;
            boolean result = (boolean) isPrime.invoke(countPrimes, fixedPrime);
            isPrime.setAccessible(false);
            assertTrue(message, result);
        } catch (NoSuchMethodException e) {
            fail("You must implement a method with the name 'isPrime'");
        } catch (InvocationTargetException e) {
            fail("Method isPrime must take a single argument of type long");
        } catch (IllegalAccessException e) {
            fail("Method isPrime must be declared private");
        }
    }

    @Test(timeout = 1000)
    //@ScoringWeight(3.75)
    public void testCountPrimesIsPrimeFalseRandom() {
        String message = "isPrime must return true if the argument is prime and false otherwise";
        CountPrimes countPrimes = new CountPrimes(0, 0);
        Class<?> clazz = countPrimes.getClass();

        try {
            Method isPrime = clazz.getDeclaredMethod("isPrime", Long.TYPE);
            isPrime.setAccessible(true);
            long randomNonPrime = generateRandomPrime() + 1;
            boolean result = (boolean) isPrime.invoke(countPrimes, randomNonPrime);
            isPrime.setAccessible(false);
            assertFalse(message, result);
        } catch (NoSuchMethodException e) {
            fail("You must implement a method with the name 'isPrime'");
        } catch (InvocationTargetException e) {
            fail("Method isPrime must take a single argument of type long");
        } catch (IllegalAccessException e) {
            fail("Method isPrime must be declared private");
        }
    }

    @Test(timeout = 1000)
    //@ScoringWeight(3.75)
    public void testCountPrimesIsPrimeFalseFixed() {
        String message = "isPrime must return true if the argument is prime and false otherwise";
        CountPrimes countPrimes = new CountPrimes(0, 0);
        Class<?> clazz = countPrimes.getClass();

        try {
            Method isPrime = clazz.getDeclaredMethod("isPrime", Long.TYPE);
            isPrime.setAccessible(true);
            long fixedNonPrime = 22115657;
            boolean result = (boolean) isPrime.invoke(countPrimes, fixedNonPrime);
            isPrime.setAccessible(false);
            assertFalse(message, result);
        } catch (NoSuchMethodException e) {
            fail("You must implement a method with the name 'isPrime'");
        } catch (InvocationTargetException e) {
            fail("Method isPrime must take a single argument of type long");
        } catch (IllegalAccessException e) {
            fail("Method isPrime must be declared private");
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(5)
    public void testCountPrimesGetNumPrimes() {
        String message = "getNumPrimes must return the int value of the instance variable numPrimes";
        CountPrimes.resetNumPrimes();
        assertEquals(message, 0, CountPrimes.getNumPrimes());
    }

    @Test(timeout = 1000)
    //@ScoringWeight(10)
    public void testCountPrimesRunComputesPrimeCount() {
        String message = "CountPrimes run method must compute the number of primes in the interval [lower,upper] and " +
                "update the instance variable numPrimes accordingly";
        CountPrimes.resetNumPrimes();
        CountPrimes countPrimes = new CountPrimes(-1, 120);
        countPrimes.start();
        try {
            countPrimes.join();
        } catch (InterruptedException e) {
            fail("Thread was interrupted. Try re-running test cases.");
        }
        int expectedNumPrimes = 30;
        int actualNumPrimes = CountPrimes.getNumPrimes();
        assertEquals(message, expectedNumPrimes, actualNumPrimes);
    }

    @Test(timeout = 100)
    //@ScoringWeight(5)
    public void testCountPrimesExecutorConstructor() {
        String message = "CountPrimesExecutor must have a three-argument constructor (one int, two longs) which " +
                "initializes the instance variables 'lower', 'upper', 'numThreads', and 'countPrimeThreads'";
        int numThreads = r.nextInt(32) + 1;
        long lower = r.nextInt(1000) + 1;
        long upper = r.nextInt(10000) + lower + 1;
        CountPrimesExecutor countPrimesExecutor = new CountPrimesExecutor(numThreads, lower, upper);
        Class<?> clazz = countPrimesExecutor.getClass();

        try {
            Field lowerField = clazz.getDeclaredField("lower");
            lowerField.setAccessible(true);
            assertEquals(message, lower, lowerField.getLong(countPrimesExecutor));
            lowerField.setAccessible(false);

            Field upperField = clazz.getDeclaredField("upper");
            upperField.setAccessible(true);
            assertEquals(message, upper, upperField.getLong(countPrimesExecutor));
            upperField.setAccessible(false);

            Field numThreadsField = clazz.getDeclaredField("numThreads");
            numThreadsField.setAccessible(true);
            assertEquals(message, numThreads, numThreadsField.getInt(countPrimesExecutor));
            numThreadsField.setAccessible(false);

            Field countPrimeThreads = clazz.getDeclaredField("countPrimeThreads");
            countPrimeThreads.setAccessible(true);
            assertEquals(message, numThreads, ((CountPrimes[]) countPrimeThreads.get(countPrimesExecutor)).length);
            countPrimeThreads.setAccessible(false);
        } catch (NoSuchFieldException e) {
            fail("One or more of your instance variables is spelled incorrectly. " +
                    "Make sure you are using the correct case and spelling");
        } catch (IllegalAccessException e) {
            fail("Instance variables must be declared private");
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(30)
    public void testCountPrimesExecutorConstructorInitializesThreads() {
        int numThreads = 4;
        long lower = 1;
        long upper = 120;
        long subRangeSize = 30;
        CountPrimesExecutor countPrimesExecutor = new CountPrimesExecutor(numThreads, lower, upper);
        Class<?> clazz = countPrimesExecutor.getClass();

        CountPrimes[] actualThreads = null;
        try {
            Field countPrimeThreads = clazz.getDeclaredField("countPrimeThreads");
            countPrimeThreads.setAccessible(true);
            actualThreads = (CountPrimes[]) countPrimeThreads.get(countPrimesExecutor);
            assertNotNull("countPrimeThreads must be initialized in the constructor", actualThreads);
            assertEquals("countPrimeThreads must be of length numThreads", numThreads, actualThreads.length);
            countPrimeThreads.setAccessible(false);
        } catch (NoSuchFieldException e) {
            fail("You must declare a private instance variable with the name countPrimeThreads of type CountPrimes[]");
        } catch (IllegalAccessException e) {
            fail("countPrimeThreads must be declared private");
        }

        try {
            for (int i = 0; i < numThreads; i++) {
                CountPrimes countPrimes = actualThreads[i];
                Class<?> countPrimesClazz = countPrimes.getClass();
                assertNotNull("CountPrimesExecutor constructor must initialize the CountPrime threads", countPrimes);
                Field countPrimesLower = countPrimesClazz.getDeclaredField("lower");
                countPrimesLower.setAccessible(true);
                long expectedLower = 1 + (subRangeSize * i);
                assertEquals("CountPrimesExecutor must split the specified interval [lower,upper] into equal " +
                                "sub-intervals to be processed by each CountPrimes thread. You can assume that this " +
                        "test case will only test for intervals which are evenly divisible by numThreads",
                        expectedLower, countPrimesLower.getLong(countPrimes));
                countPrimesLower.setAccessible(false);

                Field countPrimesUpper = countPrimesClazz.getDeclaredField("upper");
                countPrimesUpper.setAccessible(true);
                long expectedUpper = subRangeSize * (i + 1);
                assertEquals("CountPrimesExecutor must split the specified interval [lower,upper] into equal " +
                                "sub-intervals to be processed by each CountPrimes thread. You can assume that this " +
                        "test case will only test for intervals which are evenly divisible by numThreads",
                        expectedUpper, countPrimesUpper.getLong(countPrimes));
                countPrimesUpper.setAccessible(false);
            }
        } catch (NoSuchFieldException e) {
            fail("You must declare the private instance variables lower and upper of type long");
        } catch (IllegalAccessException e) {
            fail("CountPrimesExecutor instance variables lower and upper must be declared private");
        }
    }

    @Test(timeout = 10000)
    //@ScoringWeight(30)
    public void testCountPrimesExecutorExecute() {
        CountPrimes.resetNumPrimes();
        int numThreads = r.nextInt(32) + 1;
        long lower = 1;
        long upper = 109298;
        CountPrimesExecutor countPrimesExecutor = new CountPrimesExecutor(numThreads, lower, upper);
        countPrimesExecutor.executeThreads();
        String message = "CountPrimesExecutor method executeThreads must start each of the threads in " +
                "countPrimeThreads and wait all threads to complete before resuming execution of the main thread";
        int expectedNumPrimes = 10390;
        int actualNumPrimes = CountPrimes.getNumPrimes();
        assertEquals(message, expectedNumPrimes, actualNumPrimes);
    }
}
