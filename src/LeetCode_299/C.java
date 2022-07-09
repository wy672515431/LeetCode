package LeetCode_299;

public class C {
    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        //可以交换两个数组的[left .. right]的部分,然后求max(sum(nums1), sum(nums2))
        //对于nums1. newSum = sum + (nums2[left] - nums1[left]) + .... + (nums2[right] - nums1[right])
        return Math.max(solve(nums1, nums2), solve(nums2, nums1));        
    }

    private int solve(int[] nums1, int[] nums2) {
        int sum = 0, temSum = 0, maxSum = 0;
        for (int i = 0; i < nums1.length; i++) {
            sum += nums1[i];
            temSum = Math.max(temSum + nums2[i] - nums1[i], 0);
            maxSum = Math.max(maxSum, temSum);
        }
        return sum + maxSum;
    }
}
