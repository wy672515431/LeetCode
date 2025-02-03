package bytedance.二分;

public class 寻找旋转排序数组中的最小值 {
    // 旋转后的数组肯定会分为两段升序的序列
    // 最小值是两端升序序列的断点
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        int mid;
        while (low <= high) {
            mid = (high - low) / 2 + low;
            // low 和 mid 在同一段上，mid和High在不同段上
            if (nums[mid] > nums[low] && nums[mid] > nums[high]) {
                low = mid + 1;
            } else if (nums[mid] > nums[low] && nums[mid] < nums[high]) {
                // low 、mid、high同一段上
                high = mid;
            } else if (nums[mid] < nums[low] && nums[mid] < nums[high]) {
                // mid 和 high 在同一段上
                high = mid;
            } else if (nums[mid] == nums[low] && nums[high] < nums[mid]) {
                low = mid + 1;
            } else {
                return nums[low];
            }
        }
        return -1;
    }

    public int optimizedFindMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int mid;
        while (low < high) {
            mid = low + (high - low) / 2;
            // 最小值在mid的右侧
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }
}
