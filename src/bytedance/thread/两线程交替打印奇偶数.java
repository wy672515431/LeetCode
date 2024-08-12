package bytedance.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class 两线程交替打印奇偶数 {

    public static void main(String[] args) throws InterruptedException {
//        ParityPrinter printer = new ParityPrinter(100);
//        SemaphoreParityPrinter printer = new SemaphoreParityPrinter(100);
        ConditionParityPrinter printer = new ConditionParityPrinter(99);
        Thread t1 = new Thread(printer::printOdd);
        Thread t2 = new Thread(printer::printEven);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    static class ParityPrinter {
        // 打印的最大值
        private final int max;
        // 当前值
        private volatile int count;
        // lock
        private static final Object lock = new Object();

        ParityPrinter(int max) {
            this.max = max;
            this.count = 1;
        }

        public void printOdd() {

            for (int i = 1; i <= (max + 1) / 2; i++) {
                // 加锁
                synchronized (lock) {
                    while (count % 2 == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + count++);
                    // 通知偶数线程
                    lock.notify();
                }
            }
        }

        public void printEven() {
            for (int i = 1; i <= max / 2; i++) {
                // 加锁
                synchronized (lock) {
                    while (count % 2 == 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + count++);
                    lock.notify();
                }
            }
        }
    }

    static class SemaphoreParityPrinter {
        private final int max;
        private int count;
        private static final Semaphore oddSemaphore = new Semaphore(1);
        private static final Semaphore evenSemaphore = new Semaphore(0);

        SemaphoreParityPrinter(int max) {
            this.max = max;
            this.count = 1;
        }

        public void printOdd() {
            for (int i = 1; i <= (max + 1) / 2; i++) {
                try {
                    oddSemaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + ": " + count++);
                    evenSemaphore.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        public void printEven() {
            for (int i = 1; i <= max / 2; i++) {
                try {
                    evenSemaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + ": " + count++);
                    oddSemaphore.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    static class ConditionParityPrinter {
        private final int max;
        private int count;
        private static final ReentrantLock lock = new ReentrantLock();
        private static final Condition condition = lock.newCondition();

        ConditionParityPrinter(int max) {
            this.max = max;
            this.count = 1;
        }

        public void printOdd() {
            for (int i = 1; i <= (max + 1) / 2; i++) {
                try {
                    lock.lock();
                    while (count % 2 == 0) {
                        condition.await();
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + count++);
                    condition.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        }

        public void printEven() {
            for (int i = 1; i <= max / 2; i++) {
                try {
                    lock.lock();
                    while (count % 2 == 1) {
                        condition.await();
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + count++);
                    condition.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
