public class A extends Thread {
    public void run() {
        while (true) {
            Thread.yield();
            System.out.println("A");
        }
    }
    public static void main(String[] args) {
        Thread t = new A();
        t.start();
    }
}