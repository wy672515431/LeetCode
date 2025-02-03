package bytedance;

public class 机器人大冒险 {
    public boolean robot(String command, int[][] obstacles, int x, int y) {
        int strideX = 0, strideY = 0;
        int n = command.length();
        // 计算每一步的坐标
        int[][] points = new int[n][2];
        for (int i = 0; i < n; i++) {
            char ch = command.charAt(i);
            if (ch == 'U') {
                strideY++;
            } else {
                strideX++;
            }
            if (i != n - 1) {
                points[i + 1][0] = strideX;
                points[i + 1][1] = strideY;
            }
        }
        for (int[] obstacle : obstacles) {
            int ox = obstacle[0];
            int oy = obstacle[1];
            if (ox > x || oy > y) {
                continue;
            }
            if (strideX == 0) {
                if (ox == 0) {
                    return false;
                } else {
                    continue;
                }
            }
            if (strideY == 0) {
                if (oy == 0) {
                    return false;
                } else {
                    continue;
                }
            }
            for (int[] point : points) {
                int px = point[0];
                int py = point[1];
                // 至少已经完成了timex次循环，最多(timex + 1)次。
                if ((ox - px) % strideX == 0 && (oy - py) % strideY == 0 && (ox - px) / strideX == (oy - py) / strideY) {
                    return false;
                }
            }
        }
        for (int[] point : points) {
            int px = point[0];
            int py = point[1];
            // 至少已经完成了timex次循环，最多(timex + 1)次。
            if ((x - px) % strideX == 0 && (y - py) % strideY == 0 && (x - px) / strideX == (y - py) / strideY) {
                return true;
            }
        }
        return false;
    }
}
