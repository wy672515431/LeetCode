package bytedance;

public class 分糖果 {
    public int candy(int[] ratings) {
        // 对于位置i，分为左边[0, i - 1]和右边[i + 1, n - 1]
        int n = ratings.length;
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int right = 0, ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                right = right + 1;
            } else {
                right = 1;
            }
            ans += Math.max(left[i], right);
        }
        return ans;
    }
}
