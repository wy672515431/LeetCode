package LeetCode_135_Double;

public class C {
    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 2, 4, 3};
        int k = 4;
        C c = new C();
        c.minChanges(nums, k);
    }

    // 差分数组长度
    private static final int MAX_LEN = (int) 1e5 + 5;

    public int minChanges(int[] nums, int k) {
        // 差分数组
        long[] diff = new long[MAX_LEN];
        int n = nums.length;
        for (int i = 0; i < n / 2; i++) {
            int min = Math.min(nums[i], nums[n - i - 1]);
            int max = Math.max(nums[i], nums[n - i - 1]);
            int abs = max - min;
            int lowerAbs;
            int upperAbs;
            // 执行一次操作，将其中的一个值变为 0 ~ k 之间
            // 求此时两个数abs之差的范围
            if (k <= min) {
                // 移动最小值
                upperAbs = max;
                // 移动最大值
                lowerAbs = min - k;
            } else if (k >= max) {
                upperAbs = Math.max(max, k - min);
                lowerAbs = 0;
            } else {
                upperAbs = max;
                lowerAbs = 0;
            }
            // 执行两次的话, 插值在[0, k]范围
            // 检测[0, k] 和 [lowerAbs, upperAbs] 是否有交集
            if (lowerAbs >= k) {
                // 将[lowerAbs, upperAbs]区间值加1
                if (abs >= lowerAbs && abs <= upperAbs) {
                    diff[lowerAbs]++;
                    diff[upperAbs + 1]--;
                    diff[abs]--;
                    diff[abs + 1]++;
                } else {
                    diff[lowerAbs]++;
                    diff[upperAbs + 1]--;
                }
            } else {
                diff[0] += 2;
                diff[lowerAbs] -= 2;
                diff[lowerAbs]++;
                diff[upperAbs + 1]--;
                if (k > upperAbs) {
                    diff[upperAbs + 1] += 2;
                    diff[k + 1] -= 2;
                }
                if (abs >= lowerAbs && abs <= upperAbs) {
                    diff[abs]--;
                    diff[abs + 1]++;
                } else if (abs >= 0 && abs <= k) {
                    diff[abs] -= 2;
                    diff[abs + 1] += 2;
                }
            }
            // Math.max(k, upperAbs) + 1 ~ MAX 置为Integer.MAX_VALUE;
            diff[Math.max(k, upperAbs) + 1] += MAX_LEN;
            diff[MAX_LEN - 1] -= MAX_LEN;
        }
        long ans = Integer.MAX_VALUE;
        long val = MAX_LEN;
        for (int i = 0; i < MAX_LEN; i++) {
            if (i == 0) {
                val = diff[i];
            } else {
                val = diff[i] + val;
            }
            ans = Math.min(ans, val);
        }
        return (int) ans;
    }
}
