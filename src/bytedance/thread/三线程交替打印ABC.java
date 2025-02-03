package bytedance.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class 三线程交替打印ABC {
    // 交替打印主要是要有标志位，确定当前是哪个线程在打印
    public static void main(String[] args) throws InterruptedException {
//        ABCPrinter printer = new ABCPrinter(10);
        ConditionABCPrinter printer = new ConditionABCPrinter(2);
        Thread t1 = new Thread(printer::printA);
        Thread t2 = new Thread(printer::printB);
        Thread t3 = new Thread(printer::printC);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }

    static class ABCPrinter {
        // 打印几轮
        private final int round;
        private static final Semaphore semaphoreA = new Semaphore(1);
        private static final Semaphore semaphoreB = new Semaphore(0);
        private static final Semaphore semaphoreC = new Semaphore(0);

        ABCPrinter(int round) {
            this.round = round;
        }

        public void printA() {
            for (int i = 1; i <= round; i++) {
                try {
                    semaphoreA.acquire();
                    System.out.println("A");
                    semaphoreB.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        public void printB() {
            for (int i = 1; i <= round; i++) {
                try {
                    semaphoreB.acquire();
                    System.out.println("B");
                    semaphoreC.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    ;
                    return;
                }
            }
        }

        public void printC() {
            for (int i = 1; i <= round; i++) {
                try {
                    semaphoreC.acquire();
                    System.out.println("C");
                    semaphoreA.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    ;
                    return;
                }
            }
        }
    }

    static class ConditionABCPrinter {
        private final int round;
        private int turn;
        private static final ReentrantLock lock = new ReentrantLock();
        private static final Condition conditionA = lock.newCondition();
        private static final Condition conditionB = lock.newCondition();
        private static final Condition conditionC = lock.newCondition();

        ConditionABCPrinter(int round) {
            this.round = round;
            this.turn = 0;
        }

        public void printA() {
            for (int i = 1; i <= round; i++) {
                try {
                    lock.lock();
                    while (turn % 3 != 0) {
                        conditionA.await();
                    }
                    System.out.println("A");
                    turn++;
                    conditionB.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                } finally {
                    lock.unlock();
                }
            }
        }

        public void printB() {
            for (int i = 1; i <= round; i++) {
                try {
                    lock.lock();
                    while (turn % 3 != 1) {
                        conditionB.await();
                    }
                    System.out.println("B");
                    turn++;
                    conditionC.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                } finally {
                    lock.unlock();
                }
            }
        }

        public void printC() {
            for (int i = 1; i <= round; i++) {
                try {
                    lock.lock();
                    while (turn % 3 != 2) {
                        conditionC.await();
                    }
                    System.out.println("C");
                    turn++;
                    conditionA.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
