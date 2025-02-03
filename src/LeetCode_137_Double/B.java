package LeetCode_137_Double;

import java.util.ArrayList;
import java.util.List;

public class B {
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        for (int i = 0; i < n - k; i++) {
            res[i] = nums[i + k - 1];
        }
        // 如果nums[i + 1] != nums[i] + 1
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] != nums[i] + 1) {
                // 导致 nums[i + 1 - k] ~ nums[i + 1]之间的数无法完成
                int left = Math.max((i - k), 0);
                int right = Math.min(i + 1, n - k);
                List<Integer> temp = new ArrayList<>();
                temp.add(left);
                temp.add(right);
                list.add(temp);
            }
        }
        if (list.isEmpty()) {
            return res;
        }
        // 合并区间
        List<List<Integer>> merge = new ArrayList<>();
        int start = list.get(0).get(0);
        int end = list.get(0).get(1);
        for (int i = 1; i < list.size(); i++) {
            int curStart = list.get(i).get(0);
            int curEnd = list.get(i).get(1);
            if (curStart <= end) {
                end = Math.max(end, curEnd);
            } else {
                List<Integer> temp = new ArrayList<>();
                temp.add(start);
                temp.add(end);
                merge.add(temp);
                start = curStart;
                end = curEnd;
            }
        }
        List<Integer> temp = new ArrayList<>();
        temp.add(start);
        temp.add(end);
        merge.add(temp);
        for (List<Integer> l : merge) {
            int left = l.get(0);
            int right = l.get(1);
            for (int i = left; i <= right; i++) {
                res[i] = -1;
            }
        }
        return res;
    }
}
