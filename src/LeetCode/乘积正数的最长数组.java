package LeetCode;

/**
 * 给你一个整数数组 nums，请你求出乘积为正数的最长子数组的长度。
 * 一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。
 * 请你返回乘积为正数的最长子数组长度。
 */
public class 乘积正数的最长数组 {
    public int getMaxLen(int[] nums) {
        //前面连续的正数子数组长度.如果前面
        int positiveLen = nums[0] > 0 ? 1 : 0;
        //前面连续的负数子数组长度.如果前面的negative为0，当nums[i] > 0 时，其仍为0。
        int negativeLen = nums[0] < 0 ? 1 : 0;
        int maxLen = positiveLen;
        for (int i = 1 ; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tem = positiveLen;
                positiveLen = negativeLen == 0 ? 0 : negativeLen + 1;
                negativeLen = tem + 1;
            } else if (nums[i] > 0) {
                positiveLen = positiveLen + 1;
                negativeLen = negativeLen == 0 ? 0 : negativeLen + 1;
            } else {
                positiveLen = 0;
                negativeLen = 0;
            }
            maxLen = Math.max(maxLen, positiveLen);
        }
        return maxLen;
    }
    
}
