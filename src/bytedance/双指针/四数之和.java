package bytedance.双指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 四数之和 {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        if (n < 4) {
            return ans;
        }
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            // 去除重复
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int l = j + 1, r = n - 1;
                while (l < r) {
                    long sum = (long)nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum == target) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[l]);
                        temp.add(nums[r]);
                        ans.add(temp);

                        l++;
                        r--;

                        while (l < r && nums[l] == nums[l - 1]) {
                            l++;
                        }

                        while (l < r && nums[r] == nums[r + 1]) {
                            r--;
                        }
                    } else if (sum > target) {
                        r--;
                    } else {
                        l++;
                    }
                }
            }
        }
        return ans;
    }
}
