package LeetCode_427;

public class A {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                result[i] = nums[(i + nums[i]) % n];
            } else if (nums[i] < 0) {
                int leftShift = Math.abs(nums[i]) % n;
                result[i] = nums[(i - leftShift + n) % n];
            }
        }
        return result;
    }
}
