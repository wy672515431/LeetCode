package LeetCode_136_Double;

import java.util.HashMap;
import java.util.Map;

public class A {
    public int winningPlayerCount(int n, int[][] pick) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int[] p : pick) {
            if (!map.containsKey(p[0])) {
                map.put(p[0], new int[11]);
            }
            map.get(p[0])[p[1]]++;
        }
        int ans = 0;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] cnt = entry.getValue();
            for (int i = 0; i <= 10; i++) {
                if (cnt[i] > entry.getKey()) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }
}
