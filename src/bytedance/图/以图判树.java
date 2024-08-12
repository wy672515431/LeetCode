package bytedance.图;

public class 以图判树 {
    // 无向图是树的充要条件是：1. 无环 2. 连通
    public boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            // 存在环
            if (uf.find(u) == uf.find(v)) {
                return false;
            }
            uf.union(u, v);
        }
        return uf.unionCount == n - 1;
    }

    static class UnionFind {
        int[] parent;
        int[] rank;
        int unionCount;

        public UnionFind(int n) {
            unionCount = 0;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            unionCount++;
            if (rank[rootX] <= rank[rootY]) {
                parent[rootX] = rootY;
                if (rank[rootX] == rank[rootY]) {
                    rank[rootY]++;
                }
            } else {
                parent[rootY] = rootX;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    }
}
