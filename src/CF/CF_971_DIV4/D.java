package CF.CF_971_DIV4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(bufferedReader.readLine());
            Set<Integer> set1 = new HashSet<>();
            Set<Integer> set2 = new HashSet<>();
            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) {
                String[] input = bufferedReader.readLine().split(" ");
                int x = Integer.parseInt(input[0]);
                int y = Integer.parseInt(input[1]);
                points[i] = new Point(x, y);
                if (y == 0) {
                    set1.add(x);
                } else {
                    set2.add(x);
                }
            }
            long ans = 0L;
            for (int i = 0; i < n; i++) {
                int y = points[i].y;
                int x = points[i].x;
                if (y == 1) {
                    if (set1.contains(x)) {
                        ans += set1.size() - 1;
                    }
                    if (set1.contains(x - 1) && set1.contains(x + 1)) {
                        ans++;
                    }
                } else {
                    if (set2.contains(x)) {
                        ans += set2.size() - 1;
                    }
                    if (set2.contains(x - 1) && set2.contains(x + 1)) {
                        ans++;
                    }
                }
            }
            System.out.println(ans);
        }
        bufferedReader.close();
    }

    static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
