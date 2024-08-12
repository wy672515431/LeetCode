package bytedance.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Condition {
    public static void main(String[] args) throws InterruptedException {
        // 边界
        int n = 2;
        // 三个线程，一个打印0，一个打印奇数，一个打印偶数
        // 依次输出 0，1，0，2，...，0，n
        ReentrantLock lock = new ReentrantLock();
        java.util.concurrent.locks.Condition zero = lock.newCondition();
        java.util.concurrent.locks.Condition odd = lock.newCondition();
        java.util.concurrent.locks.Condition even = lock.newCondition();
        AtomicInteger threadId = new AtomicInteger(1);
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < n; i++) {
                    while (threadId.get() != 1) {
                        zero.await();
                    }
                    System.out.println(0);
                    if (i % 2 == 0) {
                        threadId.set(2);
                        odd.signal();
                    } else {
                        threadId.set(3);
                        even.signal();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 1; i <= n; i += 2) {
                    while (threadId.get() != 2) {
                        odd.await();
                    }
                    System.out.println(i);
                    threadId.set(1);
                    zero.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread t3 = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 2; i <= n; i += 2) {
                    while (threadId.get() != 3) {
                        even.await();
                    }
                    System.out.println(i);
                    threadId.set(1);
                    zero.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }
}
