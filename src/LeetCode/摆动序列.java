package LeetCode;

public class 摆动序列 {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 1) {
            return nums.length;
        }
        int ans = 1;
        int preDiff = 0;
        int curDiff;
        for (int i = 1; i < nums.length; i++) {
            curDiff = nums[i] - nums[i - 1];
            if ((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)) {
                ans++;
                preDiff = curDiff;
            }
        }
        return ans;
    }
}
