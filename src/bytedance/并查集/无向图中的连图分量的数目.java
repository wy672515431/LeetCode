package bytedance.并查集;

public class 无向图中的连图分量的数目 {
    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        return n - uf.unionCount;
    }


    static class UnionFind {
        int[] parent;
        int[] rank;
        int unionCount;
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            unionCount = 0;
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
    }
}

