package bytedance.thread;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class PressureTest {
    private static final int MAX_THREADS = 200;
    private static final int REQUESTS_PER_SECOND = 2000;
    private static final int BATCH = REQUESTS_PER_SECOND / MAX_THREADS;
    private static final int DURATION = 10;
    private static final AtomicInteger counter = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        // 2000 requests per second
        ExecutorService threadPool = Executors.newFixedThreadPool(MAX_THREADS);
        for (int i = 0; i < DURATION; i++) {
            long startTime = System.currentTimeMillis();
            for (int j = 0; j < BATCH; j++) {
                for (int k = 0; k < MAX_THREADS; k++) {
                    threadPool.submit(() -> {
                        counter.incrementAndGet();
                        System.out.println("Request sent: " + counter.get() + " at " + LocalTime.now());
                    });
                }
                Thread.sleep(100);
            }
            long endTime = System.currentTimeMillis();
            long sleepTime = 1000 - (endTime - startTime);
            if (sleepTime > 0) {
                Thread.sleep(sleepTime);
            }
        }
    }
}
