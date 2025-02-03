package bytedance.二分;

public class 寻找旋转排序数组中的最小值II {
    /**
     * 存在重复数字
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1, mid;
        while (low < high) {
            mid = (high - low) / 2 + low;
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else if (nums[mid] < nums[high]) {
                high = mid;
            } else {
                // nums[mid] == nums[high]
                // 相等有以下情况
                // 1.三者在同一个区间上 nums[mid] >= nums[low]
                // 2. mid 和 high在同一个区间上，此时nums[mid]不可能大于nums[low] nums[mid] <= nums[low]
                // 3. low 和 mid在一个区间上 nums[mid] >= nums[low]
                if (nums[mid] == nums[low]) {
                    low++;
                } else {
                    high = mid;
                }
            }
        }
        return nums[low];
    }
}
