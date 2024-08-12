package bytedance.数组;

public class 轮转数组 {
    /**
     * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数
     * 1 2 3 4 5 6 7 -> 5 6 7 1 2 3 4
     * 要将 [1 2 3 4]和 [5 6 7]交换.
     * 可以先反转整个数组 [7 6 5 4 3 2 1] -> [5 6 7][1 2 3 4]
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
