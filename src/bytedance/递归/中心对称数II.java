package bytedance.递归;

import java.util.HashMap;
import java.util.Map;

public class 中心对称数II {
    int ans = 0;
    char[] str;
    Map<Integer, Integer> map = new HashMap<>(){{
        put(0, 0);
        put(1, 1);
        put(6, 9);
        put(8, 8);
        put(9, 6);
    }};

    public int strobogrammaticInRange(String low, String high) {
        for (int i = low.length(); i <= high.length(); i++) {
            str = new char[i];
            dfs(0, i - 1, low, high);
        }
        return ans;
    }

    private void dfs(int i, int j, String low, String high) {
        if (i == j) {
            // 0, 1, 8
            for (int key: map.keySet()) {
                if (key == 0 || key == 1 || key == 8) {
                    str[i] = (char) (key + '0');
                    if (str.length == low.length() && new String(str).compareTo(low) < 0) {
                        continue;
                    }
                    if (str.length == high.length() && new String(str).compareTo(high) > 0) {
                        continue;
                    }
                    ans++;
                }
            }
            return;
        }
        if (i > j) {
            if (str.length == low.length() && new String(str).compareTo(low) < 0) {
                return;
            }
            if (str.length == high.length() && new String(str).compareTo(high) > 0) {
                return;
            }
            ans++;
            return;
        }
        for (int key: map.keySet()) {
            if (i == 0 && key == 0) {
                continue;
            }
            str[i] = (char) (key + '0');
            str[j] = (char) (map.get(key) + '0');
            dfs(i + 1, j - 1, low, high);
        }
    }

}
