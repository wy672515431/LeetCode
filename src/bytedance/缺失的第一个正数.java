package bytedance;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 缺失的第一个正数 {
    public int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num > 0) {
                set.add(num);
            }
        }
        int ans = 1;
        while (set.contains(ans)) {
            ans++;
        }
        return ans;
    }

    public static int optimizedFirstMissingPositive(int[] nums) {
        // 我们希望数组的第x - 1个元素为x
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 交换，并确保交换前当前位置不是正确的
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[temp - 1] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public static void main(String[] args) {

    }
}
