package bytedance.并查集;

public class 冗余连接 {
    int[] ans = new int[2];
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n + 1);
        for (int[] edge: edges) {
            if (uf.find(edge[0]) == uf.find(edge[1])) {
                ans[0] = edge[0];
                ans[1] = edge[1];
            } else {
                uf.union(edge[0], edge[1]);
            }
        }
        return ans;
    }

    static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            if (rank[rootX] <= rank[rootY]) {
                parent[rootX] = rootY;
                if (rank[rootX] == rank[rootY]) {
                    rank[rootY]++;
                }
            } else {
                parent[rootY] = rootX;
            }
        }
    }
}
