package bytedance.数组;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 数组的度 {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int degree = 1;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!map.containsKey(num)) {
                List<Integer> stToEnd = new ArrayList<>();
                stToEnd.add(1);
                stToEnd.add(i);
                stToEnd.add(i);
                map.put(num, stToEnd);
            }  else {
                List<Integer> stToEnd = map.get(num);
                stToEnd.set(0, stToEnd.getFirst() + 1);
                stToEnd.set(2, i);
                degree = Math.max(degree, stToEnd.getFirst());
            }
        }
        int ans = Integer.MAX_VALUE;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> val = entry.getValue();
            if (val.getFirst() == degree) {
                ans = Math.min(ans, val.getLast() - val.get(1) + 1);
            }
        }
        return ans;
    }
}
