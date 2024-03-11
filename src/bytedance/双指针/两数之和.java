package bytedance.双指针;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class 两数之和 {
    public int[] twoSum(int[] nums, int target) {
        // 时间复杂度(O(lgn))
        Node[] nodes = new Node[nums.length];
        for (int i = 0; i < nums.length - 1; i++) {
            nodes[i] = new Node(nums[i], i);
        }
        Arrays.sort(nodes, Comparator.comparingInt(o -> o.val));
        int[] ans = new int[2];
        int i = 0, j = nums.length - 1;
        while (i < j) {
            if (nodes[i].val + nodes[j].val == target) {
                ans[0] = nodes[i].pos;
                ans[1] = nodes[j].pos;
                return ans;
            } else if (nodes[i].val + nodes[j].val < target) {
                i++;
            } else {
                j--;
            }
        }
        return ans;
    }

    static class Node {
        int val;
        int pos;

        Node(int val, int pos) {
            this.val = val;
            this.pos = pos;
        }
    }

    public int[] twoSumOptimized(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[2];

        for (int i = 0; i < nums.length; i++) {
            if (map.get(target - nums[i]) != null) {
                ans[0] = i;
                ans[1] = map.get(target - nums[i]);
                return ans;
            }
            map.put(nums[i], i);
        }
        return ans;
    }
}
