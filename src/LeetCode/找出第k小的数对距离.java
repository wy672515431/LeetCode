package LeetCode;

import java.util.Arrays;

public class 找出第k小的数对距离 {
    public int smallestDistancePair(int[] nums, int k) {
        int length = nums.length;
        Arrays.sort(nums);
        int left = 0;
        int right = nums[length - 1] - nums[0];
        //第k小的数对距离在left~right之间
        while (left <= right) {
            int mid = (left + right) / 2;
            //距离小于Mid的元组个数 nums[j] - nums[i] < mid -> nums[i] > nums[j] - mid
            int cnt = 0;
            for (int i = 0, j = 0; j < length; j++) {
                if (nums[j] - nums[i] > mid) {
                    i++;
                }
                cnt += j - i;
            }
            //距离小于mid的元组个数小于k。
            if (cnt < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
