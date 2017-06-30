import java.util.concurrent.CountDownLatch;

/**
 * Created by Bruce on 11/16/16.
 */
public class CountPrimes extends Thread {
    private long lower;
    private long upper;
    private static int numPrimes = 0;
    private static Object lock = new Object();

    public CountPrimes(long lower, long upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public void run() {
        for (long i = lower; i <= upper; i++) {
            if (isPrime(i)) {
                synchronized (lock) {
                    numPrimes++;
                }
            }
        }
        CountPrimesExecutor.decrementCountDownLatch();
    }

    public static int getNumPrimes() {
        return numPrimes;
    }

    public static void resetNumPrimes() {
        numPrimes = 0;
    }

    public boolean isPrime(long num) {
        if (num < 1) {
            return false;
        }
        boolean prime = (num % 2 != 0);
        long root = (long) (Math.sqrt((double)(num)));
        for (long i = 3; i <= root && prime; i += 2) {
            if (num % i == 0) {
                prime = false;
                break;
            }
        }
        return prime;
    }
}
