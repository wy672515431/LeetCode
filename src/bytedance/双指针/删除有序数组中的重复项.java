package bytedance.双指针;

public class 删除有序数组中的重复项 {
    // 一般情况，保留重复项k个
    public int removeDuplicates(int[] nums, int k) {
        int n  = nums.length;
        if (n <= k) {
            return n;
        }
        // slow代表处理过的数组长度, fast代表遍历的数组长度
        int slow = k, fast = k;
        while (fast < n) {
            // 如果fast和前面k个相同，则说明超过了k个，反之没超过k个
            if (nums[slow - k] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
