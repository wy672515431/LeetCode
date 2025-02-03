package bytedance.tree;

public class 建立四叉树 {

    public Node construct(int[][] grid) {
        int n = grid.length;
        return construct(grid, 1, n, 1, n);
    }

    /**
     *
     * @param grid
     * @param iStart 行的起始位置
     * @param iEnd 行的结束位置
     * @param jStart 列的起始位置
     * @param jEnd 列的结束位置
     * @return
     */
    public Node construct(int[][] grid, int iStart, int iEnd, int jStart, int jEnd) {
        if (iStart > iEnd || jStart > jEnd) {
            return null;
        }
        Node root = new Node();
        // 判断叶子节点的标志，当前所有格子的值必须相同
        int val = grid[iStart - 1][jStart - 1];
        boolean isSame = true;
        for (int i = iStart; i <= iEnd; i++) {
            for (int j = jStart; j <= jEnd; j++) {
                if (grid[i - 1][j - 1] != val) {
                    isSame = false;
                    break;
                }
            }
            if (!isSame) {
                break;
            }
        }
        if (isSame) {
            root.isLeaf = true;
            root.val = val != 0;
            return root;
        }
        Node topLeftNode = construct(grid, iStart, iStart + (iEnd - iStart) / 2, jStart, jStart + (jEnd - jStart) / 2);
        Node topRightNode = construct(grid, iStart, iStart + (iEnd - iStart) / 2, jStart + (jEnd - jStart) / 2 + 1, jEnd);
        Node bottomLeftNode = construct(grid, iStart + (iEnd - iStart) / 2 + 1, iEnd, jStart, jStart + (jEnd - jStart) / 2);
        Node bottomRightNode = construct(grid, iStart + (iEnd - iStart) / 2 + 1, iEnd, jStart + (jEnd - jStart) / 2 + 1, jEnd);

        root.topLeft = topLeftNode;
        root.topRight = topRightNode;
        root.bottomLeft = bottomLeftNode;
        root.bottomRight = bottomRightNode;


        return root;
    }

    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}
