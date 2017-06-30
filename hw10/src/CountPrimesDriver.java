/**
 * Entry point for CountPrimes. Parses program arguments, calls the CountPrimesExecutor to initialize threads, and
 * prints basic wall-clock stats for program execution time.
 *
 * @author Neil Allison
 * @version November 05, 2016
 */
public class CountPrimesDriver {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java CountPrimesExecutor [numThreads] [lower bound] [upper bound]");
            return;
        }

        int numProcessors = Runtime.getRuntime().availableProcessors();
        int numThreads = Integer.parseInt(args[0]);
        long lowerBound = Long.parseLong(args[1]);
        long upperBound = Long.parseLong(args[2]);
        
        System.out.printf("Computing the total number of primes in the interval [%d,%d] using [%d] threads%n", lowerBound, upperBound, numThreads);
        System.out.printf("Available processors: %d%n", numProcessors);

        if (numThreads > numProcessors) {
            System.out.println("Warning: numThreads is more than available processors. Performance may be degraded");
        }

        CountPrimesExecutor executor = new CountPrimesExecutor(numThreads, lowerBound, upperBound);
        long startTime = System.currentTimeMillis();
        executor.executeThreads();
        long finishTime = System.currentTimeMillis();
        System.out.printf("Total Primes: %d%n", CountPrimes.getNumPrimes());
        System.out.printf("Elapsed Time: %d ms%n", finishTime - startTime);
    }
}
