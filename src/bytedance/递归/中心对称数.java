package bytedance.递归;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 中心对称数 {
    List<String> ans = new ArrayList<>();
    char[] str;
    Map<Integer, Integer> map = new HashMap<>(){{
        put(0, 0);
        put(1, 1);
        put(6, 9);
        put(8, 8);
        put(9, 6);
    }};

    public List<String> findStrobogrammatic(int n) {
        str = new char[n];
        // convert str to string
        dfs(0, n - 1);
        return ans;
    }

    private void dfs(int i, int j) {
        if (i > j) {
            ans.add(new String(str));
            return;
        }
        // 只能是0, 1, 8
        if (i == j) {
            for (int key: map.keySet()) {
                if (key == 0 || key == 1 || key == 8) {
                    str[i] = (char) (key + '0');
                    ans.add(new String(str));
                }
            }
            return;
        }
        for (int key: map.keySet()) {
            if (i == 0 && key == 0) {
                continue;
            }
            str[i] = (char) (key + '0');
            str[j] = (char) (map.get(key) + '0');
            dfs(i + 1, j - 1);
        }
    }
}
