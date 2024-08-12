package bytedance.并查集;

public class 相似字符串数组 {
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        DSU dsu = new DSU(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 如果字符串i和字符串j相似，则合并
                if (isSimilar(strs[i], strs[j])) {
                    dsu.union(i, j);
                }
            }
        }
        return n - dsu.unionCount;
    }

    /**
     * 判断字符串1和字符串2是否相似
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 是否相似
     */
    private boolean isSimilar(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        assert (len1 == len2);
        for (int i = 0; i < len1; i++) {
            char ch1 = str1.charAt(i);
            char ch2 = str2.charAt(i);
            // 存在不同字符
            if (ch1 != ch2) {
                // 找到后面的第一个不同的字符
                int j = i + 1;
                while (j < len1 && str1.charAt(j) == str2.charAt(j)) {
                    j++;
                }
                if (j == len1) {
                    return false;
                }
                if (str1.charAt(j) != ch2 || str2.charAt(j) != ch1) {
                    return false;
                }
                // 后面字符必须相同
                j++;
                while (j < len1) {
                    if (str1.charAt(j) != str2.charAt(j)) {
                        return false;
                    }
                    j++;
                }
                break;
            }
        }
        return true;
    }

    static class DSU {
        private int[] parent;
        private int[] rank;
        int unionCount;

        public DSU(int n) {
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
            if (rootX != rootY) {
                unionCount++;
                if (rank[x] <= rank[y]) {
                    parent[rootX] = rootY;
                    if (rank[x] == rank[y]) {
                        rank[y]++;
                    }
                } else {
                    parent[rootY] = rootX;
                }
            }
        }
    }
}
