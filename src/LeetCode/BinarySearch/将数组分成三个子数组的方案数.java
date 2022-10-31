package LeetCode.BinarySearch;

public class 将数组分成三个子数组的方案数 {
    static final int mod = (int)(Math.pow(10, 9) + 7);
    public static int waysToSplit(int[] nums) {
        int ans = 0;
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        //双指针
        int firstArrayEnd = 0;
        //第一个数组的边界值
        for (; firstArrayEnd < n - 2; firstArrayEnd++) {
            int firstArraySum = preSum[firstArrayEnd + 1];
            //上界和下界
            int minSecondArrayEnd = firstArrayEnd + 1;
            int maxSecondArrayEnd = n - 2;
            int midSecondArrayEnd;
            int res = 0;
            //求的最大
            while (minSecondArrayEnd <= maxSecondArrayEnd) {
                midSecondArrayEnd = (maxSecondArrayEnd - minSecondArrayEnd) / 2 + minSecondArrayEnd;
                int secondArraySum = preSum[midSecondArrayEnd + 1] - preSum[firstArrayEnd + 1];
                int thirdArraySum = preSum[n] - preSum[midSecondArrayEnd + 1];
                if (firstArraySum <= secondArraySum && secondArraySum <= thirdArraySum) {
                    res = midSecondArrayEnd;
                    minSecondArrayEnd = midSecondArrayEnd + 1;
                } else if (firstArraySum > secondArraySum) {
                    minSecondArrayEnd = midSecondArrayEnd + 1;
                } else if (secondArraySum > thirdArraySum) {
                    maxSecondArrayEnd = midSecondArrayEnd - 1;
                }
            }
            //求最小值
            minSecondArrayEnd = firstArrayEnd + 1;
            maxSecondArrayEnd = n - 2;
            int minRes = 0;
            while (minSecondArrayEnd <= maxSecondArrayEnd) {
                midSecondArrayEnd = (maxSecondArrayEnd - minSecondArrayEnd) / 2 + minSecondArrayEnd;
                int secondArraySum = preSum[midSecondArrayEnd + 1] - preSum[firstArrayEnd + 1];
                int thirdArraySum = preSum[n] - preSum[midSecondArrayEnd + 1];
                if (firstArraySum <= secondArraySum && secondArraySum <= thirdArraySum) {
                    minRes = midSecondArrayEnd;
                    maxSecondArrayEnd = midSecondArrayEnd - 1;
                } else if (firstArraySum > secondArraySum) {
                    minSecondArrayEnd = midSecondArrayEnd + 1;
                } else if (secondArraySum > thirdArraySum) {
                    maxSecondArrayEnd = midSecondArrayEnd - 1;
                }
            }
            if (res != 0) {
                ans = (ans + res - minRes + 1) % mod;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 5, 0};
        waysToSplit(nums);
        System.out.println(waysToSplit(nums));
    }
}
