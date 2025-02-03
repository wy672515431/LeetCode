package bytedance.math;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class 直线上最多的点数 {
    public int maxPoints(int[][] points) {
        int n = points.length;
        Map<Line, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];
                if (x1 == x2) {
                    Line line = new Line(Double.MAX_VALUE, x1);
                    Set<Integer> set = map.getOrDefault(line, new HashSet<>());
                    set.add(i);
                    set.add(j);
                    map.put(line, set);
                } else {
                    double k = (double) (y2 - y1) / (x2 - x1);
                    double b = y1 - k * x1;
                    Line line = new Line(k, b);
                    Set<Integer> set = map.getOrDefault(line, new HashSet<>());
                    set.add(i);
                    set.add(j);
                    map.put(line, set);
                }
            }
        }
        int ans = 1;
        for (Set<Integer> set : map.values()) {
            ans = Math.max(ans, set.size());
        }
        return ans;
    }

    static class Line {
        // y = kx + b;
        double k;
        double b;

        public Line(double k, double b) {
            this.k = k;
            this.b = b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(k, b);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Line line = (Line) obj;
            return Double.compare(line.k, k) == 0 && Double.compare(line.b, b) == 0;
        }
    }
}
