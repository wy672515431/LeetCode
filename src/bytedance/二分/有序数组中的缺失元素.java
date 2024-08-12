package bytedance.二分;

public class 有序数组中的缺失元素 {
    public int missingElement(int[] nums, int k) {
        int len = nums.length;
        int low = 0;
        int high = len - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int cnt = getCnt(nums, mid, k);
            if (cnt < k) {
                if (mid < len - 1 && nums[mid + 1] - nums[mid] - 1 >= k - cnt) {
                    return nums[mid] + k - cnt;
                }
                low = mid + 1;
            } else if (cnt >= k) {
                // 在前面
                high = mid - 1;
            }
        }
        // 说明缺失的元素在数组最后，减去前面的个数
        return nums[low - 1] + k - getCnt(nums, len - 1, k);
    }

    private int getCnt(int[] nums, int mid, int k) {
        int cnt = 0;
        for (int i = 1; i <= mid; i++) {
            cnt += nums[i] - nums[i - 1] - 1;
        }
        return cnt;
    }
}
