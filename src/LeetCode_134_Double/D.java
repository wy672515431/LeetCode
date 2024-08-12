package LeetCode_134_Double;

public class D {
    /**
     * 如何计算子数组的and和
     * 1.从左往右遍历，对于i和j < i， nums[j] & nums[i]
     * @param nums
     * @param k
     * @return
     */
    public long countSubarrays(int[] nums, int k) {
        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // 快速求子数组的按位与
            for (int j = i - 1; j >= 0 && (num & nums[j]) != nums[j]; j--) {
                nums[j] &= num;
            }
            ans += lowerBound(nums, i, k + 1) - lowerBound(nums, i, k);
        }
        return ans;
    }

    private int lowerBound(int [] nums, int right, int target) {
        int left = 0;
        if (nums[right] < target) {
            return right + 1;
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}