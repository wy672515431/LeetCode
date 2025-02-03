package bytedance.二分;

public class 搜索插入位置 {
    public int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length - 1, mid;
        if (nums[high] < target) {
            return high + 1;
        }
        // 找到第一个大于等于target的数的下标
        while (low < high) {
            mid = (high - low) / 2 + low;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
