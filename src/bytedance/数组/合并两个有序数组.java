package bytedance.数组;

public class 合并两个有序数组 {
    /**
     * 将 nums2 合并到 nums1中
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, cnt = m + n - 1;
        // 倒序遍历，这样不用用额外空间
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[cnt--] = nums1[i--];
            } else {
                nums1[cnt--] = nums2[j--];
            }
        }
        while (i >= 0) {
            nums1[cnt--] = nums1[i--];
        }
        while (j >= 0) {
            nums1[cnt--] = nums2[j--];
        }
    }
}
