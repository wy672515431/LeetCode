package LeetCode_319;

import LeetCode.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class C {
    private int ans = 0;

    /**
     * 本质上是求使一数组有序的最小交换次数,引入置换环的定义。
     * 给定一个数组, element nums[i] has a directed edge to nums[j] if nums[i] should be at the position
     * of nums[j].需要交换的最小次数是 sum(cycle.size - 1)
     *
     * @param root root of a tree
     * @return the minimum times of swaping
     */
    public int minimumOperations(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            getMinSwaps(list);
        }
        return ans;
    }

    public void getMinSwaps(List<Integer> list) {
        int size = list.size();
        HashMap<Integer, Integer> map = new HashMap<>();
        boolean[] isVisited = new boolean[size];
        for (int i = 0; i < size; i++) {
            map.put(list.get(i), i);
        }
        Collections.sort(list);
        for (int i = 0; i < size; i++) {
            // if ith element was swapped, or it is on the right position
            if (isVisited[i] || map.get(list.get(i)) == i) {
                continue;
            }
            int j = i, cycleSize = 0;
            while (!isVisited[j]) {
                isVisited[j] = true;
                // get the next element --- list.get(j) is the sorted element and map.get(list.get(j))
                // is the previous position
                j = map.get(list.get(j));
                cycleSize++;
            }
            if (cycleSize > 0) {
                ans += (cycleSize - 1);
            }
        }
    }
}
