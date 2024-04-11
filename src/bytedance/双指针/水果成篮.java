package bytedance.双指针;

import java.util.HashSet;
import java.util.Set;

public class 水果成篮 {
    /**
     * 我们要求一个最长的区间，里面只包含两类数字
     *
     * @param fruits
     * @return
     */
    public int totalFruit(int[] fruits) {
        int l = 0, r = -1, fruit1 = -1, fruit2 = -1, ans = 0;
        int n = fruits.length;
        Set<Integer> set = new HashSet<>();
        // 滑动窗口，维持里面只有两类水果
        while (r < n) {
            r++;
            if (r < n) {
                int cur = fruits[r];
                // 检查目前set的大小
                int size = set.size();
                if (set.contains(cur)) {
                    if (cur == fruits[fruit1]) {
                        fruit1 = r;
                    } else if (fruit2 != -1) {
                        fruit2 = r;
                    }
                } else if (size == 0) {
                    set.add(cur);
                    fruit1 = r;
                } else if (size == 1) {
                    set.add(cur);
                    fruit2 = r;
                } else {
                    set.add(cur);
                    // 找到位于最后一次出西安位于后面的水果
                    if (fruit1 < fruit2) {
                        set.remove(fruits[fruit1]);
                    } else {
                        set.remove(fruits[fruit2]);
                    }
                    // 更新
                    l = Math.min(fruit1, fruit2) + 1;
                    fruit1 = Math.max(fruit1, fruit2);
                    fruit2 = r;
                }
                ans = Math.max(ans, r - l + 1);
            }
        }
        return ans;
    }
}
