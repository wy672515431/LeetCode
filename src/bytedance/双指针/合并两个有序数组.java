package bytedance.双指针;

public class 合并两个有序数组 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 双指针
        /*
        int[] tem = new int[m];
        System.arraycopy(nums1, 0, tem, 0, m);
        int i = 0, j = 0, cnt = 0;
        while (i < m && j < n) {
            if (tem[i] < nums2[j]) {
                nums1[cnt++] = tem[i];
                i++;
            } else {
                nums1[cnt++] = nums2[j];
                j++;
            }
        }
        while (i < m) {
            nums1[cnt++] = tem[i];
            i++;
        }

        while (j < n) {
            nums1[cnt++] = nums2[j];
            j++;
        }
        */

        // 上述用了O(m + n)的空间复杂度，可以进行优化
        // 反向双指针
        int i = m - 1, j = n - 1, cnt = n + m - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[cnt--] = nums1[i];
                i--;
            } else {
                nums1[cnt--] = nums2[j];
                j--;
            }
        }

        while (i > 0) {
            nums1[cnt--] = nums1[i];
            i--;
        }

        while (j > 0) {
            nums1[cnt--] = nums2[j];
            j--;
        }
    }
}
