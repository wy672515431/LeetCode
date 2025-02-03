package bytedance.递归;

public class 寻找两个正序数组的中位数 {
    // nums1 和 nums2 都是升序排列
    // 对于一个有序数组，如果其长度n + m为奇数，则中位数为 (n + m - 1) / 2
    // 如果是偶数，(n + m - 1) / 2 + (n + m) / 2
    // 注意，对于奇数, (n + m + 1) / 2 == (n + m + 2) / 2
    // 对于偶数，(n + m + 1) / 2 < (n + m + 2) / 2
    // 我们本质上是找第k小的数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        // 比下标大1
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;

        int x = solve(nums1, 0, n - 1, nums2, 0, m - 1, left);
        int y = solve(nums1, 0, n - 1, nums2, 0, m - 1, right);

        return (x + y) / 2.0;
    }

    public int solve(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        // 比较 nums1[start1 + k / 2 - 1]和nums2[start2 + k / 2 - 1]的大小, 第k大肯定不在较小的那个数的左边
        // 当start == end时，第k小数必为 nums2[start2 + k - 1];
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        if (len1 == 0) {
            return nums2[start2 + k - 1];
        }
        if (len2 == 0) {
            return nums1[start1 + k - 1];
        }

        // 当 k = 1时
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        // 这里有坑，注意 k / 2可能超过len1的长度, 可能会导致数组越界
        int newStart1 = start1 + Math.min(len1, k / 2) - 1;
        int newStart2 = start2 + Math.min(len2, k / 2) - 1;
        if (nums1[newStart1] < nums2[newStart2]) {
            // 起始是newStart1 + 1
            return solve(nums1, newStart1 + 1, end1, nums2, start2, end2, k - (newStart1 - start1 + 1));
        } else {
            return solve(nums1, start1, end1, nums2, newStart2 + 1, end2, k - (newStart2 - start2 + 1));
        }
    }
}
