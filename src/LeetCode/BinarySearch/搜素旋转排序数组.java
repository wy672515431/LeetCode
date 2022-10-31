package LeetCode.BinarySearch;

public class 搜素旋转排序数组 {
    public boolean search(int[] nums, int target) {
        //我们这个数组存在一个分界点
        //先递增然后突然递减一个数最后再递增
        int n = nums.length;
        int low = 0;
        int high = nums.length - 1;
        int mid;
        while (low <= high) {
            mid = (high - low) / 2 + low;
            if (nums[mid] == target) {
                return true;
            }
            //左边是有序的
            if (nums[low] < nums[mid]) {
                //此时target一定在Mid左边
                if (nums[low] <= target && nums[mid] > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else if (nums[low] == nums[mid]) {
                low++;
            } else {
                if (nums[mid] < target && nums[high] >= target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return false;
    }


    /**
     * 返回数组的最小元素
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else if (nums[pivot] > nums[high]) {
                low = pivot + 1;
            } else {
                high -= 1;
            }
        }
        return nums[low];
    }
}
