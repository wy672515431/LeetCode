package bytedance.双指针;

public class 移动零 {
    public void moveZeroes(int[] nums) {
        int slow = 0, fast = 0, n = nums.length;
        while (fast < n) {
            if (nums[fast] != 0) {
                nums[slow++] = nums[fast];
            }
            fast++;
        }
        for (int i = slow; i < n; i++) {
            nums[i] = 0;
        }
    }
}
