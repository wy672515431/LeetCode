package 数据结构学习计划;

public class 找到小镇的法官 {
    /**
     * 小镇法官存在的条件
     * 存在唯一一个出度为0且入度为n-1的节点
     * @param n
     * @param trust
     * @return
     */
    public int findJudge(int n, int[][] trust) {
        int[] in = new int[n];
        int[] out = new int[n];
        for (int i = 0; i < trust.length; i++) {
            out[trust[i][0] - 1]++;
            in[trust[i][1] - 1]++;
        }
        int cnt = 0;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            if (in[i] == n - 1 && out[i] == 0) {
                cnt++;
                ans = i + 1;
            }
        }
        if (cnt == 1) {
            return ans;
        } else {
            return -1;
        }
    }
}
