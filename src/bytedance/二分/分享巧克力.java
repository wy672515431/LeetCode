package bytedance.二分;

import java.util.Arrays;

public class 分享巧克力 {
    public int maximizeSweetness(int[] sweetness, int k) {
        int low = Arrays.stream(sweetness).min().orElseThrow();
        int high = Arrays.stream(sweetness).sum();
        int ans = low;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(sweetness, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(int[] sweetness, int k, int min) {
        int count = 0, sum = 0, n = sweetness.length;
        for (int i = 0; i < n; i++) {
            sum += sweetness[i];
            if (sum >= min) {
                count++;
                sum = 0;
            }
        }
        if (count < k + 1) {
            return false;
        }
        return true;
    }
}
