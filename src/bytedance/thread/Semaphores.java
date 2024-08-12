package bytedance.thread;

import java.util.concurrent.Semaphore;

public class Semaphores {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("producer: " + i);
                semaphore.release();
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    semaphore.acquire();
                    System.out.println("consumer: " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
