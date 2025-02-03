package LeetCode_420;

import java.util.HashMap;
import java.util.Map;

public class C {
    public static void main(String[] args) {
        C c = new C();
        c.minOperations(new int[]{25, 7});
    }

    public int minOperations(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length, ans = 0;
        for (int i = n - 2; i >= 0; i--) {
            // 减小
            while (nums[i] > nums[i + 1]) {
                if (!map.containsKey(nums[i])) {
                    int res = calc(nums[i]);
                    map.put(nums[i], res);
                }
                if (nums[i] == map.get(nums[i])) {
                    return -1;
                }
                nums[i] = map.get(nums[i]);
                ans++;
            }
        }
        return ans;
    }

    private int calc(int num) {
        int res = num;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                res = i;
                break;
            }
        }
        return res;
    }
}
