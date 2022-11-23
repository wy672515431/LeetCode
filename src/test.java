import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
*@description: description
*@author: wang yong
*@date: 2022/11/5
*/
public class test {
    public static void main(String[] args) {
        // int[][] grid = {
        //         {1,1,1,1,0,0,0,0},
        //         {1,1,1,1,0,0,0,0},
        //         {1,1,1,1,1,1,1,1},
        //         {1,1,1,1,1,1,1,1},
        //         {1,1,1,1,0,0,0,0},
        //         {1,1,1,1,0,0,0,0},
        //         {1,1,1,1,0,0,0,0},
        //         {1,1,1,1,0,0,0,0}};
        // construct(grid, 1, grid.length, 1, grid.length);
        kthGrammar(3, 3);


    }


    /**
     * @param n
     * @param k
     * @return
     */
    public static int kthGrammar(int n, int k) {
        //我们需要忽略第一位，本质上是求一个完全二叉树的节点
        if (n == 1) {
            return 0;
        }
        int bits = 1 << (n - 2);
        int num = getNum(n, k);
        int ans = 0;
        while (bits > 0) {
            if ((bits & num) > 0) {
                ans ^= 1;
            } else {
                //
            }
            bits >>= 1;
        }
        return ans;
    }

    /**
     * @param n
     * @param k
     * @return
     */
    public static int getNum(int n, int k) {
        return (int) Math.pow(2, n - 1) + k - 1;
    }

    /**
     * @param grid
     * @param iStart
     * @param iEnd
     * @param jStart
     * @param jEnd
     * @return
     */
    //我们不妨从A开始
    public static Node construct(int[][] grid, int iStart, int iEnd, int jStart, int jEnd) {
        if (iStart > iEnd || jStart > jEnd) {
            return null;
        }
        Node root = new Node();
        int val = grid[iStart - 1][jStart - 1];
        boolean isSame = true;
        for (int i = iStart; i <= iEnd; i++) {
            for (int j = jStart; j <= jEnd; j++) {
                if (!isSame) {
                    break;
                }
                if (grid[i - 1][j - 1] != val) {
                    isSame = false;
                    break;
                }
            }
        }
        if (isSame) {
            root.isLeaf = true;
            root.val = (val == 0) ? false : true;
            return root;
        }
        Node topLeftNode = construct(grid, iStart, iEnd / 2, jStart, jEnd / 2);
        Node topRightNode = construct(grid, iStart, iEnd / 2, jEnd / 2 + 1, jEnd);
        Node bottomLeftNode = construct(grid, iEnd / 2 + 1, iEnd, jStart, jEnd / 2);
        Node bottomRightNode = construct(grid, iEnd / 2 + 1, iEnd, jEnd / 2 + 1, jEnd);

        root.topLeft = topLeftNode;
        root.topRight = topRightNode;
        root.bottomLeft = bottomLeftNode;
        root.bottomRight = bottomRightNode;


        return root;
    }


}

class Node {
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



