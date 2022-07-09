package LeetCode;

/**
 * 给你一个整数数组nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * 如果存在这样的三元组下标 (i, j, k)且满足 i < j < k ，使得nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 * 对于j  nums[j] 大于左边最小元素， nums[j] < 右边最大元素
 */
public class 递增的三元子序列 {
    /**
     * 空间复杂度O(n)
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3)
            return false;
        int[] leftMin = new int[nums.length];
        int[] rightMax = new int[nums.length];
        leftMin[0] = nums[0];
        rightMax[nums.length - 1] = nums[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]);
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > leftMin[i - 1] && nums[i] < rightMax[i + 1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 空间复杂度O(1)
     * 贪心算法
     * 维护两个变量first second
     * 其中初始值first = nums[0], second = +∞
     * 如果nums[i] > second ,存在三元组
     * nums[i] > first second = nums[i]
     * nums[i] < first  first = nums[i]
     * 对于第三种情况，更新完之后虽然first出现在second前面，但是在 second 的前面一定存在一个元素 first’ 小于 second，因此当遇到num>second 时，仍然存在三元组
     * @param nums
     * @return
     */
    public boolean increasingTriplet1(int[] nums) {
        if (nums.length < 3)
            return false;
        int first = nums[0];
        int second = Integer.MAX_VALUE;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > second)
                return true;
            else if(nums[i] > first) {
                second = nums[i];
            }else {
                first = nums[i];
            }
        }
        return false;
    }
}



