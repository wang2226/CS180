import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Bruce on 11/15/16.
 */
public class Counter2 implements Counter {
    private AtomicInteger value = new AtomicInteger(0);

    public void inc() {
        value.incrementAndGet();
    }

    public void dec() {
        value.decrementAndGet();
    }

    public int get() {
        return value.get();
    }
}
