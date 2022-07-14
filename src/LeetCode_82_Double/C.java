package LeetCode_82_Double;

import java.util.Arrays;

public class C {
    private static final int MAXN = 100005;
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        //统计difference为i的个数,每次取掉最大的difference
        int[] diff = new int[MAXN];
        for (int i = 0; i < nums1.length; i++) {
            diff[Math.abs(nums1[i] - nums2[i])]++;
        }
        int k = k1 + k2;
        for (int i = diff.length - 1; i >= 1; i--) {
            if (diff[i] == 0) {
                continue;
            } else {
                if (k > diff[i]) {
                    diff[i - 1] += diff[i];
                    k -= diff[i];
                    diff[i] = 0;
                } else {
                    diff[i - 1] += k;
                    diff[i] = diff[i] - k;
                    break;
                }
            }
        }
        long ans = 0;
        for (int i = 1; i < diff.length; i++) {
            ans += (long) diff[i] * i * i;
        }
        return  ans;
    }


}
