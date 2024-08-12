package bytedance.thread;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println(1);
        });

        CompletableFuture<Void> future2 = future1.thenRunAsync(() -> {
            System.out.println(2);
        });

        CompletableFuture<Void> future3 = future2.thenRunAsync(() -> {
            System.out.println(3);
        });

        Thread.sleep(1000);
    }
}
