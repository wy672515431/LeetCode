package bytedance.并查集;

/**
 * 并查集
 * 并查集是一种描述不相交集合的数据结构，同属一个集合内的元素等价，通常选取一个元素作为代表元素，
 * 通过代表元素来判断两个元素是否属于同一个集合。将集合看作是一个树，树的根节点就是代表元素。
 * 1. union, 将两个集合合并起来
 * 2. find, 查找该元素的代表元素, 代表元素 find(x) == x
 */
public class UnionFind {
    private int[] parent;
    private int[] size;
    private int[] rank;
    int unionCount;
    public UnionFind(int[][] isConnected) {
        int n = isConnected.length;
        parent = new int[n];
        size = new int[n];
        rank = new int[n];
        unionCount = 0;
        // init，最开始每个元素都是一个集合
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
            rank[i] = 1;
        }
    }

    /**
     * 将x和y所在的集合合并
     * 这样的合并方法可能会将树退化成链表，影响find的效率
     * 可不可以按照大小合并？但是对于链状树来说，即使较小的节点也可以拥有较高的高度
     * 可不可以按照两棵树的高度来合并？
     * @param x 元素x
     * @param y 元素y
     */
    public void union(int x, int y) {
        if (find(x) == find(y)) {
            return;
        }
        unionCount++;
        if (size[x] <= size[y]) {
            parent[find(x)] = parent[find(y)];
            size[y] += size[x];
        } else {
            parent[find(y)] = parent[find(x)];
            size[x] += size[y];
        }
    }

    public void unionByRank(int x, int y) {
        if (find(x) == find(y)) {
            return;
        }
        unionCount++;
        if (rank[x] <= rank[y]) {
            parent[find(x)] = parent[find(y)];
            if (rank[x] == rank[y]) {
                rank[y]++;
            }
        } else {
            parent[find(y)] = parent[find(x)];
        }
    }

    /**
     * 查找x的代表元素
     * 进行路径压缩，将树的高度压缩
     * @param x 元素
     * @return x对应的代表元素
     */
    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        // 将find(parent[x])的结果赋值给parent[x]
        // 返回parent[x]，这样可以将树的高度压缩
        return parent[x] = find(parent[x]);
    }

}

class Main {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind(isConnected);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    uf.unionByRank(i, j);
                }
            }
        }
        return n - uf.unionCount;
    }
}
