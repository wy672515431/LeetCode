package bytedance.dp;

public class 打家劫舍II {
    public int rob(int[] nums) {
        // 1.偷了第一间房，则不能偷最后一间房
        // 2.偷了最后一间房，则不能偷第一间房
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        // 偷第一间房 ans1代表偷当前房的最大值 ans2代表不偷当前房的最大值
        int ans1 = nums[0], ans2 = 0;
        for (int i = 1; i < n - 1; i++) {
            int temp = ans1;
            ans1 = Math.max(ans1, ans2 + nums[i]);
            ans2 = Math.max(temp, ans2);
        }
        // 偷最后一间房 ans3代表偷当前房的最大值 ans4代表不偷当前房的最大值
        int ans3 = nums[1], ans4 = 0;
        for (int i = 2; i < n; i++) {
            int temp = ans3;
            ans3 = Math.max(ans3, ans4 + nums[i]);
            ans4 = Math.max(temp, ans4);
        }
        return Math.max(Math.max(ans1, ans2), Math.max(ans3, ans4));
    }
}
