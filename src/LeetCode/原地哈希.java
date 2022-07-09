package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class 原地哈希 {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            //nums[i] -> 放在下标为nums[i] - 1的地方。
            while (nums[i] != nums[nums[i] - 1]) {
                int temp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[temp - 1] = temp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                ans.add(nums[i]);
            }
        }
        return ans;
    }
}