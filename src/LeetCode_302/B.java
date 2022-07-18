package LeetCode_302;

import java.util.*;

public class B {
    public int maximumSum(int[] nums) {
        int ans = -1;
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int sum = calc(nums[i]);
            if (map.get(sum) == null) {
                ArrayList<Integer> list = new ArrayList<>();
                map.put(sum, list);
            }
            map.get(sum).add(nums[i]);
        }
        for (ArrayList<Integer> list : map.values()) {
            if (list.size() < 2) {
                continue;
            } else {
                list.sort((o1, o2) -> o2 - o1);
                ans = Math.max(ans, list.get(0) + list.get(1));
            }
        }
        return ans;
    }

    public int calc(int num) {
        int sum = 0;
        while (num > 0) {
            int tem = num % 10;
            sum += tem;
            num /= 10;
        }
        return sum;
    }
}
