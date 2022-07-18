package LeetCode_302;

import java.util.Comparator;
import java.util.PriorityQueue;

public class C  {
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            PriorityQueue<Trim> queue = new PriorityQueue<>((o1, o2) -> {
                if (o1.num.compareTo(o2.num) > 0) {
                    return 1;
                } else if (o1.num.compareTo(o2.num) < 0) {
                    return -1;
                } else {
                    return o1.index - o2.index;
                }
            });
            int trim = queries[i][1];
            //找到第k小
            int k = queries[i][0];
            for (int j = 0; j < nums.length; j++) {
                queue.offer(new Trim(nums[j].substring(nums[j].length() - trim), j));
            }
            while (k > 0) {
                ans[i] = queue.poll().index;
                k--;
            }
        }

        return ans;
    }

    class Trim {
        String num;
        int index;
        public Trim(String num, int index) {
            this.num = num;
            this.index = index;
        }
    }
}
