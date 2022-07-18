package LeetCode;

public class 四叉树 {
    /**
     * 对两个四叉树进行或操作，只有当quadTree1和quadTree2是叶子节点时，进行或操作
     * @param quadTree1
     * @param quadTree2
     * @return
     */
    public Node intersect(Node quadTree1, Node quadTree2) {
        if (quadTree1.isLeaf && quadTree2.isLeaf) {
            if (quadTree1.val) {
                return quadTree1;
            } else {
                return quadTree2;
            }
        }
        Node ans = new Node();
        ans.topLeft = intersect(quadTree1.isLeaf ? quadTree1 : quadTree1.topLeft,
                quadTree2.isLeaf ? quadTree2 : quadTree2.topLeft);
        ans.topRight = intersect(quadTree1.isLeaf ? quadTree1 : quadTree1.topRight,
                quadTree2.isLeaf ? quadTree2 : quadTree2.topRight);
        ans.bottomLeft = intersect(quadTree1.isLeaf ? quadTree1 : quadTree1.bottomLeft,
                quadTree2.isLeaf ? quadTree2 : quadTree2.bottomLeft);
        ans.bottomRight = intersect(quadTree1.isLeaf ? quadTree1 : quadTree1.bottomRight,
                quadTree2.isLeaf ? quadTree2 : quadTree2.bottomRight);
        //当ans的四个子节点均为叶子节点，且四个子节点值相同
        boolean a = ans.topLeft.isLeaf && ans.topRight.isLeaf
                && ans.bottomLeft.isLeaf && ans.bottomRight.isLeaf;
        boolean b = (ans.topLeft.val == ans.topRight.val) && (ans.topLeft.val == ans.bottomLeft.val)
                && (ans.topLeft.val == ans.bottomRight.val);
        ans.isLeaf = a && b;
        ans.val = ans.topLeft.val;
        if (ans.isLeaf) {
            ans.topLeft = null;
            ans.topRight = null;
            ans.bottomLeft = null;
            ans.bottomRight = null;
        }
        return ans;
    }
}

class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};

