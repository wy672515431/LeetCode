package LeetCode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class 用最少数量的箭引爆气球 {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int ans = 0;
        int intersectionStart = points[0][0];
        int intersectionEnd = points[0][1];
        for (int i = 1; i < points.length; i++) {
            int xStart = points[i][0];
            int xEnd = points[i][1];
            //说明有交集
            if (xStart >= intersectionStart && xStart <= intersectionEnd) {
                intersectionStart = xStart;
                intersectionEnd = Math.min(intersectionEnd, xEnd);
            } else {
                ans++;
                intersectionStart = xStart;
                intersectionEnd = xEnd;
            }
        }
        ans++;
        return ans;
    }
}
