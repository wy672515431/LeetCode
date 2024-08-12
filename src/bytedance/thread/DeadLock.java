package bytedance.thread;

import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
    public static void main(String[] args) throws InterruptedException {
        // 模拟死锁
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            lock1.lock();
            try {
                // 等待一段时间，让t2也能获取到lock2
                Thread.sleep(1000);
                lock2.lock();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock1.unlock();
                lock2.unlock();
            }

        });

        Thread t2 = new Thread(() -> {
            lock2.lock();
            try {
                // 等待一段时间，让t1也能获取到lock1
                Thread.sleep(1000);
                lock1.lock();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
