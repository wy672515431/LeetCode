package LeetCode.BinarySearch;

public class 寻找峰值 {
    public int findPeakElement(int[] nums) {
        if(nums.length == 1)
            return 0;
        return solve(nums);
    }

    public int solve(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int mid;
        while(low <= high) {
            mid = low + (high - low) / 2;
            if(mid == nums.length - 1 && nums[mid] > nums[mid - 1])
                return mid;
            if(mid == 0 && nums[mid] > nums[mid + 1])
                return mid;
            if(nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1] && mid != 0 && mid != nums.length - 1)
                return mid;
            if(nums[mid] < nums[mid + 1]){
                low = mid + 1;
            }else if (nums[mid] < nums[mid - 1]){
                high = mid - 1;
            }
        }
        return -1;
    }
}
