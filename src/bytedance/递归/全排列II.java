package bytedance.递归;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class 全排列II {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> permutation = new ArrayList<>();
    boolean[] isVisited;
    int n;
    public List<List<Integer>> permuteUnique(int[] nums) {
        n = nums.length;
        isVisited = new boolean[n];
        Arrays.sort(nums);
        solve(nums);
        return ans;
    }

    private void solve(int[] nums) {
        if (permutation.size() == n) {
            ans.add(new ArrayList<>(permutation));
            return;
        }
        for (int i = 0; i < n; i++) {
            // nums[i]没访问过，且去重，但是当nums[i - 1]被访问过则不用考虑去重
            if (!isVisited[i] && (i == 0 || (nums[i] != nums[i - 1]) || isVisited[i - 1])) {
                isVisited[i] = true;
                permutation.add(nums[i]);
                solve(nums);
                isVisited[i] = false;
                permutation.removeLast();
            }
        }
    }

    public static void main(String[] args) {

    }
}
