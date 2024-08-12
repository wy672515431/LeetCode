package bytedance.二分;

public class H指数 {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int low = 0, high = n;
        int ans = 0;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (check(citations, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(int[] citations, int target) {
        int count = 0;
        for (int citation : citations) {
            if (citation >= target) {
                count++;
            }
        }
        return count >= target;
    }
}
