/**
 * Created by Bruce on 11/16/16.
 */

import java.util.concurrent.CountDownLatch;

public class CountPrimesExecutor {
    private long lower;
    private long upper;
    private int numThreads;
    private CountPrimes[] countPrimeThreads;
    private static CountDownLatch latch;

    public CountPrimesExecutor(int numThreads, long lower, long upper) {
        this.lower = lower;
        this.upper = upper;
        this.numThreads = numThreads;
        latch = new CountDownLatch(numThreads);
        countPrimeThreads = new CountPrimes[numThreads];
        int start = 0;
        long quotient = upper / numThreads;
        long remainder = upper % numThreads;
        for (int i = 0; i < numThreads; i++) {
            long work = quotient;
            if (i < remainder) {
                work++;
            }
            countPrimeThreads[i] = new CountPrimes(start + 1, start + work);
            start += work;
        }
    }

    public void executeThreads() {
        for (int i = 0; i < numThreads; i++) {
            countPrimeThreads[i].start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void decrementCountDownLatch() {
        latch.countDown();
    }

}
