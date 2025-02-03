package bytedance.二分;

public class 搜索旋转数组 {
    // 两段升序，两段升序之间会有一个点
    public int search(int[] nums, int target) {
        // 我们这个数组存在一个分界点
        // 先递增然后突然递减一个数最后再递增
        int low = 0;
        int high = nums.length - 1;
        int mid;
        while (low <= high) {
            mid = (high - low) / 2 + low;
            if (nums[mid] == target) {
                return mid;
            }
            // 在同一段升序上
            if (nums[low] < nums[mid]) {
                // 在[LOW, MID]递增区间
                if (nums[low] <= target && nums[mid] > target) {
                    high = mid - 1;
                } else {
                    // 不在
                    low = mid + 1;
                }
            } else if (nums[low] > nums[mid]) {
                // 不再同一个区间里
                // nums[low] > nums[mid]
                // 如果在[mid, high]递增区间
                if (nums[mid] < target && nums[high] >= target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                // nums[low] == nums[mid]
                low++;
            }
        }
        return -1;
    }
}
