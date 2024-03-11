package bytedance.二分;

public class x的平方根 {
    public static int mySqrt(int x) {
        // 1.为了避免乘法溢出，采用除法，同时排除除以0的情况
        if (x == 0) {
            return 0;
        }
        // 二分查找
        int low = 1, high = x, ans = 0;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (x / mid < mid) {
                high = mid - 1;
            } else {
                ans = mid;
                low = mid + 1;
            }
        }
        return ans;
    }
}
