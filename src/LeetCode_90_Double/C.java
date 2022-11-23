package LeetCode_90_Double;

import java.util.Arrays;
import java.util.HashMap;

public class C {
    /**
     *
     * @param nums
     * @param space
     * @return
     */
    public int destroyTargets(int[] nums, int space) {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> map1 = new HashMap<>();
        for (int num : nums) {
            map.put(num % space, map.getOrDefault(num % space, 0) + 1);
            map1.put(num % space, Math.min(map1.getOrDefault(num % space, Integer.MAX_VALUE), num));
        }
        int key = Integer.MAX_VALUE;
        int max = 0;
        for (int key1 : map.keySet()) {
            if (map.get(key1) > max) {
                max = map.get(key1);
                key = map1.get(key1);
            } else if (map.get(key1) == max) {
                key = Math.min(map1.get(key1), key);
            }
        }
        return key;
    }
}
