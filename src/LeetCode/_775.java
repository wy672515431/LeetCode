package LeetCode;

public class _775 {
    private int local = 0;
    private int global = 0;
    public static final int INF = 0x3f3f3f3f;

    /**
     * 本质上是求逆序对, 复杂了
     * 一个局部逆序是一个全局逆序，本质上是求是否存在全局逆序
     *
     * @param nums
     * @return
     */
    public boolean isIdealPermutation(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                local++;
            }
        }
        mergesort(nums, 0, nums.length - 1);
        return global == local;
    }


    public boolean isIdealPermutation1(int[] nums) {
        int n = nums.length, minSuff = nums[n - 1];
        for (int i = n - 3; i >= 0; i--) {
            if (nums[i] > minSuff) {
                return false;
            }
            minSuff = Math.min(minSuff, nums[i + 1]);
        }
        return true;
    }

    public void merge(int[] nums, int p, int q, int r) {
        int length1 = q - p + 1;
        int length2 = r - q;
        int[] temp1 = new int[length1 + 1];
        int[] temp2 = new int[length2 + 1];
        temp1[length1] = INF;
        temp2[length2] = INF;
        for (int i = 0; i < length1; i++) {
            temp1[i] = nums[i + p];
        }
        for (int i = 0; i < length2; i++) {
            temp2[i] = nums[i + q + 1];
        }
        int i = 0, j = 0;
        for (int k = p; k <= r; k++) {
            if (temp1[i] <= temp2[j]) {
                nums[k] = temp1[i];
                i++;
            } else {
                nums[k] = temp2[j];
                j++;
                global += length1 - i;
            }
        }
    }

    public void mergesort(int[] nums, int p, int q) {
        if (p < q) {
            int r = (q - p) / 2 + p;
            mergesort(nums, p, r);
            mergesort(nums, r + 1, q);
            merge(nums, p, r, q);
        }
    }
}
