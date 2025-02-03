package bytedance.二分;

public class 在排序数组中查找元素的第一个和最后一个位置 {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int index1 = lowerBound(nums, target);
        int index2 = upperBound(nums, target);
        if (index1 == nums.length || (index2 == index1)) {
            return new int[]{-1, -1};
        }
        return new int[]{index1, index2 - 1};
    }

    /**
     * 查找第一个大于等于target的元素
     *
     * @param nums
     * @param target
     * @return
     */
    private int lowerBound(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        // 不存在大于等于target的元素
        if (nums[high] < target) {
            return high + 1;
        }
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    /**
     * 查找第一个大于target的元素
     *
     * @param nums
     * @param target
     * @return
     */
    private int upperBound(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        // 不存在大于target的元素
        if (nums[high] <= target) {
            return high + 1;
        }
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
