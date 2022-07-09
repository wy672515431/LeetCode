package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 三数之和 {
    List<List<Integer>> ans = new ArrayList();

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3)
            return ans;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    while (left < right && nums[i] + nums[left] + nums[right] > 0) {
                        right--;
                    }
                    if (left < right && nums[i] + nums[left] + nums[right] == 0) {
                        ArrayList tem = new ArrayList();
                        int tem1 = nums[left];
                        int tem2 = nums[right];
                        tem.add(nums[i]);
                        tem.add(nums[left]);
                        tem.add(nums[right]);
                        ans.add(tem);
                        while (left < right && nums[left] == tem1)
                            left++;
                        while (left < right && nums[right] == tem2)
                            right--;
                    }
                    while (left < right && nums[i] + nums[left] + nums[right] < 0)
                        left++;
                }
            }
        }
        return ans;
    }
}
