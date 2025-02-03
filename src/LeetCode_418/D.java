package LeetCode_418;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class D {
    public static void main(String[] args) {
        D d = new D();
        int[] nums = {1, 6, 6};
        long[] queries = {2, 0, 1};
        int[] res = d.gcdValues(nums, queries);
        System.out.println(Arrays.toString(res));
    }
    public int[] gcdValues(int[] nums, long[] queries) {
        int n = nums.length;
        Arrays.sort(nums);
        // 统计每个元素的出现次数
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        int maxNum = nums[n - 1];
        // gcd的范围是[1, maxNum]
        int[] gcdCount = new int[maxNum + 1];
        for (int i = 1; i <= maxNum; i++) {
            // 统计i的倍数的个数
            for (int j = i; j <= maxNum; j += i) {
                gcdCount[i] += countMap.getOrDefault(j, 0);
            }
        }
        List<Node> nodes = new ArrayList<>();
        Map<Integer, Long> gcdMap = new HashMap<>();
        for (int i = maxNum; i >= 1; i--) {
            if (gcdCount[i] < 2) {
                continue;
            }
            // 任选两个数
            long sum = (long) (gcdCount[i] - 1) * gcdCount[i] / 2;
            for (int j = i * 2; j <= maxNum; j += i) {
                sum -= gcdMap.getOrDefault(j, 0L);
            }
            nodes.add(new Node(i, sum));
            gcdMap.put(i, sum);
        }
        nodes.sort(Comparator.comparingInt(a -> a.gcd));
        List<Node> preSum = new ArrayList<>();
        long sum = 0;
        for (Node node : nodes) {
            sum += node.count;
            preSum.add(new Node(node.gcd, sum));
        }
        for (int i = 0; i < preSum.size(); i++) {
            System.out.println(preSum.get(i).gcd + " " + preSum.get(i).count);
        }
        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            long query = queries[i] + 1;
            int low = 0, high = preSum.size() - 1;
            // 找到第一个大于等于query的位置
            int ans = preSum.size();
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (preSum.get(mid).count >= query) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            if (ans == preSum.size()) {
                res[i] = 0;
            } else {
                res[i] = preSum.get(ans).gcd;
            }
        }
        return res;
    }

    static class Node {
        int gcd;
        long count;
        Node(int gcd, long count) {
            this.gcd = gcd;
            this.count = count;
        }
    }
}
