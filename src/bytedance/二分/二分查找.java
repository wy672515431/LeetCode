package bytedance.二分;

public class 二分查找 {
    public int search(int[] nums, int target) {
        int n = nums.length, low = 0, high = n - 1, mid;
        while (low <= high) {
            mid = (high - low) / 2 + low;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
