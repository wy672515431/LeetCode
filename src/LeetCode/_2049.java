package LeetCode;

/**
 * 给你一棵根节点为 0 的二叉树，它总共有 n个节点，节点编号为0到n - 1。同时给你一个下标从0开始的整数数组parents表示这棵树，其中parents[i]是节点 i的父节点。
 * 由于节点 0是根，所以parents[0] == -1。
 *
 * 一个子树的 大小为这个子树内节点的数目。每个节点都有一个与之关联的分数。求出某个节点分数的方法是，
 * 将这个节点和与它相连的边全部 删除，剩余部分是若干个 非空子树，这个节点的 分数为所有这些子树 大小的乘积。
 *
 * 请你返回有 最高得分节点的 数目
 *
 */
public class _2049 {
    /**
     * 删除一个节点的边，其中第一种情况删除的是与父节点的边，此时 score1 = 整颗树的大小 - 以删除节点为根的树的大小。
     * 删除的是节点与其子节点的边 score2 = 左子树的大小,score3 = 右子树的大小
     * @param parents
     * @return
     */

    public int countHighestScoreNodes(int[] parents) {
        Node[] nodes = new Node[parents.length];
        for(int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node();
        }
        for(int i = 0; i < parents.length; i++) {
            if(parents[i] == -1) {
                //do nothing
                continue;
            }else {
                if(nodes[parents[i]].left == null) {
                    nodes[parents[i]].left = nodes[i];
                }else {
                    nodes[parents[i]].right = nodes[i];
                }
            }
        }
        getSumOfTheNode(nodes[0]);


        int ans = 1;
        long max = 0;
        long score;
        for(int i = 0; i < parents.length; i++) {
            //没有与父节点的边
            score = 1;
            if(nodes[i].left != null)
                score *= nodes[i].left.sum;
            if(nodes[i].right != null)
                score *= nodes[i].right.sum;
            if(parents[i] != -1)
                score *= (nodes[0].sum - nodes[i].sum);
            if(score > max) {
                max = score;
                ans = 1;
            }else if(score == max) {
                ans++;
            }
        }
        return ans;
    }

    private void getSumOfTheNode(Node node) {
        if(node == null)
            return;
        if(node.left == null && node.right == null) {
            node.sum = 1;
            return;
        }
        getSumOfTheNode(node.left);
        getSumOfTheNode(node.right);
        if(node.left != null)
            node.sum += node.left.sum;
        if(node.right != null)
            node.sum += node.right.sum;

    }

    class Node{
        Node left;
        Node right;
        int sum;
        public Node() {
            this.sum = 1;
        }
    }
}
