package LeetCode_321;

public class D {
    public int countSubarrays(int[] nums, int k) {
        int ans = 0;
        // 确定k的位置
        int pos = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == k) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            return ans;
        }
        // 统计在pos前面比其大和小的数目
        // 统计在pos后面比其大和小的数目

        return ans;
    }
}
