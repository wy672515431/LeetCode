package LeetCode.BinarySearch;

import java.util.Arrays;

public class 最高频元素的频数 {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] preSum = new int[n + 1];
        //从下标1开始 preSum[0] = 0, preSum[1] = nums[0] preSum[2] = nums[1] + nums[0]
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        //进行k次加1操作
        int low = 1;
        int high = n;
        int mid;
        int res = 0;
        while (low <= high) {
            mid = (high - low) / 2 + low;
            if (check(preSum, mid, k)) {
                res = mid;
                low = mid + 1;
            } else {
                high = mid -1;
            }
        }
        return res;
    }

    public boolean check(int[] preSum, int length, int k) {
        //length代表我们截取preSum的长度
        for (int i = length; i < preSum.length; i++) {
            int maxNum = preSum[i] - preSum[i - 1];
            int maxSum = maxNum * length;
            int curSum = preSum[i] - preSum[i - length];
            if (maxSum - curSum <= k) {
                return  true;
            }
        }
        return false;
    }


}
