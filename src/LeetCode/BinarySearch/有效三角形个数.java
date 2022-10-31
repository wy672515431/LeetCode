package LeetCode.BinarySearch;

import java.util.Arrays;

public class 有效三角形个数 {
    public int triangleNumber(int[] nums) {
        //最小的两边大于第二边
        int ans = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            //过滤边长为0的边
            if (nums[i] == 0) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 1; j++) {
                int sum = nums[i] + nums[j];
                int index = lowerBound(nums, sum, j);
                ans += (index - j - 1);
            }
        }
        return ans;
    }

    //lowerBound 找到大或等的第一个值
    public int lowerBound(int[] nums, int target, int bound) {
        int low = bound;
        int high = nums.length - 1;
        int mid;
        //下标不存在
        if (target > nums[high]) {
            return high + 1;
        }
        while (low < high) {
            mid = (high - low) / 2 + low;
            if (nums[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
