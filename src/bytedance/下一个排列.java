package bytedance;

public class 下一个排列 {
    public void nextPermutation(int[] nums) {
        // 下一个排列总会比上一个排列大
        // 1 2 3 4 -> 1 2 4 3 -> 1 3 2 4 -> 1 3 4 2
        // 1.将左边的较小数和右边的较大数替换，为了使替换后的数尽可能的小
        // 我们尽可能满足，左边较小数尽可能靠后，且右边的较大数尽可能小
        // 交换完成后，较大数后面的数需要重新排列大小
        // 1. 寻找较小数
        int n = nums.length;
        int smaller = -1;
        // 获得较小数的位置
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                smaller = i;
                break;
            }
        }

        // 有可能为最后一个排列
        if (smaller >= 0) {
            // 找到一个尽可能小的较大数
            for (int i = n - 1; i > smaller; i--) {
                if (nums[i] > nums[smaller]) {
                    // 交换最小数和最大数
                    swap(nums, smaller, i);
                    break;
                }
            }
        }
        // 翻转后面的数字 [smaller + 1, n - 1]
        reverse(nums, smaller + 1, n - 1);

    }

    private void swap(int[] nums, int i, int j) {
        int tem = nums[i];
        nums[i] = nums[j];
        nums[j] = tem;
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}
