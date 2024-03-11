package bytedance.random;

import java.util.Random;

public class Rand7ToRand10 {
    public int rand10() {
        // 将(rand7() - 1)看作7进制，则 7 * (rand7() - 1)可以表达[0 ... 42]
        // 7 * (rand7() - 1) + rand7() 可以等概率表示[1, 49]的数，采用拒绝采样
        // 如何求期望？
        // 被拒绝的概率是 9 / 49, 每次要执行两次, E = 2 + 2 * (9 / 49) + ... = (2) / (1 - 9 / 49) = 2.45
        // 减少期望的方式就是减少被拒绝的概率
        int ans = 0;
        do {
            int a = rand7();
            int b = rand7();
            ans = 7 * (a - 1) + b;
        } while (ans > 40);
        // ans [1, 40]
        return ans % 10 + 1;
    }

    public int optimizedRand10() {
        int ans = 0;
        while (true) {
            int a = rand7();
            int b = rand7();
            ans = 7 * (a - 1) + b;
            if (ans <= 40) {
                return ans % 10 + 1;
            }
            // 等概率的[1, 9]
            a = ans - 40;
            b = rand7();
            // [1, 63]
            ans = 7 * (a - 1) + b;
            if (ans <= 60) {
                return ans % 10 + 1;
            }
            // [1, 3]
            a = ans - 60;
            b = rand7();
            // [1, 21]
            ans = 7 * (a - 1) + b;
            if (ans <= 20) {
                return ans % 10 + 1;
            }
        }
    }

    public int rand7() {
        Random random = new Random();
        return random.nextInt(7) + 1;
    }
}
