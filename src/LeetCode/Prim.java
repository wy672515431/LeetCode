package LeetCode;

import java.util.Arrays;

public class Prim {
    private static final int INF = 0x3f3f3f3f;
    public int minCostConnectPoints(int[][] points) {
        //邻接矩阵
        int len = points.length;
        int[][] matrix = new int[len][len];
        for (int i = 0; i < len; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            for (int j = i + 1; j < len; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];
                int dis = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                matrix[i][j] = dis;
                matrix[j][i] = dis;
            }
        }
        //prim算法
        //将顶点分为两个集合S 和 V - S
        //数组dis[]代表的使V-S集合中顶点到S的最近距离
        boolean[] visit = new boolean[len];
        int[] dis = new int[len];
        int ans = 0;
        Arrays.fill(dis, INF);
        dis[0] = 0;
        for (int i = 0; i < len; i++) {
            int u = -1, min = INF;
            //选取距离最近的点
            for (int j = 0; j < len; j++) {
                if (!visit[j] && dis[j] < min) {
                    u = j;
                    min = dis[j];
                }
            }
            //加入集合
            visit[u] = true;
            ans += dis[u];
            //更新距离
            for (int j = 0; j < len; j++) {
                if (!visit[j] && matrix[u][j] < dis[j]) {
                    dis[j] = matrix[u][j];
                }
            }
        }
        return ans;
    }
}
