package LeetCode.BinarySearch;

public class _1870 {
    public int minSpeedOnTime(int[] dist, double hour) {
        //如果hour小于dist.length，那么永远无法到达车站
        if (hour <= dist.length - 1 ) {
            return -1;
        }
        int low = 1;
        int high = Integer.MAX_VALUE;
        int mid;
        while (low < high) {
            mid = (high - low) / 2 + low;
            if (check(dist, mid, hour)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public boolean check(int[] dist, int speed, double hour) {
        double costHours = 0;
        for (int i = 0; i < dist.length; i++) {
            if (i == dist.length - 1) {
                costHours += dist[i] * 1.0 / speed;
            } else {
                costHours += Math.ceil(dist[i] * 1.0 / speed);
            }
        }
        return costHours <= hour;
    }
}
