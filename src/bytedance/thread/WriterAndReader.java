package bytedance.thread;


import java.util.concurrent.Semaphore;

public class WriterAndReader {
    // 读读允许，写写不允许，读写不允许
    // 每次最多一个写者，多个读者
    static Semaphore flag = new Semaphore(1);
    static Semaphore wMutex = new Semaphore(1);
    // 用来更新rCount的锁
    static Semaphore rCountMutex = new Semaphore(1);
    // 统计读者个数
    static int rCount = 0;
    int[] buffer = new int[1];
    public static void main(String[] args){
        Thread writer = new Thread(() -> {
            try {
                while (true) {
                    writerRun();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread writer2 = new Thread(() -> {
            try {
                while (true) {
                    writerRun();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread reader = new Thread(()-> {
            try {
                while (true) {
                    readerRun();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread reader2 = new Thread(() -> {
            try {
                while (true) {
                    readerRun();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        writer.start();
        writer2.start();
        reader.start();
        reader2.start();


    }

    private static void writerRun() throws InterruptedException {
        // 阻止读者无限加入
        flag.acquire();
        wMutex.acquire();

        System.out.println("Writer: " + Thread.currentThread().getName());

        wMutex.release();
        flag.release();
    }

    private static void readerRun() throws InterruptedException {
        flag.acquire();
        rCountMutex.acquire();
        if (rCount == 0) {
            // 阻塞写者
            wMutex.acquire();
        }
        rCount++;
        rCountMutex.release();
        flag.release();

        System.out.println("Reader: " + Thread.currentThread().getName());

        // 读取结束
        rCountMutex.acquire();
        rCount--;
        if (rCount == 0) {
            // 释放写者
            wMutex.release();
        }
        rCountMutex.release();
    }
}
