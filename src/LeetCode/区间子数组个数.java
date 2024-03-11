package LeetCode;

public class 区间子数组个数 {
    public static int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int res = 0, last2 = -1, last1 = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= left && nums[i] <= right) {
                last1 = i;
            } else if (nums[i] > right) {
                last2 = i;
                last1 = -1;
            }
            if (last1 != -1) {
                res += last1 - last2;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {73, 55, 36, 5, 55, 14, 9, 7, 72, 52};
        numSubarrayBoundedMax(nums, 32, 69);
    }
}
