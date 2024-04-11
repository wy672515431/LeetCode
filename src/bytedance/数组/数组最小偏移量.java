package bytedance.数组;

import java.util.Set;
import java.util.TreeSet;

public class 数组最小偏移量 {
    public int minimumDeviation(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        // 奇数只能扩大一次, 将奇数乘以二后，我们不要考虑增大，只用考虑如何减小最大值
        for (int num : nums) {
            set.add(num % 2 == 0 ? num : num * 2);
        }
        int ans = set.last() - set.first();
        while (ans > 0 && set.last() % 2 == 0) {
            int max = set.last();
            set.remove(max);
            set.add(max / 2);
            ans = Math.min(ans, set.last() - set.first());
        }
        return ans;
    }
}
