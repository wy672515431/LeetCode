package bytedance.双指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 三数之和 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // nums[i], 为了避免重复，需要顾虑掉nums[i] == nums[i - 1]
            if (i == 0 || nums[i] != nums[i - 1]) {
                // 固定了nums[i]
                int j = i + 1, k = nums.length - 1;
                while (j < k) {
                    while (j < k && nums[i] + nums[j] + nums[k] < 0) {
                        j++;
                    }
                    while (j < k && nums[i] + nums[j] + nums[k] > 0) {
                        k--;
                    }
                    if (j < k && nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> res = new ArrayList<>();
                        res.add(nums[i]);
                        res.add(nums[j]);
                        res.add(nums[k]);
                        ans.add(res);
                        // 防止重复
                        int curK = nums[k];
                        int curJ = nums[j];
                        while (j < k && nums[k] == curK) {
                            k--;
                        }
                        while (j < k && nums[j] == curJ) {
                            j++;
                        }
                    }
                }
            }
        }
        return ans;
    }
}
