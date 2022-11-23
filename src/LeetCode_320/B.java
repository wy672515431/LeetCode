package LeetCode_320;

import LeetCode.TreeNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class B {
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> inorder = new ArrayList<>();
        inorder(root, inorder);
        inorder.sort(Comparator.comparingInt(o -> o));
        for (int num : queries) {
            List<Integer> temp = new ArrayList<>();
            int index = lowerBound(inorder, num);
            if (index == -1) {
                temp.add(-1);
                temp.add(inorder.get(0));
            } else if (index == inorder.size()) {
                temp.add(inorder.get(index - 1));
                temp.add(-1);
            } else {
                if (inorder.get(index) == num) {
                    temp.add(num);
                    temp.add(num);
                } else {
                    temp.add(inorder.get(index - 1));
                    temp.add(inorder.get(index));
                }
            }
            ans.add(temp);
        }
        return ans;
    }

    public void inorder(TreeNode root, List<Integer> inorder) {
        if (root == null) {
            return;
        }
        inorder(root.left, inorder);
        inorder.add(root.val);
        inorder(root.right, inorder);
    }

    /**
     * 找出大于等于target的最小index
     * @param inorder
     * @param target
     * @return
     */
    public int lowerBound(List<Integer> inorder, int target) {
        int low = 0;
        int high = inorder.size() - 1;
        if (target > inorder.get(high)) {
            return high + 1;
        }
        if (target < inorder.get(low)) {
            return -1;
        }
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (inorder.get(mid) >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
