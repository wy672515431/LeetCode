package bytedance.数组;

import java.util.Arrays;
import java.util.Comparator;

public class 用最少数量的箭引爆气球 {
    public int findMinArrowShots(int[][] points) {
        int ans = 1, n = points.length;
        Arrays.sort(points, Comparator.comparingInt(o -> o[0]));
        int start = points[0][0];
        int end = points[0][1];
        for (int i = 1; i < n; i++) {
            int nextStart = points[i][0];
            int nextEnd = points[i][1];
            if (nextStart > end) {
                ans++;
                start = nextStart;
                end = nextEnd;
            } else if (nextEnd <= end) {
                start = nextStart;
                end = Math.min(end, nextEnd);
            }
        }
        return ans;
    }
}
