package bytedance.数组;

import java.util.HashSet;
import java.util.Set;

public class 最长连续序列 {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 1;
        for (int num : nums) {
            if (!set.contains(num)) {
                continue;
            }
            int tem = num;
            int len = 1;
            set.remove(num);
            while (set.contains(tem - 1)) {
                len++;
                set.remove(tem - 1);
                tem = tem - 1;
            }
            while (set.contains(num + 1)) {
                len++;
                set.remove(num + 1);
                num = num + 1;
            }
            ans = Math.max(ans, len);
        }
        return ans;
    }

    public int longestConsecutive1(int[] nums) {
        if (nums.length == 0)
            return 0;
        Set<Integer> set1 = new HashSet();
        for (int i = 0; i < nums.length; i++)
            set1.add(nums[i]);
        int ans = 1;
        for (int i = 0; i < nums.length; i++) {
            // 找到最小的即可
            if (set1.contains(nums[i] - 1))
                continue;
            int start = nums[i];
            while (set1.contains(start + 1))
                start++;
            ans = Math.max(ans, start - nums[i] + 1);
        }
        return ans;
    }
}
