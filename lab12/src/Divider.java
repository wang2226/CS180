/**
 * Created by Bruce on 11/15/16.
 */
public class Divider implements Runnable {
    private static int counter = 0;
    private int start;
    private int end;
    private static Object lock = new Object();

    public Divider(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        for (int i = start; i <= end; i++) {
                if (i % 7 == 0) {
                    synchronized (lock) {
                    counter++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Divider(1, 1000));
        Thread t2 = new Thread(new Divider(1001, 2000));
        Thread t3 = new Thread(new Divider(2001, 3000));
        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println("Big Problem");
        }
        System.out.println(counter);
    }
}
