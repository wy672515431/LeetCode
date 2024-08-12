package bytedance.math;

import java.util.HashSet;
import java.util.Set;

public class 快乐数 {
    // 本质上可以变为判断列表是否存在循环
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        set.add(n);
        while (true) {
            int sum = 0;
            while (n > 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            if (sum == 1) {
                return true;
            }
            if (set.contains(sum)) {
                return false;
            }
            set.add(sum);
            n = sum;
        }
    }

    private int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }

    public boolean isHappy1(int n) {
        int slow = n;
        int fast = getNext(n);
        while (fast != 1 && getNext(fast) != 1) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
            if (slow == fast) {
                return false;
            }
        }
        return true;
    }
}
