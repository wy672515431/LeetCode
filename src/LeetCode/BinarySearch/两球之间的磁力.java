package LeetCode.BinarySearch;

import java.util.Arrays;

public class 两球之间的磁力 {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int n = position.length;
        int low = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            low = Math.min(low, position[i] - position[i - 1]);
        }
        int high = position[n - 1] - position[0];
        int mid;
        int ret = 0;
        while (low  <= high) {
            mid = (high - low) / 2 + low;
            if (check(position, mid, m)) {
                ret = mid;
                //不知道mid + 1是否可行
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ret;
    }

    public boolean check(int[] position, int minDistance, int m) {
        int firstBall = position[0];
        //剩下的球的个数
        m = m -1;
        for (int i = 1; i < position.length; i++) {
            if (position[i] - firstBall >= minDistance) {
                m = m - 1;
                firstBall = position[i];
            }
        }
        return m <= 0;
    }
}
