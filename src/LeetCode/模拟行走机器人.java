package LeetCode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class 模拟行走机器人 {
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<Point> set = new HashSet<>();
        for (int[] obstacle: obstacles) {
            set.add(new Point(obstacle[0], obstacle[1]));
        }
        int horizontal = 0, vertical = 1;
        int x = 0, y = 0;
        int ans = 0;
        for (int command : commands) {
            if (command == -2) {
                int temp = horizontal;
                horizontal = -vertical;
                vertical = temp;
            } else if (command == -1) {
                int temp = horizontal;
                horizontal = vertical;
                vertical = -temp;
            } else {
                for (int i = 0; i < command; i++) {
                    if (set.contains(new Point(x + horizontal, y + vertical))) {
                        break;
                    }
                    x += horizontal;
                    y += vertical;
                }
                ans = Math.max(ans, x * x + y * y);
            }
        }
        return ans;
    }

    class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
