package LeetCode_83_Double;

public class B {
    public long zeroFilledSubarray(int[] nums) {
        long ans = 0;
        long len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                ans += (1 + len) * len / 2;
                len = 0;
            } else {
                len++;
            }
        }
        ans += (1 + len) * len / 2;
        return ans;
    }
}
