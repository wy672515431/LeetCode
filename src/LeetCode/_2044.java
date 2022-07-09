package LeetCode;

public class _2044 {
    /**
     * 给你一个整数数组 nums ，请你找出 nums 子集 按位或 可能得到的 最大值 ，并返回按位或能得到最大值的 不同非空子集的数目 。
     */
    private int maxBitwiseOr = 0;
    private int ans = 0;
    public int countMaxOrSubsets(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            maxBitwiseOr |=  nums[i];
        }
        getAllSubsets(nums, 0, 0);
        return ans;
    }


    public void getAllSubsets(int[] nums, int bitwiseOrSum, int start) {
        for (int i = start; i < nums.length; i++) {
            bitwiseOrSum |= nums[i];
            if(bitwiseOrSum == maxBitwiseOr) {
                ans++;
            }
            getAllSubsets(nums, bitwiseOrSum, i+1);
        }
    }
}
