package bytedance.图;

public class Floyd {
    /**
     * 最短路径的子路径仍然是最短路径
     * f[k][i][j]代表经过前k的节点,i到j的最短路。
     * i-k的最短路加上k-j的最短路一定是i-j的最短路
     * f[k][i][j] = min(f[k - 1][i][j], f[k - 1][i][k] + f[k - 1][k][j])
     * 不受前面更新的影响
     * @param matrix 邻接矩阵表示, 方阵
     * @param n 图的顶点
     */
    public void floyd(int[][] matrix, int n) {
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
            dist[i][i] = 0;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("the distance between %1s %2s is %3s%n", i, j, dist[i][j]);
            }
        }
    }
}
