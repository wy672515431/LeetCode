    package bytedance.patterndesign;

/**
 * 饿汉式单例模式
 * 优点：线程安全
 * 缺点：类加载时就初始化，浪费内存, 可以通过反射破坏单例
 */
public class Singleton {
    private static final Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(() -> {
                DCLSingleton instance = DCLSingleton.getInstance();
                System.out.println(instance);
            });
            threads[i] = thread;
        }
        // 启动线程
        for (int i = 0; i < 20; i++) {
            threads[i].start();
        }
    }
}



/**
 * 懒汉式单例模式
 * 优点：延迟加载
 * 缺点：线程不安全
 */
class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

/**
 * 加锁，每一次创建就需要加锁，效率低
 */
class SafeLazySingleton {
    private static SafeLazySingleton instance;

    private SafeLazySingleton() {
    }

    public static synchronized SafeLazySingleton getInstance() {
        if (instance == null) {
            instance = new SafeLazySingleton();
        }
        return instance;
    }
}

/**
 * Double check singleton
 */
class DCLSingleton {
    // volatile保证可见性和禁止指令重排
    // 1. 分配内存 2. 初始化 3. 指向内存
    // 如果不使用volatile，可能会发生指令重排，导致第二步和第三步顺序颠倒
    // 导致其他线程拿到的instance不为null，但是实际上还没有初始化
    private static volatile DCLSingleton instance;

    private DCLSingleton() {}

    public static DCLSingleton getInstance() {
        // 第一次判断
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                // 第二次判断
                // 可能有多个线程同时通过第一次判断，只有一个线程能通过第二次判断
                // 当第一个抢到锁的线程创建完实例后，后面的线程不会再创建实例
                if (instance == null) {
                    // new不是一个原子操作
                    // 1. 分配内存
                    // 2. 内存初始化
                    // 3. 将instance指向分配的内存
                    // 如果不使用volatile，可能会发生指令重排，导致第二步和第三步顺序颠倒
                    // 导致其他线程拿到的instance不为null，但是实际上还没有初始化
                    // 这时其他线程会拿到一个未初始化的实例
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }
}

/**
 * 静态内部类
 * 优点：线程安全，延迟加载
 */

class InnerClassSingleton {
    private InnerClassSingleton() {}

    public static InnerClassSingleton getInstance() {
        return InnerClass.instance;
    }

    // 静态内部类
    private static class InnerClass {
        private static final InnerClassSingleton instance = new InnerClassSingleton();
    }
}

/**
 * 枚举单例
 * 优点：线程安全，防止反射和序列化破坏单例
 */

enum EnumSingleton {
    // static final修饰
    INSTANCE;
    public void doSomething() {
        System.out.println("do something");
    }
}
