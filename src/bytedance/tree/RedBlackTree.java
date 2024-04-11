package bytedance.tree;

public class RedBlackTree {
    // 根节点
    RedBlackTreeNode root;

    public RedBlackTreeNode search(int key) {
        RedBlackTreeNode node = root;
        while (node != null) {
            if (node.key == key) {
                return node;
            } else if (node.key > key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    public void insert(int key) {
        RedBlackTreeNode newNode = new RedBlackTreeNode(key);
        RedBlackTreeNode node = root;
        RedBlackTreeNode parent = null;
        // 找到插入位置
        while (node != null) {
            parent = node;
            if (node.key > key) {
                node = node.left;
            } else if (node.key < key) {
                node = node.right;
            } else {
                throw new RuntimeException("key is already in the tree");
            }
        }
        // 插入，默认是红色
        newNode.red = true;
        if (parent == null) {
            root = newNode;
        } else if (parent.key > key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        newNode.parent = parent;

        // 进行调整
        fixRedBlackPropertiesAfterInsert(newNode);
    }

    public void delete(int key) {
        RedBlackTreeNode nodeToDelete = search(key);
        if (nodeToDelete == null) {
            return;
        }
        RedBlackTreeNode movedUpNode;
        boolean deletedColor;
        if (nodeToDelete.left == null || nodeToDelete.right == null) {
            movedUpNode = deleteNodeWithZeroOrOneChild(nodeToDelete);
            deletedColor = nodeToDelete.red;
        } else {
            // 有两个子节点，找到以nodeToDelete.right为根节点的子树的中序遍历的第一个节点
            RedBlackTreeNode successor = findMinimum(nodeToDelete.right);
            // 将数据复制到nodeToDelete
            // 维持了二叉搜索树的性质
            nodeToDelete.key = successor.key;
            movedUpNode = deleteNodeWithZeroOrOneChild(successor);
            deletedColor = successor.red;
        }

        // 删除的是黑色节点才有影响
        if (!deletedColor) {
            fixRedBlackPropertiesAfterDelete(movedUpNode);
            // 清除Nil节点
            if (movedUpNode.getClass() == NilNode.class) {
                replaceParentsChild(movedUpNode.parent, movedUpNode, null);
            }
        }
    }

    private void fixRedBlackPropertiesAfterDelete(RedBlackTreeNode node) {
        // 1. 删除的节点是根节点
        // 如果根节点没有子节点，则现在的根节点是Nil节点，直接返回
        // 如果根节点有一个子节点，则子节点必为红色，将其染成黑色
        // 如果根节点有两个子节点，则实际删除的是successor节点，无影响

        if (node == root) {
            node.red = false;
            return;
        }

        // 2.删除节点的兄弟节点是红色
        RedBlackTreeNode sibling = getSibling(node);
        if (sibling.red) {
            handleSibling(node, sibling);
            sibling = getSibling(node);
        }

        // 3. 删除节点的兄弟节点是黑色, 且有两个黑色节点，父节点是红色
        // 4. 删除节点的兄弟节点是黑色，且有两个黑色节点，父节点是黑色
        if (isBlack(sibling.left) && isBlack(sibling.right)) {
            sibling.red = true;

            if (node.parent.red) {
                node.parent.red = false;
            } else {
                fixRedBlackPropertiesAfterDelete(node.parent);
            }
        }
        // node = parent.left -> outer nephew = sibling.right
        // node = parent.right -> outer nephew = sibling.left
        // 5. 删除节点的兄弟节点是黑色，且至少有一个红色节点，且其outer nephew节点是黑色
        // 6. 删除节点的兄弟节点是黑色，且至少有一个红色节点，且其outer nephew节点是红色
        else {
            handleBlackSiblingWithAtLeastOneRedChild(node, sibling);
        }
    }

    private RedBlackTreeNode findMinimum(RedBlackTreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private RedBlackTreeNode deleteNodeWithZeroOrOneChild(RedBlackTreeNode node) {
        if (node.left != null) {
            replaceParentsChild(node.parent, node, node.left);
            return node.left;
        }

        if (node.right != null) {
            replaceParentsChild(node.parent, node, node.right);
            return node.right;
        }
        // RedBlackTreeNode has no children -->
        // * node is red --> just remove it
        // * node is black --> replace it by a temporary NIL node (needed to fix the R-B rules)
        RedBlackTreeNode newNode = node.red ? null : new NilNode();
        replaceParentsChild(node.parent, node, newNode);
        return newNode;
    }

    // 因为新插入的节点是红色，则不会违反 从任意节点到其每个叶子(Null)的所有路径都包含相同数量的黑色节点
    // 可能违反红色节点的子节点都是黑色性质
    private void fixRedBlackPropertiesAfterInsert(RedBlackTreeNode node) {
        RedBlackTreeNode parent = node.parent;
        // 1. 如果节点是根节点，则直接将节点变为黑色
        if (parent == null) {
            node.red = false;
            return;
        }

        // parent是黑色，不需要调整
        if (!parent.red) {
            return;
        }

        RedBlackTreeNode grandparent = parent.parent;
        // 2. parent节点是根节点且是红色的
        if (grandparent == null) {
            parent.red = false;
            return;
        }
        // uncle节点代表的是parent的兄弟节点
        // 3. parent和uncle节点都是红色的 将parent和uncle节点变为黑色，grandparent变为红色，然后递归调整grandparent
        RedBlackTreeNode uncle = getUncle(parent);
        if (uncle != null && uncle.red) {
            parent.red = false;
            uncle.red = false;
            grandparent.red = true;
            fixRedBlackPropertiesAfterInsert(grandparent);
        }
        // 4. parent是红色，uncle是黑色，且node是parent的右节点，parent是grandparent的左节点
        // 或者node是parent的左节点，parent是grandparent的右节点
        // 如果node是parent的右节点，先左旋转，反之先右旋转

        // 5. parent是红色，uncle是黑色，且node是parent的左节点，parent是grandparent的左节点
        // 或者node是parent的右节点，parent是grandparent的右节点
        else if (parent == grandparent.left) {
            // case 4a
            if (node == parent.right) {
                rotateLeft(parent);
                // 现在parent已经是node了
                parent = node;
            }
            // 右旋转grandparent
            rotateRight(grandparent);
            grandparent.red = true;
            parent.red = false;
        } else {
            // case 4b
            if (node == parent.left) {
                rotateRight(parent);
                parent = node;
            }
            rotateLeft(grandparent);
            grandparent.red = true;
            parent.red = false;
        }
    }

    private void handleBlackSiblingWithAtLeastOneRedChild(RedBlackTreeNode node, RedBlackTreeNode sibling) {
        boolean nodeIsLeftChild = node == node.parent.left;

        // Case 5: Black sibling with at least one red child + "outer nephew" is black
        // --> Recolor sibling and its child, and rotate around sibling
        if (nodeIsLeftChild && isBlack(sibling.right)) {
            sibling.left.red = false;
            sibling.red = true;
            rotateRight(sibling);
            sibling = node.parent.right;
        } else if (!nodeIsLeftChild && isBlack(sibling.left)) {
            sibling.right.red = false;
            sibling.red = true;
            rotateLeft(sibling);
            sibling = node.parent.left;
        }

        // Fall-through to case 6...

        // Case 6: Black sibling with at least one red child + "outer nephew" is red
        // --> Recolor sibling + parent + sibling's child, and rotate around parent
        sibling.red = node.parent.red;
        node.parent.red = false;
        if (nodeIsLeftChild) {
            sibling.right.red = false;
            rotateLeft(node.parent);
        } else {
            sibling.left.red = false;
            rotateRight(node.parent);
        }
    }

    private boolean isBlack(RedBlackTreeNode node) {
        return node == null || !node.red;
    }

    private void handleSibling(RedBlackTreeNode node, RedBlackTreeNode sibling) {
        sibling.red = false;
        node.parent.red = true;

        // rotate
        if (node == node.parent.left) {
            rotateLeft(node.parent);
        } else {
            rotateRight(node.parent);
        }
    }

    private RedBlackTreeNode getSibling(RedBlackTreeNode node) {
        RedBlackTreeNode parent = node.parent;
        if (parent.left == node) {
            return parent.right;
        } else if (parent.right == node) {
            return parent.left;
        } else {
            throw new RuntimeException("node is not the child of parent");
        }
    }

    private RedBlackTreeNode getUncle(RedBlackTreeNode parent) {
        RedBlackTreeNode grandparent = parent.parent;
        if (grandparent.left == parent) {
            return grandparent.right;
        } else if (grandparent.right == parent) {
            return grandparent.left;
        } else {
            throw new RuntimeException("parent is not the child of grandparent");
        }
    }

    // 右旋转
    private void rotateRight(RedBlackTreeNode node) {
        RedBlackTreeNode parent = node.parent;
        RedBlackTreeNode left = node.left;
        // 左节点的右节点变为node的左节点
        node.left = left.right;
        // 变更左节点的右节点的parent
        if (left.right != null) {
            left.right.parent = node;
        }
        // 左节点的right节点变为node
        left.right = node;
        // 修改node的parent
        node.parent = left;
        // 修改parent
        replaceParentsChild(parent, node, left);
    }

    private void rotateLeft(RedBlackTreeNode node) {
        RedBlackTreeNode parent = node.parent;
        RedBlackTreeNode right = node.right;

        node.right = right.left;
        if (right.left != null) {
            right.left.parent = node;
        }
        right.left = node;
        node.parent = right;
        replaceParentsChild(parent, node, right);
    }

    private void replaceParentsChild(RedBlackTreeNode parent, RedBlackTreeNode oldChild, RedBlackTreeNode newChild) {
        if (parent == null) {
            // 说明oldChild是根节点
            root = newChild;
        } else if (parent.left == oldChild) {
            parent.left = newChild;
        } else if (parent.right == oldChild) {
            parent.right = newChild;
        } else {
            throw new RuntimeException("parent's child is not the oldChild");
        }

        if (newChild != null) {
            newChild.parent = parent;
        }
    }
}

/**
 * 红黑树的节点
 * 性质:
 * 1. 节点是红色或黑色
 * 2. 所有Null节点是黑色
 * 3. 如果一个节点是红色的，则它的两个子节点都是黑色的，且必须是两个Null或者两个有值节点
 * 4. 从任意节点到其每个叶子(Null)的所有路径都包含相同数量的黑色节点
 * 拥有n个内部节点的红黑树的高度至多为2log(n + 1)
 * 证明逆否命题： 如果一个树的高度为h，那么它至少有2^(h / 2) - 1个内部节点
 * 设从根节点到Null节点的路径上的黑色节点数为bh(x), 易得bh(x) >= h / 2
 * 命题转化为证明：如果一个树的高度为h，那么它至少有2^(bh(x)) - 1个内部节点
 * 采用数学归纳法证明
 */
class RedBlackTreeNode {
    // 颜色 true - red, false - black
    boolean red;
    int key;
    RedBlackTreeNode left;
    RedBlackTreeNode right;
    RedBlackTreeNode parent;

    RedBlackTreeNode(int key) {
        this.key = key;
    }
}

class NilNode extends RedBlackTreeNode {
    NilNode() {
        super(0);
        red = false;
    }
}
