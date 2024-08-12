import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


/**
 * @description: description
 * @author: wang yong
 * @date: 2022/11/5
 */
public class test {
    private static int ans = 0;
    private static final int MAX = 1 << 20;
    private static int solve(int[] tree, int index) {
        if (index >= tree.length || tree[index] == Integer.MAX_VALUE) {
            return 0;
        }
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int leftVal = solve(tree, left);
        int rightVal = solve(tree, right);
        int left1 = 0;
        int right1 = 0;
        if (left < tree.length && tree[left] != Integer.MAX_VALUE && tree[left] == tree[index]) {
            left1 = leftVal + 1;
        }
        if (right < tree.length && tree[right] != Integer.MAX_VALUE && tree[right] == tree[index]) {
            right1 = rightVal + 1;
        }
        ans = Math.max(ans, left1 + right1);
        return Math.max(left1, right1);
    }
    public static void main(String[] args) {
        System.out.println(fun());
    }

    private static int fun() {
        int a = 1;
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            System.exit(-1);
        } finally {
            // 覆盖了栈中的值
            a = 2;
            return a;
        }
    }

    private static final int MOD = (int)1e9 + 7;
    private static long fastPow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = res * a % MOD;
            }
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
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
            map.put(ch, new ArrayList<>() {{
                add(0);
            }});
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



