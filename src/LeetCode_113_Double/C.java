package LeetCode_113_Double;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class C {
    public int countPairs(List<List<Integer>> coordinates, int k) {
        long ans = 0;
        Map<Point, Integer> map = new HashMap<>();
        for (List<Integer> coordinate : coordinates) {
            int x = coordinate.get(0);
            int y = coordinate.get(1);
            Point point = new Point(x, y);
            map.put(point, map.getOrDefault(point, 0) + 1);
        }
        for (List<Integer> coordinate : coordinates) {
            int x = coordinate.get(0);
            int y = coordinate.get(1);
            // x ^ x1 = 0 ~ k
            for (int i = 0; i <= k; i++) {
                int newX = i ^ x;
                int newY = (k - i) ^ y;
                Point point = new Point(newX, newY);
                if (map.get(point) != null) {
                    if (point.x == x && point.y == y) {
                        ans += map.get(point) - 1;
                    } else {
                        ans += map.get(point);
                    }
                }
            }
        }
        return (int)(ans / 2);
    }

    static class Point {
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
