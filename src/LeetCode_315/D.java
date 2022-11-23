package LeetCode_315;

public class D {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        // dp[i] --- the number of arrays which end is nums[i]
        int minPos = -1;
        int maxPos = -1;
        int barrier = -1;
        long ans = 0L;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == minK) {
                minPos = i;
            }
            if (nums[i] == maxK) {
                maxPos = i;
            }
            if (nums[i] > maxK || nums[i] < minK) {
                barrier = i;
                minPos = -1;
                maxPos = -1;
            }
            if (minPos != -1 && maxPos != -1) {
                ans += Math.min(minPos, maxPos) - barrier;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
