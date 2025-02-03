package bytedance.并查集;

import java.util.Arrays;
import java.util.Comparator;

public class 彼此熟识的最早时间 {
    public int earliestAcq(int[][] logs, int n) {
        Arrays.sort(logs, Comparator.comparingInt(a -> a[0]));
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < logs.length; i++) {
            uf.union(logs[i][1], logs[i][2]);
            if (uf.count == 1) {
                return logs[i][0];
            }
        }
        return -1;
    }

    static class UnionFind {
        int[] parent;
        int[] rank;
        int count;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            Arrays.fill(rank, 1);
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX == parentY) {
                return;
            }
            count--;
            if (rank[x] <= rank[y]) {
                parent[parentX] = parentY;
                if (rank[x] == rank[y]) {
                    rank[y]++;
                }
            } else {
                parent[parentY] = parentX;
            }
        }
    }
}
