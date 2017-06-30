public class B implements Runnable {
    public void run() {
        while (true) {
            Thread.yield();
            System.out.println("B");
        }
    }
    public static void main(String[] args) {
        Runnable r = new B();
        Thread t = new Thread(r);
        t.start();
    }
}