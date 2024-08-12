package bytedance.双指针;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 找出最长等值数组 {
    public int longestEqualSubarray(List<Integer> nums, int k) {
        int ans = 1;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            int x = nums.get(i);
            if (!map.containsKey(x)) {
                List<Integer> indexList = new ArrayList<>();
                map.put(x, indexList);
            }
            map.get(x).add(i);
        }
        for (Map.Entry<Integer, List<Integer>> entry: map.entrySet()) {
            int key = entry.getKey();
            List<Integer> indexList = entry.getValue();
            int size = indexList.size();
            int l = 0, r = -1;
            while (r < size) {
                r++;
                if (r < size) {
                    // index[r] - index[j] + 1
                    // r - j + 1
                    int lenToBeDeleted = indexList.get(r) - indexList.get(l) - (r - l);
                    if (lenToBeDeleted > k) {
                        l++;
                    } else {
                        ans = Math.max(ans, r - l + 1);
                    }
                }
            }
        }
        return ans;
    }
}
