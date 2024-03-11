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
import java.util.Map;


/**
*@description: description
*@author: wang yong
*@date: 2022/11/5
*/
public class test {
    public static void main(String[] args) {
        System.out.println(0.3*0.15*(0.9*0.75*0.10+0.9*0.25*0.15+0.1*0.75*0.65+0.1*0.25*0.80));
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

    Map<Character, List<Integer>> map = new HashMap<>();
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> ans = new ArrayList<>();
        char[] sequence = s.toCharArray();
        int len = sequence.length;
        for (char ch = 'a'; ch <= 'z'; ch++) {
            map.put(ch, new ArrayList<>(){{add(0);}});
        }
        for (int i = 0; i < len; i++) {
            char ch = sequence[i];
            for (Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
                char key = entry.getKey();
                List<Integer> countList = entry.getValue();
                if (key == ch) {
                    int cnt = countList.get(i) + 1;
                    countList.add(cnt);
                } else {
                    int cnt = countList.get(i);
                    countList.add(cnt);
                }
            }
        }
        for (int[] query : queries) {
            int start = query[0];
            int end = query[1];
            int times = query[2];
            int cnt = calc(start, end);
            // if end - start + 1 is even, then the num of every ch must be even
            // end - start + 1 is odd, we should allow one odd character
            if ((end - start + 1) % 2 == 0) {
                if (cnt % 2 != 0) {
                    ans.add(false);
                } else {
                    if (cnt / 2 <= times) {
                        ans.add(true);
                    } else {
                        ans.add(false);
                    }
                }
            } else {
                if (cnt % 2 == 0) {
                    ans.add(false);
                } else {
                    if ((cnt - 1) / 2 <= times) {
                        ans.add(true);
                    } else {
                        ans.add(false);
                    }
                }
            }
        }
        return ans;
    }
    public int calc(int start, int end) {
        int cnt = 0;
        for (List<Integer> countList : map.values()) {
            cnt += countList.get(end + 1) - countList.get(start);
        }
        return cnt;
     }
}



