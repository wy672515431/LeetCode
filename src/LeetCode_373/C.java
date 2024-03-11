package LeetCode_373;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ConcurrentHashMap;

public class C {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Node[] nodes = new Node[n];
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(nums[i], i);
        }
        Arrays.sort(nodes, Comparator.comparingInt(o -> o.val));
        // 按照值排序，找到序列
        for (int i = 0; i < n; i++) {
            int start = i;
            int index = start + 1;
            for (; index < n; index++) {
                if (nodes[index].val - nodes[index - 1].val > limit) {
                    break;
                }
            }
            // 当前区间是[start, index - 1], 在区间进行排序
            Node[] temp = Arrays.copyOfRange(nodes, start, index);
            Arrays.sort(temp, Comparator.comparingInt(o -> o.index));
            for (int j = 0; j < temp.length; j++) {
                res[temp[j].index] = nodes[start + j].val;
            }
            i = index - 1;
        }
        return res;
    }

    class Node {
        int val;
        int index;
        Node(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
}
