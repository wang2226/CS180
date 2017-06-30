/**
 * Created by Bruce on 11/15/16.
 */
public class Driver {
    public static void main(String[] args) {
        Thread t1 = new A();
        t1.start();

        Runnable r = new B();
        Thread t2 = new Thread(r);
        t2.start();
    }
}
